package org.knowm.xchange.bitmex.service.polling;

import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.bitmex.dto.marketdata.BitMexInfo;
import org.knowm.xchange.bitmex.BitMexAdapters;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.polling.marketdata.PollingMarketDataService;

public class BitMexMarketDataService extends BitMexMarketDataServiceRaw implements PollingMarketDataService {

  public BitMexMarketDataService(Exchange exchange) {
    super(exchange);
  }

  public BitMexInfo getAllYoBitInfo() throws IOException {
    return null;
  }

  @Override
  public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {
    return BitMexAdapters.adaptTrades(getTrades(currencyPair), currencyPair);
  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws ExchangeException,
      NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    return null;
  }

  @Override
  public OrderBook getOrderBook(CurrencyPair currencyPair,Object... args)throws IOException {
    long level = 50;
    if (args != null && args.length > 0) {
      if (args[0] instanceof Number) {
        Number arg = (Number) args[0];
        level = arg.intValue();
      }
    } return BitMexAdapters.adaptOrderBook(getOrderBookA(currencyPair, level), currencyPair);
  }
}
