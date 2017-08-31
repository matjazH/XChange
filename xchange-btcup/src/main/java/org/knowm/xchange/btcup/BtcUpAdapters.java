package org.knowm.xchange.btcup;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;

import org.knowm.xchange.btcup.dto.trade.BtcUpOrder;
import org.knowm.xchange.btcup.dto.account.BtcUpAccount;
import org.knowm.xchange.btcup.dto.marketdata.BtcUpPrice;
import org.knowm.xchange.btcup.dto.marketdata.BtcUpOrderBook;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.dto.trade.UserTrade;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.btcup.dto.trade.BtcUpTrade;

/**
 * Created by VITTACH on 28/08/17.
 */
public class BtcUpAdapters {
  private BtcUpAdapters() {}

  public static Ticker adaptTicker(BtcUpPrice ticker, CurrencyPair pair) {

    BigDecimal last = ticker.getLast();
    BigDecimal high = ticker.getHigh();
    BigDecimal low = ticker.getLow();
    BigDecimal volume = ticker.getDaily_volume();
    BigDecimal bid = ticker.getBid();
    BigDecimal ask = ticker.getAsk();

    return new Ticker.Builder().currencyPair(pair).last(last).bid(bid).ask(ask).high(high).low(low).volume(volume).build();
  }

  public static Long adaptTicksToMilliseconds(Long ticks) {

    Long ticksPerMillisecond = 10000L;
    Long epochTicks = 621355968000000000L;
    Long ticksSinceEpoch = ticks - epochTicks;
    return ticksSinceEpoch / ticksPerMillisecond;
  }

  public static Trade adaptTrade(BtcUpTrade trade, CurrencyPair currencyPair) {

    BigDecimal amount = trade.getQty();
    BigDecimal price = trade.getPrice();

    Date date = new Date(adaptTicksToMilliseconds(trade.getTicks()));
    Order.OrderType type = trade.getSide() == 0 ? Order.OrderType.BID : Order.OrderType.ASK;
    return new Trade(type, amount, currencyPair, price, date, String.valueOf(trade.getId()));
  }

  public static Trades adaptTrades(BtcUpTrade[] btcupTrades, CurrencyPair currencyPair) {

    List<Trade> tradesList = new ArrayList<Trade>();
    long lastTradeId = 0;
    for (BtcUpTrade trade : btcupTrades) {
      long tradeId = trade.getId();
      if (tradeId > lastTradeId) {
        lastTradeId = tradeId;
      }
      tradesList.add(0, adaptTrade(trade, currencyPair));
    }
    return new Trades(tradesList, lastTradeId, Trades.TradeSortType.SortByID);
  }

  public static LimitOrder createOrder(CurrencyPair currencyPair, BigDecimal[] priceAndAmount, Order.OrderType orderType) {
    return new LimitOrder(orderType, priceAndAmount[1], currencyPair, "", null, priceAndAmount[0]);
  }

  public static List<LimitOrder> createOrders(CurrencyPair currencyPair, Order.OrderType orderType, List<BigDecimal[]> orders) {

    List<LimitOrder> limitOrders = new ArrayList<LimitOrder>();
    for (BigDecimal[] ask : orders) {
      limitOrders.add(createOrder(currencyPair, ask, orderType));
    }
    return limitOrders;
  }

  public static OrderBook adaptOrderBook(BtcUpOrderBook btcupOrderBook, CurrencyPair currencyPair) {

    List<LimitOrder> asks = createOrders(currencyPair, Order.OrderType.ASK, btcupOrderBook.getAsks());
    List<LimitOrder> bids = createOrders(currencyPair, Order.OrderType.BID, btcupOrderBook.getBids());
    Date date = new Date();
    return new OrderBook(date, asks, bids);
  }

  public static Wallet adaptWallet(BtcUpAccount account) {

    ArrayList<Balance> balances = new ArrayList<>();

    balances.add(new Balance(new Currency("LTC"), account.getLtc().getAvailable().add(account.getLtc().getBlocked()),
        account.getLtc().getAvailable(), account.getLtc().getBlocked(),
        BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

    balances.add(new Balance(new Currency("BTC"), account.getBtc().getAvailable().add(account.getBtc().getBlocked()),
        account.getBtc().getAvailable(), account.getBtc().getBlocked(),
        BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

    balances.add(new Balance(new Currency("ETH"), account.getEth().getAvailable().add(account.getEth().getBlocked()),
        account.getEth().getAvailable(), account.getEth().getBlocked(),
        BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

    balances.add(new Balance(new Currency("USD"), account.getUsd().getAvailable().add(account.getUsd().getBlocked()),
        account.getUsd().getAvailable(), account.getUsd().getBlocked(),
        BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

    balances.add(new Balance(new Currency("ZEC"), account.getZec().getAvailable().add(account.getZec().getBlocked()),
        account.getZec().getAvailable(), account.getZec().getBlocked(),
        BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

    balances.add(new Balance(new Currency("EMC"), account.getEmc().getAvailable().add(account.getEmc().getBlocked()),
        account.getEmc().getAvailable(), account.getEmc().getBlocked(),
        BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

    balances.add(new Balance(new Currency("SNM"), account.getSnm().getAvailable().add(account.getSnm().getBlocked()),
        account.getSnm().getAvailable(), account.getSnm().getBlocked(),
        BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

    balances.add(new Balance(new Currency("SNT"), account.getSnt().getAvailable().add(account.getSnt().getBlocked()),
        account.getSnt().getAvailable(), account.getSnt().getBlocked(),
        BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

    return new Wallet(balances);
  }

  public static LimitOrder adaptOpenOrder(BtcUpOrder btcUpOpenOrder) {

    return new LimitOrder(btcUpOpenOrder.getSide() == 1 ? Order.OrderType.ASK : Order.OrderType.BID,
        btcUpOpenOrder.getCachOrderQty(), new CurrencyPair(btcUpOpenOrder.getPair().replace("_", "/")),
        String.valueOf(btcUpOpenOrder.getId()), new Date(adaptTicksToMilliseconds(btcUpOpenOrder.getTicks())),
        btcUpOpenOrder.getPrice());
  }

  public static List<LimitOrder> adaptOpenOrders(List<BtcUpOrder> btcUpOpenOrders) {

    List<LimitOrder> openOrders = new ArrayList<>();
    for (BtcUpOrder order : btcUpOpenOrders) {
      openOrders.add(adaptOpenOrder(order));
    }

    return openOrders;
  }


  public static List<UserTrade> adaptUserTrades(List<BtcUpTrade> btcUpTradeHistory) {

    List<UserTrade> userTrades = new ArrayList<>();
    for (BtcUpTrade btcUpTrade : btcUpTradeHistory) {
      userTrades.add(adaptUserTrade(btcUpTrade));
    }

    return userTrades;
  }

  public static UserTrade adaptUserTrade(BtcUpTrade trade) {

    CurrencyPair currencyPair = new CurrencyPair(trade.getPair().replace("_", "/"));

    Date date = new Date(adaptTicksToMilliseconds(trade.getTicks()));
    String orderId = String.valueOf(trade.getOrderId());
    Order.OrderType orderType = trade.getSide() == 0 ? Order.OrderType.BID : Order.OrderType.ASK;

    return new UserTrade(orderType, trade.getQty(), currencyPair, trade.getPrice(), date, orderId, orderId,
        null, currencyPair.counter);
  }
}
