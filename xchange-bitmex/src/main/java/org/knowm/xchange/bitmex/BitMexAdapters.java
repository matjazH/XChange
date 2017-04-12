package org.knowm.xchange.bitmex;

import java.util.*;
import java.math.BigDecimal;

import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.bitmex.dto.account.BitMexAccount;
import org.knowm.xchange.bitmex.dto.marketdata.*;
import org.knowm.xchange.bitmex.dto.marketdata.BitMexTrade;
import org.knowm.xchange.dto.marketdata.Trades.TradeSortType;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.marketdata.OrderBook;

public class BitMexAdapters {

  public static CurrencyPair adaptCurrencyPair(String pair) {
    final String[] currencies = pair.toUpperCase().split("_");
    return new CurrencyPair(currencies[0].toUpperCase(), currencies[1].toUpperCase());
  }

  public static OrderBook adaptOrderBook(BitMexOrderBook book, CurrencyPair currencyPair) {
    List<LimitOrder> asks = toLimitOrderList(book.getAsks(), OrderType.ASK, currencyPair);
    List<LimitOrder> bids = toLimitOrderList(book.getBids(), OrderType.BID, currencyPair);

    return new OrderBook(null, asks, bids);
  }

  private static List<LimitOrder> toLimitOrderList(List<BitMexAsksBidsData> levels, OrderType orderType,
                                                   CurrencyPair currencyPair) {

    List<LimitOrder> allLevels = new ArrayList<LimitOrder>(levels.size());
    for (int i = 0; i < levels.size(); i++) {
      BitMexAsksBidsData ask = levels.get(i);
      allLevels.add(new LimitOrder(orderType, ask.getQuantity(), currencyPair, "0", null, ask.getRate()));
    }

    return allLevels;

  }

  public static AccountInfo adaptAccount(BitMexAccount yoBitAccount) {
    List<Balance> balances = new ArrayList<Balance>();

    Map<String, BigDecimal> funds = yoBitAccount.getFunds();
    Map<String, BigDecimal> orders = yoBitAccount.getFundsInclOrders();

    if (orders != null) {
      Iterator<Map.Entry<String, BigDecimal>> itOrders = orders.entrySet().iterator();

      while (itOrders.hasNext()) {
        Map.Entry<String, BigDecimal> pairOrders = itOrders.next();
        balances.add(new Balance(new Currency(pairOrders.getKey()), funds.get(pairOrders.getKey()), pairOrders.getValue()));
      }
    }

    return new AccountInfo(new Wallet(balances));
  }

  public static Trades adaptTrades(BitMexTrades coinbaseTrades, CurrencyPair currencyPair) {
    List<BitMexTrade> ctrades = coinbaseTrades.getTrades();

    List<Trade> trades = new ArrayList<Trade>(ctrades.size());

    int lastTrade = 0;

    for (int i = 0; i < ctrades.size(); i++) {
      BitMexTrade trade = ctrades.get(i);

      OrderType type = trade.getType().equals("bid") ? OrderType.BID : OrderType.ASK;

      Trade t = new Trade(type, trade.getAmount(), currencyPair, trade.getPrice(),
          parseDate(trade.getTimestamp()), String.valueOf(trade.getTid()));
      trades.add(t);
      lastTrade = i;
    }

    return new Trades(trades, ctrades.get(lastTrade).getTid(), TradeSortType.SortByID);
  }

  private static Date parseDate(Long rawDateLong) {
    return new java.util.Date((long) rawDateLong * 1000);
  }

  public static Ticker adaptTicker(BitMexTicker yoBitTicker, CurrencyPair currencyPair) {
    BigDecimal ask = yoBitTicker.getAsk();
    BigDecimal bid = yoBitTicker.getBid();
    BigDecimal low = yoBitTicker.getMin();
    BigDecimal volume = yoBitTicker.getVolume();
    BigDecimal high = yoBitTicker.getMax();
    BigDecimal last = yoBitTicker.getLast();

    return new Ticker.Builder().currencyPair(currencyPair).last(last).bid(bid).ask(ask).high(high).low(low).volume(volume).build();
  }

}
