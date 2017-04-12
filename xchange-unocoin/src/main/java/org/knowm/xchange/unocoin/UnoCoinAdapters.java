package org.knowm.xchange.unocoin;

import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.unocoin.dto.marketdata.*;
import org.knowm.xchange.dto.marketdata.Trades.TradeSortType;

import java.math.BigDecimal;
import java.util.*;

public class UnoCoinAdapters {

  public static CurrencyPair adaptCurrencyPair(String pair) {
    final String[] currencies = pair.toUpperCase().split("_");
    return new CurrencyPair(currencies[0].toUpperCase(), currencies[1].toUpperCase());
  }

  public static OrderBook adaptOrderBook(UnoCoinOrderBook book, CurrencyPair currencyPair) {
    List<LimitOrder> asks = toLimitOrderList(book.getAsks(), OrderType.ASK, currencyPair);
    List<LimitOrder> bids = toLimitOrderList(book.getBids(), OrderType.BID, currencyPair);

    return new OrderBook(null, asks, bids);
  }

  private static List<LimitOrder> toLimitOrderList(List<UnoCoinAsksBidsData> levels, OrderType orderType,
                                                   CurrencyPair currencyPair) {

    List<LimitOrder> allLevels = new ArrayList<LimitOrder>(levels.size());
    for (int i = 0; i < levels.size(); i++) {
      UnoCoinAsksBidsData ask = levels.get(i);
      allLevels.add(new LimitOrder(orderType, ask.getQuantity(), currencyPair, "0", null, ask.getRate()));
    }

    return allLevels;

  }

  /*
  public static AccountInfo adaptAccount(UnoCoinAccount UnoCoinAccount) {
    List<Balance> balances = new ArrayList<Balance>();

    Map<String, BigDecimal> funds = UnoCoinAccount.getFunds();
    Map<String, BigDecimal> orders = UnoCoinAccount.getFundsInclOrders();

    if (orders != null) {
      Iterator<Map.Entry<String, BigDecimal>> itOrders = orders.entrySet().iterator();

      while (itOrders.hasNext()) {
        Map.Entry<String, BigDecimal> pairOrders = itOrders.next();
        balances.add(new Balance(new Currency(pairOrders.getKey()), funds.get(pairOrders.getKey()), pairOrders.getValue()));
      }
    }

    return new AccountInfo(new Wallet(balances));
  }
  */

  public static Trades adaptTrades(UnoCoinTrades coinbaseTrades, CurrencyPair currencyPair) {
    List<UnoCoinTrade> ctrades = coinbaseTrades.getTrades();

    List<Trade> trades = new ArrayList<Trade>(ctrades.size());

    int lastTrade = 0;

    for (int i = 0; i < ctrades.size(); i++) {
      UnoCoinTrade trade = ctrades.get(i);

      OrderType type = trade.getType().equals("bid") ? OrderType.BID : OrderType.ASK;

      Trade t = new Trade(type, trade.getAmount(), currencyPair, trade.getPrice(),
          parseDate(trade.getTimestamp()), String.valueOf(trade.getTid()));
      trades.add(t);
      lastTrade = i;
    }

    return new Trades(trades, ctrades.get(lastTrade).getTid(), TradeSortType.SortByID);
  }

  private static Date parseDate(Long rawDateLong) {
    return new Date((long) rawDateLong * 1000);
  }

  public static Ticker adaptTicker(UnoCoinTicker UnoCoinTicker, CurrencyPair currencyPair) {
    BigDecimal ask = UnoCoinTicker.getAsk();
    BigDecimal bid = UnoCoinTicker.getBid();
    BigDecimal low = UnoCoinTicker.getMin();
    BigDecimal volume = UnoCoinTicker.getVolume();
    BigDecimal high = UnoCoinTicker.getMax();
    BigDecimal last = UnoCoinTicker.getLast();

    return new Ticker.Builder().currencyPair(currencyPair).last(last).bid(bid).ask(ask).high(high).low(low).volume(volume).build();
  }

}
