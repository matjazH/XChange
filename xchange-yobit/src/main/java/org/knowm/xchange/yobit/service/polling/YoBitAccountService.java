package org.knowm.xchange.yobit.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.yobit.YoBitAdapters;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.service.polling.account.PollingAccountService;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.yobit.dto.account.YoBitAccount;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;

import java.io.IOException;
import java.math.BigDecimal;

public class YoBitAccountService
    extends YoBitAccountServiceRaw implements PollingAccountService {

  public YoBitAccountService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public String requestDepositAddress(Currency currency, String... args)
      throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    YoBitAccount yoBitAccountInfo = getYoBitDepositAddress(currency.getCurrencyCode());
    return yoBitAccountInfo.getAddresses();
  }

  @Override
  public AccountInfo getAccountInfo() throws IOException {
    YoBitAccount yoBitAccountInfo = getYoBitAccountInfo();
    return YoBitAdapters.adaptAccount(yoBitAccountInfo);
  }

  @Override
  public String withdrawFunds(Currency currency, BigDecimal amount, String address)
      throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    return null;
  }
}
