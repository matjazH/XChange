package org.knowm.xchange.gemini.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by tabtrader on 03/10/2016.
 */
public class GeminiOrderBook {

  /*
  RESPONSE

  The response will be two arrays

  Field	Type	Description
  bids	array	The bids currently on the book. These are offers to buy at a given price
  asks	array	The asks currently on the book. These are offers to sell at a given price
  The bids and the asks are grouped by price, so each entry may represent multiple orders at that price. Each element of the array will be a JSON object

  Field	Type	Description
  price	decimal	The price
  amount	decimal	The total quantity remaining at the price
  timestamp	timestamp	DO NOT USE - this field is included for compatibility reasons only and is just populated with a dummy value.
   */

  private List<Order> bids;
  private List<Order> asks;

  public GeminiOrderBook(@JsonProperty("bids") List<Order> bids,
                         @JsonProperty("aks") List<Order> asks) {
    this.bids = bids;
    this.asks = asks;
  }

  public List<Order> getBids() {
    return bids;
  }

  public List<Order> getAsks() {
    return asks;
  }

  public static class Order {

    private BigDecimal price;
    private BigDecimal amount;

    public Order(@JsonProperty("price") BigDecimal price,
                 @JsonProperty("amount") BigDecimal amount) {
      this.price = price;
      this.amount = amount;
    }

    public BigDecimal getPrice() {
      return price;
    }

    public BigDecimal getAmount() {
      return amount;
    }
  }

}
