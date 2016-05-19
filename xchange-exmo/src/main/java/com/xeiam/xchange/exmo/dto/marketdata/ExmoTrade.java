package com.xeiam.xchange.exmo.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 21/12/15
 * Time: 16:11
 */
public class ExmoTrade {

  /*
  {"trade_id":2949777,"type":"sell","quantity":"0.06282","price":"440","amount":"27.6408","date":1450703192}
   */

  protected final Long tradeId;
  protected final String type;
  protected final BigDecimal quantity;
  protected final BigDecimal price;
  protected final BigDecimal amount;
  protected final Long date;

  public ExmoTrade(@JsonProperty("trade_id") Long tradeId,
                   @JsonProperty("type") String type,
                   @JsonProperty("quantity") BigDecimal quantity,
                   @JsonProperty("price") BigDecimal price,
                   @JsonProperty("amount") BigDecimal amount,
                   @JsonProperty("date") Long date){
    this.tradeId = tradeId;
    this.type = type;
    this.quantity = quantity;
    this.price = price;
    this.amount = amount;
    this.date = date;
  }

  public Long getTradeId() {
    return tradeId;
  }

  public String getType() {
    return type;
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

  public Long getDate() {
    return date;
  }

  @Override
  public String toString() {
    return "ExmoTrade{" +
        "tradeId=" + tradeId +
        ", type='" + type + '\'' +
        ", quantity=" + quantity +
        ", price=" + price +
        ", amount=" + amount +
        ", date=" + date +
        '}';
  }
}
