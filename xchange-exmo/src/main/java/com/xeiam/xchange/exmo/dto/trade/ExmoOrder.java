package com.xeiam.xchange.exmo.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 21/12/15
 * Time: 20:08
 */
public class ExmoOrder {

  protected final String orderId;
  protected final Long created;
  protected final String type;
  protected final String pair;
  protected final BigDecimal price;
  protected final BigDecimal quantity;
  protected final BigDecimal amount;

  public ExmoOrder(@JsonProperty("order_id") String orderId,
                   @JsonProperty("created") Long created,
                   @JsonProperty("type") String type,
                   @JsonProperty("pair") String pair,
                   @JsonProperty("price") BigDecimal price,
                   @JsonProperty("quantity") BigDecimal quantity,
                   @JsonProperty("amount") BigDecimal amount) {
    this.orderId = orderId;
    this.created = created;
    this.type = type;
    this.pair = pair;
    this.price = price;
    this.quantity = quantity;
    this.amount = amount;
  }

  public String getOrderId() {
    return orderId;
  }

  public Long getCreated() {
    return created;
  }

  public String getType() {
    return type;
  }

  public String getPair() {
    return pair;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }

  public BigDecimal getAmount() {
    return amount;
  }
}
