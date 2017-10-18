package org.knowm.xchange.therock;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrade;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.therock.dto.account.TheRockBalance;
import org.knowm.xchange.therock.dto.marketdata.TheRockBid;
import org.knowm.xchange.therock.dto.marketdata.TheRockOrderBook;
import org.knowm.xchange.therock.dto.marketdata.TheRockTrade;
import org.knowm.xchange.therock.dto.marketdata.TheRockTrades;
import org.knowm.xchange.therock.dto.trade.TheRockOrder;
import org.knowm.xchange.therock.dto.trade.TheRockOrders;
import org.knowm.xchange.therock.dto.trade.TheRockUserTrade;
import org.knowm.xchange.therock.dto.trade.TheRockUserTrades;


public final class TheRockAdapters {
  public static TheRockOrder.Side adaptSide(Order.OrderType type) {

    return type == Order.OrderType.BID ? TheRockOrder.Side.buy : TheRockOrder.Side.sell;
  }

  public static AccountInfo adaptAccountInfo(List<TheRockBalance> trBalances, String userName) {

    ArrayList<Balance> balances = new ArrayList(trBalances.size());

    for (TheRockBalance blc : trBalances) {

      Currency currency = Currency.getInstance(blc.getCurrency());

      balances.add(new Balance(currency, blc.getBalance(), blc.getTradingBalance()));
    }

    return new AccountInfo(userName, new Wallet[]{new Wallet(balances)});
  }

  public static OrderBook adaptOrderBook(TheRockOrderBook theRockOrderBook) {

    List<LimitOrder> asks = new ArrayList();

    List<LimitOrder> bids = new ArrayList();

    for (TheRockBid theRockBid : theRockOrderBook.getAsks()) {

      asks.add(adaptBid(theRockOrderBook.getCurrencyPair(), Order.OrderType.ASK, theRockBid, theRockOrderBook.getDate()));
    }

    for (TheRockBid theRockBid : theRockOrderBook.getBids()) {

      bids.add(adaptBid(theRockOrderBook.getCurrencyPair(), Order.OrderType.BID, theRockBid, theRockOrderBook.getDate()));
    }

    return new OrderBook(theRockOrderBook.getDate(), asks, bids);
  }

  private static LimitOrder adaptBid(CurrencyPair currencyPair, Order.OrderType orderType, TheRockBid theRockBid, Date timestamp) {

    return new LimitOrder.Builder(orderType, currencyPair).limitPrice(theRockBid.getPrice()).tradableAmount(theRockBid.getAmount()).timestamp(timestamp).build();
  }

  public static Trades adaptTrades(TheRockTrades trades, CurrencyPair currencyPair) throws InvalidFormatException {
    List<Trade> tradesList = new ArrayList(trades.getCount());
    long lastTradeId = 0L;
    for (int i = 0; i < trades.getCount(); i++) {
      TheRockTrade trade = trades.getTrades()[i];
      if ((trade.getSide() == TheRockTrade.Side.buy) || (trade.getSide() == TheRockTrade.Side.sell)) {

        long tradeId = trade.getId();
        if (tradeId > lastTradeId) {
          lastTradeId = tradeId;
        }
        tradesList.add(adaptTrade(trade, currencyPair));
      }
    }

    return new Trades(tradesList, lastTradeId, Trades.TradeSortType.SortByID);
  }

  public static Trade adaptTrade(TheRockTrade trade, CurrencyPair currencyPair) throws InvalidFormatException {

    String tradeId = String.valueOf(trade.getId());

    return new Trade(trade.getSide() == TheRockTrade.Side.sell ? Order.OrderType.ASK : Order.OrderType.BID, trade.getAmount(), currencyPair, trade.getPrice(), trade.getDate(), tradeId);
  }

  public static UserTrade adaptUserTrade(TheRockUserTrade trade, CurrencyPair currencyPair) throws InvalidFormatException {

    String tradeId = String.valueOf(trade.getId());


    return new UserTrade.Builder().id(tradeId).tradableAmount(trade.getAmount()).currencyPair(currencyPair).price(trade.getPrice())
        .timestamp(trade.getDate()).orderId(String.valueOf(trade.getOrderId()))
        .type(trade.getSide() == TheRockTrade.Side.buy ? Order.OrderType.BID : Order.OrderType.ASK)
        .feeAmount(trade.getFeeAmount()).feeCurrency(trade.getFeeCurrency() == null ? null : new Currency(trade.getFeeCurrency()))
        .build();
  }

  public static UserTrades adaptUserTrades(TheRockUserTrades trades, CurrencyPair currencyPair) throws InvalidFormatException {

    List<UserTrade> tradesList = new ArrayList(trades.getCount());

    long lastTradeId = 0L;

    for (int i = 0; i < trades.getCount(); i++) {

      TheRockUserTrade trade = trades.getTrades()[i];

      long tradeId = trade.getId();

      if (tradeId > lastTradeId) {
        lastTradeId = tradeId;
      }
      tradesList.add(adaptUserTrade(trade, currencyPair));
    }
    return new UserTrades(tradesList, lastTradeId, Trades.TradeSortType.SortByID);
  }

  public static LimitOrder adaptOrder(TheRockOrder o) {
    return new LimitOrder(adaptOrderType(o.getSide()), o.getAmount(), o.getFundId().pair,
        Long.toString(o.getId().longValue()), null, o.getPrice());
  }

  public static Order.OrderType adaptOrderType(TheRockOrder.Side orderSide) {

    return orderSide.equals(TheRockOrder.Side.buy) ? Order.OrderType.BID : Order.OrderType.ASK;
  }

  public static OpenOrders adaptOrders(TheRockOrders theRockOrders) {

    List<LimitOrder> orders = new ArrayList(theRockOrders.getOrders().length);

    for (TheRockOrder theRockOrder : theRockOrders.getOrders()) {

      orders.add(adaptOrder(theRockOrder));
    }

    return new OpenOrders(orders);
  }
}