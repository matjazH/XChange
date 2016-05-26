package org.knowm.xchange.exmo.service.polling;

import com.xeiam.xchange.Exchange;
import org.knowm.xchange.exmo.ExmoAuthenticated;
import org.knowm.xchange.exmo.dto.account.ExmoUserInfo;
import org.knowm.xchange.exmo.service.ExmoDigest;
import si.mazi.rescu.RestProxyFactory;

import java.io.IOException;
import java.util.Map;

/**
 * @author gnandiga
 */
public class ExmoAccountServiceRaw extends ExmoBasePollingService {

  private final ExmoAuthenticated exmoAuthenticated;
  private final ExmoDigest signatureCreator;
  private final String apiKey;

  /**
   * Constructor
   *
   * @param exchange
   */
  protected ExmoAccountServiceRaw(Exchange exchange) {

    super(exchange);

    this.exmoAuthenticated = RestProxyFactory.createProxy(ExmoAuthenticated.class, exchange.getExchangeSpecification().getSslUri());
    this.signatureCreator = ExmoDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
  }

  public ExmoUserInfo getExmoUserInfo() throws IOException {

    return exmoAuthenticated.getUserInfo(apiKey, signatureCreator, exchange.getNonceFactory());
  }

  public Map<String, String> getExmoDepositAddress() throws IOException {

    return exmoAuthenticated.getDepositAddress(apiKey, signatureCreator, exchange.getNonceFactory());
  }
}
