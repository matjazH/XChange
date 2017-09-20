package org.knowm.xchange.therock.dto.account;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;


@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class TheRockBalance {
  private String currency;
  private BigDecimal balance;
  private BigDecimal tradingBalance;

  public String getCurrency() {
    return this.currency;
  }

  public BigDecimal getBalance() {
    return this.balance;
  }

  public BigDecimal getTradingBalance() {
    return this.tradingBalance;
  }

  public String toString() {
    return String.format("TheRockBalance{currency='%s', balance=%s, trandingBalance=%s}", new Object[]{this.currency, this.balance, this.tradingBalance});
  }
}