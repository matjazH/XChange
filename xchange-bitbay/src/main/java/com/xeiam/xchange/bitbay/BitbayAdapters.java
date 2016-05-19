package com.xeiam.xchange.bitbay;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.xeiam.xchange.bitbay.dto.account.BitbayAccount;
import com.xeiam.xchange.bitbay.dto.account.BitbayBalance;
import com.xeiam.xchange.bitbay.dto.marketdata.BitbayOrderBook;
import com.xeiam.xchange.bitbay.dto.marketdata.BitbayTicker;
import com.xeiam.xchange.bitbay.dto.marketdata.BitbayTrade;
import com.xeiam.xchange.bitbay.dto.trade.BitbayOrder;
import com.xeiam.xchange.bitbay.dto.trade.BitbayTransaction;
import com.xeiam.xchange.currency.*;
import com.xeiam.xchange.dto.Order.OrderType;
import com.xeiam.xchange.dto.account.AccountInfo;
import com.xeiam.xchange.dto.account.Balance;
import com.xeiam.xchange.dto.account.Wallet;
import com.xeiam.xchange.dto.marketdata.OrderBook;
import com.xeiam.xchange.dto.marketdata.Ticker;
import com.xeiam.xchange.dto.marketdata.Trade;
import com.xeiam.xchange.dto.marketdata.Trades;
import com.xeiam.xchange.dto.trade.*;
import com.xeiam.xchange.exceptions.ExchangeException;

/**
 * @author kpysniak
 */
public class BitbayAdapters {

  private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

  /**
   * Singleton
   */
  private BitbayAdapters() {

  }

  /**
   * Adapts a BitbayTicker to a Ticker Object
   *
   * @param bitbayTicker The exchange specific ticker
   * @param currencyPair (e.g. BTC/USD)
   * @return The ticker
   */
  public static Ticker adaptTicker(BitbayTicker bitbayTicker, CurrencyPair currencyPair) {

    BigDecimal ask = bitbayTicker.getAsk();
    BigDecimal bid = bitbayTicker.getBid();
    BigDecimal high = bitbayTicker.getMax();
    BigDecimal low = bitbayTicker.getMin();
    BigDecimal volume = bitbayTicker.getVolume();
    BigDecimal last = bitbayTicker.getLast();

    return new Ticker.Builder().currencyPair(currencyPair).last(last).bid(bid).ask(ask).high(high).low(low).volume(volume).build();
  }

  /**
   * @param orders
   * @param orderType
   * @param currencyPair
   * @return
   */
  private static List<LimitOrder> transformArrayToLimitOrders(BigDecimal[][] orders, OrderType orderType, CurrencyPair currencyPair) {

    List<LimitOrder> limitOrders = new ArrayList<LimitOrder>();

    for (BigDecimal[] order : orders) {
      limitOrders.add(new LimitOrder(orderType, order[1], currencyPair, null, new Date(), order[0]));
    }

    return limitOrders;
  }

  /**
   * @param bitbayOrderBook
   * @param currencyPair
   * @return
   */
  public static OrderBook adaptOrderBook(BitbayOrderBook bitbayOrderBook, CurrencyPair currencyPair) {

    OrderBook orderBook = new OrderBook(null, transformArrayToLimitOrders(bitbayOrderBook.getAsks(), OrderType.ASK, currencyPair),
        transformArrayToLimitOrders(bitbayOrderBook.getBids(), OrderType.BID, currencyPair));

    return orderBook;
  }

  /**
   * @param bitbayTrades
   * @param currencyPair
   * @return
   */
  public static Trades adaptTrades(BitbayTrade[] bitbayTrades, CurrencyPair currencyPair) {

    List<Trade> tradeList = new ArrayList<Trade>();

    for (BitbayTrade bitbayTrade : bitbayTrades) {

      Trade trade = new Trade(null, bitbayTrade.getAmount(), currencyPair, bitbayTrade.getPrice(), new Date(bitbayTrade.getDate()*1000),
          bitbayTrade.getTid());

      tradeList.add(trade);
    }

    Trades trades = new Trades(tradeList, Trades.TradeSortType.SortByTimestamp);
    return trades;
  }

  public static AccountInfo adaptAccount(BitbayAccount bitbayAccount) {

    List<Balance> balances = new ArrayList<Balance>();

    for (Map.Entry<String, BitbayBalance> entry : bitbayAccount.getBalances().entrySet()) {
      com.xeiam.xchange.currency.Currency currency = com.xeiam.xchange.currency.Currency.getInstance(entry.getKey());
      BitbayBalance bitbayBalance = entry.getValue();
      BigDecimal total = bitbayBalance.getAvailable().add(bitbayBalance.getLocked());
      balances.add(new Balance(currency, total, bitbayBalance.getAvailable(), bitbayBalance.getLocked()));
    }

    return new AccountInfo(new Wallet(balances));
  }

  public static OpenOrders adaptOpenOrders(List<BitbayOrder> bitbayOrders) {
    List<LimitOrder> orders = new ArrayList<LimitOrder>();

    for (BitbayOrder bitbayOrder : bitbayOrders) {
      if ("active".equals(bitbayOrder.getStatus())) {

        OrderType type = "ask".equals(bitbayOrder.getType()) ? OrderType.ASK : OrderType.BID;
        CurrencyPair pair = new CurrencyPair(bitbayOrder.getOrderCurrency(), bitbayOrder.getPaymentCurrency());

        Date date = parseDate(bitbayOrder.getOrderDate());
        BigDecimal rate = bitbayOrder.getStartPrice().divide(bitbayOrder.getStartUnits());
        orders.add(new LimitOrder(type, bitbayOrder.getUnits(), pair, bitbayOrder.getOrderId(), date, rate));
      }
    }

    return new OpenOrders(orders);
  }

  public static UserTrades adaptTradeHistory(List<BitbayTransaction> transactions) {

    List<UserTrade> trades = new ArrayList<UserTrade>();

    for (BitbayTransaction transaction : transactions) {
      OrderType orderType = "BID".equals(transaction.getType()) ? OrderType.BID : OrderType.ASK;

      BigDecimal tradableAmount = transaction.getAmount();
      BigDecimal price = transaction.getPrice();

      Date timestamp = parseDate(transaction.getDate());

      String market = transaction.getMarket();
      String[] currencies = market.split("-");
      CurrencyPair pair = new CurrencyPair(currencies[0], currencies[1]);

      UserTrade trade = new UserTrade(orderType, tradableAmount, pair, price, timestamp, null, null);
      trades.add(trade);
    }

    return new UserTrades(trades, Trades.TradeSortType.SortByTimestamp);
  }


  public static Date parseDate(String dateString) {
    try {
      return dateFormatter.parse(dateString);
    } catch (ParseException e) {
      throw new ExchangeException("Illegal date/time format", e);
    }
  }
}
