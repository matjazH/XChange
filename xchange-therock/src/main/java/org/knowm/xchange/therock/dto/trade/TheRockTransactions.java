package org.knowm.xchange.therock.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TheRockTransactions {
  private final TheRockTransaction[] transactions;

  public TheRockTransactions(@JsonProperty("transactions") TheRockTransaction[] transactions, @JsonProperty("meta") Object ignored) {
    this.transactions = transactions;
  }

  public int getCount() {
    return this.transactions.length;
  }

  public TheRockTransaction[] getTransactions() {
    return this.transactions;
  }
}