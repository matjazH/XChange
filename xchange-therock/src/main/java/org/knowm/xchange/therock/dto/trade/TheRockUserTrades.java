package org.knowm.xchange.therock.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TheRockUserTrades {
  private final TheRockUserTrade[] trades;

  public TheRockUserTrades(@JsonProperty("trades") TheRockUserTrade[] trades, @JsonProperty("meta") Object ignored) {
    this.trades = trades;
  }

  public int getCount() {
    return this.trades.length;
  }

  public TheRockUserTrade[] getTrades() {
    return this.trades;
  }
}