package org.knowm.xchange.bl3p;

import org.knowm.xchange.bl3p.dto.account.Bl3pAccountInfo;
import org.knowm.xchange.bl3p.dto.Bl3pValue;
import org.knowm.xchange.bl3p.dto.marketdata.Bl3pOrderbook;
import org.knowm.xchange.bl3p.dto.marketdata.Bl3pTicker;
import org.knowm.xchange.bl3p.dto.marketdata.Bl3pTrade;
import org.knowm.xchange.bl3p.dto.marketdata.Bl3pTrades;
import org.knowm.xchange.bl3p.dto.trade.Bl3pOpenOrder;
import org.knowm.xchange.bl3p.dto.trade.Bl3pOpenOrders;
import com.xeiam.xchange.currency.*;
import com.xeiam.xchange.currency.Currency;
import com.xeiam.xchange.dto.Order;
import com.xeiam.xchange.dto.account.AccountInfo;
import com.xeiam.xchange.dto.account.Balance;
import com.xeiam.xchange.dto.account.Wallet;
import com.xeiam.xchange.dto.marketdata.OrderBook;
import com.xeiam.xchange.dto.marketdata.Ticker;
import com.xeiam.xchange.dto.marketdata.Trade;
import com.xeiam.xchange.dto.marketdata.Trades;
import com.xeiam.xchange.dto.trade.LimitOrder;
import com.xeiam.xchange.dto.trade.OpenOrders;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 14/08/15
 * Time: 19:30
 */
public class Bl3pAdapters {

  public static final Ticker adaptBl3pTicker(Bl3pTicker bl3pTicker, CurrencyPair currencyPair) {

    BigDecimal last = bl3pTicker.getLast();
    BigDecimal bid = bl3pTicker.getBid();
    BigDecimal ask = bl3pTicker.getAsk();
    BigDecimal high = bl3pTicker.getHigh();
    BigDecimal low = bl3pTicker.getLow();
    BigDecimal volume = bl3pTicker.getVolume().getH24();
    Date timestamp = new Date(bl3pTicker.getTimestamp() * 1000);

    return new Ticker.Builder().currencyPair(currencyPair).last(last).bid(bid).ask(ask).high(high).low(low).volume(volume).timestamp(timestamp)
        .build();
  }

  public static final OrderBook adaptBl3pOrderbook(Bl3pOrderbook bl3pOrderbook, CurrencyPair currencyPair) {

    OrderBook orderBook = new OrderBook(null, adaptDepths(bl3pOrderbook.getAsks(), Order.OrderType.ASK, currencyPair),
        adaptDepths(bl3pOrderbook.getBids(), Order.OrderType.BID, currencyPair));

    return orderBook;

  }

  public static final List<LimitOrder> adaptDepths(Bl3pTrade[] bl3pTrades, Order.OrderType orderType, CurrencyPair currencyPair) {

    List<LimitOrder> limitOrders = new ArrayList<LimitOrder>();

    if (bl3pTrades != null) {
      for (Bl3pTrade bl3pTrade : bl3pTrades) {
        LimitOrder order = new LimitOrder(orderType, bl3pTrade.getAmount().scaleByPowerOfTen(-8), currencyPair, null, new Date(), bl3pTrade.getPrice().scaleByPowerOfTen(-5));
        limitOrders.add(order);
      }
    }

    return limitOrders;
  }

  public static final Trades adaptBl3pTrades(Bl3pTrades bl3pTrades, CurrencyPair currencyPair) {

    List<Trade> trades = new ArrayList<Trade>();
    long lastId = 0l;

    if (bl3pTrades != null && bl3pTrades.getTrades() != null) {
      for (Bl3pTrade bl3pTrade : bl3pTrades.getTrades()) {
        Trade trade = new Trade(null,
            bl3pTrade.getAmount().scaleByPowerOfTen(-8),
            currencyPair,
            bl3pTrade.getPrice().scaleByPowerOfTen(-5),
            new Date(bl3pTrade.getDate()),
            String.valueOf(bl3pTrade.getTradeId()));
        trades.add(trade);
        lastId = bl3pTrade.getTradeId();
      }
    }

    return new Trades(trades, lastId, Trades.TradeSortType.SortByTimestamp);
  }

  public static AccountInfo adaptAccount(Bl3pAccountInfo bl3pAccountInfo) {
    List<Balance> balances = new ArrayList<Balance>();
    for (Map<String, Bl3pValue> stringBl3pWalletMap : bl3pAccountInfo.getWallets().values()) {

      Bl3pValue available = stringBl3pWalletMap.get("available");
      Bl3pValue balance = stringBl3pWalletMap.get("balance");

      Currency currency = Currency.getInstance(available.getCurrency());
      balances.add(new Balance(currency, balance.getValue(), available.getValue()));
    }

    return new AccountInfo(new Wallet(balances));
  }

  public static OpenOrders adaptOpenOprder(Bl3pOpenOrders bl3pOpenOrders) {

    List<LimitOrder> limitOrders = new ArrayList<LimitOrder>();

    for (Bl3pOpenOrder bl3pOpenOrder : bl3pOpenOrders.getOrders()) {
      Order.OrderType orderType = Order.OrderType.valueOf(bl3pOpenOrder.getType().toUpperCase());
      BigDecimal amount = bl3pOpenOrder.getAmount().getValue();
      BigDecimal price = bl3pOpenOrder.getPrice().getValue();
      Date date = new Date(bl3pOpenOrder.getDate() * 1000);
      String id = String.valueOf(bl3pOpenOrder.getOrderId());
      CurrencyPair pair = new CurrencyPair(bl3pOpenOrder.getItem(), bl3pOpenOrder.getCurrency());

      limitOrders.add(new LimitOrder(orderType, amount, pair, id, date, price));
    }
    return new OpenOrders(limitOrders);
  }
}
