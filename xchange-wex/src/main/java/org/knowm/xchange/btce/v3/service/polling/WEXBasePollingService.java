package org.knowm.xchange.btce.v3.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.btce.v3.WEXAuthenticated;
import org.knowm.xchange.btce.v3.dto.WEXReturn;
import org.knowm.xchange.btce.v3.service.WEXHmacPostBodyDigest;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.FundsExceededException;
import org.knowm.xchange.exceptions.NonceException;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.polling.BasePollingService;

import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

public class WEXBasePollingService extends BaseExchangeService implements BasePollingService {

  private static final String ERR_MSG_NONCE = "invalid nonce parameter; on key:";
  private static final String ERR_MSG_FUNDS = "It is not enough ";

  protected final String apiKey;
  protected final WEXAuthenticated btce;
  protected final ParamsDigest signatureCreator;

  /**
   * Constructor
   *
   * @param exchange
   */
  public WEXBasePollingService(Exchange exchange) {

    super(exchange);

    this.btce = RestProxyFactory.createProxy(WEXAuthenticated.class, exchange.getExchangeSpecification().getSslUri(), createClientConfig(exchange.getExchangeSpecification()));
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.signatureCreator = WEXHmacPostBodyDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
  }

  protected void checkResult(WEXReturn<?> result) {
    String error = result.getError();

    if (!result.isSuccess()) {
      if (error != null) {
        if (error.startsWith(ERR_MSG_NONCE)) {
          throw new NonceException(error);
        } else if (error.startsWith(ERR_MSG_FUNDS)) {
          throw new FundsExceededException(error);
        }
      }
      throw new ExchangeException(error);
    }

    else if (result.getReturnValue() == null) {
      throw new ExchangeException("Didn't receive any return value. Message: " + error);
    }

    else if (error != null) {
      throw new ExchangeException(error);
    }
  }

}
