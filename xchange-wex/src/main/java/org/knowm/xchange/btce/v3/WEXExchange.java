package org.knowm.xchange.btce.v3;

import java.io.InputStream;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.btce.v3.dto.marketdata.WEXExchangeInfo;
import org.knowm.xchange.btce.v3.dto.meta.WEXMetaData;
import org.knowm.xchange.btce.v3.service.polling.WEXAccountService;
import org.knowm.xchange.btce.v3.service.polling.WEXMarketDataService;
import org.knowm.xchange.btce.v3.service.polling.WEXTradeService;
import org.knowm.xchange.utils.nonce.TimestampIncrementingNonceFactory;

import si.mazi.rescu.SynchronizedValueFactory;

public class WEXExchange extends BaseExchange implements Exchange {

  private SynchronizedValueFactory<Long> nonceFactory = new TimestampIncrementingNonceFactory();
  private WEXMetaData WEXMetaData;
  private WEXExchangeInfo WEXExchangeInfo;

  @Override
  protected void initServices() {

    this.pollingMarketDataService = new WEXMarketDataService(this);
    this.pollingAccountService = new WEXAccountService(this);
    this.pollingTradeService = new WEXTradeService(this);
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://wex.nz");
    exchangeSpecification.setHost("btc-e.com");
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
    WEXMetaData = loadMetaData(is, WEXMetaData.class);
    metaData = WEXAdapters.toMetaData(null, WEXMetaData);
  }

  @Override
  public void remoteInit() {
    try {
      WEXMarketDataService marketDataService = (WEXMarketDataService) pollingMarketDataService;
      WEXExchangeInfo = marketDataService.getBTCEInfo();
      metaData = WEXAdapters.toMetaData(WEXExchangeInfo, WEXMetaData);
    } catch (Exception e) {
      logger.warn("An exception occurred while loading the metadata file from the file. This may lead to unexpected results.", e);
    }
  }

  public WEXMetaData getWEXMetaData() {
    return WEXMetaData;
  }

  public WEXExchangeInfo getWEXExchangeInfo() {
    return WEXExchangeInfo;
  }
}
