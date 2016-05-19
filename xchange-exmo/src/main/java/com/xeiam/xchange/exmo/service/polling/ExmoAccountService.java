package com.xeiam.xchange.exmo.service.polling;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.currency.Currency;
import com.xeiam.xchange.dto.account.AccountInfo;
import com.xeiam.xchange.exceptions.ExchangeException;
import com.xeiam.xchange.exceptions.NotAvailableFromExchangeException;
import com.xeiam.xchange.exceptions.NotYetImplementedForExchangeException;
import com.xeiam.xchange.exmo.ExmoAdapters;
import com.xeiam.xchange.exmo.dto.account.ExmoUserInfo;
import com.xeiam.xchange.service.polling.account.PollingAccountService;

public class ExmoAccountService extends ExmoAccountServiceRaw implements PollingAccountService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public ExmoAccountService(Exchange exchange) {

    super(exchange);
  }

  @Override
  public AccountInfo getAccountInfo() throws IOException {
    ExmoUserInfo exmoUserInfo = getExmoUserInfo();
    if (exmoUserInfo.getError() != null) {
      throw new ExchangeException(exmoUserInfo.getError());
    }
    return ExmoAdapters.adaptAccountInfo(exmoUserInfo);
  }

  @Override
  public String requestDepositAddress(Currency currency, String... args) throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {

    Map<String, String> exmoDepositAddress = getExmoDepositAddress();
    return exmoDepositAddress.get(currency);
  }

  @Override
  public String withdrawFunds(Currency currency, BigDecimal amount, String address) throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    throw new NotYetImplementedForExchangeException();
  }
}
