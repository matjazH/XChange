package org.knowm.xchange.gemini.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.gemini.GeminiAdapters;
import org.knowm.xchange.gemini.dto.account.GeminiBalance;
import org.knowm.xchange.service.polling.account.PollingAccountService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by tabtrader on 04/10/2016.
 */
public class GeminiAccountService extends GeminiAccountServiceRaw implements PollingAccountService {
  /**
   * Constructor
   *
   * @param exchange
   */
  public GeminiAccountService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public AccountInfo getAccountInfo() throws IOException {

    List<GeminiBalance> geminiBalances = getGeminiBalances();
    return new AccountInfo(GeminiAdapters.adaptWallet(geminiBalances));
  }

  @Override
  public String withdrawFunds(Currency currency, BigDecimal amount, String address) throws IOException {

    throw new NotYetImplementedForExchangeException();
  }

  @Override
  public String requestDepositAddress(Currency currency, String... args) throws IOException {

    throw new NotYetImplementedForExchangeException();
  }
}
