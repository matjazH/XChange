package org.knowm.xchange.exmo.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exmo.ExmoAuthenticated;
import org.knowm.xchange.exmo.dto.ExmoResult;
import org.knowm.xchange.exmo.dto.trade.*;
import org.knowm.xchange.exmo.service.ExmoDigest;
import si.mazi.rescu.RestProxyFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author gnandiga
 */
public class ExmoTradeServiceRaw extends ExmoBasePollingService {

  private final ExmoAuthenticated exmoAuthenticated;
  private final ExmoDigest signatureCreator;
  private final String apiKey;

  /**
   * @param exchange
   */
  public ExmoTradeServiceRaw(Exchange exchange) {

    super(exchange);
    this.exmoAuthenticated = RestProxyFactory.createProxy(ExmoAuthenticated.class, exchange.getExchangeSpecification().getSslUri(), createClientConfig(exchange.getExchangeSpecification()));
    this.signatureCreator = ExmoDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
  }

  public Map<String, List<ExmoOrder>> getExmoOpenOrders() throws IOException {

    return exmoAuthenticated.getUserOpenOrders(apiKey, signatureCreator, exchange.getNonceFactory());
  }

  public OrderResult placeExmoOrder(String pair, BigDecimal quantity, BigDecimal price, ExmoOrderType type) throws IOException {

    return exmoAuthenticated.createOrder(apiKey, signatureCreator, exchange.getNonceFactory(),
        pair, quantity, price, type, "tabtrader");
  }

  public ExmoResult cancelExmoOrder(String orderId) throws IOException {

    return exmoAuthenticated.cancelOrder(apiKey, signatureCreator, exchange.getNonceFactory(), orderId);
  }

  public Map<String, List<ExmoUserTrade>> getExmoUserTrades(String pair, Integer limit, Integer offset) throws IOException {

    return exmoAuthenticated.getUserTrades(apiKey, signatureCreator, exchange.getNonceFactory(),
        pair, offset, limit);
  }

  public ExmoOrderTrades getExmoOrderTrades(String orderId)  throws IOException {

    return exmoAuthenticated.getOrderTrades(apiKey, signatureCreator,
        exchange.getNonceFactory(), orderId);
  }
}
