package com.xeiam.xchange.bl3p.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xeiam.xchange.bl3p.dto.Bl3pValue;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 17/08/15
 * Time: 13:25
 */
public class Bl3pTrade {

  private Long tradeId;// trade_id;
  private Long date;
  private BigDecimal amount; //amount_int int
  private BigDecimal price; //price_int int
  private Long count;

  public Bl3pTrade(@JsonProperty("trade_id") Long tradeId,
                   @JsonProperty("date") Long date,
                   @JsonProperty("amount_int") BigDecimal amount,
                   @JsonProperty("price_int") BigDecimal price,
                   @JsonProperty("count") Long count) {
    this.tradeId = tradeId;
    this.date = date;
    this.amount = amount;
    this.price = price;
    this.count = count;
  }

  public Long getTradeId() {
    return tradeId;
  }

  public Long getDate() {
    return date;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public Long getCount() {
    return count;
  }
}
