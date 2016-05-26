package com.xeiam.xchange.vaultoro.service.marketdata;

import com.xeiam.xchange.vaultoro.VaultoroExchange;
import com.xeiam.xchange.vaultoro.dto.marketdata.VaultoroMarket;
import com.xeiam.xchange.vaultoro.service.polling.VaultoroMarketDataServiceRaw;
import org.junit.Test;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.ExchangeFactory;
import com.xeiam.xchange.service.polling.marketdata.PollingMarketDataService;

/**
 * @author timmolter
 */
public class TickerFetchIntegration {

  @Test
  public void tickerFetchTest() throws Exception {

    Exchange exchange = ExchangeFactory.INSTANCE.createExchange(VaultoroExchange.class.getName());
    PollingMarketDataService marketDataService = exchange.getPollingMarketDataService();

//    Ticker ticker = marketDataService.getTicker(new CurrencyPair("BTC", "USD"));
//    System.out.println(ticker.toString());

    VaultoroMarketDataServiceRaw vaultoroMarketDataServiceRaw = (VaultoroMarketDataServiceRaw)marketDataService;
    VaultoroMarket vaultoroMarkets = vaultoroMarketDataServiceRaw.getVaultoroMarkets();
    System.out.println(vaultoroMarkets);
  }

}
