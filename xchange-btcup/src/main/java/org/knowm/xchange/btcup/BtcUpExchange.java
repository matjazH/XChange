package org.knowm.xchange.btcup;

import si.mazi.rescu.SynchronizedValueFactory;
import org.knowm.xchange.utils.nonce.CurrentTimeNonceFactory;
import org.knowm.xchange.btcup.service.BtcUpPollingTradeService;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.btcup.service.BtcUpPollingAccountService;
import org.knowm.xchange.btcup.service.BtcUpPollingMarketDataService;

import java.io.InputStream;

/**
 * Created by VITTACH on 28/08/17.
 */
public class BtcUpExchange extends BaseExchange implements Exchange {

  private SynchronizedValueFactory<Long> nonceFactory = new CurrentTimeNonceFactory();

  @Override
  protected void initServices() {
    this.pollingMarketDataService = new BtcUpPollingMarketDataService(this);
    this.pollingAccountService = new BtcUpPollingAccountService(this);
    this.pollingTradeService = new BtcUpPollingTradeService(this);
  }

  @Override
  protected void loadMetaData(InputStream is) {
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://tapi.btc-up.com");
    exchangeSpecification.setExchangeName("btcUp");
    exchangeSpecification.setHost("btc-up.com");
    exchangeSpecification.setPort(80);

    return exchangeSpecification;
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    return nonceFactory;
  }
}
