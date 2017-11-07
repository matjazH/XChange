package org.knowm.xchange.gemini.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.gemini.GeminiAdapters;
import org.knowm.xchange.gemini.dto.GeminiBaseRequest;
import org.knowm.xchange.gemini.dto.trade.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by tabtrader on 05/10/2016.
 */
public class GeminiTradeServiceRaw extends GeminiBasePollingService {
  /**
   * Constructor
   *
   * @param exchange
   */
  protected GeminiTradeServiceRaw(Exchange exchange) {
    super(exchange);
  }

  public List<GeminiOrder> getGeminiOrders() throws IOException {
    GeminiBaseRequest geminiBaseRequest = new GeminiBaseRequest();
    geminiBaseRequest.setNonce(exchange.getNonceFactory().createValue());
    return gemini.getOrders(apiKey, geminiBaseRequest, signatureCreator);
  }

  public List<GeminiUserTrade> getGeminiTrades(CurrencyPair pair, Long limit, Long since) throws IOException {
    String symbol = GeminiAdapters.adaptPair(pair);
    GeminiTradesRequest geminiTradesRequest = new GeminiTradesRequest(symbol, limit, since);
    geminiTradesRequest.setNonce(exchange.getNonceFactory().createValue());
    return gemini.getTrades(apiKey, geminiTradesRequest, signatureCreator);
  }

  public GeminiOrder getGeminiOrder(Long orderId) throws IOException {
    GeminiOrderRequest geminiOrderRequest = new GeminiOrderRequest(orderId);
    geminiOrderRequest.setNonce(exchange.getNonceFactory().createValue());
    return gemini.getOrderStatus(apiKey, geminiOrderRequest, signatureCreator);
  }

  public GeminiOrder placeNewLimitOrder(LimitOrder limitOrder) throws IOException {

    GeminiNewOrderRequest request = new GeminiNewOrderRequest(GeminiAdapters.adaptPair(limitOrder.getCurrencyPair()));
    request.setPrice(limitOrder.getLimitPrice().toPlainString());
    request.setAmount(limitOrder.getTradableAmount().toPlainString());
    request.setSide(limitOrder.getType() == Order.OrderType.BID ? "buy" : "sell");
    request.setType("exchange limit");
    request.setNonce(exchange.getNonceFactory().createValue());
    return gemini.postNewOrder(apiKey, request, signatureCreator);
  }

  public GeminiOrder cancelOrder(Long orderId) throws IOException {
    GeminiOrderRequest geminiOrderRequest = new GeminiOrderRequest(orderId);
    geminiOrderRequest.setNonce(exchange.getNonceFactory().createValue());
    return gemini.cancelOrder(apiKey, geminiOrderRequest, signatureCreator);
  }
}
