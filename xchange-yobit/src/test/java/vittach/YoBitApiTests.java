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
import org.knowm.xchange.bitmex.YoBitExchange;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.service.polling.trade.PollingTradeService;
import org.knowm.xchange.bitmex.service.polling.YoBitMarketDataService;
import org.knowm.xchange.service.polling.account.PollingAccountService;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.bitmex.dto.marketdata.YoBitInfo;
import org.knowm.xchange.service.polling.marketdata.PollingMarketDataService;

/**
 * Created by developer on 27/03/17.
 */
public class YoBitApiTests {
  private String result = "\n";
  private String orderId = "250003129656121";
  private Exchange anyExchangeInstance;
  private ArrayList<CurrencyPair> currencyPairs;
  private PollingMarketDataService pollingMarketDataService;

  @Before
  public void initializing() {
    currencyPairs = new ArrayList<>();

    currencyPairs.add(CurrencyPair.LTC_BTC);
    currencyPairs.add(CurrencyPair.ETH_BTC);
    currencyPairs.add(CurrencyPair.BTC_RUR);
    currencyPairs.add(new CurrencyPair("DASH", "BTC"));

    ExchangeSpecification exchangesSpecifics = new ExchangeSpecification(YoBitExchange.class);

    exchangesSpecifics.setSslUri("https://bitmex.net/");
    exchangesSpecifics.setUserName("tabtrader");
    exchangesSpecifics.setApiKey("82E6CA392077DF1268064B358987DE83");
    exchangesSpecifics.setSecretKey("bcdfa507fc07c60dffdb381013223f3f");

    anyExchangeInstance = ExchangeFactory.INSTANCE.createExchange(exchangesSpecifics);
    //anyExchangeInstance= ExchangeFactory.INSTANCE.createExchange(YoBitExchange.class.getName());
    pollingMarketDataService = anyExchangeInstance.getPollingMarketDataService();
  }

  @Test
  public void getActiveOrders() throws IOException {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    PollingTradeService pollingMarketDataService = anyExchangeInstance.getPollingTradeService();
    OpenOrders openOrders = pollingMarketDataService.getOpenOrders();
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
    PollingTradeService pollingMarketDataService = anyExchangeInstance.getPollingTradeService();
    boolean openOrders = pollingMarketDataService.cancelOrder(orderId);
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
    PollingTradeService pollingMarketDataService = anyExchangeInstance.getPollingTradeService();
    CurrencyPair currencyPair = CurrencyPair.BTC_RUR;
    Order.OrderType orderType = Order.OrderType.ASK;
    BigDecimal tradableAmount = new BigDecimal(0.001);
    BigDecimal limitPrice = new BigDecimal(75000);
    Date date = new Date();
    LimitOrder limitOrder;
    limitOrder = new LimitOrder(orderType, tradableAmount, currencyPair, "", date, limitPrice);

    orderId = pollingMarketDataService.placeLimitOrder(limitOrder);
    result += " - placeLimitOrder \n" + orderId + "\n";
  }

  @Test
  public void getDepositAddress() throws IOException {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    PollingAccountService pollingAccountService = anyExchangeInstance.getPollingAccountService();
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
    PollingAccountService pollingAccountService = anyExchangeInstance.getPollingAccountService();
    AccountInfo accountInfo = pollingAccountService.getAccountInfo();
    result += " - getBalanceInfo \n" + accountInfo + "\n";
  }

  @Test
  @Ignore
  public void getOrderInfo() throws IOException {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    PollingTradeService pollingMarketDataService = anyExchangeInstance.getPollingTradeService();
    Collection<Order> order = pollingMarketDataService.getOrder(orderId);
    result += " - getOrderInfo \n" + order + "\n";
  }

  @Test
  public void getPublicTicker() throws IOException {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    for (CurrencyPair currencyPair : currencyPairs) {
      Ticker ticker = pollingMarketDataService.getTicker(currencyPair);
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
      Trades tradeHistory = pollingMarketDataService.getTrades(currencyPair, id);
      result += currencyPair.toString() + " - getPublicTrades \n" + tradeHistory.toString() + "\n";
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
      OrderBook orderBook = pollingMarketDataService.getOrderBook(currencyPair);
      result += currencyPair.toString() + " - getPublicOrders \n" + orderBook.toString() + "\n";
    }
  }

  @Test
  public void getPublicInfo() throws IOException {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    YoBitInfo allYoBitInfo = ((YoBitMarketDataService) pollingMarketDataService).getAllYoBitInfo();
    result += " - getPublicInfo \n" + allYoBitInfo.toString() + "\n";
  }

  @After
  public void printResults() {
    System.out.println(result);
  }
}
