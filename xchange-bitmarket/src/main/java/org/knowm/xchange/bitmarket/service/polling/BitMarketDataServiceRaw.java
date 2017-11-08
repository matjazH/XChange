package org.knowm.xchange.bitmarket.service.polling;

import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitmarket.dto.marketdata.BitMarketOrderBook;
import org.knowm.xchange.bitmarket.dto.marketdata.BitMarketTicker;
import org.knowm.xchange.bitmarket.dto.marketdata.BitMarketTrade;
import org.knowm.xchange.currency.CurrencyPair;

/**
 * @author kpysniak
 */
public class BitMarketDataServiceRaw extends BitMarketBasePollingService {

  public static final String LITEMINEX = "LiteMineX";

  /**
   * Constructor
   *
   * @param exchange
   */
  protected BitMarketDataServiceRaw(Exchange exchange) {
    super(exchange);
  }

  public BitMarketTicker getBitMarketTicker(CurrencyPair currencyPair) throws IOException {

    String currencyCode = currencyPair.base.getCurrencyCode();
    if (LITEMINEX.equalsIgnoreCase(currencyCode)) {
      currencyCode = LITEMINEX;
    }

    return bitMarket.getTicker(currencyCode + currencyPair.counter.getCurrencyCode().toUpperCase());
  }

  public BitMarketOrderBook getBitMarketOrderBook(CurrencyPair currencyPair) throws IOException {

    String currencyCode = currencyPair.base.getCurrencyCode();
    if (LITEMINEX.equalsIgnoreCase(currencyCode)) {
      currencyCode = LITEMINEX;
    }

    return bitMarket.getOrderBook(currencyCode + currencyPair.counter.getCurrencyCode().toUpperCase());
  }

  public BitMarketTrade[] getBitMarketTrades(CurrencyPair currencyPair) throws IOException {

    String currencyCode = currencyPair.base.getCurrencyCode();
    if (LITEMINEX.equalsIgnoreCase(currencyCode)) {
      currencyCode = LITEMINEX;
    }

    return bitMarket.getTrades(currencyCode + currencyPair.counter.getCurrencyCode().toUpperCase());
  }

}
