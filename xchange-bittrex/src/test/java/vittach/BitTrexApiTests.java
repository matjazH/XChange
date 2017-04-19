package vittach;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;

import java.util.Arrays;
import java.util.Date;
import java.io.IOException;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.Collection;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bittrex.v1.BittrexExchange;
import org.knowm.xchange.bittrex.v1.dto.marketdata.BittrexCurrency;
import org.knowm.xchange.bittrex.v1.dto.trade.BittrexOpenOrder;
import org.knowm.xchange.bittrex.v1.service.BittrexMarketDataServiceRaw;
import org.knowm.xchange.bittrex.v1.service.BittrexTradeServiceRaw;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.service.marketdata.MarketDataService;

/**
 * Created by developer on 27/03/17.
 */
public class BitTrexApiTests {
  private String result = "\n";
  private String orderId = "9c4fc44d-aa0e-4a20-a828-81f49bd228cd";

  private Exchange anyExchangeInstance;
  private MarketDataService marketDataService;
  private ArrayList<CurrencyPair> currencyPairs;

  @Before
  public void initializing() {
    currencyPairs = new ArrayList<>();

    currencyPairs.add(CurrencyPair.LTC_BTC);
    currencyPairs.add(new CurrencyPair("BTC", "USDT"));
    currencyPairs.add(new CurrencyPair("DASH", "BTC"));

    ExchangeSpecification exchangesSpecifics = new ExchangeSpecification(BittrexExchange.class);

    exchangesSpecifics.setSslUri("https://bittrex.com/api/");
    exchangesSpecifics.setUserName("tabtrader");
    exchangesSpecifics.setApiKey("64d3acf0afa74eff8bbcadbc4fbb28f2");
    exchangesSpecifics.setSecretKey("614859b0e6324b0999233a1c00e70efe");

    anyExchangeInstance = ExchangeFactory.INSTANCE.createExchange(exchangesSpecifics);
    //anyExchangeInstance=ExchangeFactory.INSTANCE.createExchange(BittrexExchange.class.getName());
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
    CurrencyPair currencyPair = new CurrencyPair("BTC", "USDT");
    Order.OrderType orderType = Order.OrderType.ASK;
    BigDecimal tradableAmount = new BigDecimal(0.001);
    BigDecimal limitPrice = new BigDecimal(2000);

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
    BigDecimal tradableAmount = new BigDecimal(1);
    BigDecimal limitPrice = new BigDecimal(0.001);
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

  @Test
  public void getPublicCurrencies() throws IOException {
    BittrexCurrency[] currencies = ((BittrexMarketDataServiceRaw) marketDataService).getBittrexCurrencies();
    result += " - getPublicCurrencies \n" + Arrays.toString(currencies) + "\n";
  }

  @Test
  public void getOrderInfo() throws IOException {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    TradeService marketDataService = anyExchangeInstance.getTradeService();
    BittrexOpenOrder bittrexOrder = ((BittrexTradeServiceRaw) marketDataService).getBittrexOrder(orderId);
    result += " - getOrderInfo \n" + bittrexOrder + "\n";
  }

  @After
  public void printResults() {
    System.out.println(result);
  }
}
