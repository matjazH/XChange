package org.knowm.xchange.quoine.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 28/09/15
 * Time: 18:59
 */
public class QuoineTrade {

//  {"id":257864,"quantity":0.00963667,"price":239.3,"taker_side":"buy","created_at":"2015-10-03T12:49:22Z"},

  private final Long id;
  private final BigDecimal quantity;
  private final BigDecimal price;
  private final String takerSide;
  private final String createdAt;

  public QuoineTrade(@JsonProperty("id") Long id,
                     @JsonProperty("quantity") BigDecimal quantity,
                     @JsonProperty("price") BigDecimal price,
                     @JsonProperty("taker_side") String takerSide,
                     @JsonProperty("created_at") String createdAt) {
    this.id = id;
    this.quantity = quantity;
    this.price = price;
    this.takerSide = takerSide;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public String getTakerSide() {
    return takerSide;
  }

  public String getCreatedAt() {
    return createdAt;
  }
}
