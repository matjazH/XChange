package org.knowm.xchange.gemini.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by tabtrader on 04/10/2016.
 */
public class GeminiBalance {

  private String currency;
  private BigDecimal amount;
  private BigDecimal available;

  public GeminiBalance(@JsonProperty("currency") String currency,
                       @JsonProperty("amount") BigDecimal amount,
                       @JsonProperty("available") BigDecimal available) {
    this.currency = currency;
    this.amount = amount;
    this.available = available;
  }

  public String getCurrency() {
    return currency;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public BigDecimal getAvailable() {
    return available;
  }

  @Override
  public String toString() {
    return "GeminiBalance{" +
        "currency='" + currency + '\'' +
        ", amount=" + amount +
        ", available=" + available +
        '}';
  }
}
