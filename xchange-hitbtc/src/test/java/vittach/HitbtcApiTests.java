package vittach;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.hitbtc.HitbtcExchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.math.BigDecimal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by developer on 27/03/17.
 */
public class HitbtcApiTests {
  private String result = "\n";
  private String orderId = "10346570";

  private Exchange anyExchangeInstance;
  private MarketDataService marketDataService;
  private ArrayList<CurrencyPair> currencyPairs;

  @Before
  public void initializing() {
    currencyPairs = new ArrayList<>();

    currencyPairs.add(new CurrencyPair("LTC", "BTC"));
    currencyPairs.add(new CurrencyPair("ZEC", "BTC"));
    currencyPairs.add(new CurrencyPair("ETH", "BTC"));

    ExchangeSpecification exchangesSpecifics = new ExchangeSpecification(HitbtcExchange.class);

    exchangesSpecifics.setSslUri("https://api.hitbtc.com");
    exchangesSpecifics.setUserName("tabtrader");
    exchangesSpecifics.setApiKey("mxtgIwiinGjsvRugt8S9jrvkfI");
    exchangesSpecifics.setSecretKey("G0APxNToz0UcKauFfDgIEfl2mts");

    //anyExchangeInstance = ExchangeFactory.INSTANCE.createExchange(exchangesSpecifics);
    anyExchangeInstance=ExchangeFactory.INSTANCE.createExchange(HitbtcExchange.class.getName());
    marketDataService = anyExchangeInstance.getMarketDataService();
  }

  @Test
  @Ignore
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
  public void placeSelLimitOrder() throws IOException {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    TradeService marketDataService = anyExchangeInstance.getTradeService();
    CurrencyPair currencyPair = new CurrencyPair("BTC","EUR");
    Order.OrderType orderType = Order.OrderType.ASK;
    BigDecimal tradableAmount = new BigDecimal("0.001");
    BigDecimal limitPrice = new BigDecimal("10000");

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
    CurrencyPair currencyPair = new CurrencyPair("GHS","BTC");
    Order.OrderType orderType = Order.OrderType.BID;
    BigDecimal tradableAmount = new BigDecimal("1");
    BigDecimal limitPrice = new BigDecimal("0.00001");

    Date date = new Date();
    LimitOrder limitOrder;
    limitOrder = new LimitOrder(orderType, tradableAmount, currencyPair, "", date, limitPrice);

    orderId = marketDataService.placeLimitOrder(limitOrder);
    result += " - placeLimitOrder \n" + orderId + "\n";
  }

  @Test
  @Ignore
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
      Trades tradeHistory = marketDataService.getTrades(currencyPair);
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

  /*
  @Test
  @Ignore
  public void getOrderInfo() throws IOException {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    TradeService marketDataService = anyExchangeInstance.getTradeService();
    GeminiOrderStatusResponse order = ((GeminiTradeServiceRaw) marketDataService).getGeminiOrderStatus(orderId);
    result += " - getOrderInfo \n" + order + "\n";
  }

  @Test
  public void getSymbolDetails() throws IOException {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Collection<String> geminiSymbols = ((GeminiMarketDataServiceRaw) marketDataService).getGeminiSymbols();
    result += " - getSymbolDetail \n";
    for(String obj: geminiSymbols)
      result += obj.toString() + "\n";
  }

  @Test
  @Ignore
  public void getTradeHistory() throws IOException {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    TradeService marketDataService = anyExchangeInstance.getTradeService();
    result += " - getTradeHistory \n";
    for (final CurrencyPair currencyPair : currencyPairs) {
      TradeHistoryParamCurrencyPair tradeHistoryParamCurrencyPair = new TradeHistoryParamCurrencyPair() {
        @Override
        public void setCurrencyPair(CurrencyPair pair) {

        }

        @Override
        public CurrencyPair getCurrencyPair() {
          return currencyPair;
        }
      };
      UserTrades tradeHistory = marketDataService.getTradeHistory(tradeHistoryParamCurrencyPair);
      result += tradeHistory;
    }
    result += "\n";
  }
  */

  @After
  public void printResults() {
    System.out.println(result);
  }
}
