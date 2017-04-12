package org.knowm.xchange.gemini.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by tabtrader on 04/10/2016.
 */
public class GeminiTrade {

  private Long timestamp;
  private Long timestampms;
  private Integer tid;
  private BigDecimal price;
  private BigDecimal amount;
  private String exchange;
  private Type type;
  private Boolean broken;

  public GeminiTrade(@JsonProperty("timestamp") Long timestamp,
                     @JsonProperty("timestampms") Long timestampms,
                     @JsonProperty("tid") Integer tid,
                     @JsonProperty("price") BigDecimal price,
                     @JsonProperty("amount") BigDecimal amount,
                     @JsonProperty("exchange") String exchange,
                     @JsonProperty("type") Type type,
                     @JsonProperty("broken") Boolean broken) {
    this.timestamp = timestamp;
    this.timestampms = timestampms;
    this.tid = tid;
    this.price = price;
    this.amount = amount;
    this.exchange = exchange;
    this.type = type;
    this.broken = broken;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public Long getTimestampms() {
    return timestampms;
  }

  public Integer getTid() {
    return tid;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public String getExchange() {
    return exchange;
  }

  public Type getType() {
    return type;
  }

  public Boolean getBroken() {
    return broken;
  }

  public enum Type {
    buy,
    sell,
    auction
  }
}
