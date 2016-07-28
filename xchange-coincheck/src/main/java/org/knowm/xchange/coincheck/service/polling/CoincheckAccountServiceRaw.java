package org.knowm.xchange.coincheck.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.coincheck.dto.account.CoincheckBalance;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 02/06/16
 * Time: 19:22
 */
public class CoincheckAccountServiceRaw extends CoincheckBasePollingService {

  protected CoincheckAccountServiceRaw(Exchange exchange) {
    super(exchange);
  }

  protected CoincheckBalance getCoincheckBalance() throws IOException {

    return coincheck.getBalance(apiKey, exchange.getNonceFactory(), signatureCreator);
  }
}
