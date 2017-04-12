package org.knowm.xchange.exmo.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.exmo.ExmoAdapters;
import org.knowm.xchange.exmo.dto.ExmoResult;
import org.knowm.xchange.exmo.dto.trade.ExmoOrder;
import org.knowm.xchange.exmo.dto.trade.ExmoUserTrade;
import org.knowm.xchange.exmo.dto.trade.OrderResult;
import org.knowm.xchange.service.polling.trade.PollingTradeService;
import org.knowm.xchange.service.polling.trade.params.DefaultTradeHistoryParamPaging;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParams;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ExmoTradeService extends ExmoTradeServiceRaw implements PollingTradeService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public ExmoTradeService(Exchange exchange) {

    super(exchange);
  }

  @Override
  public OpenOrders getOpenOrders() throws IOException {

    Map<String, List<ExmoOrder>> exmoOpenOrders = getExmoOpenOrders();
    return ExmoAdapters.adaptOpenOrders(exmoOpenOrders);
  }

  @Override
  public String placeMarketOrder(MarketOrder marketOrder) throws IOException {

    String symbol = ExmoAdapters.toExmoSymbol(marketOrder.getCurrencyPair());
    ExmoOrderType exmoType = marketOrder.getType() == Order.OrderType.ASK ? ExmoOrderType.market_sell : ExmoOrderType.market_buy;

    OrderResult orderResult = placeExmoOrder(symbol, marketOrder.getTradableAmount(), null, exmoType);
    return String.valueOf(orderResult.getOrderId());
  }

  @Override
  public String placeLimitOrder(LimitOrder limitOrder) throws IOException {

    String symbol = ExmoAdapters.toExmoSymbol(limitOrder.getCurrencyPair());
    ExmoOrderType exmoType = limitOrder.getType() == Order.OrderType.ASK ? ExmoOrderType.sell : ExmoOrderType.buy;

    OrderResult orderResult = placeExmoOrder(symbol, limitOrder.getTradableAmount(), limitOrder.getLimitPrice(), exmoType);
    if (!orderResult.isResult()) {
      throw new ExchangeException(orderResult.getError());
    }
    return String.valueOf(orderResult.getOrderId());
  }

  @Override
  public boolean cancelOrder(String orderId) throws IOException {
    ExmoResult exmoResult = cancelExmoOrder(orderId);
    return exmoResult.isResult();
  }

  @Override
  public UserTrades getTradeHistory(TradeHistoryParams params) throws IOException {

    String symbol = null;
    Integer limit = null;
    Integer offset = null;

    if (params instanceof ExmoTradeHistoryParams) {
      ExmoTradeHistoryParams exmoParams = (ExmoTradeHistoryParams) params;

      CurrencyPair[] pairs = exmoParams.getPairs();
      if (pairs != null && pairs.length > 0) {
        StringBuilder symbols = new StringBuilder();
        for (CurrencyPair pair : pairs) {
          symbols.append(ExmoAdapters.toExmoSymbol(pair)).append(",");
        }
        symbol = symbols.toString();
      }

      limit = exmoParams.getLimit();
      offset = exmoParams.getOffset();
    }

    Map<String, List<ExmoUserTrade>> exmoUserTrades = getExmoUserTrades(symbol, limit, offset);
    return ExmoAdapters.adaptTradeHistory(exmoUserTrades);
  }

  @Override
  public ExmoTradeHistoryParams createTradeHistoryParams() {

    return new ExmoTradeHistoryParams();
  }

  @Override
  public Collection<Order> getOrder(String... orderIds) throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {

    return null;
  }

}
