package org.knowm.xchange.btce.v3.service.polling;

import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.btce.v3.dto.marketdata.WEXDepthWrapper;
import org.knowm.xchange.btce.v3.dto.marketdata.WEXExchangeInfo;
import org.knowm.xchange.btce.v3.dto.marketdata.WEXTickerWrapper;
import org.knowm.xchange.btce.v3.dto.marketdata.WEXTradesWrapper;

/**
 * @author brox
 */
public class WEXMarketDataServiceRaw extends WEXBasePollingService {

  protected static final int FULL_SIZE = 2000;

  /**
   * Constructor
   *
   * @param exchange
   */
  public WEXMarketDataServiceRaw(Exchange exchange) {

    super(exchange);
  }

  /**
   * @param pairs Dash-delimited string of currency pairs to retrieve (e.g. "btc_usd-ltc_btc")
   * @return WEXTickerWrapper object
   * @throws IOException
   */
  public WEXTickerWrapper getBTCETicker(String pairs) throws IOException {

    return btce.getTicker(pairs.toLowerCase(), 1);
  }

  /**
   * Get market depth from exchange
   *
   * @param pairs Dash-delimited string of currency pairs to retrieve (e.g. "btc_usd-ltc_btc")
   * @param size Integer value from 1 to 2000 -> get corresponding number of items
   * @return WEXDepthWrapper object
   * @throws IOException
   */
  public WEXDepthWrapper getBTCEDepth(String pairs, int size) throws IOException {

    if (size < 1) {
      size = 1;
    }

    if (size > FULL_SIZE) {
      size = FULL_SIZE;
    }

    return btce.getDepth(pairs.toLowerCase(), size, 1);
  }

  /**
   * Get recent trades from exchange
   *
   * @param pairs Dash-delimited string of currency pairs to retrieve (e.g. "btc_usd-ltc_btc")
   * @param size Integer value from 1 to 2000 -> get corresponding number of items
   * @return WEXTradesWrapper object
   * @throws IOException
   */
  public WEXTradesWrapper getBTCETrades(String pairs, int size) throws IOException {

    if (size < 1) {
      size = 1;
    }

    if (size > FULL_SIZE) {
      size = FULL_SIZE;
    }

    return btce.getTrades(pairs.toLowerCase(), size, 1);
  }

  /**
   * Get BTC-e exchange info
   *
   * @return WEXExchangeInfo object
   * @throws IOException
   */
  public WEXExchangeInfo getBTCEInfo() throws IOException {

    return btce.getInfo();
  }

}
