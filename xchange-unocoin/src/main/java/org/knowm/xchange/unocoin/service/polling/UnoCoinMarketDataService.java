package org.knowm.xchange.unocoin.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.unocoin.dto.marketdata.UnoCoinInfo;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.service.polling.marketdata.PollingMarketDataService;

import java.io.IOException;

public class UnoCoinMarketDataService extends UnoCoinMarketDataServiceRaw implements PollingMarketDataService {

  public UnoCoinMarketDataService(Exchange exchange) {
    super(exchange);
  }

  public UnoCoinInfo getAllUnoCoinInfo() throws IOException {
    return getInfo();
  }

  @Override
  public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {
    return null;// UnoCoinAdapters.adaptTrades(getTrades(currencyPair), currencyPair);
  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws ExchangeException,
      NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    return null;// UnoCoinAdapters.adaptTicker(getUnoCoinTicker(currencyPair), currencyPair);
  }

  @Override
  public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {
    /*
		long level = 50;
		if (args != null && args.length > 0) {
			if (args[0] instanceof Number) {
				Number arg = (Number) args[0];
				level = arg.intValue();
			}
		}
		return UnoCoinAdapters.adaptOrderBook(getOrderBookA( currencyPair, level), currencyPair);
		*/
    return null;
  }
}
