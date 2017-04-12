package org.knowm.xchange.exmo.service.polling;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.exmo.ExmoAdapters;
import org.knowm.xchange.exmo.dto.account.ExmoUserInfo;
import org.knowm.xchange.service.polling.account.PollingAccountService;

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
