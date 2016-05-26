package org.knowm.xchange.quoine;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.Order.OrderType;
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
import org.knowm.xchange.quoine.dto.account.CryptoAccount;
import org.knowm.xchange.quoine.dto.account.FiatAccount;
import org.knowm.xchange.quoine.dto.account.QuoineAccountInfo;
import org.knowm.xchange.quoine.dto.account.QuoineTradingAccountInfo;
import org.knowm.xchange.quoine.dto.marketdata.QuoineOrderBook;
import org.knowm.xchange.quoine.dto.marketdata.QuoineProduct;
import org.knowm.xchange.quoine.dto.marketdata.QuoineTrade;
import org.knowm.xchange.quoine.dto.marketdata.QuoineTradesList;
import org.knowm.xchange.quoine.dto.trade.Model;
import org.knowm.xchange.quoine.dto.trade.QuoineOrdersList;
import org.knowm.xchange.utils.DateUtils;

public class QuoineAdapters {

  public static Ticker adaptTicker(QuoineProduct quoineTicker, CurrencyPair currencyPair) {

    Ticker.Builder builder = new Ticker.Builder();
    builder.ask(quoineTicker.getMarketAsk());
    builder.bid(quoineTicker.getMarketBid());
    builder.last(quoineTicker.getLastTradedPrice());
    builder.volume(quoineTicker.getVolume24h());
    builder.currencyPair(currencyPair);
    return builder.build();
  }

  public static OrderBook adaptOrderBook(QuoineOrderBook quoineOrderBook, CurrencyPair currencyPair) {

    List<LimitOrder> asks = createOrders(currencyPair, Order.OrderType.ASK, quoineOrderBook.getSellPriceLevels());
    List<LimitOrder> bids = createOrders(currencyPair, Order.OrderType.BID, quoineOrderBook.getBuyPriceLevels());
    return new OrderBook(null, asks, bids);
  }

  public static List<LimitOrder> createOrders(CurrencyPair currencyPair, Order.OrderType orderType, List<List<BigDecimal>> orders) {

    List<LimitOrder> limitOrders = new ArrayList<LimitOrder>();
    for (List<BigDecimal> ask : orders) {
      checkArgument(ask.size() == 2, "Expected a pair (price, amount) but got {0} elements.", ask.size());
      limitOrders.add(createOrder(currencyPair, ask, orderType));
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

  public static Wallet adaptTradingWallet(QuoineTradingAccountInfo[] quoineWallet) {
    List<Balance> balances = new ArrayList<Balance>(quoineWallet.length);

    // btc position is sum of all positions in margin. Asuming all currencies are using the same margin level.
    BigDecimal btcPosition = BigDecimal.ZERO;

    for (int i = 0; i < quoineWallet.length; i++) {
      QuoineTradingAccountInfo info = quoineWallet[i];

      balances.add(new Balance(Currency.getInstance(info.getCollateralCurrency()), info.getFreeMargin()));

      btcPosition = btcPosition.add(info.getPosition());
    }

    balances.add(new Balance(Currency.BTC, btcPosition));

    return new Wallet(balances);
  }

  public static Wallet adaptWallet(QuoineAccountInfo quoineWallet) {

    List<Balance> balances = new ArrayList<Balance>();

    // Adapt to XChange DTOs
    CryptoAccount[] cryptoAccounts = quoineWallet.getCryptoAccounts();
    if (cryptoAccounts != null) {
      for (CryptoAccount cryptoAccount : cryptoAccounts) {
        if (cryptoAccount != null) {
          Balance fiatBalance = new Balance(Currency.getInstance(cryptoAccount.getCurrency()), cryptoAccount.getBalance());
          balances.add(fiatBalance);
        }
      }
    }
    FiatAccount[] fiatAccounts = quoineWallet.getFiatAccounts();
    if (fiatAccounts != null) {
      for (FiatAccount fiatAccount : fiatAccounts) {
        if (fiatAccount != null) {
          Balance fiatBalance;
          if (fiatAccount.getFreeBalance() != null) {
            fiatBalance = new Balance(Currency.getInstance(fiatAccount.getCurrency()), fiatAccount.getBalance(), fiatAccount.getFreeBalance());
          } else {
            fiatBalance = new Balance(Currency.getInstance(fiatAccount.getCurrency()), fiatAccount.getBalance());
          }
          balances.add(fiatBalance);
        }
      }
    }

    return new Wallet(balances);

  }

  public static OpenOrders adapteOpenOrders(QuoineOrdersList quoineOrdersList) {

    List<LimitOrder> openOrders = new ArrayList<LimitOrder>();
    for (Model model : quoineOrdersList.getModels()) {
      if (model.getStatus().equals("live")) {

        // currencey pair
        String baseSymbol = model.getCurrencyPairCode().substring(0, 3);
        String counterSymbol = model.getCurrencyPairCode().substring(3, 6);
        CurrencyPair currencyPair = new CurrencyPair(baseSymbol, counterSymbol);

        // OrderType
        OrderType orderType = model.getSide().equals("sell") ? OrderType.ASK : OrderType.BID;

        // Timestamp
        Date timestamp = new Date(model.getCreatedAt().longValue() * 1000L);

        LimitOrder limitOrder = new LimitOrder(orderType, model.getQuantity(), currencyPair, model.getId(), timestamp, model.getPrice());

        openOrders.add(limitOrder);
      }
    }

    return new OpenOrders(openOrders);
  }


  public static Trades adaptTrades(QuoineTradesList tradesList, CurrencyPair currencyPair) {

    List<Trade> trades = new ArrayList<Trade>();
    long lastTradeId = 0;

    if (tradesList != null && tradesList.getTrades() != null) {
      for (QuoineTrade quoineTrade : tradesList.getTrades()) {
        final long tradeId = quoineTrade.getId();
        if (tradeId > lastTradeId) {
          lastTradeId = tradeId;
        }
        OrderType type = quoineTrade.getTakerSide() == "ask" ? OrderType.ASK : OrderType.BID;
        trades.add(new Trade(type, quoineTrade.getQuantity(), currencyPair, quoineTrade.getPrice(),
            DateUtils.fromMillisUtc(Long.valueOf(quoineTrade.getCreatedAt()) * 1000L), String.valueOf(tradeId)));
      }
    }

    return new Trades(trades, lastTradeId, Trades.TradeSortType.SortByID);
  }

  public static UserTrades adaptUserTrades(QuoineTradesList tradesList, CurrencyPair currencyPair) {

    List<UserTrade> trades = new ArrayList<UserTrade>();
    long lastTradeId = 0;

    if (tradesList != null && tradesList.getTrades() != null) {
      for (QuoineTrade quoineTrade : tradesList.getTrades()) {
        final long tradeId = quoineTrade.getId();
        if (tradeId > lastTradeId) {
          lastTradeId = tradeId;
        }
        OrderType type = "sell".equals(quoineTrade.getTakerSide()) ? OrderType.ASK : OrderType.BID;

        trades.add(new UserTrade(type, quoineTrade.getQuantity(), currencyPair, quoineTrade.getPrice(),
            DateUtils.fromMillisUtc(Long.valueOf(quoineTrade.getCreatedAt()) * 1000L), String.valueOf(tradeId), null, null, null));
      }
    }

    return new UserTrades(trades, lastTradeId, Trades.TradeSortType.SortByID);
  }
}
