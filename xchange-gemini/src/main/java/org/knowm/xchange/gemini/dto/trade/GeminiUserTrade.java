package org.knowm.xchange.gemini.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by tabtrader on 05/10/2016.
 */
public class GeminiUserTrade {

  private BigDecimal price;
  private BigDecimal amount;
  private Long timestamp;
  private Long timestampms;
  private Type type;
  private Boolean aggressor;
  private String feeCurrency;
  private BigDecimal feeAmount;
  private Long tid;
  private String orderId;
  private String clientOrderId;
  private String breakType;

  public GeminiUserTrade(@JsonProperty("price") BigDecimal price,
                         @JsonProperty("amount") BigDecimal amount,
                         @JsonProperty("timestamp") Long timestamp,
                         @JsonProperty("timestampms") Long timestampms,
                         @JsonProperty("type") Type type,
                         @JsonProperty("aggressor") Boolean aggressor,
                         @JsonProperty("fee_currency") String feeCurrency,
                         @JsonProperty("fee_amount") BigDecimal feeAmount,
                         @JsonProperty("tid") Long tid,
                         @JsonProperty("order_id") String orderId,
                         @JsonProperty("client_order_id") String clientOrderId,
                         @JsonProperty("break") String breakType) {
    this.price = price;
    this.amount = amount;
    this.timestamp = timestamp;
    this.timestampms = timestampms;
    this.type = type;
    this.aggressor = aggressor;
    this.feeCurrency = feeCurrency;
    this.feeAmount = feeAmount;
    this.tid = tid;
    this.orderId = orderId;
    this.clientOrderId = clientOrderId;
    this.breakType = breakType;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public Long getTimestampms() {
    return timestampms;
  }

  public Type getType() {
    return type;
  }

  public Boolean getAggressor() {
    return aggressor;
  }

  public String getFeeCurrency() {
    return feeCurrency;
  }

  public BigDecimal getFeeAmount() {
    return feeAmount;
  }

  public Long getTid() {
    return tid;
  }

  public String getOrderId() {
    return orderId;
  }

  public String getClientOrderId() {
    return clientOrderId;
  }

  public String getBreakType() {
    return breakType;
  }


  public enum Type {
    Buy,
    Sell
  }
}
