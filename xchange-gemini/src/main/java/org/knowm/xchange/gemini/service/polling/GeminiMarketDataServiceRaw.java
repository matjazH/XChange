package org.knowm.xchange.gemini.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.gemini.dto.marketdata.GeminiOrderBook;
import org.knowm.xchange.gemini.dto.marketdata.GeminiTicker;
import org.knowm.xchange.gemini.dto.marketdata.GeminiTrade;

import java.io.IOException;
import java.util.List;

/**
 * Created by tabtrader on 03/10/2016.
 */
public class GeminiMarketDataServiceRaw extends GeminiBasePollingService {

  /**
   * Constructor
   *
   * @param exchange
   */
  protected GeminiMarketDataServiceRaw(Exchange exchange) {
    super(exchange);
  }

  public List<String> getGeminiSymbols() throws IOException {
    List<String> symbols = gemini.getSymbols();
    return symbols;
  }

  public GeminiTicker getGiminiTicker(String symbol) throws IOException {
    return gemini.getTicker(symbol);
  }

  public GeminiOrderBook getGeminiOrderBook(String symbol, Integer limitBids, Integer limitAsks) throws IOException {
    return gemini.getOrderBook(symbol, limitBids, limitAsks);
  }

  public List<GeminiTrade> getGeminiTrades(String symbol, Integer limitTrades, Boolean includeBreaks) throws IOException {
    return gemini.getTrades(symbol, limitTrades, includeBreaks);
  }
}
