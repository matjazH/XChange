package org.knowm.xchange.unocoin;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.BaseExchange;
import si.mazi.rescu.SynchronizedValueFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.utils.nonce.CurrentTime1000NonceFactory;
import org.knowm.xchange.unocoin.service.polling.UnoCoinMarketDataService;

public class UnoCoinExchange extends BaseExchange implements Exchange {
  private SynchronizedValueFactory<Long> nonceFactory = new CurrentTime1000NonceFactory();

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    return nonceFactory;
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {
    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setExchangeDescription("UnoCoin.Com - India's Bitcoin Comp");
    exchangeSpecification.setSslUri("https://unocoin.com");
    exchangeSpecification.setExchangeName("UnoCoin");
    exchangeSpecification.setHost("unocoin.com");
    exchangeSpecification.setPort(80);
    return exchangeSpecification;
  }

  @Override
  protected void initServices() {
    this.pollingMarketDataService = new UnoCoinMarketDataService(this);
    /*
		this.pollingAccountService = new UnoCoinAccountService(this);
		this.pollingTradeService = new UnoCoinTradeService(this);
		*/
  }
}
