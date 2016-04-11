package com.xeiam.xchange.bl3p.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xeiam.xchange.bl3p.dto.Bl3pError;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 17/08/15
 * Time: 13:44
 */
public class Bl3pTrades extends Bl3pError {

  private Bl3pTrade[] trades;

  public Bl3pTrades(@JsonProperty("code") String code,
                    @JsonProperty("message") String message,
                    @JsonProperty("trades") Bl3pTrade[] trades) {
    super(code, message);

    this.trades = trades;
  }

  public Bl3pTrade[] getTrades() {
    return trades;
  }
}
