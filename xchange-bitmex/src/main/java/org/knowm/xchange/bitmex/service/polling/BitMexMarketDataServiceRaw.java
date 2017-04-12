package org.knowm.xchange.bitmex.service.polling;

import java.util.Map;
import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitmex.BitMex;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.bitmex.dto.marketdata.BitMexInfo;
import org.knowm.xchange.bitmex.service.BitMexBaseService;
import org.knowm.xchange.bitmex.dto.marketdata.BitMexTrades;
import org.knowm.xchange.bitmex.dto.marketdata.BitMexTicker;
import org.knowm.xchange.bitmex.dto.marketdata.BitMexOrderBook;

public class BitMexMarketDataServiceRaw extends BitMexBaseService<BitMex> {

  protected BitMexMarketDataServiceRaw(Exchange exchange) {
    super(BitMex.class, exchange);
  }

  public BitMexOrderBook getOrderBookA(CurrencyPair currencyPair, Long limit)
      throws IOException {
    return coinbaseEx.getOrderBook(
        currencyPair.base.getCurrencyCode().toLowerCase(),
        currencyPair.counter.getCurrencyCode().toLowerCase(), limit);
  }

  public BitMexTrades getTrades(CurrencyPair currencyPair) throws IOException {
    return this.coinbaseEx.getTrades(
        currencyPair.base.getCurrencyCode().toLowerCase(),
        currencyPair.counter.getCurrencyCode().toLowerCase());
  }
}
