package org.knowm.xchange.bitmex.dto.marketdata;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BitMexTrade {
  private final Long tid;
  private final String type;
  private final Long timestamp;
  private final BigDecimal price;
  private final BigDecimal amount;

  public BitMexTrade(
      @JsonProperty("type") String type,
      @JsonProperty("price") BigDecimal price,
      @JsonProperty("amount") BigDecimal amount,
      @JsonProperty("tid") Long tid,
      @JsonProperty("timestamp") Long timestamp) {
    super();
    this.type = type;
    this.price = price;
    this.amount = amount;
    this.tid = tid;
    this.timestamp = timestamp;
  }

  public Long getTid() {
    return tid;
  }

  public String getType() {
    return type;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  @Override
  public String toString() {
    return "BitMexTrade [type=" + type + ", price=" + price + ", amount=" + amount + ", tid=" + tid + ", timestamp=" + timestamp + "]";
  }
}
