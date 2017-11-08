package org.knowm.xchange.hitbtc.service.polling;

import java.io.IOException;
import java.util.Map;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.hitbtc.HitbtcAdapters;
import org.knowm.xchange.hitbtc.dto.HitbtcException;
import org.knowm.xchange.hitbtc.dto.marketdata.*;

/**
 * @author kpysniak
 */
public class HitbtcMarketDataServiceRaw extends HitbtcBasePollingService {

  /**
   * Constructor
   *
   * @param exchange
   */
  protected HitbtcMarketDataServiceRaw(Exchange exchange) {

    super(exchange);
  }

  public HitbtcSymbol[] getHitbtcSymbols() throws IOException {

    try {
      return hitbtc.getSymbols();
    } catch (HitbtcException e) {
      handleException(e);
      return null;
    }
  }

  public Map<String, HitbtcTicker> getHitbtcTickers() throws IOException {

    try {
      return hitbtc.getHitbtcTickers();
    } catch (HitbtcException e) {
      handleException(e);
      return null;
    }
  }

  public HitbtcTicker getHitbtcTicker(CurrencyPair currencyPair) throws IOException {

    try {
      return hitbtc.getHitbtcTicker(HitbtcAdapters.adaptCurrencyPair(currencyPair));
    } catch (HitbtcException e) {
      handleException(e);
      return null;
    }
  }

  public HitbtcOrderBookResponse getOrderBook(CurrencyPair currencyPair) throws IOException {

    try {
      return hitbtc.getOrderBook(HitbtcAdapters.adaptCurrencyPair(currencyPair));
    } catch (HitbtcException e) {
      handleException(e);
      return null;
    }
  }

  public HitbtcTrade[] getHitbtcTrades(CurrencyPair currencyPair, long from, HitbtcTrades.HitbtcTradesSortField sortBy,
      HitbtcTrades.HitbtcTradesSortDirection sortDirection, long startIndex, long maxResults) throws IOException {

    try {
      return hitbtc.getTrades(HitbtcAdapters.adaptCurrencyPair(currencyPair), String.valueOf(from), sortBy.toString(),
          sortDirection.toString(), String.valueOf(startIndex), String.valueOf(maxResults), "object", "true");
    } catch (HitbtcException e) {
      handleException(e);
      return null;
    }
  }

  public HitbtcTrade[] getHitbtcTradesRecent(CurrencyPair currencyPair, long maxResults) throws IOException {

    try {
      return hitbtc.getTradesRecent(HitbtcAdapters.adaptCurrencyPair(currencyPair), String.valueOf(maxResults));
    } catch (HitbtcException e) {
      handleException(e);
      return null;
    }
  }
}
