package org.knowm.xchange.btce.v3;

import java.io.InputStream;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.btce.v3.dto.marketdata.BTCEExchangeInfo;
import org.knowm.xchange.btce.v3.dto.meta.BTCEMetaData;
import org.knowm.xchange.btce.v3.service.polling.BTCEAccountService;
import org.knowm.xchange.btce.v3.service.polling.BTCEMarketDataService;
import org.knowm.xchange.btce.v3.service.polling.BTCETradeService;
import org.knowm.xchange.utils.nonce.TimestampIncrementingNonceFactory;

import si.mazi.rescu.SynchronizedValueFactory;

public class BTCEExchange extends BaseExchange implements Exchange {

  private SynchronizedValueFactory<Long> nonceFactory = new TimestampIncrementingNonceFactory();
  private BTCEMetaData btceMetaData;
  private BTCEExchangeInfo btceExchangeInfo;

  @Override
  protected void initServices() {

    this.pollingMarketDataService = new BTCEMarketDataService(this);
    this.pollingAccountService = new BTCEAccountService(this);
    this.pollingTradeService = new BTCETradeService(this);
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://btc-e.nz");
    exchangeSpecification.setHost("btc-e.nz");
    exchangeSpecification.setPort(80);
    exchangeSpecification.setExchangeName("BTC-e");
    exchangeSpecification.setExchangeDescription("BTC-e is a Bitcoin exchange registered in Russia.");

    return exchangeSpecification;
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {

    return nonceFactory;
  }

  @Override
  protected void loadMetaData(InputStream is) {
    btceMetaData = loadMetaData(is, BTCEMetaData.class);
    metaData = BTCEAdapters.toMetaData(null, btceMetaData);
  }

  @Override
  public void remoteInit() {
    try {
      BTCEMarketDataService marketDataService = (BTCEMarketDataService) pollingMarketDataService;
      btceExchangeInfo = marketDataService.getBTCEInfo();
      metaData = BTCEAdapters.toMetaData(btceExchangeInfo, btceMetaData);
    } catch (Exception e) {
      logger.warn("An exception occurred while loading the metadata file from the file. This may lead to unexpected results.", e);
    }
  }

  public BTCEMetaData getBtceMetaData() {
    return btceMetaData;
  }

  public BTCEExchangeInfo getBtceExchangeInfo() {
    return btceExchangeInfo;
  }
}
