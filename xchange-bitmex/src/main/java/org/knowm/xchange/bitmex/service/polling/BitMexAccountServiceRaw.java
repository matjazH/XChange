package org.knowm.xchange.bitmex.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitmex.dto.BitMexResult;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;
import org.knowm.xchange.bitmex.BitMexAuthenticated;
import org.knowm.xchange.bitmex.service.BitMexDigest;
import org.knowm.xchange.bitmex.dto.account.BitMexAccount;

import java.io.IOException;

public class BitMexAccountServiceRaw extends BitMexBasePollingService {

  protected final String apiKey;
  protected BitMexAuthenticated yobit;
  protected final ParamsDigest signatureCreator;

  protected BitMexAccountServiceRaw(Exchange exchange) {

    super(exchange);
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.signatureCreator = BitMexDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
    yobit = RestProxyFactory.createProxy(BitMexAuthenticated.class, exchange.getExchangeSpecification().getSslUri());
  }

  protected BitMexAccount getYoBitDepositAddress(String currency) throws IOException {
    BitMexResult<BitMexAccount> info;
    info = yobit.getAddress(apiKey, signatureCreator, "GetDepositAddress", currency, 0, exchange.getNonceFactory());
    return info.getResult();
  }

  protected BitMexAccount getYoBitAccountInfo() throws IOException {
    BitMexResult<BitMexAccount> result = yobit.getInfo(apiKey, signatureCreator, "getInfo", exchange.getNonceFactory());
    return result.getResult();
  }
}
