package com.xeiam.xchange.bl3p.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xeiam.xchange.bl3p.dto.Bl3pValue;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 19/08/15
 * Time: 16:59
 */
public class Bl3pOpenOrder {

  public final long date;
  public final String item;
  public final Bl3pValue amount;
  public final Bl3pValue price;
  public final String currency;
  public final String label;
  public final String type;
  public final long orderId;
  public final String status;
  public final Bl3pValue amountExecuted;

  public Bl3pOpenOrder(@JsonProperty("date") long date,
                       @JsonProperty("item") String item,
                       @JsonProperty("amount") Bl3pValue amount,
                       @JsonProperty("price") Bl3pValue price,
                       @JsonProperty("currency") String currency,
                       @JsonProperty("label") String label,
                       @JsonProperty("type") String type,
                       @JsonProperty("order_id") long orderId,
                       @JsonProperty("status") String status,
                       @JsonProperty("amount_executed") Bl3pValue amountExecuted){
    this.date = date;
    this.item = item;
    this.amount = amount;
    this.price = price;
    this.currency = currency;
    this.label = label;
    this.type = type;
    this.orderId = orderId;
    this.status = status;
    this.amountExecuted = amountExecuted;
  }

  public long getDate() {
    return date;
  }

  public String getItem() {
    return item;
  }

  public Bl3pValue getAmount() {
    return amount;
  }

  public Bl3pValue getPrice() {
    return price;
  }

  public String getCurrency() {
    return currency;
  }

  public String getLabel() {
    return label;
  }

  public String getType() {
    return type;
  }

  public long getOrderId() {
    return orderId;
  }

  public String getStatus() {
    return status;
  }

  public Bl3pValue getAmountExecuted() {
    return amountExecuted;
  }
}
