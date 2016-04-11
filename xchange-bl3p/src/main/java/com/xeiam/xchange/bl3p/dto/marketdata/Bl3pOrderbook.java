package com.xeiam.xchange.bl3p.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 17/08/15
 * Time: 13:27
 */
public class Bl3pOrderbook {

  private Bl3pTrade[] asks;
  private Bl3pTrade[] bids;

  public Bl3pOrderbook(@JsonProperty("asks") Bl3pTrade[] asks,
                       @JsonProperty("bids") Bl3pTrade[] bids) {

    this.asks = asks;
    this.bids = bids;
  }

  public Bl3pTrade[] getAsks() {
    return asks;
  }

  public Bl3pTrade[] getBids() {
    return bids;
  }
}
