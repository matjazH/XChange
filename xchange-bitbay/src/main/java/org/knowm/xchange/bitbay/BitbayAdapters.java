package org.knowm.xchange.bitbay;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.knowm.xchange.bitbay.dto.account.BitbayAccount;
import org.knowm.xchange.bitbay.dto.account.BitbayBalance;
import org.knowm.xchange.bitbay.dto.marketdata.BitbayOrderBook;
import org.knowm.xchange.bitbay.dto.marketdata.BitbayTicker;
import org.knowm.xchange.bitbay.dto.marketdata.BitbayTrade;
import org.knowm.xchange.bitbay.dto.trade.BitbayOrder;
import org.knowm.xchange.bitbay.dto.trade.BitbayTransaction;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.account.AccountInfo;
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
import org.knowm.xchange.exceptions.ExchangeException;

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
  private static List<LimitOrder> transformArrayToLimitOrders(BigDecimal[][] orders, Order.OrderType orderType, CurrencyPair currencyPair) {

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

    OrderBook orderBook = new OrderBook(null, transformArrayToLimitOrders(bitbayOrderBook.getAsks(), Order.OrderType.ASK, currencyPair),
        transformArrayToLimitOrders(bitbayOrderBook.getBids(), Order.OrderType.BID, currencyPair));

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

      Trade trade = new Trade(bitbayTrade.getType().toLowerCase().equals("buy") ? Order.OrderType.BID : Order.OrderType.ASK,
          bitbayTrade.getAmount(), currencyPair, bitbayTrade.getPrice(), new Date(bitbayTrade.getDate()*1000),
          bitbayTrade.getTid());

      tradeList.add(trade);
    }

    Trades trades = new Trades(tradeList, Trades.TradeSortType.SortByTimestamp);
    return trades;
  }

  public static AccountInfo adaptAccount(BitbayAccount bitbayAccount) {

    List<Balance> balances = new ArrayList<Balance>();

    for (Map.Entry<String, BitbayBalance> entry : bitbayAccount.getBalances().entrySet()) {
      Currency currency = Currency.getInstance(entry.getKey());
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

        Order.OrderType type = "ask".equals(bitbayOrder.getType()) ? Order.OrderType.ASK : Order.OrderType.BID;
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
      Order.OrderType orderType = "BID".equals(transaction.getType()) ? Order.OrderType.BID : Order.OrderType.ASK;

      BigDecimal tradableAmount = transaction.getAmount();
      BigDecimal price = transaction.getPrice();

      Date timestamp = parseDate(transaction.getDate());

      String market = transaction.getMarket();
      String[] currencies = market.split("-");
      CurrencyPair pair = new CurrencyPair(currencies[0], currencies[1]);

      UserTrade trade = new UserTrade(orderType, tradableAmount, pair, price, timestamp, null, null, null, null);
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
