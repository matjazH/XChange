package org.knowm.xchange.bl3p.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bl3p.Bl3pAdapters;
import org.knowm.xchange.bl3p.dto.account.Bl3pAccountInfo;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.FundingRecord;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 17/08/15
 * Time: 17:20
 */
public class Bl3pAccountService extends Bl3pAccountServiceRaw implements AccountService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public Bl3pAccountService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public AccountInfo getAccountInfo() throws ExchangeException, IOException {
    Bl3pAccountInfo bl3pAccountInfo = getBl3pAccountInfo();
    return Bl3pAdapters.adaptAccount(bl3pAccountInfo);
  }

  @Override
  public String withdrawFunds(Currency currency, BigDecimal amount, String address) throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    return null;
  }

  @Override
  public String requestDepositAddress(Currency currency, String... args) throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    return null;
  }

  @Override
  public TradeHistoryParams createFundingHistoryParams() {
    return null;
  }

  @Override
  public List<FundingRecord> getFundingHistory(TradeHistoryParams params) throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    return null;
  }
}
