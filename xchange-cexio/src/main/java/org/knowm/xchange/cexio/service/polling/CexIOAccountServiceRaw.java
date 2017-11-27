package org.knowm.xchange.cexio.service.polling;

import java.io.IOException;
import java.util.Map;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.cexio.CexIOAuthenticated;
import org.knowm.xchange.cexio.dto.account.CexIOBalanceInfo;
import org.knowm.xchange.cexio.dto.account.GHashIOHashrate;
import org.knowm.xchange.cexio.dto.account.GHashIOWorker;
import org.knowm.xchange.cexio.service.CexIODigest;
import org.knowm.xchange.exceptions.ExchangeException;

import org.knowm.xchange.utils.CertHelper;
import si.mazi.rescu.ClientConfig;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

/**
 * @author timmolter
 */
public class CexIOAccountServiceRaw extends CexIOBasePollingService {

  private final CexIOAuthenticated cexIOAuthenticated;
  private ParamsDigest signatureCreator;

  /**
   * Constructor
   *
   * @param exchange
   */
  public CexIOAccountServiceRaw(Exchange exchange) {

    super(exchange);

    ClientConfig config = new ClientConfig();
    // config.setSslSocketFactory(CertHelper.createRestrictedSSLSocketFactory("TLSv1"));
    config.setProxyHost(exchange.getExchangeSpecification().getProxyHost());
    config.setProxyPort(exchange.getExchangeSpecification().getProxyPort());

    this.cexIOAuthenticated = RestProxyFactory.createProxy(CexIOAuthenticated.class, exchange.getExchangeSpecification().getSslUri(), config);
    signatureCreator = CexIODigest.createInstance(exchange.getExchangeSpecification().getSecretKey(),
        exchange.getExchangeSpecification().getUserName(), exchange.getExchangeSpecification().getApiKey());
  }

  public CexIOBalanceInfo getCexIOAccountInfo() throws IOException {

    CexIOBalanceInfo info = cexIOAuthenticated.getBalance(exchange.getExchangeSpecification().getApiKey(), signatureCreator,
        exchange.getNonceFactory());
    if (info.getError() != null) {
      throw new ExchangeException("Error getting balance. " + info.getError());
    }

    return info;
  }

  public GHashIOHashrate getHashrate() throws IOException {

    return cexIOAuthenticated.getHashrate(exchange.getExchangeSpecification().getApiKey(), signatureCreator, exchange.getNonceFactory());
  }

  public Map<String, GHashIOWorker> getWorkers() throws IOException {

    return cexIOAuthenticated.getWorkers(exchange.getExchangeSpecification().getApiKey(), signatureCreator, exchange.getNonceFactory()).getWorkers();
  }

}
