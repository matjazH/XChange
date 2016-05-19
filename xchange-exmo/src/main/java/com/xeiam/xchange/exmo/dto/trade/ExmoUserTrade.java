package com.xeiam.xchange.exmo.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 22/12/15
 * Time: 17:45
 */
public class ExmoUserTrade {

  /*
  "trade_id": 3,
      "date": 1435488248,
      "type": "buy",
      "pair": "BTC_USD",
      "order_id": 7,
      "quantity": 1,
      "price": 100,
      "amount": 100
   */

  protected final Long tradeId;
  protected final Long date;
  protected final String type;
  protected final String pair;
  protected final Long orderId;
  protected final BigDecimal quantity;
  protected final BigDecimal price;
  protected final BigDecimal amount;

  public ExmoUserTrade(@JsonProperty("trade_id") Long tradeId,
                       @JsonProperty("date") Long date,
                       @JsonProperty("type") String type,
                       @JsonProperty("pair") String pair,
                       @JsonProperty("order_id") Long orderId,
                       @JsonProperty("quantity") BigDecimal quantity,
                       @JsonProperty("price") BigDecimal price,
                       @JsonProperty("amount") BigDecimal amount){
    this.tradeId = tradeId;
    this.date = date;
    this.type = type;
    this.pair = pair;
    this.orderId = orderId;
    this.quantity = quantity;
    this.price = price;
    this.amount = amount;
  }

  public Long getTradeId() {
    return tradeId;
  }

  public Long getDate() {
    return date;
  }

  public String getType() {
    return type;
  }

  public String getPair() {
    return pair;
  }

  public Long getOrderId() {
    return orderId;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BigDecimal getAmount() {
    return amount;
  }
}
