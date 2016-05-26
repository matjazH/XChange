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
    exSpec.setExchangeSpecificParametersItem(QuoineExchange.KEY_USER_ID, "137");

    exSpec.setSecretKey("VB4iFYqW1WGxIjw8hWwqZzTZbgWuSy2Crw8bda/VoMz4IN6ovnCezlQbgoR42wJ1jGAK4G0dDtXuxlpezh1Bng==");

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

//      LimitOrder limitOrder = new LimitOrder(Order.OrderType.ASK, new BigDecimal("0.01"), CurrencyPair.BTC_USD, null, new Date(), new BigDecimal("400"));
//      String s = pollingTradeService.placeLimitOrder(limitOrder);
//      System.out.println(s);
//
//
//      OpenOrders openOrders = pollingTradeService.getOpenOrders();
//      System.out.println(openOrders);
//
//      for (LimitOrder limitOrder : openOrders.getOpenOrders()) {
//        pollingTradeService.cancelOrder(limitOrder.getId());
//      }
//
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

/*
    QuoineOrderType type1 = QuoineOrderType.market_with_range;
    QuoineOrderType type2 = QuoineOrderType.margin_market_with_range;


    QuoineOrderType type11 = QuoineOrderType.getOrderType(type1.getValue());
    QuoineOrderType type22 = QuoineOrderType.getOrderType(type2.getValue());

    System.out.println(type1 + " " + type1.getValue() + " " + type1.name());
    System.out.println(type11 + " " + type11.getValue() + " " + type11.name());
    System.out.println(type2 + " " + type2.getValue() + " " + type2.name());
    System.out.println(type22 + " " + type22.getValue() + " " + type22.name());

    System.out.println(QuoineOrderType.valueOf(type2.getValue()));*/

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
