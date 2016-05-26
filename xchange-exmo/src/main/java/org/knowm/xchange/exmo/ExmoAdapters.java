package org.knowm.xchange.exmo;

import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.marketdata.Trades.TradeSortType;
import org.knowm.xchange.dto.trade.*;
import org.knowm.xchange.exmo.dto.account.ExmoUserInfo;
import org.knowm.xchange.exmo.dto.marketdata.ExmoOrderbook;
import org.knowm.xchange.exmo.dto.marketdata.ExmoTicker;
import org.knowm.xchange.exmo.dto.marketdata.ExmoTrade;
import org.knowm.xchange.exmo.dto.trade.ExmoOrder;
import org.knowm.xchange.exmo.dto.trade.ExmoUserTrade;
import org.knowm.xchange.utils.DateUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Various adapters for converting from Bitstamp DTOs to XChange DTOs
 */
public final class ExmoAdapters {

  /**
   * private Constructor
   */
  private ExmoAdapters() {

  }

  public static String toExmoSymbol(CurrencyPair pair) {

    return pair.base + "_" + pair.counter;
  }

  public static CurrencyPair toCurrencyPair(String exmoSymbol) {

    String[] split = exmoSymbol.split("_");
    return new CurrencyPair(split[0], split[1]);
  }

  public static AccountInfo adaptAccountInfo(ExmoUserInfo userInfo) {

    List<Balance> balances = new ArrayList<Balance>();
    for (Map.Entry<String, BigDecimal> balancePair : userInfo.getBalances().entrySet()) {
      Currency currency = Currency.getInstance(balancePair.getKey());
      balances.add(new Balance(currency, balancePair.getValue()));
    }

    return new AccountInfo(String.valueOf(userInfo.getUid()), new Wallet(balances));
  }

  public static OrderBook adaptOrderBook(ExmoOrderbook exmoOrderbook, CurrencyPair currencyPair) {

    List<LimitOrder> asks = createOrders(currencyPair, Order.OrderType.ASK, exmoOrderbook.getAsks());
    List<LimitOrder> bids = createOrders(currencyPair, Order.OrderType.BID, exmoOrderbook.getBids());
    return new OrderBook(null, asks, bids);
  }

  public static List<LimitOrder> createOrders(CurrencyPair currencyPair, Order.OrderType orderType, List<List<BigDecimal>> orders) {

    List<LimitOrder> limitOrders = new ArrayList<LimitOrder>();
    for (List<BigDecimal> order : orders) {
      limitOrders.add(createOrder(currencyPair, order, orderType));
    }
    return limitOrders;
  }

  public static LimitOrder createOrder(CurrencyPair currencyPair, List<BigDecimal> priceAndAmount, Order.OrderType orderType) {

    return new LimitOrder(orderType, priceAndAmount.get(1), currencyPair, "", null, priceAndAmount.get(0));
  }

  public static Trades adaptTrades(List<ExmoTrade> exmoTrades, CurrencyPair currencyPair) {

    List<Trade> trades = new ArrayList<Trade>();
    long lastTradeId = 0;

    for (ExmoTrade exmoTrade : exmoTrades) {
      final Long tradeId = exmoTrade.getTradeId();
      if (tradeId != null && tradeId > lastTradeId) {
        lastTradeId = tradeId;
      }
      OrderType type = "buy".equals(exmoTrade.getType()) ? OrderType.BID : OrderType.ASK;
      trades.add(new Trade(type, exmoTrade.getQuantity(), currencyPair, exmoTrade.getPrice(),
              DateUtils.fromMillisUtc(exmoTrade.getDate() * 1000L), String.valueOf(tradeId)));
    }
    return new Trades(trades, lastTradeId, TradeSortType.SortByID);
  }

  public static Ticker adaptTicker(ExmoTicker exmoTicker, CurrencyPair currencyPair) {

    BigDecimal last = exmoTicker.getLastTrade();
    BigDecimal bid = exmoTicker.getBuyPrice();
    BigDecimal ask = exmoTicker.getSellPrice();
    BigDecimal high = exmoTicker.getHigh();
    BigDecimal low = exmoTicker.getLow();
    BigDecimal volume = exmoTicker.getVol();
    Date timestamp = new Date(exmoTicker.getUpdated() * 1000L);

    return new Ticker.Builder().currencyPair(currencyPair).last(last).bid(bid).ask(ask).high(high).low(low).volume(volume)
        .timestamp(timestamp).build();
  }

  public static OpenOrders adaptOpenOrders(Map<String, List<ExmoOrder>> exmoOrders) {

    List<LimitOrder> limitOrders = new ArrayList<LimitOrder>();
    for (Map.Entry<String, List<ExmoOrder>> entry : exmoOrders.entrySet()) {
      CurrencyPair pair = toCurrencyPair(entry.getKey());

      for (ExmoOrder exmoOrder : entry.getValue()) {
        OrderType type = "buy".equals(exmoOrder.getType()) ? OrderType.BID : OrderType.ASK;
        limitOrders.add(new LimitOrder(type, exmoOrder.getQuantity(), pair, exmoOrder.getOrderId(), new Date(exmoOrder.getCreated() * 1000), exmoOrder.getPrice()));
      }
    }
    return new OpenOrders(limitOrders);
  }

  public static UserTrades adaptTradeHistory(Map<String, List<ExmoUserTrade>> exmoUserTrades) {

    List<UserTrade> trades = new ArrayList<UserTrade>();
    long lastTradeId = 0;

    for (Map.Entry<String, List<ExmoUserTrade>> entry : exmoUserTrades.entrySet()) {
      CurrencyPair pair = toCurrencyPair(entry.getKey());
      for (ExmoUserTrade exmoTrade : entry.getValue()) {
        if (exmoTrade.getTradeId() > lastTradeId) {
          lastTradeId = exmoTrade.getTradeId();
        }

        OrderType type = "buy".equals(exmoTrade.getType()) ? OrderType.BID : OrderType.ASK;
        trades.add(new UserTrade(type, exmoTrade.getQuantity(), pair, exmoTrade.getPrice(), new Date(exmoTrade.getDate() * 1000),
                String.valueOf(exmoTrade.getTradeId()), String.valueOf(exmoTrade.getOrderId()), null, null));
      }
    }

    return new UserTrades(trades, lastTradeId, TradeSortType.SortByID);
  }

}
