package org.knowm.xchange.cexio;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.knowm.xchange.cexio.dto.account.CexIOBalance;
import org.knowm.xchange.cexio.dto.account.CexIOBalanceInfo;
import org.knowm.xchange.cexio.dto.marketdata.CexIODepth;
import org.knowm.xchange.cexio.dto.marketdata.CexIOTicker;
import org.knowm.xchange.cexio.dto.marketdata.CexIOTrade;
import org.knowm.xchange.cexio.dto.trade.CexIOOrder;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.marketdata.Trades.TradeSortType;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.utils.DateUtils;

/**
 * Author: brox Since: 2/6/14
 */

public class CexIOAdapters {

  /**
   * Adapts a CexIOTrade to a Trade Object
   *
   * @param trade CexIO trade object
   * @param currencyPair trade currencies
   * @return The XChange Trade
   */
  public static Trade adaptTrade(CexIOTrade trade, CurrencyPair currencyPair) {

    BigDecimal amount = trade.getAmount();
    BigDecimal price = trade.getPrice();
    Date date = DateUtils.fromMillisUtc(trade.getDate() * 1000L);
    // Cex.IO API does not return trade type
    return new Trade(null, amount, currencyPair, price, date, String.valueOf(trade.getTid()));
  }

  /**
   * Adapts a CexIOTrade[] to a Trades Object
   *
   * @param cexioTrades The CexIO trade data returned by API
   * @param currencyPair trade currencies
   * @return The trades
   */
  public static Trades adaptTrades(CexIOTrade[] cexioTrades, CurrencyPair currencyPair) {

    List<Trade> tradesList = new ArrayList<Trade>();
    long lastTradeId = 0;
    for (CexIOTrade trade : cexioTrades) {
      long tradeId = trade.getTid();
      if (tradeId > lastTradeId) {
        lastTradeId = tradeId;
      }
      // Date is reversed order. Insert at index 0 instead of appending
      tradesList.add(0, adaptTrade(trade, currencyPair));
    }
    return new Trades(tradesList, lastTradeId, TradeSortType.SortByID);
  }

  /**
   * Adapts a CexIOTicker to a Ticker Object
   *
   * @param ticker The exchange specific ticker
   * @param currencyPair The currency pair (e.g. BTC/USD)
   * @return The ticker
   */
  public static Ticker adaptTicker(CexIOTicker ticker, CurrencyPair currencyPair) {

    BigDecimal last = ticker.getLast();
    BigDecimal bid = ticker.getBid();
    BigDecimal ask = ticker.getAsk();
    BigDecimal high = ticker.getHigh();
    BigDecimal low = ticker.getLow();
    BigDecimal volume = ticker.getVolume();
    Date timestamp = new Date(ticker.getTimestamp() * 1000L);

    return new Ticker.Builder().currencyPair(currencyPair).last(last).bid(bid).ask(ask).high(high).low(low).volume(volume).timestamp(timestamp)
        .build();
  }

  /**
   * Adapts Cex.IO Depth to OrderBook Object
   *
   * @param depth Cex.IO order book
   * @param currencyPair The currency pair (e.g. BTC/USD)
   * @return The XChange OrderBook
   */
  public static OrderBook adaptOrderBook(CexIODepth depth, CurrencyPair currencyPair) {

    List<LimitOrder> asks = createOrders(currencyPair, Order.OrderType.ASK, depth.getAsks());
    List<LimitOrder> bids = createOrders(currencyPair, Order.OrderType.BID, depth.getBids());
    Date date = new Date(depth.getTimestamp() * 1000);
    return new OrderBook(date, asks, bids);
  }

  /**
   * Adapts CexIOBalanceInfo to Wallet
   *
   * @param balance CexIOBalanceInfo balance
   * @return The account info
   */
  public static Wallet adaptWallet(CexIOBalanceInfo balance) {

    List<Balance> balances = new ArrayList<Balance>();

    // Adapt to XChange DTOs
    if (balance.getBalanceBCH() != null) {
      balances.add(adaptBalance(new Currency("BCH"), balance.getBalanceBCH()));
    }
    if (balance.getBalanceBTC() != null) {
      balances.add(adaptBalance(Currency.BTC, balance.getBalanceBTC()));
    }
    if (balance.getBalanceEUR() != null) {
      balances.add(adaptBalance(Currency.EUR, balance.getBalanceEUR()));
    }
    if (balance.getBalanceGBP() != null) {
      balances.add(adaptBalance(Currency.GBP, balance.getBalanceGBP()));
    }
    if (balance.getBalanceUSD() != null) {
      balances.add(adaptBalance(Currency.USD, balance.getBalanceUSD()));
    }
    if (balance.getBalanceRUB() != null) {
      balances.add(adaptBalance(Currency.RUB, balance.getBalanceRUB()));
    }
    if (balance.getBalanceDASH() != null) {
      balances.add(adaptBalance(new Currency("DASH"), balance.getBalanceDASH()));
    }
    if (balance.getBalanceGHS() != null) {
      balances.add(adaptBalance(Currency.GHS, balance.getBalanceGHS()));
    }
    if (balance.getBalanceZEC() != null) {
      balances.add(adaptBalance(new Currency("ZEC"), balance.getBalanceZEC()));
    }
    if (balance.getBalanceETH() != null) {
      balances.add(adaptBalance(Currency.ETH, balance.getBalanceETH()));
    }

    return new Wallet(balances);
  }

  public static Balance adaptBalance(Currency currency, CexIOBalance balance) {
    return new Balance(currency, null, balance.getAvailable(), balance.getOrders());
  }

  public static List<LimitOrder> createOrders(CurrencyPair currencyPair, Order.OrderType orderType, List<List<BigDecimal>> orders) {

    List<LimitOrder> limitOrders = new ArrayList<LimitOrder>();
    for (List<BigDecimal> o : orders) {
      checkArgument(o.size() == 2, "Expected a pair (price, amount) but got {0} elements.", o.size());
      limitOrders.add(createOrder(currencyPair, o, orderType));
    }
    return limitOrders;
  }

  public static LimitOrder createOrder(CurrencyPair currencyPair, List<BigDecimal> priceAndAmount, Order.OrderType orderType) {

    return new LimitOrder(orderType, priceAndAmount.get(1), currencyPair, "", null, priceAndAmount.get(0));
  }

  public static void checkArgument(boolean argument, String msgPattern, Object... msgArgs) {

    if (!argument) {
      throw new IllegalArgumentException(MessageFormat.format(msgPattern, msgArgs));
    }
  }

  public static OpenOrders adaptOpenOrders(List<CexIOOrder> cexIOOrderList) {

    List<LimitOrder> limitOrders = new ArrayList<LimitOrder>();

    for (CexIOOrder cexIOOrder : cexIOOrderList) {
      Order.OrderType orderType = cexIOOrder.getType() == CexIOOrder.Type.buy ? Order.OrderType.BID : Order.OrderType.ASK;
      String id = Long.toString(cexIOOrder.getId());
      limitOrders.add(new LimitOrder(orderType, cexIOOrder.getPending(),
          new CurrencyPair(cexIOOrder.getTradableIdentifier(), cexIOOrder.getTransactionCurrency()), id,
          DateUtils.fromMillisUtc(cexIOOrder.getTime()), cexIOOrder.getPrice()));
    }

    return new OpenOrders(limitOrders);

  }

}
