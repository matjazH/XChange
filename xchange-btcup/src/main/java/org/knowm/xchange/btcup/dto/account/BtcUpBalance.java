package org.knowm.xchange.btcup.dto.account;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by VITTACH on 28/08/17.
 */
public class BtcUpBalance {

  private BigDecimal makerFee;
  private BigDecimal takerFee;

  private BigDecimal blocked;
  private BigDecimal available;

  public BtcUpBalance(@JsonProperty("makerFee") BigDecimal makerFee,
                      @JsonProperty("takerFee") BigDecimal takerFee,
                      @JsonProperty("blocked") BigDecimal blocked,
                      @JsonProperty("available") BigDecimal available) {
    this.blocked = blocked;
    this.available = available;

    this.makerFee = makerFee;

    this.takerFee = takerFee;
  }

  public BigDecimal getBlocked() {
    return blocked;
  }

  public BigDecimal getAvailable() {
    return available;
  }

  public BigDecimal getMakerFee() {
    return makerFee;
  }

  public BigDecimal getTakerFee() {
    return takerFee;
  }

  @Override
  public String toString() {
    return "BtcUpAccount{" +
        "blocked=" + blocked +
        ", available=" + available +
        '}';
  }
}
