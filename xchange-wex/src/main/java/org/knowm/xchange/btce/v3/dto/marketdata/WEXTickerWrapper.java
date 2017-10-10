package org.knowm.xchange.btce.v3.dto.marketdata;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Author: brox Since: 11/12/13 11:00 PM Data object representing multi-currency market data from WEX API v.3
 */
public class WEXTickerWrapper {

  private final Map<String, WEXTicker> tickerMap;

  /**
   * Constructor
   * 
   * @param resultV3
   */
  @JsonCreator
  public WEXTickerWrapper(Map<String, WEXTicker> resultV3) {

    this.tickerMap = resultV3;
  }

  public Map<String, WEXTicker> getTickerMap() {

    return tickerMap;
  }

  public WEXTicker getTicker(String pair) {

    WEXTicker result = null;
    if (tickerMap.containsKey(pair)) {
      result = tickerMap.get(pair);
    }
    return result;
  }

  @Override
  public String toString() {

    return "BTCETickerV3 [map=" + tickerMap.toString() + "]";
  }

}
