package org.knowm.xchange.ccex.service;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ccex.CCEXAdapters;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.ccex.dto.marketdata.CCEXMarket;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.service.polling.marketdata.PollingMarketDataService;

/**
 * @author Andraž Prinčič
 */
public class CCEXMarketDataService extends CCEXMarketDataServiceRaw implements PollingMarketDataService {

  public CCEXMarketDataService(Exchange exchange) {
    super(exchange);
  }

  public List<CCEXMarket> getAllCCEXInfo() throws IOException {
    return super.getConbaseExProducts();
  }

  @Override
  public List<CurrencyPair> getExchangeSymbols() throws IOException {
    List<CurrencyPair> currencyPairs = new ArrayList<CurrencyPair>();
    List<String> pairs = super.getPairs().getPairs();
    for(String pair: pairs)
      currencyPairs.add(new CurrencyPair(pair.replace("-","/")));
    return currencyPairs;
  }

  @Override
  public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args)
          throws IOException {

    int depth = 50;
    if (args != null&&args.length>0) {
      if (args[0] instanceof Number) {
        Number arg = (Number) args[0];
        depth = arg.intValue();
      }
    }

    return CCEXAdapters.adaptOrderBook(getCCEXOrderBook(currencyPair,depth),currencyPair);
  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {
    return CCEXAdapters.adaptTicker(getTicker(currencyPair), currencyPair);
  }

  @Override
  public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {
    return CCEXAdapters.adaptTrades(getCCEXTrades(currencyPair), currencyPair);
  }

}