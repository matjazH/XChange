package org.knowm.xchange.hitbtc.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.hitbtc.service.HitbtcHmacDigest;
import org.knowm.xchange.exceptions.FundsExceededException;
import org.knowm.xchange.service.polling.BasePollingService;
import org.knowm.xchange.hitbtc.HitbtcAuthenticated;
import org.knowm.xchange.hitbtc.dto.HitbtcException;

import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

public class HitbtcBasePollingService extends BaseExchangeService implements BasePollingService {

  protected final HitbtcAuthenticated hitbtc;
  protected final String apiKey;
  protected final ParamsDigest signatureCreator;

  /**
   * Constructor
   *
   * @param exchange
   */
  protected HitbtcBasePollingService(Exchange exchange) {

    super(exchange);

    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.signatureCreator =  HitbtcHmacDigest.createInstance(exchange.getExchangeSpecification().getApiKey(), exchange.getExchangeSpecification().getSecretKey());
    this.hitbtc = RestProxyFactory.createProxy(HitbtcAuthenticated.class, exchange.getExchangeSpecification().getSslUri(), createClientConfig(exchange.getExchangeSpecification()));
  }

  void handleException(HitbtcException hitbtcException) {
    if (hitbtcException != null) {
      if (hitbtcException.getMessage().toLowerCase().contains("funds")) {
        throw new FundsExceededException(hitbtcException.getMessage());
      } else if (hitbtcException.getMessage().toLowerCase().contains("order")) {
        throw new IllegalStateException(hitbtcException.getMessage());
      } else {
        throw new IllegalArgumentException("Order was rejected " + hitbtcException.getMessage());
      }
    }
    throw new ExchangeException(hitbtcException.getCode() + ": " + hitbtcException.getMessage());
  }
}
