package org.knowm.xchange.hitbtc.dto.trade;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HitbtcExecutionReport {

  private Date createdAt;
  private String side;
  private String type;
  private String symbol;
  private String status;
  private String clientOrderId;
  private BigDecimal price;
  private BigDecimal quantity;
  private BigDecimal cumQuantity;
  private Long orderId;

  public HitbtcExecutionReport(@JsonProperty("id") Long orderId, @JsonProperty("clientOrderId") String clientOrderId,
                               @JsonProperty("symbol") String symbol, @JsonProperty("side") String side,
                               @JsonProperty("status") String status, @JsonProperty("type") String type,
                               @JsonProperty("quantity") BigDecimal quantity,@JsonProperty("price") BigDecimal price,
                               @JsonProperty("createdAt") String createdAt,
                               @JsonProperty("cumQuantity") BigDecimal cumQuantity) {

    super();
    this.status = status;
    this.cumQuantity= cumQuantity;
    this.clientOrderId = clientOrderId;
    this.side = side;
    this.price = price;
    this.symbol = symbol;
    this.orderId = orderId;
    this.quantity = quantity;
    this.type = type;
    try {
      this.createdAt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(createdAt);
    } catch (ParseException e) {
      e.printStackTrace();
    }

  }

  public String getSide() {

    return side;
  }

  public Long getOrderId() {

    return orderId;
  }

  public String getClientOrderId() {

    return clientOrderId;
  }

  public String getSymbol() {

    return symbol;
  }

  public long getTimestamp() {

    return createdAt.getTime();
  }

  public BigDecimal getPrice() {

    return price;
  }

  public String getType() {
    return type;
  }

  public String getStatus() {
    return status;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }

  public String getExecReportType() {

    return type;
  }

  public BigDecimal getCumQuantity() {
    return cumQuantity;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  @Override
  public String toString() {
    return "HitbtcExecutionReport{" +
        "side='" + side + '\'' +
        ", type='" + type + '\'' +
        ", symbol='" + symbol + '\'' +
        ", status='" + status + '\'' +
        ", price=" + price +
        ", quantity=" + quantity +
        ", clientOrderId='" + clientOrderId + '\'' +
        ", cumQuantity=" + cumQuantity +
        ", createdAt=" + createdAt +
        ", orderId=" + orderId +
        '}';
  }
}
