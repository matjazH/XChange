package org.knowm.xchange.gdax;

import java.io.IOException;
import java.util.List;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.gdax.dto.marketdata.GDAXProduct;
import org.knowm.xchange.gdax.service.GDAXAccountService;
import org.knowm.xchange.gdax.service.GDAXMarketDataService;
import org.knowm.xchange.gdax.service.GDAXMarketDataServiceRaw;
import org.knowm.xchange.gdax.service.GDAXTradeService;
import org.knowm.xchange.utils.nonce.CurrentTimeNonceFactory;

import si.mazi.rescu.SynchronizedValueFactory;

public class GDAXExchange extends BaseExchange implements Exchange {

  private SynchronizedValueFactory<Long> nonceFactory = new CurrentTimeNonceFactory();

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    return nonceFactory;
  }

  @Override
  protected void initServices() {
    this.pollingMarketDataService = new GDAXMarketDataService(this);
    this.pollingAccountService = new GDAXAccountService(this);
    this.pollingTradeService = new GDAXTradeService(this);
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification=new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://api.gdax.com");
    exchangeSpecification.setHost("api.gdax.com");
    exchangeSpecification.setPort(80);
    exchangeSpecification.setExchangeName("GDAX");
    exchangeSpecification.setExchangeDescription("GDAX Exchange is a exchange recently launched in Jan 2015");
    return exchangeSpecification;
  }
}
