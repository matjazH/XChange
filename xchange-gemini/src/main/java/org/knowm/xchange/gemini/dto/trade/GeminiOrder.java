package org.knowm.xchange.gemini.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by tabtrader on 05/10/2016.
 */
public class GeminiOrder {

  private String orderId;
  private String clientOrderId;
  private String symbol;
  private String exchange;
  private BigDecimal price;
  private BigDecimal avgExecutionPrice;
  private String side;
  private String type;
  private Long timestampms;
  private Long timestamp;
  private Boolean isLive;
  private Boolean isCancelled;
  private BigDecimal executedAmount;
  private BigDecimal remainingAmount;
  private BigDecimal originalAmount;


  public GeminiOrder(@JsonProperty("order_id") String orderId,
                     @JsonProperty("client_order_id") String clientOrderId,
                     @JsonProperty("symbol") String symbol,
                     @JsonProperty("exchange") String exchange,
                     @JsonProperty("price") BigDecimal price,
                     @JsonProperty("avg_execution_price") BigDecimal avgExecutionPrice,
                     @JsonProperty("side") String side,
                     @JsonProperty("type") String type,
                     @JsonProperty("timestampms") Long timestampms,
                     @JsonProperty("timestamp") Long timestamp,
                     @JsonProperty("is_live") Boolean isLive,
                     @JsonProperty("is_cancelled") Boolean isCancelled,
                     @JsonProperty("executed_amount") BigDecimal executedAmount,
                     @JsonProperty("remaining_amount") BigDecimal remainingAmount,
                     @JsonProperty("original_amount") BigDecimal originalAmount) {

    this.side = side;
    this.orderId = orderId;
    this.clientOrderId = clientOrderId;
    this.symbol = symbol;
    this.exchange = exchange;
    this.price = price;
    this.avgExecutionPrice = avgExecutionPrice;
    this.type = type;
    this.timestampms = timestampms;
    this.timestamp = timestamp;
    this.isLive = isLive;
    this.isCancelled = isCancelled;
    this.executedAmount = executedAmount;
    this.remainingAmount = remainingAmount;
    this.originalAmount = originalAmount;
  }

  public String getOrderId() {
    return orderId;
  }

  public String getClientOrderId() {
    return clientOrderId;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getExchange() {
    return exchange;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BigDecimal getAvgExecutionPrice() {
    return avgExecutionPrice;
  }

  public String getSide() {
    return side;
  }

  public String getType() {
    return type;
  }

  public Long getTimestampms() {
    return timestampms;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public Boolean getLive() {
    return isLive;
  }

  public Boolean getCancelled() {
    return isCancelled;
  }

  public BigDecimal getExecutedAmount() {
    return executedAmount;
  }

  public BigDecimal getRemainingAmount() {
    return remainingAmount;
  }

  public BigDecimal getOriginalAmount() {
    return originalAmount;
  }

  @Override
  public String toString() {
    return "GeminiOrder{" +
        "orderId='" + orderId + '\'' +
        ", clientOrderId='" + clientOrderId + '\'' +
        ", symbol='" + symbol + '\'' +
        ", exchange='" + exchange + '\'' +
        ", price=" + price +
        ", avgExecutionPrice=" + avgExecutionPrice +
        ", type='" + type + '\'' +
        ", timestampms=" + timestampms +
        ", timestamp=" + timestamp +
        ", isLive=" + isLive +
        ", isCancelled=" + isCancelled +
        ", executedAmount=" + executedAmount +
        ", remainingAmount=" + remainingAmount +
        ", originalAmount=" + originalAmount +
        '}';
  }
}
