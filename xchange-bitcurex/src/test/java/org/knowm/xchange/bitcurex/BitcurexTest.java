package org.knowm.xchange.bitcurex;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.service.polling.account.PollingAccountService;
import org.knowm.xchange.service.polling.marketdata.PollingMarketDataService;
import org.knowm.xchange.service.polling.trade.PollingTradeService;
import org.junit.Test;

import java.io.IOException;

/**
 * Tests the BitcurexAdapter class
 */
public class BitcurexTest {

  @Test
  public void testMarket() throws IOException {

    Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BitcurexExchange.class.getName());
    PollingMarketDataService pollingMarketDataService = exchange.getPollingMarketDataService();
    Ticker ticker = pollingMarketDataService.getTicker(CurrencyPair.BTC_PLN);
    System.out.println(ticker);
    OrderBook orderBook = pollingMarketDataService.getOrderBook(CurrencyPair.BTC_PLN);
    System.out.println(orderBook);
    Trades trades = pollingMarketDataService.getTrades(CurrencyPair.BTC_PLN);
    System.out.println(trades);

  }

/*
  todo fix
  @Test
  public void testAccount() throws IOException {

    ExchangeSpecification exSpec = new ExchangeSpecification(BitcurexExchange.class);
    exSpec.setSslUri("https://api.bitcurex.com");
    exSpec.setPort(80);

    exSpec.setApiKey("a300408430b0245ef736260a507e0cb7f16ff787be34f7194eee4cea48239ac77d7e1565f2ecca99c5a311703e36d29728b2714c616ad134a2768d9cd41431093778");
    exSpec.setSecretKey("965b38734b55904903e3f2e1589b99b7697a4546");


    Exchange exchangeAuth = ExchangeFactory.INSTANCE.createExchange(exSpec);

    PollingAccountService pollingAccountService = exchangeAuth.getPollingAccountService();
    AccountInfo accountInfo = pollingAccountService.getAccountInfo();
    System.out.println(accountInfo);

    PollingTradeService pollingTradeService = exchangeAuth.getPollingTradeService();
    OpenOrders openOrders = pollingTradeService.getOpenOrders();
    System.out.println(openOrders);


  }*/


}
