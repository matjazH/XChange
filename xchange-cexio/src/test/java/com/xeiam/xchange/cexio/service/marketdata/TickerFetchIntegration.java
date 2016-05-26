package com.xeiam.xchange.cexio.service.marketdata;

import static org.fest.assertions.api.Assertions.assertThat;

import com.xeiam.xchange.cexio.dto.marketdata.CexIOCurrency;
import com.xeiam.xchange.cexio.service.polling.CexIOMarketDataServiceRaw;
import org.junit.Test;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.ExchangeFactory;
import com.xeiam.xchange.cexio.CexIOExchange;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.dto.marketdata.Ticker;
import com.xeiam.xchange.service.polling.marketdata.PollingMarketDataService;

import java.util.List;

/**
 * @author timmolter
 */
public class TickerFetchIntegration {

  @Test
  public void tickerFetchTest() throws Exception {

    Exchange exchange = ExchangeFactory.INSTANCE.createExchange(CexIOExchange.class.getName());
    PollingMarketDataService marketDataService = exchange.getPollingMarketDataService();
    Ticker ticker = marketDataService.getTicker(new CurrencyPair("BTC", "USD"));
    System.out.println(ticker.toString());
    assertThat(ticker).isNotNull();

    CexIOMarketDataServiceRaw cexIOMarketDataServiceRaw = (CexIOMarketDataServiceRaw)marketDataService;
    List<CexIOCurrency> cexIOCurrencies = cexIOMarketDataServiceRaw.getCexIOCurrencies();
    for (CexIOCurrency cexIOCurrency : cexIOCurrencies) {
      System.out.println("new CurrencyPair(\""+ cexIOCurrency.getSymbol1() + "\", \"" + cexIOCurrency.getSymbol2() + "\"),");
    }

  }

}
