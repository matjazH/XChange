package org.knowm.xchange.btcup.service;

import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.btcup.BtcUp;
import org.knowm.xchange.btcup.dto.marketdata.BtcUpOrderBook;
import org.knowm.xchange.btcup.dto.trade.BtcUpTrade;
import si.mazi.rescu.RestProxyFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.btcup.dto.marketdata.BtcUpPrice;

/**
 * Created by VITTACH on 28/08/17.
 */
public class BtcUpPollingMarketDataServiceRaw extends BtcUpBaseService {

  public static final int FULL_LIMIT_SIZE = 2000;

  public BtcUpPollingMarketDataServiceRaw(Exchange exchange) {
    super(exchange);
  }

  public BtcUpPrice getTicker(CurrencyPair pair) throws IOException {
    BtcUpPrice result = btcUp.getTicker(pair.base.toString().toLowerCase(),
        pair.counter.toString().toLowerCase()).getResult();

    return result;
  }

  public BtcUpTrade[] getTrades(CurrencyPair tickerPair, int size) throws IOException {

    if (size < 1) {
      size = 1;
    } else if (size > FULL_LIMIT_SIZE) {
      size = FULL_LIMIT_SIZE;
    }

    BtcUpTrade[] result = btcUp.getTrades(tickerPair.base.toString().toLowerCase(),
        tickerPair.counter.toString().toLowerCase(), size).getResult();

    return result;
  }

  public BtcUpOrderBook getOrderBook(CurrencyPair pair, int depth) throws IOException {
    BtcUpOrderBook result = btcUp.getOrderBook(pair.base.toString().toLowerCase(),
        pair.counter.toString().toLowerCase(), depth).getResult();

    return result;
  }
}
