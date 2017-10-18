package org.knowm.xchange.yobit.service.polling;

import java.io.IOException;
import java.util.List;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.yobit.YoBitAdapters;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.yobit.dto.marketdata.YoBitInfo;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.service.polling.marketdata.PollingMarketDataService;

public class YoBitMarketDataService extends YoBitMarketDataServiceRaw implements PollingMarketDataService {

  public YoBitMarketDataService(Exchange exchange) {
    super(exchange);
  }

  public YoBitInfo getAllYoBitInfo() throws IOException {
    return super.getProducts();
  }

  @Override
  public List<CurrencyPair> getExchangeSymbols() throws IOException {
    return super.getProducts().getExchangeSymbols();
  }

  @Override
  public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {
    return YoBitAdapters.adaptTrades(getTrades(currencyPair), currencyPair);
  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws ExchangeException,
      NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    return YoBitAdapters.adaptTicker(getYoBitTicker(currencyPair), currencyPair);
  }

  @Override
  public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {
    long level = 50;
    if (args != null && args.length > 0) {
      if (args[0] instanceof Number) {
        Number arg = (Number) args[0];
        level = arg.intValue();
      }
    }
    return YoBitAdapters.adaptOrderBook(getOrderBookA(currencyPair, level), currencyPair);
  }
}
