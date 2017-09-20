package org.knowm.xchange.therock.dto.account;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;


@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class TheRockWithdrawal {
  private String currency;
  private Method withdrawMethod;
  private String destinationAddress;
  private BigDecimal amount;

  private TheRockWithdrawal(String currency, BigDecimal amount, String destinationAddress) {
    this.currency = currency;
    this.destinationAddress = destinationAddress;
    this.amount = amount;
  }

  private TheRockWithdrawal(String currency, BigDecimal amount, String destinationAddress, Method withdrawMethod) {
    this.currency = currency;
    this.amount = amount;
    this.destinationAddress = destinationAddress;
    this.withdrawMethod = withdrawMethod;
  }

  public static TheRockWithdrawal createRippleWithdrawal(String currency, BigDecimal amount, String destinationAddress) {
    return new TheRockWithdrawal(currency, amount, destinationAddress, Method.RIPPLE);
  }

  public static TheRockWithdrawal createDefaultWithdrawal(String currency, BigDecimal amount, String destinationAddress) {
    return new TheRockWithdrawal(currency, amount, destinationAddress);
  }

  public String getCurrency() {
    return this.currency;
  }

  public Method getWithdrawMethod() {
    return this.withdrawMethod;
  }

  public String getDestinationAddress() {
    return this.destinationAddress;
  }

  public BigDecimal getAmount() {
    return this.amount;
  }

  public String toString() {
    return String.format("TheRockWithdrawal{currency='%s', withdrawMethod='%s', destinationAddress='%s', amount=%s}", new Object[]{this.currency, this.withdrawMethod == null ? "<default>" : this.withdrawMethod, this.destinationAddress, this.amount});
  }

  public static enum Method {
    RIPPLE;

    private Method() {
    }
  }
}