package vittach;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.bl3p.Bl3p;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.bl3p.Bl3pExchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.math.BigDecimal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by developer on 27/03/17.
 */
public class Bl3PApiTests {
  private String result = "\n";
  private String orderId = "10346570";

  private Exchange anyExchangeInstance;
  private MarketDataService marketDataService;
  private ArrayList<CurrencyPair> currencyPairs;

  @Before
  public void initializing() {
    currencyPairs = new ArrayList<>();

    currencyPairs.add(CurrencyPair.BTC_EUR);

    ExchangeSpecification exchangesSpecifics = new ExchangeSpecification(Bl3pExchange.class);

    exchangesSpecifics.setSslUri("https://api.bl3p.eu");
    exchangesSpecifics.setUserName("tabtrader");
    exchangesSpecifics.setApiKey("1ff4b751-7f40-4874-b792-82b18d01d774");
    exchangesSpecifics.setSecretKey("0oVIrDbrPOCrZn7Xv9Yw9eX3aOskAZw7EsLyNW+3Ghipr31VEHFDFZvpo5FXJAKAmOuNeF5JT5OSttU+aGc9eg==");

    anyExchangeInstance = ExchangeFactory.INSTANCE.createExchange(exchangesSpecifics);
    //anyExchangeInstance=ExchangeFactory.INSTANCE.createExchange(Bl3pExchange.class.getName());
    marketDataService = anyExchangeInstance.getMarketDataService();
  }

  @Test
  public void getActiveOrders() throws IOException {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    TradeService marketDataService = anyExchangeInstance.getTradeService();
    OpenOrders openOrders = marketDataService.getOpenOrders();
    result += " - getActiveOrders \n" + openOrders + "\n";
  }

  @Test
  @Ignore
  public void cancelOrder() throws IOException {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    TradeService marketDataService = anyExchangeInstance.getTradeService();
    boolean openOrders = marketDataService.cancelOrder(orderId);
    result += " - cancelOrder \n" + openOrders + "\n";
  }

  @Test
  @Ignore
  public void placeLimitOrder() throws IOException {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    TradeService marketDataService = anyExchangeInstance.getTradeService();
    /*
    CurrencyPair currencyPair = new CurrencyPair("BTC","EUR");
    Order.OrderType orderType = Order.OrderType.BID;
    BigDecimal tradableAmount = new BigDecimal(0.0005);
    BigDecimal limitPrice = new BigDecimal(0.01);
    */

    CurrencyPair currencyPair = new CurrencyPair("BTC","EUR");
    Order.OrderType orderType = Order.OrderType.ASK;
    BigDecimal tradableAmount = new BigDecimal(0.001);
    BigDecimal limitPrice = new BigDecimal(10000);

    Date date = new Date();
    LimitOrder limitOrder;
    limitOrder = new LimitOrder(orderType, tradableAmount, currencyPair, "", date, limitPrice);

    orderId = marketDataService.placeLimitOrder(limitOrder);
    result += " - placeLimitOrder \n" + orderId + "\n";
  }

  @Test
  public void getDepositAddress() throws IOException {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    AccountService pollingAccountService = anyExchangeInstance.getAccountService();
    String depositAddress = pollingAccountService.requestDepositAddress(Currency.BTC);
    result += " - getDepositAddress \n" + depositAddress + "\n";
  }

  @Test
  public void getBalanceInfo() throws IOException {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    AccountService pollingAccountService = anyExchangeInstance.getAccountService();
    AccountInfo accountInfo = pollingAccountService.getAccountInfo();
    result += " - getBalanceInfo \n" + accountInfo + "\n";
  }

  @Test
  public void getPublicTicker() throws IOException {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    for (CurrencyPair currencyPair : currencyPairs) {
      Ticker ticker = marketDataService.getTicker(currencyPair);
      result += currencyPair.toString() + " - getPublicTicker \n" + ticker.toString() + "\n";
    }
  }

  @Test
  public void getPublicTrades() throws IOException {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    int tid = 5432;
    Long id = new Long(tid);
    for (CurrencyPair currencyPair : currencyPairs) {
      Trades tradeHistory = marketDataService.getTrades(currencyPair, id);
      result += currencyPair.toString() + " - getPubTrades \n" + tradeHistory.toString() + "\n";
    }
  }

  @Test
  public void getPublicOrders() throws IOException {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    for (CurrencyPair currencyPair : currencyPairs) {
      OrderBook orderBook = marketDataService.getOrderBook(currencyPair);
      result += currencyPair.toString() + " - getPublicOrders \n" + orderBook.toString() + "\n";
    }
  }

  @After
  public void printResults() {
    System.out.println(result);
  }
}
