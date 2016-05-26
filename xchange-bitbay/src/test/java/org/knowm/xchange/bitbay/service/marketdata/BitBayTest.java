package org.knowm.xchange.bitbay.service.marketdata;

import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.bitbay.BitbayExchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.service.polling.account.PollingAccountService;
import org.knowm.xchange.service.polling.marketdata.PollingMarketDataService;
import org.knowm.xchange.service.polling.trade.PollingTradeService;
import org.knowm.xchange.service.polling.trade.params.DefaultTradeHistoryParamCurrencyPair;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 12/01/15
 * Time: 19:51
 */
public class BitBayTest {

  @Test
  public void test() throws IOException {
    ExchangeSpecification exSpec = new ExchangeSpecification(BitbayExchange.class);

    exSpec.setSslUri("https://bitbay.net");
    exSpec.setApiKey("a26a3b1c885d44d9cb98c2ce15b9b90b");
    exSpec.setSecretKey("b7ff4e320951de16cddc40025959b7b9");
    exSpec.setHost("bitbay.net");
    exSpec.setPort(80);

    Exchange exchangeAuth = ExchangeFactory.INSTANCE.createExchange(exSpec);

    PollingAccountService pollingAccountService = exchangeAuth.getPollingAccountService();
    AccountInfo accountInfo = pollingAccountService.getAccountInfo();
    System.out.println(accountInfo);


    PollingTradeService pollingTradeService = exchangeAuth.getPollingTradeService();

    OpenOrders openOrders = pollingTradeService.getOpenOrders();
    System.out.println(openOrders);

    UserTrades tradeHistory = pollingTradeService.getTradeHistory(new DefaultTradeHistoryParamCurrencyPair(CurrencyPair.BTC_PLN));
    System.out.println(tradeHistory);

//
//        String s = pollingTradeService.placeLimitOrder(new LimitOrder(Order.OrderType.ASK, new BigDecimal("0.014"), CurrencyPair.BTC_USD, null, new Date(), new BigDecimal("200")));
//        System.out.println(s);

  }


  @Test
  public void testMarket() throws IOException, ParseException {

    Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BitbayExchange.class.getName());
    PollingMarketDataService pollingMarketDataService = exchange.getPollingMarketDataService();

    Ticker ticker = pollingMarketDataService.getTicker(CurrencyPair.BTC_PLN);
    System.out.println(ticker);

    OrderBook orderBook = pollingMarketDataService.getOrderBook(CurrencyPair.BTC_PLN);
    System.out.println(orderBook);

    Trades trades = pollingMarketDataService.getTrades(CurrencyPair.BTC_PLN);
    System.out.println(trades);
  }

  @Test
  public void testTrades() throws IOException {


    Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BitbayExchange.class.getName());
    PollingMarketDataService pollingMarketDataService = exchange.getPollingMarketDataService();

    Trades trades = pollingMarketDataService.getTrades(CurrencyPair.BTC_PLN);
    print(trades);

//    while (trades.getTrades().size() > 0) {
//      Trade last = trades.getTrades().get(trades.getTrades().size() - 1);
//      trades = pollingMarketDataService.getTrades(CurrencyPair.BTC_PLN, Long.valueOf(last.getId()));
//      print(trades);
//    }

  }


  public void print(Trades trades) {

    List<Trade> tradesList = trades.getTrades();
    System.out.println("Loaded: " + trades.getlastID() +  " " + tradesList.size());

    if (tradesList != null && !tradesList.isEmpty()) {
      Trade first = tradesList.get(0);
      System.out.println("First: " + first.getTimestamp() + " " + first.getId());

      Trade last = tradesList.get(tradesList.size()-1);
      System.out.println("Last: " + last.getTimestamp() + " " + last.getId());

    }

  }
}
