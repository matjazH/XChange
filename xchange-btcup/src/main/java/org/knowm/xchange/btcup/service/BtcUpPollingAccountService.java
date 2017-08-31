package org.knowm.xchange.btcup.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.btcup.BtcUpAdapters;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.service.polling.account.PollingAccountService;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by VITTACH on 28/08/17.
 */
public class BtcUpPollingAccountService extends BtcUpAccountServiceRaw implements PollingAccountService {

  public BtcUpPollingAccountService(Exchange exchange) {

    super(exchange);
  }

  @Override
  public AccountInfo getAccountInfo() throws IOException {
    return new AccountInfo(exchange.getExchangeSpecification().getUserName(), BtcUpAdapters.adaptWallet(getBTCUPAccountInfo()));
  }

  @Override
  public String requestDepositAddress(Currency currency, String... arguments) throws IOException {

    throw new NotAvailableFromExchangeException();
  }

  @Override
  public String withdrawFunds(Currency currency, BigDecimal amount, String address) throws IOException {

    throw new NotAvailableFromExchangeException();
  }
}