package org.knowm.xchange.bl3p.service.polling;

import com.xeiam.xchange.Exchange;
import org.knowm.xchange.bl3p.Bl3pAuthenticated;
import org.knowm.xchange.bl3p.dto.Bl3pResult;
import org.knowm.xchange.bl3p.dto.account.Bl3pAccountInfo;
import org.knowm.xchange.bl3p.service.Bl3pDigest;
import com.xeiam.xchange.exceptions.ExchangeException;
import si.mazi.rescu.RestProxyFactory;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 17/08/15
 * Time: 16:20
 */
public class Bl3pAccountServiceRaw extends Bl3pBasePollingService {

  private final Bl3pAuthenticated bl3pAuthenticated;
  private final Bl3pDigest signatureCreator;
  private final String apiKey;

  /**
   * Constructor
   *
   * @param exchange
   */
  protected Bl3pAccountServiceRaw(Exchange exchange) {
    super(exchange);

    this.bl3pAuthenticated = RestProxyFactory.createProxy(Bl3pAuthenticated.class, exchange.getExchangeSpecification().getSslUri());
    this.signatureCreator = Bl3pDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
  }

  protected Bl3pAccountInfo getBl3pAccountInfo() throws IOException {
    Bl3pResult<Bl3pAccountInfo> result = bl3pAuthenticated.getAccountInfo(apiKey, signatureCreator);
    if ("error".equals(result.getResult())) {
      String msg = result.getData() != null ? result.getData().getMessage() : "Account error";
      throw new ExchangeException(msg);
    }
    return result.getData();
  }

}
