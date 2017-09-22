package org.knowm.xchange.ccex.service;

import java.util.List;
import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ccex.dto.account.CCEXBalance;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.ccex.dto.account.CCEXBalanceResponse;
import org.knowm.xchange.ccex.dto.account.CCEXBalancesResponse;

public class CCEXAccountServiceRaw extends CCEXBaseService {

  public CCEXAccountServiceRaw(Exchange exchange) {
    super(exchange);
  }

  public List<CCEXBalance> getCCEXAccountInfo() throws IOException {

    CCEXBalancesResponse response=cCEXAuthenticated.balances(apiKey, signatureCreator, exchange.getNonceFactory());

    if (response.isSuccess()) {
      return response.getResult();
    } else {
      throw new ExchangeException(response.getMessage());
    }
  }

  public String getCCEXDepositAddress(String currency) throws IOException {

    CCEXBalanceResponse response;
    response = cCEXAuthenticated.getdepositaddress(apiKey, signatureCreator, exchange.getNonceFactory(), currency);

    if (response.isSuccess()) {
      return response.getResult().getCryptoAddress();
    } else {
      throw new ExchangeException(response.getMessage());
    }
  }
}
