package org.knowm.xchange.bitmex.dto.marketdata;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BitMexMarketAll extends BitMexTicker {
  private BigDecimal[][] asks;
  private BigDecimal[][] bids;
  private BitMexTrade[] trades;

  public BitMexMarketAll(@JsonProperty("max") BigDecimal max, @JsonProperty("min") BigDecimal min,
                         @JsonProperty("last") BigDecimal last, @JsonProperty("bid") BigDecimal bid,
                         @JsonProperty("ask") BigDecimal ask, @JsonProperty("vwap") BigDecimal vwap,
                         @JsonProperty("average") BigDecimal average, @JsonProperty("volume") BigDecimal volume,
                         @JsonProperty("asks") BigDecimal[][] asks, @JsonProperty("bids") BigDecimal[][] bids,
                         @JsonProperty("transactions") BitMexTrade[] trades) {
    super(max, min, last, bid, ask, vwap, average, volume);

    this.asks = asks;
    this.bids = bids;
    this.trades = trades;
  }

  public BigDecimal[][] getAsks() {
    return asks;
  }

  public BigDecimal[][] getBids() {
    return bids;
  }

  public BitMexTrade[] getTrades() {
    return trades;
  }
}
