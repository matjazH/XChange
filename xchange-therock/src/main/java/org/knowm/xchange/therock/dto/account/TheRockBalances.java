package org.knowm.xchange.therock.dto.account;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;


@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class TheRockBalances {
  private List<TheRockBalance> balances;

  public List<TheRockBalance> getBalances() {
    return this.balances;

  }

  public String toString() {
    return String.format("TheRockBalances{balances=%s}", new Object[]{this.balances});

  }
}