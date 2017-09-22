package org.knowm.xchange.yobit;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.utils.nonce.CurrentTime1000NonceFactory;
import org.knowm.xchange.yobit.service.polling.YoBitTradeService;
import org.knowm.xchange.yobit.service.polling.YoBitAccountService;
import org.knowm.xchange.yobit.service.polling.YoBitMarketDataService;

import si.mazi.rescu.SynchronizedValueFactory;

public class YoBitExchange extends BaseExchange implements Exchange {
  private SynchronizedValueFactory<Long> nonceFactory = new CurrentTime1000NonceFactory();

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    return nonceFactory;
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {
    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setExchangeDescription("YoBit.Net - Ethereum (ETH) Exchange");
    exchangeSpecification.setSslUri("https://yobit.net");
    exchangeSpecification.setExchangeName("YoBit");
    exchangeSpecification.setHost("yobit.net");
    exchangeSpecification.setPort(80);
    return exchangeSpecification;
  }

  @Override
  protected void initServices() {
    this.pollingMarketDataService = new YoBitMarketDataService(this);
    this.pollingAccountService = new YoBitAccountService(this);
    this.pollingTradeService = new YoBitTradeService(this);
  }
}
