package org.knowm.xchange.coincheck.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.coincheck.CoincheckAdapters;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.polling.account.PollingAccountService;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 02/06/16
 * Time: 19:23
 */
public class CoincheckAccountService extends CoincheckAccountServiceRaw implements PollingAccountService {

  public CoincheckAccountService(Exchange exchange) {
    super(exchange);
  }

  public AccountInfo getAccountInfo() throws ExchangeException, NotAvailableFromExchangeException,
      NotYetImplementedForExchangeException, IOException {

    return CoincheckAdapters.adaptAccount(getCoincheckBalance());
  }

  public String withdrawFunds(Currency currency, BigDecimal amount, String address) throws ExchangeException,
      NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {

    return null;
  }

  public String requestDepositAddress(Currency currency, String... args) throws ExchangeException,
      NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {

    return null;
  }
}
