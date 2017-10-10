package org.knowm.xchange.btce.v3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.knowm.xchange.btce.v3.dto.account.WEXAccountInfo;
import org.knowm.xchange.btce.v3.dto.marketdata.WEXExchangeInfo;
import org.knowm.xchange.btce.v3.dto.marketdata.WEXPairInfo;
import org.knowm.xchange.btce.v3.dto.marketdata.WEXTicker;
import org.knowm.xchange.btce.v3.dto.marketdata.WEXTrade;
import org.knowm.xchange.btce.v3.dto.trade.WEXOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.knowm.xchange.btce.v3.dto.meta.WEXMetaData;
import org.knowm.xchange.btce.v3.dto.trade.WEXTradeHistoryResult;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.marketdata.Trades.TradeSortType;
import org.knowm.xchange.dto.meta.CurrencyMetaData;
import org.knowm.xchange.dto.meta.ExchangeMetaData;
import org.knowm.xchange.dto.meta.MarketMetaData;
import org.knowm.xchange.dto.meta.RateLimit;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrade;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.utils.DateUtils;

/**
 * Various adapters for converting from WEX DTOs to XChange DTOs
 */
public final class WEXAdapters {

  public static final Logger log = LoggerFactory.getLogger(WEXAdapters.class);

  /**
   * private Constructor
   */
  private WEXAdapters() {

  }

  /**
   * Adapts a List of BTCEOrders to a List of LimitOrders
   *
   * @param bTCEOrders
   * @param currencyPair
   * @param orderTypeString
   * @param id
   * @return
   */
  public static List<LimitOrder> adaptOrders(List<BigDecimal[]> bTCEOrders, CurrencyPair currencyPair, String orderTypeString, String id) {

    List<LimitOrder> limitOrders = new ArrayList<LimitOrder>();
    OrderType orderType = orderTypeString.equalsIgnoreCase("bid") ? OrderType.BID : OrderType.ASK;

    for (BigDecimal[] btceOrder : bTCEOrders) {
      limitOrders.add(adaptOrder(btceOrder[1], btceOrder[0], currencyPair, orderType, id));
    }

    return limitOrders;
  }

  /**
   * Adapts a WEXOrder to a LimitOrder
   *
   * @param amount
   * @param price
   * @param currencyPair
   * @param orderType
   * @param id
   * @return
   */
  public static LimitOrder adaptOrder(BigDecimal amount, BigDecimal price, CurrencyPair currencyPair, OrderType orderType, String id) {

    return new LimitOrder(orderType, amount, currencyPair, id, null, price);
  }

  /**
   * Adapts a BTCETradeV3 to a Trade Object
   *
   * @param bTCETrade WEX trade object v.3
   * @param currencyPair the currency pair
   * @return The XChange Trade
   */
  public static Trade adaptTrade(WEXTrade bTCETrade, CurrencyPair currencyPair) {

    OrderType orderType = bTCETrade.getTradeType().equalsIgnoreCase("bid") ? OrderType.BID : OrderType.ASK;
    BigDecimal amount = bTCETrade.getAmount();
    BigDecimal price = bTCETrade.getPrice();
    Date date = DateUtils.fromMillisUtc(bTCETrade.getDate() * 1000L);

    final String tradeId = String.valueOf(bTCETrade.getTid());
    return new Trade(orderType, amount, currencyPair, price, date, tradeId);
  }

  /**
   * Adapts a BTCETradeV3[] to a Trades Object
   *
   * @param bTCETrades The WEX trade data returned by API v.3
   * @param currencyPair the currency pair
   * @return The trades
   */
  public static Trades adaptTrades(WEXTrade[] bTCETrades, CurrencyPair currencyPair) {

    List<Trade> tradesList = new ArrayList<Trade>();
    long lastTradeId = 0;
    for (WEXTrade bTCETrade : bTCETrades) {
      // Date is reversed order. Insert at index 0 instead of appending
      long tradeId = bTCETrade.getTid();
      if (tradeId > lastTradeId) {
        lastTradeId = tradeId;
      }
      tradesList.add(0, adaptTrade(bTCETrade, currencyPair));
    }
    return new Trades(tradesList, lastTradeId, TradeSortType.SortByID);
  }

  /**
   * Adapts a WEXTicker to a Ticker Object
   *
   * @param bTCETicker
   * @return
   */
  public static Ticker adaptTicker(WEXTicker bTCETicker, CurrencyPair currencyPair) {

    BigDecimal last = bTCETicker.getLast();
    BigDecimal bid = bTCETicker.getSell();
    BigDecimal ask = bTCETicker.getBuy();
    BigDecimal high = bTCETicker.getHigh();
    BigDecimal low = bTCETicker.getLow();
    BigDecimal avg = bTCETicker.getAvg();
    BigDecimal volume = bTCETicker.getVolCur();
    Date timestamp = DateUtils.fromMillisUtc(bTCETicker.getUpdated() * 1000L);

    return new Ticker.Builder().currencyPair(currencyPair).last(last).bid(bid).ask(ask).high(high).low(low).vwap(avg).volume(volume)
        .timestamp(timestamp).build();
  }

  public static Wallet adaptWallet(WEXAccountInfo WEXAccountInfo) {

    List<Balance> balances = new ArrayList<Balance>();
    Map<String, BigDecimal> funds = WEXAccountInfo.getFunds();

    for (String lcCurrency : funds.keySet()) {
      /* BTC-E signals DASH as DSH. This is a different coin. Translate in correct DASH name */ 
      BigDecimal fund = funds.get(lcCurrency);
      if (lcCurrency.equals("dsh")) {
    	  lcCurrency = "dash";
      }
      Currency currency = Currency.getInstance(lcCurrency);
      balances.add(new Balance(currency, fund));
    }
    return new Wallet(balances);
  }

  public static OpenOrders adaptOrders(Map<Long, WEXOrder> btceOrderMap) {

    List<LimitOrder> limitOrders = new ArrayList<LimitOrder>();
    for (Long id : btceOrderMap.keySet()) {
      WEXOrder bTCEOrder = btceOrderMap.get(id);
      OrderType orderType = bTCEOrder.getType() == WEXOrder.Type.buy ? OrderType.BID : OrderType.ASK;
      BigDecimal price = bTCEOrder.getRate();
      Date timestamp = DateUtils.fromMillisUtc(bTCEOrder.getTimestampCreated() * 1000L);
      CurrencyPair currencyPair = adaptCurrencyPair(bTCEOrder.getPair());

      limitOrders.add(new LimitOrder(orderType, bTCEOrder.getAmount(), currencyPair, Long.toString(id), timestamp, price));
    }
    return new OpenOrders(limitOrders);
  }

  public static UserTrades adaptTradeHistory(Map<Long, WEXTradeHistoryResult> tradeHistory) {

    List<UserTrade> trades = new ArrayList<UserTrade>(tradeHistory.size());
    for (Entry<Long, WEXTradeHistoryResult> entry : tradeHistory.entrySet()) {
      WEXTradeHistoryResult result = entry.getValue();
      OrderType type = result.getType() == WEXTradeHistoryResult.Type.buy ? OrderType.BID : OrderType.ASK;
      BigDecimal price = result.getRate();
      BigDecimal tradableAmount = result.getAmount();
      Date timeStamp = DateUtils.fromMillisUtc(result.getTimestamp() * 1000L);
      String orderId = String.valueOf(result.getOrderId());
      String tradeId = String.valueOf(entry.getKey());
      CurrencyPair currencyPair = adaptCurrencyPair(result.getPair());
      trades.add(new UserTrade(type, tradableAmount, currencyPair, price, timeStamp, tradeId, orderId, null, (Currency) null));
    }
    return new UserTrades(trades, TradeSortType.SortByTimestamp);
  }

  public static CurrencyPair adaptCurrencyPair(String btceCurrencyPair) {

    String[] currencies = btceCurrencyPair.split("_");
    /* BTC-E signals DASH as DSH. This is a different coin. Translate in correct DASH name */ 
    if (currencies[0].equals("dsh")) {
    	currencies[0] = "dash";
    }
    if (currencies[1].equals("dsh")) {
    	currencies[1] = "dash";
    }
    return new CurrencyPair(currencies[0].toUpperCase(), currencies[1].toUpperCase());
  }

  public static List<CurrencyPair> adaptCurrencyPairs(Iterable<String> btcePairs) {

    List<CurrencyPair> pairs = new ArrayList<CurrencyPair>();
    for (String btcePair : btcePairs) {
      pairs.add(adaptCurrencyPair(btcePair));
    }

    return pairs;
  }

  public static ExchangeMetaData toMetaData(WEXExchangeInfo WEXExchangeInfo, WEXMetaData WEXMetaData) {
    Map<CurrencyPair, MarketMetaData> currencyPairs = new HashMap<CurrencyPair, MarketMetaData>();
    Map<Currency, CurrencyMetaData> currencies = new HashMap<Currency, CurrencyMetaData>();

    if (WEXExchangeInfo != null)
      for (Entry<String, WEXPairInfo> e : WEXExchangeInfo.getPairs().entrySet()) {
        CurrencyPair pair = adaptCurrencyPair(e.getKey());
        MarketMetaData marketMetaData = toMarketMetaData(e.getValue(), WEXMetaData);
        currencyPairs.put(pair, marketMetaData);

        addCurrencyMetaData(pair.base, currencies, WEXMetaData);
        addCurrencyMetaData(pair.counter, currencies, WEXMetaData);
      }

    HashSet<RateLimit> publicRateLimits = new HashSet<>(
        Collections.singleton(new RateLimit(WEXMetaData.publicInfoCacheSeconds, 1, TimeUnit.SECONDS)));
    return new ExchangeMetaData(currencyPairs, currencies, publicRateLimits, Collections.<RateLimit> emptySet(), false);
  }

  private static void addCurrencyMetaData(Currency symbol, Map<Currency, CurrencyMetaData> currencies, WEXMetaData WEXMetaData) {
    if (!currencies.containsKey(symbol)) {
      currencies.put(symbol, new CurrencyMetaData(WEXMetaData.amountScale));
    }
  }

  public static MarketMetaData toMarketMetaData(WEXPairInfo info, WEXMetaData WEXMetaData) {
    int priceScale = info.getDecimals();
    BigDecimal minimumAmount = withScale(info.getMinAmount(), WEXMetaData.amountScale);
    BigDecimal feeFraction = info.getFee().movePointLeft(2);

    return new MarketMetaData(feeFraction, minimumAmount, priceScale);
  }

  private static BigDecimal withScale(BigDecimal value, int priceScale) {
    /*
     * Last time I checked BTC-e returned an erroneous JSON result, where the minimum price for LTC/EUR was .0001 and the price scale was 3
     */
    try {
      return value.setScale(priceScale, RoundingMode.UNNECESSARY);
    } catch (ArithmeticException e) {
      log.debug("Could not round {} to {} decimal places: {}", value, priceScale, e.getMessage());
      return value.setScale(priceScale, RoundingMode.CEILING);
    }
  }

  public static String getPair(CurrencyPair currencyPair) {
	/* BTC-E signals DASH as DSH. This is a different coin. Translate in correct DASH name */	  
    String base = currencyPair.base.getCurrencyCode();
    String counter = currencyPair.counter.getCurrencyCode();    
	if (base.equals("DASH")) {
		base = "DSH";
	}
	else if (counter.equals("DASH")) {
		counter = "DSH";
	} 
    return (base + "_" + counter).toLowerCase();
  }

  public static LimitOrder createLimitOrder(MarketOrder marketOrder, WEXExchangeInfo WEXExchangeInfo) {
    WEXPairInfo WEXPairInfo = WEXExchangeInfo.getPairs().get(getPair(marketOrder.getCurrencyPair()));
    BigDecimal limitPrice = marketOrder.getType() == OrderType.BID ? WEXPairInfo.getMaxPrice() : WEXPairInfo.getMinPrice();
    return LimitOrder.Builder.from(marketOrder).limitPrice(limitPrice).build();
  }
}
