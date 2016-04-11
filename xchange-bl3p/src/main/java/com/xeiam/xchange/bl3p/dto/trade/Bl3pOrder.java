package com.xeiam.xchange.bl3p.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xeiam.xchange.bl3p.dto.Bl3pError;
import com.xeiam.xchange.bl3p.dto.Bl3pValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 19/08/15
 * Time: 14:02
 */
public class Bl3pOrder extends Bl3pError {

  private Long date;
  private Bl3pValue totalSpent;
  private String item;
  private Bl3pValue amount;
  private List<Object> trades = new ArrayList<Object>();
  private String label;
  private String type;
  private Bl3pValue totalAmount;
  private Bl3pValue price;
  private Bl3pValue totalFee;
  private String currency;
  private Long orderId;
  private Bl3pOrderStatus status;

  public Bl3pOrder(@JsonProperty("code") String code,
                   @JsonProperty("message") String message,
                   @JsonProperty("date") Long date,
                   @JsonProperty("total_spent") Bl3pValue totalSpent,
                   @JsonProperty("item") String item,
                   @JsonProperty("amount") Bl3pValue amount,
                   @JsonProperty("trades") List<Object> trades,
                   @JsonProperty("label") String label,
                   @JsonProperty("type") String type,
                   @JsonProperty("total_amount") Bl3pValue totalAmount,
                   @JsonProperty("price") Bl3pValue price,
                   @JsonProperty("total_fee") Bl3pValue totalFee,
                   @JsonProperty("currency") String currency,
                   @JsonProperty("order_id") Long orderId,
                   @JsonProperty("status") Bl3pOrderStatus status) {
    super(code, message);

    this.date = date;
    this.totalSpent = totalSpent;
    this.item = item;
    this.amount = amount;
    this.trades = trades;
    this.label = label;
    this.type = type;
    this.totalAmount = totalAmount;
    this.price = price;
    this.totalFee = totalFee;
    this.currency = currency;
    this.orderId = orderId;
    this.status = status;
  }

  public Long getDate() {
    return date;
  }

  public Bl3pValue getTotalSpent() {
    return totalSpent;
  }

  public String getItem() {
    return item;
  }

  public Bl3pValue getAmount() {
    return amount;
  }

  public List<Object> getTrades() {
    return trades;
  }

  public String getLabel() {
    return label;
  }

  public String getType() {
    return type;
  }

  public Bl3pValue getTotalAmount() {
    return totalAmount;
  }

  public Bl3pValue getPrice() {
    return price;
  }

  public Bl3pValue getTotalFee() {
    return totalFee;
  }

  public String getCurrency() {
    return currency;
  }

  public Long getOrderId() {
    return orderId;
  }

  public Bl3pOrderStatus getStatus() {
    return status;
  }

  public enum Bl3pOrderStatus {
    pending,
    open,
    closed,
    cancelled,
    placed
  }

  @Override
  public String toString() {
    return "Bl3pOrder{" +
        "date=" + date +
        ", totalSpent=" + totalSpent +
        ", item='" + item + '\'' +
        ", amount=" + amount +
        ", trades=" + trades +
        ", label='" + label + '\'' +
        ", type='" + type + '\'' +
        ", totalAmount=" + totalAmount +
        ", price=" + price +
        ", totalFee=" + totalFee +
        ", currency='" + currency + '\'' +
        ", orderId=" + orderId +
        ", status=" + status +
        '}';
  }
}
