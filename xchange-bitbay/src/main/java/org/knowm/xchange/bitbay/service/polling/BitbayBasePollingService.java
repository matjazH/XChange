package org.knowm.xchange.bitbay.service.polling;


import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitbay.BitbayAuthentiacated;
import org.knowm.xchange.bitbay.service.BitbayDigest;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.polling.BasePollingService;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

public class BitbayBasePollingService extends BaseExchangeService implements BasePollingService {

  protected final String apiKey;
  protected BitbayAuthentiacated bitbay;
  protected final ParamsDigest signatureCreator;

  protected BitbayBasePollingService(Exchange exchange) {
    super(exchange);
    this.bitbay = RestProxyFactory.createProxy(BitbayAuthentiacated.class, exchange.getExchangeSpecification().getSslUri(), createClientConfig(exchange.getExchangeSpecification()));
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.signatureCreator = BitbayDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
  }
}
