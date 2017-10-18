package org.knowm.xchange.therock.dto.account;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;


@com.fasterxml.jackson.databind.annotation.JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class TheRockWithdrawalResponse {
  private Integer transactionId;

  public Integer getTransactionId() {
    return this.transactionId;
  }

  public String toString() {
    return String.format("TheRockWithdrawalResponse{transactionId=%d}", new Object[]{this.transactionId});
  }
}