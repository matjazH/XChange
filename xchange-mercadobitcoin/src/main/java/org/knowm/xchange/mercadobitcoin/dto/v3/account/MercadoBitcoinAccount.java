package org.knowm.xchange.mercadobitcoin.dto.v3.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by tabtrader on 01/11/2016.
 */
public class MercadoBitcoinAccount {

  private HashMap<String, Balance> balances;
  private HashMap<String, Balance> withdrawalLimits;

  public MercadoBitcoinAccount(@JsonProperty("balance") HashMap<String, Balance> balances,
                               @JsonProperty("withdrawal_limits") HashMap<String, Balance> withdrawalLimits) {
    this.balances = balances;
    this.withdrawalLimits = withdrawalLimits;
  }

  public HashMap<String, Balance> getBalances() {
    return balances;
  }

  public HashMap<String, Balance> getWithdrawalLimits() {
    return withdrawalLimits;
  }

  public static class Balance {
    private BigDecimal available;
    private BigDecimal total;

    public Balance(@JsonProperty("available") BigDecimal available,
                   @JsonProperty("total") BigDecimal total) {
      this.available = available;
      this.total = total;
    }

    public BigDecimal getAvailable() {
      return available;
    }

    public BigDecimal getTotal() {
      return total;
    }
  }

}
