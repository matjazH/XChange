package org.knowm.xchange.bitbay.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitbay.BitbayAuthentiacated;
import org.knowm.xchange.bitbay.dto.account.BitbayAccount;
import org.knowm.xchange.bitbay.service.BitbayDigest;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

import java.io.IOException;

/**
 * @author yarkh
 */
public class BitbayAccountServiceRaw extends BitbayBasePollingService {

  protected BitbayAccountServiceRaw(Exchange exchange) {
      super(exchange);
  }

  protected BitbayAccount getBitbayAccountInfo(String currency) throws IOException {
    BitbayAccount info = bitbay.info(apiKey, signatureCreator, exchange.getNonceFactory(), currency);
    return info;
  }
}
