package org.knowm.xchange.yobit.dto.marketdata;

import java.math.BigDecimal;

public class YoBitAsksBidsData {
  private final BigDecimal rate;
  private final BigDecimal quantity;

  public BigDecimal getRate() {
    return rate;
  }

  /**
   * @param rate
   * @param quantity
   */
  public YoBitAsksBidsData(BigDecimal quantity, BigDecimal rate) {
    this.quantity = quantity;
    this.rate = rate;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }
}
