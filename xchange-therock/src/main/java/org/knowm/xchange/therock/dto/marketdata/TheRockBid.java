package org.knowm.xchange.therock.dto.marketdata;

import java.math.BigDecimal;

public class TheRockBid {
  private BigDecimal price;
  private BigDecimal amount;

  public BigDecimal getPrice() {
    return this.price;
  }

  public BigDecimal getAmount() {
    return this.amount;
  }

  public String toString() {
    return String.format("TheRockBid{price=%s, amount=%s}", new Object[]{this.price, this.amount});
  }
}