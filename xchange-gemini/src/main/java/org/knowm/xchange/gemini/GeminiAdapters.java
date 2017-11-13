package org.knowm.xchange.gemini;

import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrade;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.gemini.dto.account.GeminiBalance;
import org.knowm.xchange.gemini.dto.marketdata.GeminiOrderBook;
import org.knowm.xchange.gemini.dto.marketdata.GeminiTicker;
import org.knowm.xchange.gemini.dto.marketdata.GeminiTrade;
import org.knowm.xchange.gemini.dto.trade.GeminiOrder;
import org.knowm.xchange.gemini.dto.trade.GeminiUserTrade;
import org.knowm.xchange.utils.DateUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tabtrader on 04/10/2016.
 */
public class GeminiAdapters {

  private GeminiAdapters() {
  }

  public static String adaptPair(CurrencyPair currencyPair) {
    return "" + currencyPair.base + currencyPair.counter;
  }

  public static CurrencyPair adaptPair(String geminiSymbol) {
    String base = geminiSymbol.substring(0, 3);
    String counter = geminiSymbol.substring(3, 6);
    return new CurrencyPair(base, counter);
  }

  public static Ticker adaptTicker(GeminiTicker geminiTicker, CurrencyPair currencyPair) {

    BigDecimal last = geminiTicker.getLast();
    BigDecimal bid = geminiTicker.getBid();
    BigDecimal ask = geminiTicker.getAsk();
    BigDecimal volume = geminiTicker.getVolume().get(currencyPair.base.toString());

    return new Ticker.Builder()
        .currencyPair(currencyPair)
        .last(last).bid(bid).ask(ask).volume(volume)
        .build();
  }

  public static OrderBook adaptOrderBook(GeminiOrderBook geminiOrderBook, CurrencyPair currencyPair) {

    List<LimitOrder> asks = createOrders(currencyPair, Order.OrderType.ASK, geminiOrderBook.getAsks());
    List<LimitOrder> bids = createOrders(currencyPair, Order.OrderType.BID, geminiOrderBook.getBids());
    return new OrderBook(null, asks, bids);
  }

  public static List<LimitOrder> createOrders(CurrencyPair currencyPair, Order.OrderType orderType,
                                              List<GeminiOrderBook.Order> orders) {

    List<LimitOrder> limitOrders = new ArrayList<>();
    for (GeminiOrderBook.Order order : orders) {
      LimitOrder limitOrder = new LimitOrder.Builder(orderType, currencyPair)
          .limitPrice(order.getPrice())
          .tradableAmount(order.getAmount())
          .build();
      limitOrders.add(limitOrder);
    }
    return limitOrders;
  }

  public static Trades adaptTrades(List<GeminiTrade> geminiTrades, CurrencyPair currencyPair) {

    List<Trade> tradesList = new ArrayList<Trade>();
    long lastTradeId = 0;
    for (GeminiTrade geminiTrade : geminiTrades) {
      if (geminiTrade.getTid() > lastTradeId) {
        lastTradeId = geminiTrade.getTid();
      }
      tradesList.add(0, adaptTrade(geminiTrade, currencyPair));
    }
    return new Trades(tradesList, lastTradeId, Trades.TradeSortType.SortByTimestamp);
  }

  public static Trade adaptTrade(GeminiTrade geminiTrade, CurrencyPair currencyPair) {

    Order.OrderType orderType = null;
    switch (geminiTrade.getType()) {
      case buy:
        orderType = Order.OrderType.BID;
        break;
      case sell:
        orderType = Order.OrderType.ASK;
        break;
    }

    BigDecimal amount = geminiTrade.getAmount();
    BigDecimal price = geminiTrade.getPrice();
    Date date = DateUtils.fromMillisUtc(geminiTrade.getTimestampms());

    final String tradeId = String.valueOf(geminiTrade.getTid());
    return new Trade(orderType, amount, currencyPair, price, date, tradeId);
  }

  public static Wallet adaptWallet(List<GeminiBalance> response) {

    List<Balance> balances = new ArrayList<>();
    for (GeminiBalance geminiBalance : response) {
      Currency currency = Currency.getInstance(geminiBalance.getCurrency());
      balances.add(new Balance(currency, geminiBalance.getAmount(), geminiBalance.getAvailable()));
    }
    return new Wallet(balances);
  }

  public static OpenOrders adaptOrders(List<GeminiOrder> geminiOrders) {

    List<LimitOrder> limitOrders = new ArrayList<>();

    for (GeminiOrder geminiOrder : geminiOrders) {
      limitOrders.add(adaprtOrder(geminiOrder));
    }
    return new OpenOrders(limitOrders);
  }

  public static LimitOrder adaprtOrder(GeminiOrder geminiOrder) {
    Order.OrderType orderType = geminiOrder.getSide().equalsIgnoreCase("buy") ? Order.OrderType.BID : Order.OrderType.ASK;
    Date date = DateUtils.fromMillisUtc(geminiOrder.getTimestampms());

    LimitOrder.Builder builder = new LimitOrder.Builder(orderType, adaptPair(geminiOrder.getSymbol()))
        .limitPrice(geminiOrder.getPrice())
        .tradableAmount(geminiOrder.getOriginalAmount())
        .timestamp(date)
        .id(geminiOrder.getOrderId());
    return builder.build();
  }

  public static UserTrades adaptUserTrades(List<GeminiUserTrade> geminiUserTrades, CurrencyPair currencyPair) {

    List<UserTrade> pastTrades = new ArrayList<>();

    for (GeminiUserTrade geminiUserTrade : geminiUserTrades) {
      Date date = DateUtils.fromMillisUtc(geminiUserTrade.getTimestampms());
      Order.OrderType orderType = geminiUserTrade.getType() == GeminiUserTrade.Type.Buy ? Order.OrderType.BID : Order.OrderType.ASK;

      UserTrade.Builder builder = new UserTrade.Builder()
          .id(String.valueOf(geminiUserTrade.getTid()))
          .timestamp(date)
          .currencyPair(currencyPair)
          .orderId(geminiUserTrade.getOrderId())
          .price(geminiUserTrade.getPrice())
          .tradableAmount(geminiUserTrade.getAmount())
          .feeCurrency(Currency.getInstance(geminiUserTrade.getFeeCurrency()))
          .feeAmount(geminiUserTrade.getFeeAmount())
          .type(orderType);
      pastTrades.add(builder.build());
    }

    return new UserTrades(pastTrades, Trades.TradeSortType.SortByTimestamp);
  }

}
