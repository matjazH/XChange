package org.knowm.xchange.hitbtc.dto.trade;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HitbtcOrder {

  private Date createdAt;
  private final String type;
  private final String side;
  private final String status;
  private final String symbol;
  private final String clientOrderId;
  private final BigDecimal orderPrice;
  private final BigDecimal cumQuantity;
  private final BigDecimal orderQuantity;
  private final Long orderId;

  public HitbtcOrder(@JsonProperty("id") long orderId, @JsonProperty("clientOrderId") String clientOrderId,
                     @JsonProperty("symbol") String symbol, @JsonProperty("side") String side,
                     @JsonProperty("status") String status, @JsonProperty("type") String type,
                     @JsonProperty("timeInForce") String timeInForce,
                     @JsonProperty("quantity") BigDecimal quantity,@JsonProperty("price") BigDecimal price,
                     @JsonProperty("cumQuantity") BigDecimal cumQuantity,
                     @JsonProperty("createdAt") String createdAt,
                     @JsonProperty("updatedAt") String updatedAt) {

    super();
    this.orderId = orderId;
    this.cumQuantity = cumQuantity;
    this.clientOrderId = clientOrderId;
    this.side = side;
    this.type = type;
    this.status = status;
    this.symbol = symbol;
    this.orderPrice = price;
    this.orderQuantity = quantity;

    try {
      this.createdAt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(createdAt);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public long getLastTimestamp() {

    return createdAt.getTime();
  }

  public String getClientOrderId() {

    return clientOrderId;
  }

  public BigDecimal getOrderPrice() {

    return orderPrice;
  }

  public BigDecimal getOrderQuantity() {

    return orderQuantity;
  }

  public String getSide() {

    return side;
  }

  public String getType() {
    return type;
  }

  public Long getOrderId() {
    return orderId;
  }

  public String getSymbol() {

    return symbol;
  }

  public String getStatus() {
    return status;
  }

  public BigDecimal getCumQuantity() {
    return cumQuantity;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  @Override
  public String toString() {
    return "HitbtcOrder{" +
        "type='" + type + '\'' +
        ", side='" + side + '\'' +
        ", status='" + status + '\'' +
        ", symbol='" + symbol + '\'' +
        ", clientOrderId='" + clientOrderId + '\'' +
        ", orderPrice=" + orderPrice +
        ", cumQuantity=" + cumQuantity +
        ", orderQuantity=" + orderQuantity +
        ", createdAt=" + createdAt +
        ", orderId=" + orderId +
        '}';
  }
}
