package org.knowm.xchange.btcup.service;

import org.knowm.xchange.Exchange;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;
import org.knowm.xchange.btcup.BtcUpAuthenticated;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.polling.BasePollingService;

/**
 * Created by VITTACH on 28/08/17.
 */
public class BtcUpBaseService extends BaseExchangeService implements BasePollingService {

  protected final String apiKey;
  protected final BtcUpAuthenticated btcUp;
  protected final ParamsDigest signatureCreator;

  public BtcUpBaseService(Exchange exchange) {

    super(exchange);
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.signatureCreator = BtcUpDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
    this.btcUp = RestProxyFactory.createProxy(BtcUpAuthenticated.class,exchange.getExchangeSpecification().getSslUri());
  }
}
