package com.xeiam.xchange.cexio.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 17/05/16
 * Time: 15:52
 */
public class CexIOCurrency {
  private final String symbol1;
  private final String symbol2;
  private final BigDecimal minPrice;
  private final BigDecimal maxPrice;
  private final BigDecimal minLotSize;
  private final BigDecimal minLotSizeS2;

  public CexIOCurrency(@JsonProperty("symbol1") String symbol1,
                       @JsonProperty("symbol2") String symbol2,
                       @JsonProperty("minPrice") BigDecimal minPrice,
                       @JsonProperty("maxPrice") BigDecimal maxPrice,
                       @JsonProperty("minLotSize") BigDecimal minLotSize,
                       @JsonProperty("minLotSizeS2") BigDecimal minLotSizeS2) {
    this.symbol1 = symbol1;
    this.symbol2 = symbol2;
    this.minPrice = minPrice;
    this.maxPrice = maxPrice;
    this.minLotSize = minLotSize;
    this.minLotSizeS2 = minLotSizeS2;
  }

  public String getSymbol1() {
    return symbol1;
  }

  public String getSymbol2() {
    return symbol2;
  }

  public BigDecimal getMinPrice() {
    return minPrice;
  }

  public BigDecimal getMaxPrice() {
    return maxPrice;
  }

  public BigDecimal getMinLotSize() {
    return minLotSize;
  }

  public BigDecimal getMinLotSizeS2() {
    return minLotSizeS2;
  }
}
