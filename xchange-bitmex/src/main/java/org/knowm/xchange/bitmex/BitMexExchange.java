package org.knowm.xchange.bitmex;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.bitmex.service.polling.BitMexTradeService;
import org.knowm.xchange.bitmex.service.polling.BitMexAccountService;
import org.knowm.xchange.bitmex.service.polling.BitMexMarketDataService;

import org.knowm.xchange.utils.nonce.CurrentTimeNonceFactory;
import si.mazi.rescu.SynchronizedValueFactory;

public class BitMexExchange extends BaseExchange implements Exchange {
  private SynchronizedValueFactory<Long> nonceFactory = new CurrentTimeNonceFactory();

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    return nonceFactory;
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {
    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setExchangeDescription("BitMex.Com - Bitcoin Mercantile Exchange");
    exchangeSpecification.setSslUri("https://bitmex.com");
    exchangeSpecification.setExchangeName("BitMex");
    exchangeSpecification.setHost("bitmex.com");
    exchangeSpecification.setPort(80);
    return exchangeSpecification;
  }

  @Override
  protected void initServices() {
    this.pollingMarketDataService = new BitMexMarketDataService(this);
    this.pollingAccountService = new BitMexAccountService(this);
    this.pollingTradeService = new BitMexTradeService(this);
  }
}
