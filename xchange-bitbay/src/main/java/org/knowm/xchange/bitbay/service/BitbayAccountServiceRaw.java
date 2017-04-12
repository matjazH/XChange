package org.knowm.xchange.bitbay.service;

import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitbay.dto.account.BitbayAccountInfoResponse;

/**
 * @author Z. Dolezal
 */
class BitbayAccountServiceRaw extends BitbayBaseService {

  BitbayAccountServiceRaw(Exchange exchange) {
    super(exchange);
  }

  BitbayAccountInfoResponse getBitbayAccountInfo(String currency) throws IOException {
    BitbayAccountInfoResponse response = bitbayAuthenticated.info(apiKey, sign, exchange.getNonceFactory(), currency);

    checkError(response);
    return response;
  }
}
