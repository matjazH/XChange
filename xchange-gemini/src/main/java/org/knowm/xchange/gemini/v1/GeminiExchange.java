package org.knowm.xchange.gemini.v1;

import java.io.IOException;
import java.util.List;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.gemini.v1.service.GeminiAccountService;
import org.knowm.xchange.gemini.v1.service.GeminiMarketDataService;
import org.knowm.xchange.gemini.v1.service.GeminiMarketDataServiceRaw;
import org.knowm.xchange.gemini.v1.service.GeminiTradeService;
import org.knowm.xchange.utils.nonce.AtomicLongIncrementalTime2013NonceFactory;

import org.knowm.xchange.utils.nonce.CurrentTimeNonceFactory;
import si.mazi.rescu.SynchronizedValueFactory;

public class GeminiExchange extends BaseExchange implements Exchange {

  private SynchronizedValueFactory<Long> nonceFactory = new CurrentTimeNonceFactory();

  @Override
  protected void initServices() {
    this.marketDataService = new GeminiMarketDataService(this);
    this.accountService = new GeminiAccountService(this);
    this.tradeService = new GeminiTradeService(this);
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://api.gemini.com/");
    exchangeSpecification.setHost("api.gemini.com");
    exchangeSpecification.setPort(80);
    exchangeSpecification.setExchangeName("Gemini");
    exchangeSpecification.setExchangeDescription("Gemini is a bitcoin exchange.");

    return exchangeSpecification;
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {

    return nonceFactory;
  }

  @Override
  public void remoteInit() throws IOException, ExchangeException {

    GeminiMarketDataServiceRaw dataService = (GeminiMarketDataServiceRaw) this.marketDataService;
    List<CurrencyPair> currencyPairs = dataService.getExchangeSymbols();
    exchangeMetaData = GeminiAdapters.adaptMetaData(currencyPairs, exchangeMetaData);

  }

}
