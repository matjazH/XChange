package org.knowm.xchange.therock.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class TheRockTrades {
  private final TheRockTrade[] trades;

  public TheRockTrades(@JsonProperty("trades") TheRockTrade[] trades, @JsonProperty("meta") Object ignored) {
    this.trades = trades;
  }

  public int getCount() {
    return this.trades.length;
  }

  public TheRockTrade[] getTrades() {
    return this.trades;
  }

  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("TheRockTrades [count=");
    builder.append(this.trades.length);
    builder.append(", trades=");
    builder.append(Arrays.toString(this.trades));
    builder.append("]");
    return builder.toString();
  }
}