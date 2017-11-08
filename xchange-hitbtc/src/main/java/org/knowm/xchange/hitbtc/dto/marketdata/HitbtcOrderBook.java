package org.knowm.xchange.hitbtc.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author kpysniak
 */
public class HitbtcOrderBook {

  private final BigDecimal size;
  private final BigDecimal price;

  public HitbtcOrderBook(@JsonProperty("price") BigDecimal price, @JsonProperty("size") BigDecimal size) {

    this.price = price;
    this.size = size;
  }

  public BigDecimal getSize() {
    return size;
  }

  public BigDecimal getPrice() {
    return price;
  }

  @Override
  public String toString() {
    return "HitbtcOrderBook{" +
        "size=" + size +
        ", price=" + price +
        '}';
  }
}