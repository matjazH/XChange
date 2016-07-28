package org.knowm.xchange.coincheck.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 30/05/16
 * Time: 17:49
 */
public class CoincheckOrderbook {

  /*
  {
  "asks": [
    [
      27330,
      "2.25"
    ],
    [
      27340,
      "0.45"
    ]
  ],
  "bids": [
    [
      27240,
      "1.1543"
    ],
    [
      26800,
      "1.2226"
    ]
  ]
}
   */


  private final List<List<BigDecimal>> asks;
  private final List<List<BigDecimal>> bids;

  public CoincheckOrderbook(@JsonProperty("asks") List<List<BigDecimal>> asks,
                            @JsonProperty("bids") List<List<BigDecimal>> bids) {
    this.asks = asks;
    this.bids = bids;
  }

  public List<List<BigDecimal>> getBids() {
    return bids;
  }

  public List<List<BigDecimal>> getAsks() {
    return asks;
  }
}
