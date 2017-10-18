package org.knowm.xchange.btce.v3.dto.marketdata;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Author: brox Since: 11/12/13 11:00 PM Data object representing multi-currency market data from WEX API v.3
 */
public class WEXDepthWrapper {

  private final Map<String, WEXDepth> depthMap;

  /**
   * Constructor
   * 
   * @param resultV3
   */
  @JsonCreator
  public WEXDepthWrapper(Map<String, WEXDepth> resultV3) {

    this.depthMap = resultV3;
  }

  public Map<String, WEXDepth> getDepthMap() {

    return depthMap;
  }

  public WEXDepth getDepth(String pair) {

    WEXDepth result = null;
    if (depthMap.containsKey(pair)) {
      result = depthMap.get(pair);
    }
    return result;
  }

  @Override
  public String toString() {

    return "BTCEDepthV3 [map=" + depthMap.toString() + "]";
  }

}
