package org.knowm.xchange.btce.v3.service.polling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.btce.v3.WEXAdapters;
import org.knowm.xchange.btce.v3.dto.marketdata.WEXDepthWrapper;
import org.knowm.xchange.btce.v3.dto.marketdata.WEXPairInfo;
import org.knowm.xchange.btce.v3.dto.marketdata.WEXTickerWrapper;
import org.knowm.xchange.btce.v3.dto.marketdata.WEXTrade;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.polling.marketdata.PollingMarketDataService;

public class WEXMarketDataService extends WEXMarketDataServiceRaw implements PollingMarketDataService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public WEXMarketDataService(Exchange exchange) {

    super(exchange);
  }

  public List<CurrencyPair> getExchangeSymbols() throws IOException {
    List<CurrencyPair> currencyPairs = new ArrayList<>();
    Map<String, WEXPairInfo> pairs = super.getBTCEInfo().getPairs();
    for (String pair: pairs.keySet()) {
      String[] split = pair.split("_");
      currencyPairs.add(new CurrencyPair(split[0], split[1]));
    }
    return currencyPairs;
  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {

    String pairs = WEXAdapters.getPair(currencyPair);
    WEXTickerWrapper WEXTickerWrapper = getBTCETicker(pairs);

    // Adapt to XChange DTOs
    return WEXAdapters.adaptTicker(WEXTickerWrapper.getTicker(WEXAdapters.getPair(currencyPair)), currencyPair);
  }

  /**
   * Get market depth from exchange
   *
   * @param tradableIdentifier The identifier to use (e.g. BTC or GOOG). First currency of the pair
   * @param currency The currency of interest, null if irrelevant. Second currency of the pair
   * @param args Optional arguments. Exchange-specific. This implementation assumes: Integer value from 1 to 2000 -> get corresponding number of items
   * @return The OrderBook
   * @throws IOException
   */
  @Override
  public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {

    String pairs = WEXAdapters.getPair(currencyPair);
    WEXDepthWrapper WEXDepthWrapper = null;

    if (args.length > 0) {
      Object arg0 = args[0];
      if (!(arg0 instanceof Integer) || ((Integer) arg0 < 1) || ((Integer) arg0 > FULL_SIZE)) {
        throw new ExchangeException("Orderbook size argument must be an Integer in the range: (1, 2000)!");
      } else {
        WEXDepthWrapper = getBTCEDepth(pairs, (Integer) arg0);
      }
    } else { // default to full orderbook
      WEXDepthWrapper = getBTCEDepth(pairs, FULL_SIZE);
    }

    // Adapt to XChange DTOs
    List<LimitOrder> asks = WEXAdapters.adaptOrders(WEXDepthWrapper.getDepth(WEXAdapters.getPair(currencyPair)).getAsks(), currencyPair, "ask",
        "");
    List<LimitOrder> bids = WEXAdapters.adaptOrders(WEXDepthWrapper.getDepth(WEXAdapters.getPair(currencyPair)).getBids(), currencyPair, "bid",
        "");

    return new OrderBook(null, asks, bids);
  }

  /**
   * Get recent trades from exchange
   *
   * @param tradableIdentifier The identifier to use (e.g. BTC or GOOG)
   * @param currency The currency of interest
   * @param args Optional arguments. This implementation assumes args[0] is integer value limiting number of trade items to get. -1 or missing -> use
   *        default 2000 max fetch value int from 1 to 2000 -> use API v.3 to get corresponding number of trades
   * @return Trades object
   * @throws IOException
   */
  @Override
  public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {

    String pairs = WEXAdapters.getPair(currencyPair);
    int numberOfItems = -1;
    try {
      numberOfItems = (Integer) args[0];
    } catch (ArrayIndexOutOfBoundsException e) {
      // ignore, can happen if no arg given.
    }
    WEXTrade[] bTCETrades = null;

    if (numberOfItems == -1) {
      bTCETrades = getBTCETrades(pairs, FULL_SIZE).getTrades(WEXAdapters.getPair(currencyPair));
    } else {
      bTCETrades = getBTCETrades(pairs, numberOfItems).getTrades(WEXAdapters.getPair(currencyPair));
    }

    return WEXAdapters.adaptTrades(bTCETrades, currencyPair);
  }

}
