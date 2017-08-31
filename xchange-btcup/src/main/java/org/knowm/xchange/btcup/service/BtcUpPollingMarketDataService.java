package org.knowm.xchange.btcup.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.btcup.BtcUpAdapters;
import org.knowm.xchange.btcup.dto.trade.BtcUpTrade;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.service.polling.marketdata.PollingMarketDataService;

/**
 * Created by VITTACH on 28/08/17.
 */
public class BtcUpPollingMarketDataService extends BtcUpPollingMarketDataServiceRaw implements PollingMarketDataService {

  public BtcUpPollingMarketDataService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public List<CurrencyPair> getExchangeSymbols() throws IOException {
    List<CurrencyPair> currencyPairs = new ArrayList<CurrencyPair>();
    currencyPairs.add(CurrencyPair.BTC_USD);
    currencyPairs.add(CurrencyPair.ETH_USD);
    currencyPairs.add(CurrencyPair.LTC_USD);
    currencyPairs.add(new CurrencyPair("EMC", "USD"));
    currencyPairs.add(new CurrencyPair("ZEC", "USD"));
    return currencyPairs;
  }

  @Override
  public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {

    int depth = 50;
    if (args != null && args.length > 0) {
      if (args[0] instanceof Number) {
        Number arg = (Number) args[0];
        depth = arg.intValue();
      }
    }

    return BtcUpAdapters.adaptOrderBook(getOrderBook(currencyPair, depth), currencyPair);
  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {
    return BtcUpAdapters.adaptTicker(getTicker(currencyPair), currencyPair);
  }

  @Override
  public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {

    int limitNumberOfItems = -1;

    try {
      limitNumberOfItems = ((Number) args[0]).intValue();
    } catch (ArrayIndexOutOfBoundsException exception) { }

    BtcUpTrade[] BTCUPTrades;

    if (limitNumberOfItems == -1) {
      BTCUPTrades = getTrades(currencyPair, FULL_LIMIT_SIZE);
    } else {
      BTCUPTrades = getTrades(currencyPair, limitNumberOfItems);
    }

    return BtcUpAdapters.adaptTrades(BTCUPTrades, currencyPair);
  }
}