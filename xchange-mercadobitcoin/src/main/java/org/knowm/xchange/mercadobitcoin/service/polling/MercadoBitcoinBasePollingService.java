package org.knowm.xchange.mercadobitcoin.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.mercadobitcoin.dto.v3.MercadoBitcoinBaseResponse;
import org.knowm.xchange.mercadobitcoin.service.MercadoBitcoinDigest;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.polling.BasePollingService;

/**
 * @author Felipe Micaroni Lalli
 */
public class MercadoBitcoinBasePollingService extends BaseExchangeService implements BasePollingService {

  protected MercadoBitcoinDigest signatureCreator;

  /**
   * Constructor
   *
   * @param exchange
   */
  public MercadoBitcoinBasePollingService(Exchange exchange) {
    super(exchange);

    signatureCreator = MercadoBitcoinDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
  }

  protected void checkResponse(MercadoBitcoinBaseResponse response) {
    if (response.getStatusCode() != 100 && response.getErrorMessage() != null) {
      throw new ExchangeException("Error: " + response.getStatusCode()
          + " " + response.getErrorMessage());
    }
  }

}
