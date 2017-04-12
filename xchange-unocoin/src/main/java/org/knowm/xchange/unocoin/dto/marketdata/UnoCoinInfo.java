package org.knowm.xchange.unocoin.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UnoCoinInfo {
  private Long server_time;
  private UnoCoinPairs pairs;

  public UnoCoinInfo(
      @JsonProperty("server_time") Long server_time,
      @JsonProperty("pairs") UnoCoinPairs bitpairs) {
    super();
    this.pairs = bitpairs;
    this.server_time = server_time;
  }

  public Long getServer_time() {
    return server_time;
  }

  public UnoCoinPairs getPairs() {
    return pairs;
  }

  @Override
  public String toString() {
    return "UnoCoinInfo [server_time=" + server_time + ", pairs=" + pairs + "]";
  }

}