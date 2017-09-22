package org.knowm.xchange.therock.dto.marketdata;

import java.util.ArrayList;
import java.util.List;

import org.knowm.xchange.currency.CurrencyPair;

public class TheRockFunds {
  private ArrayList<CurrencyPair> currencyPairs;
  private TheRockFund[] funds;

  public TheRockFund[] getFunds() {
    return this.funds;
  }


  public TheRockFunds(@com.fasterxml.jackson.annotation.JsonProperty("funds") TheRockFund[] funds) {
    this.funds = funds;
  }

  public List<CurrencyPair> getExchangeSymbols() {
    this.currencyPairs = new ArrayList();
    for (TheRockFund fund : this.funds) {
      this.currencyPairs.add(new CurrencyPair(fund.getTradeCurrency(), fund.getBaseCurrency()));
    }
    return this.currencyPairs;
  }
}