package org.knowm.xchange.gemini.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.gemini.GeminiAdapters;
import org.knowm.xchange.gemini.dto.trade.GeminiOrder;
import org.knowm.xchange.gemini.dto.trade.GeminiUserTrade;
import org.knowm.xchange.service.polling.trade.PollingTradeService;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParamCurrencyPair;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by tabtrader on 05/10/2016.
 */
public class GeminiTradeService extends GeminiTradeServiceRaw implements PollingTradeService {
  /**
   * Constructor
   *
   * @param exchange
   */
  public GeminiTradeService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public OpenOrders getOpenOrders() throws  IOException {

    List<GeminiOrder> geminiOrders = getGeminiOrders();
    return GeminiAdapters.adaptOrders(geminiOrders);
  }

  @Override
  public String placeMarketOrder(MarketOrder marketOrder) {

    throw new NotYetImplementedForExchangeException();
  }

  @Override
  public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
    GeminiOrder geminiOrder = placeNewLimitOrder(limitOrder);
    return geminiOrder.getOrderId();
  }

  @Override
  public boolean cancelOrder(String orderId) throws IOException {

    return cancelOrder(Integer.valueOf(orderId)) != null;
  }

  @Override
  public UserTrades getTradeHistory(TradeHistoryParams params) throws IOException {

    CurrencyPair pair = null;
    Integer limit  = null;
    Long since  = null;

    if (params instanceof GeminiTradeHistoryParams) {
      GeminiTradeHistoryParams geminiParams = (GeminiTradeHistoryParams) params;
      pair = geminiParams.getCurrencyPair();
      limit = geminiParams.getLimit();
      since = geminiParams.getSince();
    } else if (params instanceof TradeHistoryParamCurrencyPair) {
      pair = ((TradeHistoryParamCurrencyPair) params).getCurrencyPair();
    }

    List<GeminiUserTrade> geminiTrades = getGeminiTrades(pair, limit, since);
    return GeminiAdapters.adaptUserTrades(geminiTrades, pair);
  }

  @Override
  public GeminiTradeHistoryParams createTradeHistoryParams() {

    return new GeminiTradeHistoryParams();
  }

  @Override
  public Collection<Order> getOrder(String... orderIds) throws IOException {

    List<Order> orders = new ArrayList<>();
    for (String orderId : orderIds) {
      orders.add(GeminiAdapters.adaprtOrder(getGeminiOrder(Integer.valueOf(orderId))));
    }
    return orders;
  }
}
