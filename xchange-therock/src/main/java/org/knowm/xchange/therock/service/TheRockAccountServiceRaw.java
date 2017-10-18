package org.knowm.xchange.therock.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.therock.TheRockAuthenticated;
import org.knowm.xchange.therock.dto.TheRockException;
import org.knowm.xchange.therock.dto.account.TheRockBalance;
import org.knowm.xchange.therock.dto.account.TheRockWithdrawal;
import org.knowm.xchange.therock.dto.account.TheRockWithdrawalResponse;
import si.mazi.rescu.RestProxyFactory;


public class TheRockAccountServiceRaw
    extends TheRockBaseService {
  private final TheRockDigest signatureCreator;
  private final TheRockAuthenticated theRockAuthenticated;
  private final String apiKey;

  protected TheRockAccountServiceRaw(Exchange exchange) {
    super(exchange);
    ExchangeSpecification spec = exchange.getExchangeSpecification();
    this.theRockAuthenticated = ((TheRockAuthenticated) RestProxyFactory.createProxy(TheRockAuthenticated.class, spec.getSslUri()));
    this.apiKey = spec.getApiKey();
    this.signatureCreator = new TheRockDigest(spec.getSecretKey());
  }

  public TheRockWithdrawalResponse withdrawDefault(Currency currency, BigDecimal amount, String destinationAddress)
      throws TheRockException, IOException {
    TheRockWithdrawal withdrawal = TheRockWithdrawal.createDefaultWithdrawal(currency.getCurrencyCode(), amount, destinationAddress);
    return this.theRockAuthenticated.withdraw(this.apiKey, this.signatureCreator, this.exchange.getNonceFactory(), withdrawal);
  }

  public TheRockWithdrawalResponse withdrawRipple(Currency currency, BigDecimal amount, String destinationAddress)
      throws TheRockException, IOException {
    TheRockWithdrawal withdrawal = TheRockWithdrawal.createRippleWithdrawal(currency.getCurrencyCode(), amount, destinationAddress);
    return this.theRockAuthenticated.withdraw(this.apiKey, this.signatureCreator, this.exchange.getNonceFactory(), withdrawal);
  }

  public List<TheRockBalance> balances() throws TheRockException, IOException {
    return this.theRockAuthenticated.balances(this.apiKey, this.signatureCreator, this.exchange.getNonceFactory()).getBalances();
  }
}