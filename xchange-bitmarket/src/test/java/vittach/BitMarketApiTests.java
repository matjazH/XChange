package vittach;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;

import java.util.Date;
import java.io.IOException;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.Collection;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.bitmarket.BitMarketExchange;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.service.marketdata.MarketDataService;

/**
 * Created by developer on 27/03/17.
 */
public class BitMarketApiTests {
  private String result = "\n";
  private String orderId = "92497774";

  private Exchange anyExchangeInstance;
  private MarketDataService marketDataService;
  private ArrayList<CurrencyPair> currencyPairs;

  @Before
  public void initializing() {
    currencyPairs = new ArrayList<>();

    currencyPairs.add(CurrencyPair.LTC_BTC);
    currencyPairs.add(CurrencyPair.BTC_PLN);
    currencyPairs.add(CurrencyPair.BTC_EUR);
    currencyPairs.add(new CurrencyPair("LTC", "PLN"));

    ExchangeSpecification exchangesSpecifics = new ExchangeSpecification(BitMarketExchange.class);

    exchangesSpecifics.setSslUri("https://www.bitmarket.pl/");
    exchangesSpecifics.setUserName("tabtrader");
    exchangesSpecifics.setApiKey("ec7083ca6746d3023cb94625cc1ba435");
    exchangesSpecifics.setSecretKey("c7094e679480fda04d0706e7580d147b");

    anyExchangeInstance = ExchangeFactory.INSTANCE.createExchange(exchangesSpecifics);
    //anyExchangeInstance=ExchangeFactory.INSTANCE.createExchange(BitMarketExchange.class.getName());
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
  public void placeSellLimitOrder() throws IOException {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    TradeService marketDataService = anyExchangeInstance.getTradeService();
    CurrencyPair currencyPair = CurrencyPair.BTC_PLN;
    Order.OrderType orderType = Order.OrderType.ASK;
    BigDecimal tradableAmount = new BigDecimal(0.01);
    BigDecimal limitPrice = new BigDecimal(10000);

    Date date = new Date();
    LimitOrder limitOrder;
    limitOrder = new LimitOrder(orderType, tradableAmount, currencyPair, "", date, limitPrice);

    orderId = marketDataService.placeLimitOrder(limitOrder);
    result += " - placeLimitOrder \n" + orderId + "\n";
  }

  @Test
  @Ignore
  public void placeBuyLimitOrder() throws IOException {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    TradeService marketDataService = anyExchangeInstance.getTradeService();
    CurrencyPair currencyPair = new CurrencyPair("LTC", "BTC");
    Order.OrderType orderType = Order.OrderType.BID;
    BigDecimal tradableAmount = new BigDecimal(0.1);
    BigDecimal limitPrice = new BigDecimal(0.0001);
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
