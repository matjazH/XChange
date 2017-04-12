package org.knowm.xchange.bl3p;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.bl3p.service.polling.Bl3pAccountService;
import org.knowm.xchange.bl3p.service.polling.Bl3pMarketDataService;
import org.knowm.xchange.bl3p.service.polling.Bl3pTradeService;
import si.mazi.rescu.SynchronizedValueFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 14/08/15
 * Time: 19:11
 */
public class Bl3pExchange extends BaseExchange implements Exchange {

  @Override
  protected void initServices() {
    this.pollingMarketDataService = new Bl3pMarketDataService(this);
    this.pollingAccountService = new Bl3pAccountService(this);
    this.pollingTradeService = new Bl3pTradeService(this);
  }

  @Override
  public void applySpecification(ExchangeSpecification exchangeSpecification) {

    super.applySpecification(exchangeSpecification);
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    return null;
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://api.bl3p.eu");
    exchangeSpecification.setHost("api.bl3p.eu");
    exchangeSpecification.setPort(80);
    exchangeSpecification.setExchangeName("Bl3p");
    exchangeSpecification.setExchangeDescription("Bitonic is a bitcoin exchange.");

    return exchangeSpecification;
  }
}
