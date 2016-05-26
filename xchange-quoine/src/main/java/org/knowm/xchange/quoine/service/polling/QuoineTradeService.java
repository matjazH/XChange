package org.knowm.xchange.quoine.service.polling;

import java.io.IOException;
import java.util.Collection;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.quoine.QuoineAdapters;
import org.knowm.xchange.quoine.dto.marketdata.QuoineTradesList;
import org.knowm.xchange.quoine.dto.trade.QuoineOrderResponse;
import org.knowm.xchange.quoine.dto.trade.QuoineOrderType;
import org.knowm.xchange.quoine.dto.trade.QuoineOrdersList;
import org.knowm.xchange.service.polling.trade.PollingTradeService;
import org.knowm.xchange.service.polling.trade.params.DefaultTradeHistoryParamCurrencyPair;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParams;

/**
 * @author Matija Mazi
 */
public class QuoineTradeService extends QuoineTradeServiceRaw implements PollingTradeService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public QuoineTradeService(Exchange exchange, boolean useMargin) {
    super(exchange, useMargin);
  }

  @Override
  public OpenOrders getOpenOrders() throws IOException {

    QuoineOrdersList quoineOrdersList = listQuoineOrders(null);
    return QuoineAdapters.adapteOpenOrders(quoineOrdersList);

  }

  @Override
  public String placeMarketOrder(MarketOrder marketOrder) throws IOException {

    QuoineOrderResponse quoinePlaceOrderResponse = placeMarketOrder(marketOrder.getCurrencyPair(), marketOrder.getType() == OrderType.ASK ? "sell"
        : "buy", marketOrder.getTradableAmount());
    return quoinePlaceOrderResponse.getId();
  }

  @Override
  public String placeLimitOrder(LimitOrder limitOrder) throws IOException {

    QuoineOrderResponse quoinePlaceOrderResponse = placeLimitOrder(limitOrder.getCurrencyPair(), limitOrder.getType() == OrderType.ASK ? "sell"
        : "buy", limitOrder.getTradableAmount(), limitOrder.getLimitPrice());
    return quoinePlaceOrderResponse.getId();
  }

  public String placeQuoineOrder(LimitOrder limitOrder, QuoineOrderType quoineOrderType, Integer leverageLevel) throws IOException {
    String type = limitOrder.getType() == OrderType.ASK ? "sell" : "buy";
    QuoineOrderResponse quoinePlaceOrderResponse = placeQuoineOrder(quoineOrderType, limitOrder.getCurrencyPair(), type, limitOrder.getTradableAmount(), limitOrder.getLimitPrice(), leverageLevel);
    return quoinePlaceOrderResponse.getId();
  }

  @Override
  public boolean cancelOrder(String orderId) throws IOException {

    QuoineOrderResponse quoineOrderResponse = cancelQuoineOrder(orderId);

    return quoineOrderResponse.getId() != null && (quoineOrderResponse.getId().equals(orderId));
  }

  @Override
  public UserTrades getTradeHistory(TradeHistoryParams params) throws IOException {

    CurrencyPair pair = null;

    if (params instanceof DefaultTradeHistoryParamCurrencyPair) {
      DefaultTradeHistoryParamCurrencyPair paramCurrencyPair = (DefaultTradeHistoryParamCurrencyPair)params;
      pair = paramCurrencyPair.getCurrencyPair();
    }

    if (pair != null) {
      QuoineTradesList quoineTradesList = listQuoineExecutions(pair.base.getCurrencyCode() + pair.counter.getCurrencyCode());
      return QuoineAdapters.adaptUserTrades(quoineTradesList, pair);
    }
    return null;
  }

  @Override
  public DefaultTradeHistoryParamCurrencyPair createTradeHistoryParams() {

    return new DefaultTradeHistoryParamCurrencyPair();
  }

  @Override
  public Collection<Order> getOrder(String... orderIds) throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    return null;
  }

}
