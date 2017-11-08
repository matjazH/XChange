package org.knowm.xchange.hitbtc.dto.marketdata;

import java.math.BigDecimal;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author kpysniak
 */
public class HitbtcOrderBookResponse {

  private final HitbtcOrderBook[] asks;
  private final HitbtcOrderBook[] bids;

  /**
   * Constructor
   * 
   * @param asks
   * @param bids
   */
  public HitbtcOrderBookResponse(@JsonProperty("ask") HitbtcOrderBook[] asks, @JsonProperty("bid") HitbtcOrderBook[] bids) {

    this.asks = asks;
    this.bids = bids;
  }

  public HitbtcOrderBook[] getAsks() {
    return asks;
  }

  public HitbtcOrderBook[] getBids() {
    return bids;
  }

  @Override
  public String toString() {
    return "HitbtcOrderBookResponse{" +
        "asks=" + Arrays.toString(asks) +
        ", bids=" + Arrays.toString(bids) +
        '}';
  }
}