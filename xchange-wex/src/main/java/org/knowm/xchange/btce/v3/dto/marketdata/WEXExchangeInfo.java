package org.knowm.xchange.btce.v3.dto.marketdata;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author brox
 */
public class WEXExchangeInfo {

  private final long serverTime;
  private final Map<String, WEXPairInfo> pairs;

  public WEXExchangeInfo(@JsonProperty("server_time") long serverTime, @JsonProperty("pairs") Map<String, WEXPairInfo> pairs) {

    this.serverTime = serverTime;
    this.pairs = pairs;
  }

  public long getServerTime() {

    return serverTime;
  }

  public Map<String, WEXPairInfo> getPairs() {

    return pairs;
  }

  @Override
  public String toString() {

    return "BTCEInfoV3 [serverTime=" + serverTime + ", pairs=" + pairs.toString() + "]";
  }

}
