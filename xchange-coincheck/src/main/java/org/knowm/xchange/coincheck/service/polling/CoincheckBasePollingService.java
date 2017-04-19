package org.knowm.xchange.coincheck.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.coincheck.CoincheckAuthenticated;
import org.knowm.xchange.coincheck.service.CoincheckDigest;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 02/06/16
 * Time: 12:20
 */
public class CoincheckBasePollingService extends BaseExchangeService implements BaseService {

  protected final CoincheckAuthenticated coincheck;
  protected final String apiKey;
  protected final ParamsDigest signatureCreator;


  /**
   * Constructor
   *
   * @param exchange
   */
  protected CoincheckBasePollingService(Exchange exchange) {
    super(exchange);

    this.coincheck = RestProxyFactory.createProxy(CoincheckAuthenticated.class, exchange.getExchangeSpecification().getSslUri(), createClientConfig(exchange.getExchangeSpecification()));
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.signatureCreator = CoincheckDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
  }
}
