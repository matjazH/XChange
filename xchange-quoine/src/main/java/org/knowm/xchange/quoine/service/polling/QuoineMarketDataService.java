package org.knowm.xchange.quoine.service.polling;

import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.quoine.QuoineAdapters;
import org.knowm.xchange.quoine.QuoineUtils;
import org.knowm.xchange.quoine.dto.marketdata.QuoineOrderBook;
import org.knowm.xchange.quoine.dto.marketdata.QuoineProduct;
import org.knowm.xchange.quoine.dto.marketdata.QuoineTradesList;
import org.knowm.xchange.service.polling.marketdata.PollingMarketDataService;

public class QuoineMarketDataService extends QuoineMarketDataServiceRaw implements PollingMarketDataService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public QuoineMarketDataService(Exchange exchange) {

    super(exchange);
  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {

    QuoineProduct quoineTicker = getQuoineProduct(QuoineUtils.toPairString(currencyPair));
    return QuoineAdapters.adaptTicker(quoineTicker, currencyPair);
  }

  @Override
  public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {

    QuoineOrderBook quoineOrderBook = getOrderBook(QuoineUtils.toID(currencyPair));
    return QuoineAdapters.adaptOrderBook(quoineOrderBook, currencyPair);
  }

  @Override
  public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {

    QuoineTradesList quoineTradesList = getExecutions(QuoineUtils.toPairString(currencyPair));
    return QuoineAdapters.adaptTrades(quoineTradesList, currencyPair);
  }

}
