package org.knowm.xchange.bitmex.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.bitmex.BitMexAdapters;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.service.polling.account.PollingAccountService;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.bitmex.dto.account.BitMexAccount;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;

import java.io.IOException;
import java.math.BigDecimal;

public class BitMexAccountService
    extends BitMexAccountServiceRaw implements PollingAccountService {

  public BitMexAccountService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public String requestDepositAddress(Currency currency, String... args)
      throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    BitMexAccount yoBitAccountInfo = getYoBitDepositAddress(currency.getCurrencyCode());
    return yoBitAccountInfo.getAddresses();
  }

  @Override
  public AccountInfo getAccountInfo() throws IOException {
    BitMexAccount yoBitAccountInfo = getYoBitAccountInfo();
    return BitMexAdapters.adaptAccount(yoBitAccountInfo);
  }

  @Override
  public String withdrawFunds(Currency currency, BigDecimal amount, String address)
      throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    return null;
  }
}
