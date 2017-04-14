package org.knowm.xchange.bl3p;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.bl3p.service.polling.Bl3pAccountService;
import org.knowm.xchange.bl3p.service.polling.Bl3pMarketDataService;
import org.knowm.xchange.bl3p.service.polling.Bl3pMarketDataServiceRaw;
import org.knowm.xchange.bl3p.service.polling.Bl3pTradeService;
import org.knowm.xchange.exceptions.ExchangeException;
import si.mazi.rescu.SynchronizedValueFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 14/08/15
 * Time: 19:11
 */
public class Bl3pExchange extends BaseExchange implements Exchange {

  @Override
  protected void initServices() {
    this.marketDataService = new Bl3pMarketDataService(this);
    this.accountService = new Bl3pAccountService(this);
    this.tradeService = new Bl3pTradeService(this);
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
    exchangeSpecification.setExchangeName("bl3p");
    exchangeSpecification.setExchangeDescription("Bitonic is a bitcoin exchange.");

    return exchangeSpecification;
  }

  @Override
  public void remoteInit() throws IOException, ExchangeException {

    Bl3pMarketDataServiceRaw dataService = (Bl3pMarketDataServiceRaw) this.marketDataService;
  }

}
