package org.knowm.xchange.gatecoin.service.polling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.gatecoin.GatecoinAdapters;
import org.knowm.xchange.gatecoin.dto.marketdata.GatecoinTicker;
import org.knowm.xchange.service.polling.marketdata.PollingMarketDataService;

/**
 * @author Sumedha
 */
public class GatecoinMarketDataService extends GatecoinMarketDataServiceRaw implements PollingMarketDataService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public GatecoinMarketDataService(Exchange exchange) {

    super(exchange);

  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {

    return GatecoinAdapters.adaptTicker(getGatecoinTicker().getTicker(), currencyPair);
  }

  @Override
  public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {

    return GatecoinAdapters.adaptOrderBook(getGatecoinOrderBook(currencyPair.toString()), currencyPair, 1000);
  }

  @Override
  public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {
    if (args.length == 0) {
      return GatecoinAdapters.adaptTrades(getGatecoinTransactions(currencyPair.toString()).getTransactions(), currencyPair);
    } else if (args.length == 1) {
      return GatecoinAdapters.adaptTrades(getGatecoinTransactions(currencyPair.toString(), (Integer) args[0], 0).getTransactions(), currencyPair);
    } else if (args.length == 2) {
      return GatecoinAdapters.adaptTrades(getGatecoinTransactions(currencyPair.toString(), (Integer) args[0], (Long) args[1]).getTransactions(),
          currencyPair);
    }
    throw new IllegalArgumentException("Illegal number of arguments: " + args.length);
  }

  public List<Ticker> getTickers() throws IOException {
    List<Ticker> result = new ArrayList<>();

    for (GatecoinTicker gatecoinTicker : getGatecoinTicker().getTicker()) {
      String symbol = gatecoinTicker.getCurrencyPair();
      CurrencyPair currencyPair = new CurrencyPair(symbol.substring(0, 3), symbol.substring(3, 6));
      result.add(GatecoinAdapters.adaptTicker(getGatecoinTicker().getTicker(), currencyPair));
    }
    return result;
  }
}
