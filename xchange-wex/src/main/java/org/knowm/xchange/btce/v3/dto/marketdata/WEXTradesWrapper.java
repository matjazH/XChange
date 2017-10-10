package org.knowm.xchange.btce.v3.dto.marketdata;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Author: brox Data object representing multi-currency trades from WEX API v.3
 */
public class WEXTradesWrapper {

  private final Map<String, WEXTrade[]> tradesMap;

  /**
   * Constructor
   * 
   * @param tradesMap
   */
  @JsonCreator
  public WEXTradesWrapper(Map<String, WEXTrade[]> tradesMap) {

    this.tradesMap = tradesMap;
  }

  public Map<String, WEXTrade[]> getTradesMap() {

    return tradesMap;
  }

  public WEXTrade[] getTrades(String pair) {

    WEXTrade[] result = null;
    if (tradesMap.containsKey(pair)) {
      result = tradesMap.get(pair);
    }
    return result;
  }

  @Override
  public String toString() {

    return "BTCETradesV3 [map=" + tradesMap.toString() + "]";
  }

}
