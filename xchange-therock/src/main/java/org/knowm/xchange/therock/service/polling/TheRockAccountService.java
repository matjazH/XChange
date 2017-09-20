package org.knowm.xchange.therock.service.polling;

import java.io.IOException;
import java.math.BigDecimal;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.service.polling.account.PollingAccountService;
import org.knowm.xchange.therock.TheRockAdapters;
import org.knowm.xchange.therock.dto.account.TheRockWithdrawalResponse;
import org.knowm.xchange.therock.service.TheRockAccountServiceRaw;


public class TheRockAccountService
    extends TheRockAccountServiceRaw
    implements PollingAccountService {
  public TheRockAccountService(Exchange exchange) {
    super(exchange);
  }

  public AccountInfo getAccountInfo() throws IOException {
    return TheRockAdapters.adaptAccountInfo(balances(), this.exchange.getExchangeSpecification().getUserName());
  }

  public String withdrawFunds(Currency currency, BigDecimal amount, String address) throws IOException {
    TheRockWithdrawalResponse response = withdrawDefault(currency, amount, address);
    return String.format("%d", new Object[]{response.getTransactionId()});
  }

  public String requestDepositAddress(Currency currency, String... arguments) throws IOException {
    throw new NotAvailableFromExchangeException();
  }
}