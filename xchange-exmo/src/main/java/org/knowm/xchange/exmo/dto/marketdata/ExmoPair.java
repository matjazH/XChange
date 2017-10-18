package org.knowm.xchange.exmo.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 21/12/15
 * Time: 17:17
 */
public class ExmoPair {
  /*
  {"min_quantity":"0.001","max_quantity":"100","min_price":"1","max_price":"10000","max_amount":"30000","min_amount":"1"}
   */
  protected final BigDecimal minQuantity;
  protected final BigDecimal maxQuantity;
  protected final BigDecimal minPrice;
  protected final BigDecimal maxPrice;
  protected final BigDecimal maxAmount;
  protected final BigDecimal minAmount;

  public ExmoPair(@JsonProperty("min_quantity") BigDecimal minQuantity,
                  @JsonProperty("max_quantity") BigDecimal maxQuantity,
                  @JsonProperty("min_price") BigDecimal minPrice,
                  @JsonProperty("max_price") BigDecimal maxPrice,
                  @JsonProperty("max_amount") BigDecimal maxAmount,
                  @JsonProperty("min_amount") BigDecimal minAmount){
    this.minQuantity = minQuantity;
    this.maxQuantity = maxQuantity;
    this.minPrice = minPrice;
    this.maxPrice = maxPrice;
    this.maxAmount = maxAmount;
    this.minAmount = minAmount;
  }

  public BigDecimal getMinQuantity() {
    return minQuantity;
  }

  public BigDecimal getMaxQuantity() {
    return maxQuantity;
  }

  public BigDecimal getMinPrice() {
    return minPrice;
  }

  public BigDecimal getMaxPrice() {
    return maxPrice;
  }

  public BigDecimal getMaxAmount() {
    return maxAmount;
  }

  public BigDecimal getMinAmount() {
    return minAmount;
  }

  @Override
  public String toString() {
    return "ExmoPair{" +
        "minQuantity=" + minQuantity +
        ", maxQuantity=" + maxQuantity +
        ", minPrice=" + minPrice +
        ", maxPrice=" + maxPrice +
        ", maxAmount=" + maxAmount +
        ", minAmount=" + minAmount +
        '}';
  }
}
