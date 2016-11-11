package org.knowm.xchange.mercadobitcoin;

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
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrade;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.mercadobitcoin.dto.MercadoBitcoinBaseTradeApiResult;
import org.knowm.xchange.mercadobitcoin.dto.account.MercadoBitcoinAccountInfo;
import org.knowm.xchange.mercadobitcoin.dto.marketdata.MercadoBitcoinOrderBook;
import org.knowm.xchange.mercadobitcoin.dto.marketdata.MercadoBitcoinTicker;
import org.knowm.xchange.mercadobitcoin.dto.marketdata.MercadoBitcoinTransaction;
import org.knowm.xchange.mercadobitcoin.dto.trade.MercadoBitcoinUserOrders;
import org.knowm.xchange.mercadobitcoin.dto.trade.MercadoBitcoinUserOrdersEntry;
import org.knowm.xchange.mercadobitcoin.dto.trade.OperationEntry;
import org.knowm.xchange.mercadobitcoin.dto.v3.account.MercadoBitcoinAccount;
import org.knowm.xchange.mercadobitcoin.dto.v3.trade.MercadoBitcoinOperation;
import org.knowm.xchange.mercadobitcoin.dto.v3.trade.MercadoBitcoinOrder;
import org.knowm.xchange.mercadobitcoin.dto.v3.trade.MercadoBitcoinOrdersResponse;
import org.knowm.xchange.utils.DateUtils;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.*;

import static org.knowm.xchange.utils.DateUtils.fromUnixTime;

/**
 * Various adapters for converting from Mercado Bitcoin DTOs to XChange DTOs
 *
 * @author Felipe Micaroni Lalli
 */
public final class MercadoBitcoinAdaptersV3 {

  /**
   * private Constructor
   */
  private MercadoBitcoinAdaptersV3() {

  }

  public static AccountInfo adaptAccountInfo(MercadoBitcoinAccount mercadoBitcoinAccount) {

    List<Balance> balances = new ArrayList<>();

    // Adapt to XChange DTOs
    for (Map.Entry<String, MercadoBitcoinAccount.Balance> entry : mercadoBitcoinAccount.getBalances().entrySet()) {
      balances.add(new Balance(Currency.getInstance(entry.getKey()), entry.getValue().getTotal()));
    }

    return new AccountInfo(new Wallet(balances));
  }


  public static List<LimitOrder> adaptOrders(List<MercadoBitcoinOrder> mercadoBitcoinOrders) {

    List<LimitOrder> limitOrders = new ArrayList<>();

    for (MercadoBitcoinOrder mercadoBitcoinOrder : mercadoBitcoinOrders) {
      limitOrders.add(adaprtOrder(mercadoBitcoinOrder));
    }
    return limitOrders;
  }

  public static LimitOrder adaprtOrder(MercadoBitcoinOrder mercadoBitcoinOrder) {
    Order.OrderType orderType = mercadoBitcoinOrder.getOrderType() == 1 ? Order.OrderType.BID : Order.OrderType.ASK;
    Date date = DateUtils.fromUnixTime(mercadoBitcoinOrder.getCreatedTimestamp());

    LimitOrder.Builder builder = new LimitOrder.Builder(orderType, adaptPair(mercadoBitcoinOrder.getCoinPair()))
        .limitPrice(mercadoBitcoinOrder.getLimitPrice())
        .tradableAmount(mercadoBitcoinOrder.getQuantity())
        .timestamp(date)
        .id(String.valueOf(mercadoBitcoinOrder.getOrderId()));
    return builder.build();
  }

  public static UserTrades toUserTrades(MercadoBitcoinOrdersResponse orders) {

    List<UserTrade> result = new LinkedList<>();

    for (MercadoBitcoinOrder mercadoBitcoinOrder : orders.getOrders()) {
      Order.OrderType orderType = mercadoBitcoinOrder.getOrderType() == 1 ? Order.OrderType.BID : Order.OrderType.ASK;

      for (MercadoBitcoinOperation mercadoBitcoinOperation : mercadoBitcoinOrder.getOperations()) {
        UserTrade userTrade = new UserTrade.Builder()
            .currencyPair(adaptPair(mercadoBitcoinOrder.getCoinPair()))
            .id(String.valueOf(mercadoBitcoinOperation.getOperationId()))
            .orderId(String.valueOf(mercadoBitcoinOrder.getOrderId()))
            .price(mercadoBitcoinOperation.getPrice())
            .timestamp(fromUnixTime(mercadoBitcoinOperation.getExecutedTimestamp()))
            .tradableAmount(mercadoBitcoinOperation.getQuantity())
            .type(orderType)
//            .feeAmount(orderType)
            .build();

        result.add(userTrade);
      }
    }
    return new UserTrades(result, Trades.TradeSortType.SortByID);
  }

  public static CurrencyPair adaptPair(String mercadoPair) {
    String counter = mercadoPair.substring(0, 3);
    String base = mercadoPair.substring(3, 6);
    return new CurrencyPair(base, counter);
  }

  public static String adaptPair(CurrencyPair currencyPair) {
    return "" + currencyPair.counter + currencyPair.base;
  }
}
