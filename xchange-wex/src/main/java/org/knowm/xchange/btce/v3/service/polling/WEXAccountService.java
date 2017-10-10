package org.knowm.xchange.btce.v3.service.polling;

import java.io.IOException;
import java.math.BigDecimal;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.btce.v3.WEXAdapters;
import org.knowm.xchange.btce.v3.dto.account.WEXAccountInfo;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.service.polling.account.PollingAccountService;

/**
 * @author Matija Mazi
 */
public class WEXAccountService extends WEXAccountServiceRaw implements PollingAccountService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public WEXAccountService(Exchange exchange) {

    super(exchange);
  }

  @Override
  public AccountInfo getAccountInfo() throws IOException {

    WEXAccountInfo info = getBTCEAccountInfo(null, null, null, null, null, null, null);
    return new AccountInfo(WEXAdapters.adaptWallet(info));
  }

  @Override
  public String withdrawFunds(Currency currency, BigDecimal amount, String address) throws IOException {
    String s = withdraw(currency.toString(), amount, address);
    return s;
  }

  @Override
  public String requestDepositAddress(Currency currency, String... args) throws IOException {

    throw new NotAvailableFromExchangeException();
  }

}
