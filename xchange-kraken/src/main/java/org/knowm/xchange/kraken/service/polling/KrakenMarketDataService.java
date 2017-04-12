package org.knowm.xchange.kraken.service.polling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.kraken.KrakenAdapters;
import org.knowm.xchange.kraken.dto.marketdata.KrakenDepth;
import org.knowm.xchange.kraken.dto.marketdata.KrakenPublicTrades;
import org.knowm.xchange.kraken.dto.marketdata.KrakenTicker;
import org.knowm.xchange.service.polling.marketdata.PollingMarketDataService;

public class KrakenMarketDataService extends KrakenMarketDataServiceRaw implements PollingMarketDataService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public KrakenMarketDataService(Exchange exchange) {

    super(exchange);
  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {

    return KrakenAdapters.adaptTicker(getKrakenTicker(currencyPair), currencyPair);
  }

  @Override
  public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {

    long count = Long.MAX_VALUE;

    if (args.length > 0) {
      Object arg0 = args[0];
      if (arg0 instanceof Long) {
        count = (Long) arg0;
      } else if (arg0 instanceof Integer) {
        count = (Integer) arg0;
      } else {
        throw new ExchangeException("args[0] must be of type Long or Integer");
      }
    }

    KrakenDepth krakenDepth = getKrakenDepth(currencyPair, count);

    return KrakenAdapters.adaptOrderBook(krakenDepth, currencyPair);
  }

  @Override
  public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {

    Long since = null;

    if (args.length > 0) {
      Object arg0 = args[0];
      if (arg0 instanceof Long) {
        since = (Long) arg0;
      } else {
        throw new ExchangeException("args[0] must be of type Long!");
      }
    }

    KrakenPublicTrades krakenTrades = getKrakenTrades(currencyPair, since);
    Trades trades = KrakenAdapters.adaptTrades(krakenTrades.getTrades(), currencyPair, krakenTrades.getLast());
    return trades;
  }

  public List<Ticker> getTickers() throws IOException {

    List<Ticker> tickers = new ArrayList<>();

    Collection<CurrencyPair> exchangeSymbols = getExchangeSymbols();
    Map<String, KrakenTicker> krakenTickerMap = getKrakenTicker(exchangeSymbols.toArray(new CurrencyPair[exchangeSymbols.size()]));

    for (Map.Entry<String, KrakenTicker> tickerEntry : krakenTickerMap.entrySet()) {

      String baseCurrency = tickerEntry.getKey().substring(0, 4);
      String counterCurrency = tickerEntry.getKey().substring(4);

      Currency base = KrakenAdapters.adaptCurrency(baseCurrency);
      Currency counter = KrakenAdapters.adaptCurrency(counterCurrency);

      CurrencyPair currencyPair = new CurrencyPair(base, counter);
      tickers.add(KrakenAdapters.adaptTicker(tickerEntry.getValue(), currencyPair));
    }

    return tickers;
  }
}
