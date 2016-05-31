package org.knowm.xchange.quoine.dto;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.quoine.QuoineExchange;
import org.knowm.xchange.service.polling.account.PollingAccountService;
import org.knowm.xchange.service.polling.marketdata.PollingMarketDataService;
import org.knowm.xchange.service.polling.trade.PollingTradeService;
import org.knowm.xchange.service.polling.trade.params.DefaultTradeHistoryParamCurrencyPair;
import org.junit.Test;

import java.io.IOException;

public class QuoineTest {



  @Test
  public void testAccount() throws IOException {
    ExchangeSpecification exSpec = new ExchangeSpecification(QuoineExchange.class);

    exSpec.setSslUri("https://api.quoine.com");

    Exchange exchangeAuth = ExchangeFactory.INSTANCE.createExchange(exSpec);

    try {
      PollingAccountService pollingAccountService = exchangeAuth.getPollingAccountService();
      AccountInfo accountInfo = pollingAccountService.getAccountInfo();
      System.out.println(accountInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }


    try {
      PollingTradeService pollingTradeService = exchangeAuth.getPollingTradeService();

      UserTrades tradeHistory = pollingTradeService.getTradeHistory(new DefaultTradeHistoryParamCurrencyPair(CurrencyPair.BTC_USD));
      System.out.println(tradeHistory);

      tradeHistory = pollingTradeService.getTradeHistory(new DefaultTradeHistoryParamCurrencyPair(CurrencyPair.BTC_JPY));
      System.out.println(tradeHistory);

      OpenOrders openOrders = pollingTradeService.getOpenOrders();
      System.out.println(openOrders);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }


  @Test
  public void test() throws IOException {

    Exchange exchange = ExchangeFactory.INSTANCE.createExchange(QuoineExchange.class.getName());
    PollingMarketDataService pollingMarketDataService = exchange.getPollingMarketDataService();

    Ticker ticker = pollingMarketDataService.getTicker(CurrencyPair.BTC_USD);
    System.out.println(ticker);

    Trades trades = pollingMarketDataService.getTrades(CurrencyPair.BTC_USD);
    System.out.println(trades);

    OrderBook orderBook = pollingMarketDataService.getOrderBook(CurrencyPair.BTC_USD);
    System.out.println(orderBook);
/*
    QuoineMarketDataService quoineMarketDataService = (QuoineMarketDataService) pollingMarketDataService;
    QuoineProduct[] quoineProducts = quoineMarketDataService.getQuoineProducts();

    for (QuoineProduct quoineProduct : quoineProducts) {
      System.out.println(quoineProduct);
    }
*/

  }
}
