package org.knowm.xchange.bitmex.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YoBitInfo {
  private Long server_time;
  private YoBitPairs pairs;

  public YoBitInfo(
      @JsonProperty("server_time") Long server_time,
      @JsonProperty("pairs") YoBitPairs bitpairs) {
    super();
    this.pairs = bitpairs;
    this.server_time = server_time;
  }

  public Long getServer_time() {
    return server_time;
  }

  public YoBitPairs getPairs() {
    return pairs;
  }

  @Override
  public String toString() {
    return "YoBitInfo [server_time=" + server_time + ", pairs=" + pairs + "]";
  }

}