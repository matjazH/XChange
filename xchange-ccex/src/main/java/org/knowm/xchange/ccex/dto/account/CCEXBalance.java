package org.knowm.xchange.ccex.dto.account;

import java.math.BigDecimal;
import java.math.MathContext;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CCEXBalance {

  private String Currency;
  private BigDecimal Balance;
  private BigDecimal Pending;
  private BigDecimal Available;
  private String CryptoAddress;

  public CCEXBalance(
      @JsonProperty("Currency") String currency,
      @JsonProperty("Balance") BigDecimal balance,
      @JsonProperty("Available") BigDecimal available,
      @JsonProperty("Pending") BigDecimal pending,
      @JsonProperty("CryptoAddress") String cryptoAddress) {
    super();
    Available=new BigDecimal(available.doubleValue());
    Balance=new BigDecimal(balance.doubleValue());
    Currency = currency;
    Pending=new BigDecimal(pending.doubleValue());
    CryptoAddress = cryptoAddress;
  }

  public void setAvailable(BigDecimal available) {
    Available = available;
  }

  public void setCryptoAddress(String cryptoAddress) {
    CryptoAddress = cryptoAddress;
  }

  public String getCurrency() {
    return Currency;
  }

  public BigDecimal getPending() {
    return Pending;
  }

  public void setCurrency(String currency) {
    Currency = currency;
  }

  public BigDecimal getBalance() {
    return Balance;
  }

  public void setBalance(BigDecimal balance) {
    Balance = balance;
  }

  public BigDecimal getAvailable() {
    return Available;
  }

  public void setPending(BigDecimal pending) {
    Pending = pending;
  }

  public String getCryptoAddress() {
    return CryptoAddress;
  }

  @Override
  public String toString() {
    return "CCEXBalance [Currency=" + Currency + ", Balance=" + Balance + ", Available=" + Available + ", Pending="
        + Pending + ", CryptoAddress=" + CryptoAddress + "]";
  }
}
