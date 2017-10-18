package org.knowm.xchange.btcup.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.math.BigDecimal;

/**
 * Created by VITTACH on 28/08/17.
 */
public class BtcUpOrderBook {

  private List<BigDecimal[]> asks;
  private List<BigDecimal[]> bids;

  public BtcUpOrderBook(@JsonProperty("asks") List<BigDecimal[]> asks,
                        @JsonProperty("bids") List<BigDecimal[]> bids) {
    this.asks = asks;
    this.bids = bids;
  }

  public List<BigDecimal[]> getAsks() {
    return asks;
  }

  public List<BigDecimal[]> getBids() {
    return bids;
  }

}