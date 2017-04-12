package org.knowm.xchange.unocoin.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class UnoCoinMarketAll extends UnoCoinTicker {
  private BigDecimal[][] asks;
  private BigDecimal[][] bids;
  private UnoCoinTrade[] trades;

  public UnoCoinMarketAll(@JsonProperty("max") BigDecimal max, @JsonProperty("min") BigDecimal min,
                          @JsonProperty("last") BigDecimal last, @JsonProperty("bid") BigDecimal bid,
                          @JsonProperty("ask") BigDecimal ask, @JsonProperty("vwap") BigDecimal vwap,
                          @JsonProperty("average") BigDecimal average, @JsonProperty("volume") BigDecimal volume,
                          @JsonProperty("asks") BigDecimal[][] asks, @JsonProperty("bids") BigDecimal[][] bids,
                          @JsonProperty("transactions") UnoCoinTrade[] trades) {
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

  public UnoCoinTrade[] getTrades() {
    return trades;
  }
}
