package org.knowm.xchange.bitmex.dto.marketdata;

import java.math.BigDecimal;

public class BitMexAsksBidsData {
  private final BigDecimal rate;
  private final BigDecimal quantity;

  public BigDecimal getRate() {
    return rate;
  }

  /**
   * @param rate
   * @param quantity
   */
  public BitMexAsksBidsData(BigDecimal quantity, BigDecimal rate) {
    this.quantity = quantity;
    this.rate = rate;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }
}
