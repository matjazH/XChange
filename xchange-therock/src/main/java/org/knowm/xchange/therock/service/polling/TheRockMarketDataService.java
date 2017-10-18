package org.knowm.xchange.therock.service.polling;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.service.polling.marketdata.PollingMarketDataService;
import org.knowm.xchange.therock.TheRock;
import org.knowm.xchange.therock.TheRockAdapters;
import org.knowm.xchange.therock.dto.marketdata.TheRockOrderBook;
import org.knowm.xchange.therock.dto.marketdata.TheRockTicker;
import org.knowm.xchange.therock.service.TheRockMarketDataServiceRaw;

public class TheRockMarketDataService
    extends TheRockMarketDataServiceRaw implements PollingMarketDataService {
  public TheRockMarketDataService(Exchange exchange) {
    super(exchange);
  }

  public List<CurrencyPair> getExchangeSymbols() throws IOException {
    return super.getFunds().getExchangeSymbols();
  }

  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {
    TheRockTicker t = getTheRockTicker(new TheRock.Pair(currencyPair));
    return new Ticker.Builder().currencyPair(currencyPair).last(t.getLast()).bid(t.getBid()).ask(t.getAsk()).high(t.getHigh()).low(t.getLow())
        .volume(t.getVolumeTraded()).timestamp(new Date()).build();
  }

  public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {
    TheRockOrderBook theRockOrderBook = getTheRockOrderBook(new TheRock.Pair(currencyPair));
    return TheRockAdapters.adaptOrderBook(theRockOrderBook);
  }

  public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {
    return TheRockAdapters.adaptTrades(getTheRockTrades(new TheRock.Pair(currencyPair), args), currencyPair);
  }
}