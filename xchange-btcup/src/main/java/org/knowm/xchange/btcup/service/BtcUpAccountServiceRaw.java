package org.knowm.xchange.btcup.service;

import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.btcup.dto.account.BtcUpAccount;

/**
 * Created by VITTACH on 28/08/17.
 */
public class BtcUpAccountServiceRaw extends BtcUpBaseService {

  public BtcUpAccountServiceRaw(Exchange exchange) {
    super(exchange);
  }

  public BtcUpAccount getBTCUPAccountInfo() throws IOException {

    return btcUp.account(apiKey, signatureCreator, exchange.getNonceFactory(), "account").getResult();
  }

}
