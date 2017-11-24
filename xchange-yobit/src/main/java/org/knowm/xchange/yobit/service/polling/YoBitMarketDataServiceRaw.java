package org.knowm.xchange.yobit.service.polling;

import java.util.Map;
import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.yobit.YoBit;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.yobit.service.YoBitBaseService;
import org.knowm.xchange.yobit.dto.marketdata.YoBitInfo;
import org.knowm.xchange.yobit.dto.marketdata.YoBitTrades;
import org.knowm.xchange.yobit.dto.marketdata.YoBitTicker;
import org.knowm.xchange.yobit.dto.marketdata.YoBitOrderBook;

public class YoBitMarketDataServiceRaw extends YoBitBaseService<YoBit> {

  protected YoBitMarketDataServiceRaw(Exchange exchange) {
    super(YoBit.class, exchange);
  }

  public YoBitTicker getYoBitTicker(CurrencyPair currencyPair)
      throws IOException {
    Map<String, YoBitTicker> yoBitTicker =
        yobit.getYoBitTicker(currencyPair.base.getCurrencyCode().toLowerCase(), currencyPair.counter.getCurrencyCode().toLowerCase());
    return yoBitTicker.get(currencyPair.base.getCurrencyCode().toLowerCase() + "_" + currencyPair.counter.getCurrencyCode().toLowerCase());
  }

  public YoBitInfo getProducts() throws IOException {
    YoBitInfo data = yobit.getProducts();
    return data;
  }

  public YoBitOrderBook getOrderBookA(CurrencyPair currencyPair, Long limit)
      throws IOException {
    return yobit.getOrderBook(
        currencyPair.base.getCurrencyCode().toLowerCase(),
        currencyPair.counter.getCurrencyCode().toLowerCase(), limit);
  }

  public YoBitTrades getTrades(CurrencyPair currencyPair, Long limit) throws IOException {
    return this.yobit.getTrades(
        currencyPair.base.getCurrencyCode().toLowerCase(),
        currencyPair.counter.getCurrencyCode().toLowerCase(), limit);
  }
}
