package org.knowm.xchange.coincheck.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 07/06/16
 * Time: 18:31
 */
public class CoincheckTransactions {


  public final Boolean success;
  public final List<CoincheckTransaction> transactions;
  public final String error;

  public CoincheckTransactions(@JsonProperty("success") Boolean success,
                               @JsonProperty("transactions") List<CoincheckTransaction> transactions,
                               @JsonProperty("error") String error) {
    this.success = success;
    this.transactions = transactions;
    this.error = error;
  }

  public Boolean getSuccess() {
    return success;
  }

  public List<CoincheckTransaction> getTransactions() {
    return transactions;
  }

  public String getError() {
    return error;
  }
}
