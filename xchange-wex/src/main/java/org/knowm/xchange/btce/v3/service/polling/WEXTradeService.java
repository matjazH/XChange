package org.knowm.xchange.btce.v3.service.polling;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.btce.v3.WEXAdapters;
import org.knowm.xchange.btce.v3.WEXAuthenticated;
import org.knowm.xchange.btce.v3.WEXExchange;
import org.knowm.xchange.btce.v3.dto.marketdata.WEXExchangeInfo;
import org.knowm.xchange.btce.v3.dto.trade.*;
import org.knowm.xchange.btce.v3.dto.trade.WEXCancelOrderResult;
import org.knowm.xchange.btce.v3.service.polling.trade.params.WEXTradeHistoryParams;
import org.knowm.xchange.btce.v3.service.polling.trade.params.WEXTransHistoryParams;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.polling.trade.PollingTradeService;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParamCurrencyPair;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParamPaging;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParamsIdSpan;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParamsTimeSpan;
import org.knowm.xchange.utils.DateUtils;

/**
 * @author Matija Mazi
 */
public class WEXTradeService extends WEXTradeServiceRaw implements PollingTradeService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public WEXTradeService(Exchange exchange) {

    super(exchange);
  }

  @Override
  public OpenOrders getOpenOrders() throws IOException {

    Map<Long, WEXOrder> orders = getBTCEActiveOrders(null);
    return WEXAdapters.adaptOrders(orders);
  }

  /**
   * Implementation note: this method calls placeLimitOrder with LimitOrder created from passed MarketOrder and either max price in case of BID or min
   * proce in case of ASK, taken from the remote metadata cached in WEXExchange
   */
  @Override
  public String placeMarketOrder(MarketOrder marketOrder) throws IOException {
    WEXExchangeInfo WEXExchangeInfo = ((WEXExchange) exchange).getWEXExchangeInfo();
    LimitOrder order = WEXAdapters.createLimitOrder(marketOrder, WEXExchangeInfo);
    return placeLimitOrder(order);
  }

  @Override
  public String placeLimitOrder(LimitOrder limitOrder) throws IOException {

    WEXOrder.Type type = limitOrder.getType() == Order.OrderType.BID ? WEXOrder.Type.buy : WEXOrder.Type.sell;

    String pair = WEXAdapters.getPair(limitOrder.getCurrencyPair());

    WEXOrder WEXOrder = new WEXOrder(0, null, limitOrder.getLimitPrice(), limitOrder.getTradableAmount(), type, pair);

    WEXPlaceOrderResult result = placeBTCEOrder(WEXOrder);
    return Long.toString(result.getOrderId());
  }

  @Override
  public boolean cancelOrder(String orderId) throws IOException {

    WEXCancelOrderResult ret = cancelBTCEOrder(Long.parseLong(orderId));
    return (ret != null);
  }

  /**
   * Supported parameters: {@link TradeHistoryParamPaging} {@link TradeHistoryParamsIdSpan} {@link TradeHistoryParamsTimeSpan}
   * {@link TradeHistoryParamCurrencyPair} You can also override sorting order (default is descending) by using {@link WEXTradeHistoryParams}
   */
  @Override
  public UserTrades getTradeHistory(TradeHistoryParams params) throws ExchangeException, IOException {

    Long offset = null;
    Long count = null;
    Long startId = null;
    Long endId = null;
    WEXAuthenticated.SortOrder sort = WEXAuthenticated.SortOrder.DESC;
    Long startTime = null;
    Long endTime = null;
    String btcrPair = null;

    if (params instanceof TradeHistoryParamPaging) {
      TradeHistoryParamPaging pagingParams = (TradeHistoryParamPaging) params;
      Integer pageLength = pagingParams.getPageLength();
      Integer pageNumber = pagingParams.getPageNumber();
      if (pageNumber == null) {
        pageNumber = 0;
      }

      if (pageLength != null) {
        count = pageLength.longValue();
        offset = (long) (pageLength * pageNumber);
      } else {
        offset = pageNumber.longValue();
      }
    }

    if (params instanceof TradeHistoryParamsIdSpan) {
      TradeHistoryParamsIdSpan idParams = (TradeHistoryParamsIdSpan) params;
      startId = nullSafeToLong(idParams.getStartId());
      endId = nullSafeToLong(idParams.getEndId());
    }

    if (params instanceof TradeHistoryParamsTimeSpan) {
      TradeHistoryParamsTimeSpan timeParams = (TradeHistoryParamsTimeSpan) params;
      startTime = nullSafeUnixTime(timeParams.getStartTime());
      endTime = nullSafeUnixTime(timeParams.getEndTime());
    }

    if (params instanceof TradeHistoryParamCurrencyPair) {
      CurrencyPair pair = ((TradeHistoryParamCurrencyPair) params).getCurrencyPair();
      if (pair != null) {
        btcrPair = WEXAdapters.getPair(pair);
      }
    }

    if (params instanceof WEXTransHistoryParams) {
      sort = ((WEXTransHistoryParams) params).getSortOrder();
    }

    Map<Long, WEXTradeHistoryResult> resultMap = getBTCETradeHistory(offset, count, startId, endId, sort, startTime, endTime, btcrPair);
    return WEXAdapters.adaptTradeHistory(resultMap);
  }

  private static Long nullSafeToLong(String str) {

    try {
      return (str == null || str.isEmpty()) ? null : Long.valueOf(str);
    } catch (NumberFormatException e) {
      return null;
    }
  }

  private static Long nullSafeUnixTime(Date time) {
    return time != null ? DateUtils.toUnixTime(time) : null;
  }

  @Override
  public org.knowm.xchange.service.polling.trade.params.TradeHistoryParams createTradeHistoryParams() {
    return new WEXTradeHistoryParams();
  }

  @Override
  public Collection<Order> getOrder(String... orderIds)
      throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    throw new NotYetImplementedForExchangeException();
  }

  /**
   * Retrieve TransHistory. :TODO: Return could be abstracted in a fashion similar to UserTrades and also used in additional service for BitStamp
   * exchange.
   *
   * @param params
   * @return Map of transaction id to WEXTransHistoryResult
   */
  public Map<Long, WEXTransHistoryResult> getTransHistory(WEXTransHistoryParams params) throws ExchangeException, IOException {

    Long offset = null;
    Long count = null;
    Long startId = null;
    Long endId = null;
    WEXAuthenticated.SortOrder sort = WEXAuthenticated.SortOrder.DESC;
    Long startTime = null;
    Long endTime = null;

    if (params instanceof TradeHistoryParamPaging) {
      TradeHistoryParamPaging pagingParams = params;
      Integer pageLength = pagingParams.getPageLength();
      Integer pageNumber = pagingParams.getPageNumber();
      if (pageNumber == null) {
        pageNumber = 0;
      }

      if (pageLength != null) {
        count = pageLength.longValue();
        offset = (long) (pageLength * pageNumber);
      } else {
        offset = pageNumber.longValue();
      }
    }

    if (params instanceof TradeHistoryParamsIdSpan) {
      TradeHistoryParamsIdSpan idParams = params;
      startId = nullSafeToLong(idParams.getStartId());
      endId = nullSafeToLong(idParams.getEndId());
    }

    if (params instanceof TradeHistoryParamsTimeSpan) {
      TradeHistoryParamsTimeSpan timeParams = params;
      startTime = nullSafeUnixTime(timeParams.getStartTime());
      endTime = nullSafeUnixTime(timeParams.getEndTime());
    }

    if (params instanceof WEXTransHistoryParams) {
      sort = params.getSortOrder();
    }

    Map<Long, WEXTransHistoryResult> resultMap = getBTCETransHistory(offset, count, startId, endId, sort, startTime, endTime);

    return resultMap;
  }
}
