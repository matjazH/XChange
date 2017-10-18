package org.knowm.xchange.exmo.service.polling;

import java.io.IOException;
import java.util.*;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.exmo.ExmoAdapters;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.exmo.dto.marketdata.ExmoOrderbook;
import org.knowm.xchange.exmo.dto.marketdata.ExmoPair;
import org.knowm.xchange.exmo.dto.marketdata.ExmoTrade;
import org.knowm.xchange.exmo.dto.marketdata.ExmoTicker;
import org.knowm.xchange.service.polling.marketdata.PollingMarketDataService;

public class ExmoMarketDataService extends ExmoMarketDataServiceRaw implements PollingMarketDataService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public ExmoMarketDataService(Exchange exchange) {

    super(exchange);
  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {

    Map<String, ExmoTicker> exmoTickers = getExmoTickers();

    String symbol = ExmoAdapters.toExmoSymbol(currencyPair);
    for (Map.Entry<String, ExmoTicker> entry : exmoTickers.entrySet()) {
      if (symbol.equals(entry.getKey())) {
        return ExmoAdapters.adaptTicker(entry.getValue(), currencyPair);
      }
    }
    return null;
  }

  public List<Ticker> getAllTickers() throws IOException {

    List<Ticker> tickers = new ArrayList<>();

    Map<String, ExmoTicker> exmoTickers = getExmoTickers();
    for (Map.Entry<String, ExmoTicker> entry : exmoTickers.entrySet()) {
      CurrencyPair currencyPair = ExmoAdapters.toCurrencyPair(entry.getKey());
      tickers.add(ExmoAdapters.adaptTicker(entry.getValue(), currencyPair));
    }

    return tickers;
  }

  @Override
  public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {

    String symbol = ExmoAdapters.toExmoSymbol(currencyPair);
    Map<String, ExmoOrderbook> exmoOrderBook = getExmoOrderBook(symbol);
    if (exmoOrderBook != null) {
      ExmoOrderbook exmoOrderbook = exmoOrderBook.get(symbol);
      return ExmoAdapters.adaptOrderBook(exmoOrderbook, currencyPair);
    }
    return null;
  }

  @Override
  public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {

    String symbol = ExmoAdapters.toExmoSymbol(currencyPair);
    Map<String, List<ExmoTrade>> exmoTrades = getExmoTrades(symbol);

    if (exmoTrades != null) {
      List<ExmoTrade> trades = exmoTrades.get(symbol);
      return ExmoAdapters.adaptTrades(trades, currencyPair);
    }
    return null;
  }

  public Map<CurrencyPair, Trades> getTrades(CurrencyPair[] pairs) throws IOException {

    Map<CurrencyPair, Trades> result = new HashMap<>();

    StringBuilder symbols = new StringBuilder();
    for (CurrencyPair pair : pairs) {
      symbols.append(ExmoAdapters.toExmoSymbol(pair)).append(",");
    }

    Map<String, List<ExmoTrade>> exmoTrades = getExmoTrades(symbols.toString());

    if (exmoTrades != null) {
      for (Map.Entry<String, List<ExmoTrade>> entry : exmoTrades.entrySet()) {
        CurrencyPair currencyPair = ExmoAdapters.toCurrencyPair(entry.getKey());
        Trades trades = ExmoAdapters.adaptTrades(entry.getValue(), currencyPair);
        result.put(currencyPair, trades);
      }
    }
    return result;
  }

  public CurrencyPair[] getExmoCurrencyPairs() throws IOException {
    List<CurrencyPair> pairs = new ArrayList<>();

    Map<String, ExmoPair> exmoPairs = getExmoPairs();
    for (String symbol : exmoPairs.keySet()) {
      pairs.add(ExmoAdapters.toCurrencyPair(symbol));
    }

    return pairs.toArray(new CurrencyPair[pairs.size()]);
  }
}
