package com.xeiam.xchange.exmo.service.polling;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.dto.Order;
import com.xeiam.xchange.dto.trade.LimitOrder;
import com.xeiam.xchange.dto.trade.MarketOrder;
import com.xeiam.xchange.dto.trade.OpenOrders;
import com.xeiam.xchange.dto.trade.UserTrades;
import com.xeiam.xchange.exceptions.ExchangeException;
import com.xeiam.xchange.exceptions.NotAvailableFromExchangeException;
import com.xeiam.xchange.exceptions.NotYetImplementedForExchangeException;
import com.xeiam.xchange.exmo.ExmoAdapters;
import com.xeiam.xchange.exmo.dto.ExmoResult;
import com.xeiam.xchange.exmo.dto.trade.ExmoOrder;
import com.xeiam.xchange.exmo.dto.trade.ExmoUserTrade;
import com.xeiam.xchange.exmo.dto.trade.OrderResult;
import com.xeiam.xchange.service.polling.trade.PollingTradeService;
import com.xeiam.xchange.service.polling.trade.params.DefaultTradeHistoryParamPaging;
import com.xeiam.xchange.service.polling.trade.params.TradeHistoryParams;

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
