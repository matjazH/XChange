package org.knowm.xchange.hitbtc;

import java.math.BigDecimal;
import java.util.*;

import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.OrderBookUpdate;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.meta.ExchangeMetaData;
import org.knowm.xchange.dto.meta.MarketMetaData;
import org.knowm.xchange.dto.trade.*;
import org.knowm.xchange.hitbtc.dto.account.HitbtcBalance;
import org.knowm.xchange.hitbtc.dto.marketdata.*;
import org.knowm.xchange.hitbtc.dto.marketdata.HitbtcOrderBookResponse;
import org.knowm.xchange.hitbtc.dto.meta.HitbtcMetaData;
import org.knowm.xchange.hitbtc.dto.trade.HitbtcOrder;
import org.knowm.xchange.hitbtc.dto.trade.HitbtcOwnTrade;
import org.knowm.xchange.utils.jackson.CurrencyPairDeserializer;

public class HitbtcAdapters {

  public static final char DELIM = '_';

  /**
   * Singleton
   */
  private HitbtcAdapters() {
  }

  public static Date adaptTime(HitbtcTime hitbtcTime) {
    return new Date(hitbtcTime.getTimestamp());
  }

  public static CurrencyPair adaptSymbol(String symbolString) {
    return CurrencyPairDeserializer.getCurrencyPairFromString(symbolString);
  }

  public static CurrencyPair adaptSymbol(HitbtcSymbol hitbtcSymbol) {
    return new CurrencyPair(hitbtcSymbol.getCommodity(), hitbtcSymbol.getCurrency());
  }

  /**
   * Adapts a HitbtcTicker to a Ticker Object
   *
   * @param hitbtcTicker The exchange specific ticker
   * @param currencyPair (e.g. BTC/USD)
   * @return The ticker
   */
  public static Ticker adaptTicker(HitbtcTicker hitbtcTicker, CurrencyPair currencyPair) {

    BigDecimal volume = hitbtcTicker.getVolume();
    BigDecimal bid = hitbtcTicker.getBid();
    BigDecimal ask = hitbtcTicker.getAsk();
    BigDecimal low = hitbtcTicker.getLow();
    BigDecimal high = hitbtcTicker.getHigh();
    BigDecimal last = hitbtcTicker.getLast();

    return new Ticker.Builder().currencyPair(currencyPair).last(last).bid(bid).ask(ask).high(high).low(low)
        .volume(volume).timestamp(new Date(hitbtcTicker.getTimestamp())).build();
  }

  public static List<Ticker> adaptTickers(Map<String, HitbtcTicker> hitbtcTickers) {

    List<Ticker> tickers = new ArrayList<Ticker>(hitbtcTickers.size());

    for (Map.Entry<String, HitbtcTicker> ticker : hitbtcTickers.entrySet()) {

      tickers.add(adaptTicker(ticker.getValue(), adaptSymbol(ticker.getKey())));
    }

    return tickers;
  }

  public static OrderBook adaptOrderBook(HitbtcOrderBookResponse hitbtcOrderBookResponse, CurrencyPair currencyPair) {

    List<LimitOrder> asks = adaptMarketOrderToLimitOrder(hitbtcOrderBookResponse.getAsks(), OrderType.ASK, currencyPair);
    List<LimitOrder> bids = adaptMarketOrderToLimitOrder(hitbtcOrderBookResponse.getBids(), OrderType.BID, currencyPair);

    return new OrderBook(null, asks, bids);
  }

  private static List<LimitOrder> adaptMarketOrderToLimitOrder(HitbtcOrderBook[] hitbtcOrders, OrderType orderType, CurrencyPair currencyPair) {

    List<LimitOrder> orders = new ArrayList<LimitOrder>(hitbtcOrders.length);

    for (HitbtcOrderBook hitbtcOrder : hitbtcOrders) {

      BigDecimal price = hitbtcOrder.getPrice();
      BigDecimal amount = hitbtcOrder.getSize();

      LimitOrder limitOrder = new LimitOrder(orderType, amount, currencyPair, null, null, price);
      orders.add(limitOrder);
    }

    return orders;
  }

  public static OrderType adaptSide(HitbtcTrade.HitbtcTradeSide side) {

    switch (side) {
    case BUY:
      return OrderType.BID;
    case SELL:
      return OrderType.ASK;
    default:
      return null;
    }
  }

  public static Trades adaptTrades(HitbtcTrade[] hitbtcTrades, CurrencyPair currencyPair) {

    return adaptTrades(Arrays.asList(hitbtcTrades), currencyPair);
  }

  public static Trades adaptTrades(List<? extends HitbtcTrade> allHitbtcTrades, CurrencyPair currencyPair) {

    List<Trade> trades = new ArrayList<>(allHitbtcTrades.size());
    long lastTradeId = 0;
    for (HitbtcTrade hitbtcTrade : allHitbtcTrades) {
      String tid = hitbtcTrade.getTid();
      BigDecimal price = hitbtcTrade.getPrice();
      BigDecimal amount = hitbtcTrade.getAmount();
      Date timestamp = new Date(hitbtcTrade.getDate());
      long longTradeId = (tid == null) ? 0 : Long.parseLong(tid);
      if (longTradeId > lastTradeId) {
        lastTradeId = longTradeId;
      }
      OrderType orderType = adaptSide(hitbtcTrade.getSide());
      Trade trade = new Trade(orderType, amount, currencyPair, price, timestamp, tid);
      trades.add(trade);
    }

    return new Trades(trades, lastTradeId, Trades.TradeSortType.SortByTimestamp);
  }

  public static List<LimitOrder> adaptStreamingOrders(List<HitbtcStreamingOrder> orders, OrderType orderType, CurrencyPair currencyPair) {

    List<LimitOrder> limitOrders = new ArrayList<LimitOrder>(orders.size());

    for (int i = 0; i < orders.size(); i++) {
      HitbtcStreamingOrder order = orders.get(i);

      LimitOrder limitOrder = new LimitOrder(orderType, order.getSize(), currencyPair, "", null, order.getPrice());

      limitOrders.add(limitOrder);
    }

    return limitOrders;
  }

  public static OrderBook adaptSnapshotFullRefresh(HitbtcSnapshotFullRefresh hitbtcSnapshotFullRefresh) {

    CurrencyPair currencyPair = adaptSymbol(hitbtcSnapshotFullRefresh.getSymbol());

    List<LimitOrder> asks = adaptStreamingOrders(hitbtcSnapshotFullRefresh.getAsk(), OrderType.ASK, currencyPair);
    List<LimitOrder> bids = adaptStreamingOrders(hitbtcSnapshotFullRefresh.getBid(), OrderType.BID, currencyPair);

    return new OrderBook(null, asks, bids);
  }

  public static List<OrderBookUpdate> adaptIncrementalRefreshOrders(HitbtcIncrementalRefresh hitbtcIncrementalRefresh) {
    return adaptIncrementalRefreshOrders(hitbtcIncrementalRefresh, null, null);
  }

  public static List<OrderBookUpdate> adaptIncrementalRefreshOrders(HitbtcIncrementalRefresh hitbtcIncrementalRefresh, BigDecimal volume,
      Date timestamp) {

    CurrencyPair currencyPair = adaptSymbol(hitbtcIncrementalRefresh.getSymbol());
    List<HitbtcStreamingOrder> asks = hitbtcIncrementalRefresh.getAsk();
    List<HitbtcStreamingOrder> bids = hitbtcIncrementalRefresh.getBid();

    List<OrderBookUpdate> updates = new ArrayList<OrderBookUpdate>(asks.size() + bids.size());

    if (updates.size() != 1) {
      volume = null;
    }

    for (int i = 0; i < asks.size(); i++) {
      HitbtcStreamingOrder order = asks.get(i);

      OrderBookUpdate update = new OrderBookUpdate(OrderType.ASK, volume, currencyPair, order.getPrice(), timestamp, order.getSize());

      updates.add(update);
    }

    for (int i = 0; i < bids.size(); i++) {
      HitbtcStreamingOrder order = bids.get(i);

      OrderBookUpdate update = new OrderBookUpdate(OrderType.BID, volume, currencyPair, order.getPrice(), timestamp, order.getSize());

      updates.add(update);
    }

    return updates;
  }

  public static Trades adaptIncrementalRefreshTrades(HitbtcIncrementalRefresh hitbtcIncrementalRefresh) {

    CurrencyPair currencyPair = adaptSymbol(hitbtcIncrementalRefresh.getSymbol());
    List<HitbtcStreamingTrade> trades = hitbtcIncrementalRefresh.getTrade();

    return adaptTrades(trades, currencyPair);
  }

  public static BigDecimal getAmount(HitbtcOrder order, MarketMetaData marketMeta) {
    if (marketMeta != null) {
      BigDecimal minimumAmount = marketMeta.getMinimumAmount();
      if (minimumAmount != null) {
        return order.getOrderQuantity().multiply(minimumAmount);
      }
    }
    return order.getOrderQuantity();
  }

  public static OpenOrders adaptOpenOrders(HitbtcOrder[] openOrdersRaw, ExchangeMetaData metaData) {

    List<LimitOrder> openOrders = new ArrayList<LimitOrder>(openOrdersRaw.length);

    for (int i = 0; i < openOrdersRaw.length; i++) {
      HitbtcOrder o = openOrdersRaw[i];
      OrderType type = adaptOrderType(o.getSide());
      CurrencyPair pair = adaptSymbol(o.getSymbol());

      BigDecimal orderAmount = getAmount(o, metaData.getMarketMetaDataMap().get(pair));
      LimitOrder order = new LimitOrder(type, orderAmount, pair, o.getClientOrderId(), new Date(o.getLastTimestamp()),
          o.getOrderPrice());

      openOrders.add(order);
    }

    return new OpenOrders(openOrders);
  }

  public static OrderType adaptOrderType(String side) {

    return side.equals("buy") ? OrderType.BID : OrderType.ASK;
  }

  public static UserTrades adaptTradeHistory(HitbtcOwnTrade[] tradeHistoryRaw, ExchangeMetaData metaData) {

    List<UserTrade> trades = new ArrayList<UserTrade>(tradeHistoryRaw.length);
    for (int i = 0; i < tradeHistoryRaw.length; i++) {
      HitbtcOwnTrade t = tradeHistoryRaw[i];
      OrderType type = adaptOrderType(t.getSide());

      CurrencyPair pair = adaptSymbol(t.getSymbol());

      // minimumAmount is equal to lot size
      BigDecimal amount = t.getExecQuantity().multiply(metaData.getMarketMetaDataMap().get(pair).getMinimumAmount());
      Date time = new Date(t.getTimestamp());
      String id = Long.toString(t.getTradeId());

      UserTrade trade = new UserTrade(type, amount, pair, t.getExecPrice(), time, id, t.getClientOrderId(), t.getFee(),
          Currency.getInstance(pair.counter.getCurrencyCode()));

      trades.add(trade);
    }

    return new UserTrades(trades, Trades.TradeSortType.SortByTimestamp);
  }

  public static Wallet adaptWallet(HitbtcBalance[] walletRaw) {

    List<Balance> balances = new ArrayList<Balance>(walletRaw.length);

    for (HitbtcBalance balanceRaw : walletRaw) {

      Balance balance = new Balance(Currency.getInstance(balanceRaw.getCurrencyCode()), null, balanceRaw.getAvailable(), balanceRaw.getReserved());
      balances.add(balance);

    }
    return new Wallet(balances);
  }

  public static String adaptCurrencyPair(CurrencyPair pair) {

    String ebtc = (pair.base.getCurrencyCode().contains("EBTC") && !pair.base.getCurrencyCode().contains("OLD")) ?
        pair.base.getCurrencyCode() + "NEW" : pair.base.getCurrencyCode();
    return pair == null ? null : ebtc + pair.counter.getCurrencyCode();
  }

  public static String createOrderId(Order order, long nonce) {

    return order.getType().name().substring(0, 1) + DELIM + adaptCurrencyPair(order.getCurrencyPair()) + DELIM + nonce;
  }

  public static OrderType readOrderType(String orderId) {

    return orderId.charAt(0) == 'A' ? OrderType.ASK : OrderType.BID;
  }

  public static String readSymbol(String orderId) {

    int start = orderId.indexOf(DELIM);
    int end = orderId.indexOf(DELIM, start + 1);
    return orderId.substring(start + 1, end);
  }

  public static HitbtcTrade.HitbtcTradeSide getSide(OrderType type) {

    return type == OrderType.BID ? HitbtcTrade.HitbtcTradeSide.BUY : HitbtcTrade.HitbtcTradeSide.SELL;
  }

  public static ExchangeMetaData adaptToExchangeMetaData(HitbtcSymbol[] symbols, HitbtcMetaData hitbtcMetaData) {

    Map<CurrencyPair, MarketMetaData> marketMetaDataMap = new HashMap<CurrencyPair, MarketMetaData>();
    if (symbols != null) {
      for (HitbtcSymbol symbol : symbols) {
        CurrencyPair pair = adaptSymbol(symbol);
        MarketMetaData meta = new MarketMetaData(symbol.getTakeLiquidityRate(), symbol.getLot(), symbol.getStep().scale());

        marketMetaDataMap.put(pair, meta);
      }
    }

    return new ExchangeMetaData(marketMetaDataMap, hitbtcMetaData.currency, null, null, null);
  }
}
