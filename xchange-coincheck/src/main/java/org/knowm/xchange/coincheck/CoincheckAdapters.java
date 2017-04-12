package org.knowm.xchange.coincheck;

import org.knowm.xchange.coincheck.dto.CoincheckOrderSide;
import org.knowm.xchange.coincheck.dto.account.CoincheckBalance;
import org.knowm.xchange.coincheck.dto.marketdata.CoincheckOrderbook;
import org.knowm.xchange.coincheck.dto.marketdata.CoincheckTicker;
import org.knowm.xchange.coincheck.dto.marketdata.CoincheckTrade;
import org.knowm.xchange.coincheck.dto.trade.CoincheckOrder;
import org.knowm.xchange.coincheck.dto.trade.CoincheckTransaction;
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
import org.knowm.xchange.utils.DateUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 02/06/16
 * Time: 12:32
 */
public class CoincheckAdapters {


  public static Ticker adaptCoincheckTicker(CoincheckTicker coincheckTicker, CurrencyPair pair) {

    BigDecimal last = coincheckTicker.getLast();
    BigDecimal bid = coincheckTicker.getBid();
    BigDecimal ask = coincheckTicker.getAsk();
    BigDecimal high = coincheckTicker.getHigh();
    BigDecimal low = coincheckTicker.getLow();
    BigDecimal volume = coincheckTicker.getVolume();
    Date time = new Date(coincheckTicker.getTimestamp());

    return new Ticker.Builder().currencyPair(pair)
        .last(last).bid(bid).ask(ask).high(high).low(low).volume(volume).timestamp(time).build();
  }

  public static OrderBook adaptCoincheckOrderbook(CoincheckOrderbook coincheckOrderbook, CurrencyPair pair) {

    List<LimitOrder> asks = createLimitOrders(coincheckOrderbook.getAsks(), Order.OrderType.ASK, pair);
    List<LimitOrder> bids = createLimitOrders(coincheckOrderbook.getBids(), Order.OrderType.BID, pair);

    return new OrderBook(null, asks, bids);
  }

  private static List<LimitOrder> createLimitOrders(List<List<BigDecimal>> depths, Order.OrderType type, CurrencyPair pair) {
    List<LimitOrder> orders = new ArrayList<LimitOrder>();

    for (List<BigDecimal> depth : depths) {
      LimitOrder limitOrder = new LimitOrder.Builder(type, pair)
          .limitPrice(depth.get(0)).tradableAmount(depth.get(1)).build();
      orders.add(limitOrder);
    }

    return orders;
  }

  public static Trades adaptCoincheckPublicTrades(List<CoincheckTrade> coincheckTrades, CurrencyPair currencyPair) {

    List<Trade> trades = new ArrayList<Trade>();

    for (CoincheckTrade coincheckTrade : coincheckTrades) {
      Order.OrderType type = coincheckTrade.getOrderType() == CoincheckOrderSide.buy ?
          Order.OrderType.BID : Order.OrderType.ASK;
      Date timestamp = adaptTimestamp(coincheckTrade.getCreatedAt());

      Trade trade = new Trade(type, coincheckTrade.getAmount(), currencyPair, coincheckTrade.getRate(),
          timestamp, coincheckTrade.getId().toString());
      trades.add(trade);
    }

    return new Trades(trades, Trades.TradeSortType.SortByTimestamp);
  }

  public static AccountInfo adaptAccount(CoincheckBalance coincheckBalance) {

    List<Balance> balances = new ArrayList<Balance>();

    Currency currency = Currency.getInstance("JPY");
    balances.add(new Balance(currency, coincheckBalance.getJpy()));
    currency = Currency.getInstance("BTC");
    balances.add(new Balance(currency, coincheckBalance.getBtc()));

    return new AccountInfo(new Wallet(balances));
  }

  public static OpenOrders adaptOrders(List<CoincheckOrder> coincheckOrders) {

    List<LimitOrder> limitOrders = new ArrayList<LimitOrder>();
    for (CoincheckOrder coincheckOrder : coincheckOrders) {

      Order.OrderType orderType = coincheckOrder.getOrderType() == CoincheckOrderSide.buy ? Order.OrderType.BID : Order.OrderType.ASK;
      Date timestamp = adaptTimestamp(coincheckOrder.getCreatedAt());
      CurrencyPair currencyPair = adaptCurrencyPair(coincheckOrder.getPair());

      limitOrders.add(new LimitOrder(orderType, coincheckOrder.getPendingAmount(), currencyPair,
          Long.toString(coincheckOrder.getId()), timestamp, coincheckOrder.getRate()));
    }

    return new OpenOrders(limitOrders);
  }

  public static UserTrades adaptTradeHistory(List<CoincheckTransaction> coincheckTransactions) {

    List<UserTrade> trades = new ArrayList<UserTrade>();

    for (CoincheckTransaction transaction : coincheckTransactions) {

      Order.OrderType type = transaction.getSide() == CoincheckOrderSide.buy ? Order.OrderType.BID : Order.OrderType.ASK;
      Date timestamp = adaptTimestamp(transaction.getCreatedAt());
      CurrencyPair currencyPair = adaptCurrencyPair(transaction.getPair());
      String tradeId = Long.toString(transaction.getId());
      String orderId = Long.toString(transaction.getOrderId());
      BigDecimal amount = getTransactionAmount(transaction);

      trades.add(new UserTrade(type, amount, currencyPair, transaction.getRate(), timestamp,
          tradeId, orderId, transaction.getFee(), new Currency(transaction.getFeeCurrency())));
    }
    return new UserTrades(trades, Trades.TradeSortType.SortByTimestamp);
  }

  private static BigDecimal getTransactionAmount(CoincheckTransaction transaction) {
    for (Map.Entry<String, BigDecimal> entry : transaction.getFunds().entrySet()) {
      if (!entry.getKey().toLowerCase().equals(transaction.getFeeCurrency().toLowerCase())) {
        return entry.getValue().abs();
      }
    }
    return null;
  }

  public static CurrencyPair adaptCurrencyPair(String coincheckPair) {
    String[] currencies = coincheckPair.split("_");
    return new CurrencyPair(currencies[0].toUpperCase(), currencies[1].toUpperCase());
  }

  public static String adaptCoincheckPair(CurrencyPair currencyPair) {
    return currencyPair.base.getCurrencyCode().toLowerCase() + "_" + currencyPair.counter.getCurrencyCode().toLowerCase();
  }

  public static Date adaptTimestamp(String dateString) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
      sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
      return sdf.parse(dateString);
    } catch (ParseException e) {
      return null;
    }
  }
}
