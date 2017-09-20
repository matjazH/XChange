package org.knowm.xchange.therock;

import java.util.ArrayList;
import java.util.List;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.therock.service.polling.TheRockAccountService;
import org.knowm.xchange.therock.service.polling.TheRockMarketDataService;
import org.knowm.xchange.therock.service.polling.TheRockTradeService;
import org.knowm.xchange.utils.nonce.TimestampIncrementingNonceFactory;
import si.mazi.rescu.SynchronizedValueFactory;


public class TheRockExchange
    extends BaseExchange
    implements Exchange {
  List<CurrencyPair> rockExchangeSymbols = new ArrayList();

  public static final String CURRENCY_PAIR = "CURRENCY_PAIR";

  private SynchronizedValueFactory<Long> rockNonceFactory = new TimestampIncrementingNonceFactory();

  protected void initServices() {

    this.pollingMarketDataService = new TheRockMarketDataService(this);

    if ((this.exchangeSpecification.getApiKey() != null) && (this.exchangeSpecification.getSecretKey() != null)) {

      this.pollingTradeService = new TheRockTradeService(this);

      this.pollingAccountService = new TheRockAccountService(this);
    }
  }

  public void applySpecification(ExchangeSpecification exchangeSpecification) {

    super.applySpecification(exchangeSpecification);
  }

  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(getClass().getCanonicalName());

    exchangeSpecification.setSslUri("https://api.therocktrading.com");

    exchangeSpecification.setHost("api.therocktrading.com");

    exchangeSpecification.setPort(80);

    exchangeSpecification.setExchangeName("The Rock Trading");

    return exchangeSpecification;
  }

  public void remoteInit() {
    try {

      this.rockExchangeSymbols = ((TheRockMarketDataService) this.pollingMarketDataService).getExchangeSymbols();
    } catch (Exception e) {

      this.logger.warn("An exception occurred while getting exchange symbols", e);
    }
  }

  public SynchronizedValueFactory<Long> getNonceFactory() {

    return this.rockNonceFactory;
  }

  public List<CurrencyPair> getRockExchangeSymbols() {

    return this.rockExchangeSymbols;
  }
}