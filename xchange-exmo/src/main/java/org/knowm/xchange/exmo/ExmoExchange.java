package org.knowm.xchange.exmo;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.exmo.service.polling.ExmoTradeService;
import org.knowm.xchange.exmo.service.polling.ExmoAccountService;
import org.knowm.xchange.exmo.service.polling.ExmoMarketDataService;
import si.mazi.rescu.SynchronizedValueFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.utils.nonce.CurrentNanosecondTimeIncrementalNonceFactory;

public class ExmoExchange extends BaseExchange implements Exchange {

  private SynchronizedValueFactory<Long> nonceFactory = new CurrentNanosecondTimeIncrementalNonceFactory();

  @Override
  protected void initServices() {
    this.pollingMarketDataService = new ExmoMarketDataService(this);
    this.pollingTradeService = new ExmoTradeService(this);
    this.pollingAccountService = new ExmoAccountService(this);
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://api.exmo.com");
    exchangeSpecification.setHost("api.exmo.com");
    exchangeSpecification.setPort(80);
    exchangeSpecification.setExchangeName("Exmo");
    return exchangeSpecification;
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {

    return nonceFactory;
  }
}
