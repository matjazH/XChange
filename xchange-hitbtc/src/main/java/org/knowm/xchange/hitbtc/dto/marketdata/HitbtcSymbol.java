package org.knowm.xchange.hitbtc.dto.marketdata;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties("symbol")
public class HitbtcSymbol {

  private final String symbol;
  private final String commodity;
  private final String currency;
  private final BigDecimal step;
  private final BigDecimal lot;
  private final BigDecimal takeLiquidityRate;
  private final BigDecimal provideLiquidityRate;

  public HitbtcSymbol(@JsonProperty("id") String symbol,
                      @JsonProperty("baseCurrency") String commodity,
                      @JsonProperty("quoteCurrency") String currency,
                      @JsonProperty("tickSize") BigDecimal step,
                      @JsonProperty("quantityIncrement") BigDecimal lot,
                      @JsonProperty("takeLiquidityRate") BigDecimal takeLiquidityRate,
                      @JsonProperty("provideLiquidityRate") BigDecimal provideLiquidityRate) {

    this.symbol = symbol;
    this.commodity = commodity;
    this.currency = currency;
    this.step = step;
    this.lot = lot;
    this.takeLiquidityRate = takeLiquidityRate;
    this.provideLiquidityRate = provideLiquidityRate;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getCommodity() {

    return commodity;
  }

  public String getCurrency() {

    return currency;
  }

  public BigDecimal getStep() {

    return step;
  }

  public BigDecimal getLot() {

    return lot;
  }

  public BigDecimal getTakeLiquidityRate() {

    return takeLiquidityRate;
  }

  public BigDecimal getProvideLiquidityRate() {

    return provideLiquidityRate;
  }

  @Override
  public String toString() {

    return "HitbtcSymbol{" + "symbol='" + commodity + '/' + currency + '\'' + ", step=" +
        step + ", lot=" + lot + ", takeRate=" + takeLiquidityRate + ", lot=" + lot + '}';
  }
}
