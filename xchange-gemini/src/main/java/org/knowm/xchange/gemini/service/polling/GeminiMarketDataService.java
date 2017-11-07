package org.knowm.xchange.gemini.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.gemini.GeminiAdapters;
import org.knowm.xchange.gemini.dto.marketdata.GeminiOrderBook;
import org.knowm.xchange.gemini.dto.marketdata.GeminiTicker;
import org.knowm.xchange.gemini.dto.marketdata.GeminiTrade;
import org.knowm.xchange.service.polling.marketdata.PollingMarketDataService;

import java.io.IOException;
import java.util.List;

/**
 * Created by tabtrader on 03/10/2016.
 */
public class GeminiMarketDataService extends GeminiMarketDataServiceRaw implements PollingMarketDataService {
  /**
   * Constructor
   *
   * @param exchange
   */
  public GeminiMarketDataService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws ExchangeException, IOException {

    String symbol = GeminiAdapters.adaptPair(currencyPair);
    GeminiTicker geminiTicker = getGiminiTicker(symbol);
    return GeminiAdapters.adaptTicker(geminiTicker, currencyPair);
  }

  @Override
  public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws ExchangeException, IOException {

    String symbol = GeminiAdapters.adaptPair(currencyPair);
    GeminiOrderBook geminiOrderBook = getGeminiOrderBook(symbol, null, null);
    return GeminiAdapters.adaptOrderBook(geminiOrderBook, currencyPair);
  }

  @Override
  public Trades getTrades(CurrencyPair currencyPair, Object... args) throws ExchangeException, IOException {

    Long since = null;
    Long limit = null;
    Boolean includeBreakes = null;

    switch (args.length) {
      case 3:
        if (args[2] instanceof Boolean) {
          includeBreakes = (Boolean) args[2];
        } else {
          throw new ExchangeException("args[2] must be of type Long!");
        }
      case 2:
        if (args[1] instanceof Long) {
          limit = (Long) args[1];
        } else {
          throw new ExchangeException("args[1] must be of type Long!");
        }
      case 1:
        if (args[0] instanceof Long) {
          since = (Long) args[0];
        } else {
          throw new ExchangeException("args[0] must be of type Long!");
        }
    }

    String symbol = GeminiAdapters.adaptPair(currencyPair);
    List<GeminiTrade> geminiTrades = getGeminiTrades(symbol, since, limit, includeBreakes);
    return GeminiAdapters.adaptTrades(geminiTrades, currencyPair);
  }
}
