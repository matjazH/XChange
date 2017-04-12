package org.knowm.xchange.coincheck.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 02/06/16
 * Time: 19:07
 */
public class CoincheckBalance {

  public final Boolean success;
  public final BigDecimal jpy;
  public final BigDecimal btc;
  public final BigDecimal jpyReserved;
  public final BigDecimal btcReserved;
  public final BigDecimal jpyLendInUse;
  public final BigDecimal btcLendInUse;
  public final BigDecimal jpyLent;
  public final BigDecimal btcLent;
  public final BigDecimal jpyDebt;
  public final BigDecimal btcDebt;

  public CoincheckBalance(@JsonProperty("success") Boolean success,
                          @JsonProperty("jpy") BigDecimal jpy,
                          @JsonProperty("btc") BigDecimal btc,
                          @JsonProperty("jpy_reserved") BigDecimal jpyReserved,
                          @JsonProperty("btc_reserved") BigDecimal btcReserved,
                          @JsonProperty("jpy_lend_in_use") BigDecimal jpyLendInUse,
                          @JsonProperty("btc_lend_in_use") BigDecimal btcLendInUse,
                          @JsonProperty("jpy_lent") BigDecimal jpyLent,
                          @JsonProperty("btc_lent") BigDecimal btcLent,
                          @JsonProperty("jpy_debt") BigDecimal jpyDebt,
                          @JsonProperty("btc_debt") BigDecimal btcDebt) {
    this.success = success;
    this.jpy = jpy;
    this.btc = btc;
    this.jpyReserved = jpyReserved;
    this.btcReserved = btcReserved;
    this.jpyLendInUse = jpyLendInUse;
    this.btcLendInUse = btcLendInUse;
    this.jpyLent = jpyLent;
    this.btcLent = btcLent;
    this.jpyDebt = jpyDebt;
    this.btcDebt = btcDebt;
  }

  public Boolean getSuccess() {
    return success;
  }

  public BigDecimal getJpy() {
    return jpy;
  }

  public BigDecimal getBtc() {
    return btc;
  }

  public BigDecimal getJpyReserved() {
    return jpyReserved;
  }

  public BigDecimal getBtcReserved() {
    return btcReserved;
  }

  public BigDecimal getJpyLendInUse() {
    return jpyLendInUse;
  }

  public BigDecimal getBtcLendInUse() {
    return btcLendInUse;
  }

  public BigDecimal getJpyLent() {
    return jpyLent;
  }

  public BigDecimal getBtcLent() {
    return btcLent;
  }

  public BigDecimal getJpyDebt() {
    return jpyDebt;
  }

  public BigDecimal getBtcDebt() {
    return btcDebt;
  }
}
