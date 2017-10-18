package org.knowm.xchange.yobit.service.polling;

import org.knowm.xchange.Exchange;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;
import org.knowm.xchange.yobit.dto.YoBitResult;
import org.knowm.xchange.yobit.YoBitAuthenticated;
import org.knowm.xchange.yobit.service.YoBitDigest;
import org.knowm.xchange.yobit.dto.account.YoBitAccount;

import java.io.IOException;

public class YoBitAccountServiceRaw extends YoBitBasePollingService {

  protected final String apiKey;
  protected YoBitAuthenticated yobit;
  protected final ParamsDigest signatureCreator;

  protected YoBitAccountServiceRaw(Exchange exchange) {

    super(exchange);
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.signatureCreator = YoBitDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
    yobit = RestProxyFactory.createProxy(YoBitAuthenticated.class, exchange.getExchangeSpecification().getSslUri());
  }

  protected YoBitAccount getYoBitDepositAddress(String currency) throws IOException {
    YoBitResult<YoBitAccount> info;
    info = yobit.getAddress(apiKey, signatureCreator, "GetDepositAddress", currency, 0, exchange.getNonceFactory());
    return info.getResult();
  }

  protected YoBitAccount getYoBitAccountInfo() throws IOException {
    YoBitResult<YoBitAccount> result = yobit.getInfo(apiKey, signatureCreator, "getInfo", exchange.getNonceFactory());
    return result.getResult();
  }
}
