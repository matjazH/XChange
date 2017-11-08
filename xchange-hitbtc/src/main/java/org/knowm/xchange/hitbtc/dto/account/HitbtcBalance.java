package org.knowm.xchange.hitbtc.dto.account;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HitbtcBalance {

  private final String currencyCode;
  private final BigDecimal available;
  private final BigDecimal reserved;

  public HitbtcBalance(@JsonProperty("currency") String currencyCode,
                       @JsonProperty("available") BigDecimal available,
                       @JsonProperty("reserved") BigDecimal reserved) {

    this.currencyCode = currencyCode;
    this.available = available;
    this.reserved = reserved;
  }

  public String getCurrencyCode() {

    return currencyCode;
  }

  public BigDecimal getAvailable() {

    return available;
  }

  public BigDecimal getReserved() {

    return reserved;
  }

  @Override
  public String toString() {

    StringBuilder builder = new StringBuilder();
    builder.append("HitbtcBalance [currencyCode=");
    builder.append(currencyCode);
    builder.append(", available=");
    builder.append(available);
    builder.append(", reserved=");
    builder.append(reserved);
    builder.append("]");
    return builder.toString();
  }
}
