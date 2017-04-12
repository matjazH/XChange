package org.knowm.xchange.bitmex.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BitMexInfo {
  private Long server_time;
  private BitMexPairs pairs;

  public BitMexInfo(
      @JsonProperty("server_time") Long server_time,
      @JsonProperty("pairs") BitMexPairs bitpairs) {
    super();
    this.pairs = bitpairs;
    this.server_time = server_time;
  }

  public Long getServer_time() {
    return server_time;
  }

  public BitMexPairs getPairs() {
    return pairs;
  }

  @Override
  public String toString() {
    return "BitMexInfo [server_time=" + server_time + ", pairs=" + pairs + "]";
  }

}