package org.knowm.xchange.gemini.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.gemini.GeminiAuthenticated;
import org.knowm.xchange.gemini.service.GeminiHmacDigest;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.polling.BasePollingService;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

/**
 * Created by tabtrader on 03/10/2016.
 */
public class GeminiBasePollingService extends BaseExchangeService implements BasePollingService {


  protected final String apiKey;
  protected final GeminiAuthenticated gemini;
  protected final ParamsDigest signatureCreator;



  /**
   * Constructor
   *
   * @param exchange
   */
  protected GeminiBasePollingService(Exchange exchange) {
    super(exchange);

    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.gemini = RestProxyFactory.createProxy(GeminiAuthenticated.class, exchange.getExchangeSpecification().getSslUri());
    this.signatureCreator = GeminiHmacDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
  }
}
