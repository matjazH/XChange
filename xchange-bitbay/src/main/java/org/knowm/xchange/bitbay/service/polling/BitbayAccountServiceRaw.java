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

  protected BitbayAuthentiacated bitbay;
  protected final ParamsDigest signatureCreator;
  protected final String apiKey;

  protected BitbayAccountServiceRaw(Exchange exchange) {

      super(exchange);
      this.bitbay = RestProxyFactory.createProxy(BitbayAuthentiacated.class, exchange.getExchangeSpecification().getSslUri());
      this.apiKey = exchange.getExchangeSpecification().getApiKey();
      this.signatureCreator = BitbayDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
  }

    protected BitbayAccount getBitbayAccountInfo(String currency) throws IOException {

        BitbayAccount info = bitbay.info(apiKey, signatureCreator, exchange.getNonceFactory(), currency);
        return info;
    }

}
