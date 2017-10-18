package org.knowm.xchange.gemini;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.gemini.service.polling.GeminiAccountService;
import org.knowm.xchange.gemini.service.polling.GeminiMarketDataService;
import org.knowm.xchange.gemini.service.polling.GeminiTradeService;
import org.knowm.xchange.utils.nonce.CurrentTimeNonceFactory;
import si.mazi.rescu.SynchronizedValueFactory;

/**
 * Created by tabtrader on 03/10/2016.
 */
public class GeminiExchange extends BaseExchange implements Exchange {

  @Override
  protected void initServices() {

    this.pollingMarketDataService = new GeminiMarketDataService(this);
    this.pollingAccountService = new GeminiAccountService(this);
    this.pollingTradeService = new GeminiTradeService(this);
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://api.gemini.com");
    exchangeSpecification.setHost("api.gemini.com");
    exchangeSpecification.setPort(80);
    exchangeSpecification.setExchangeName("Gemini");
    exchangeSpecification.setExchangeDescription("Gemini is the World’s First Licensed Bitcoin and Ether Exchange");

    return exchangeSpecification;
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    return new CurrentTimeNonceFactory();
  }

}
