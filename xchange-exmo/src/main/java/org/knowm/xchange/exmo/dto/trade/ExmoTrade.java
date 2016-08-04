package org.knowm.xchange.exmo.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 05/04/16
 * Time: 12:00
 */
public class ExmoTrade {
  /*
  {"trade_id":"3909536",
  "order_id":"23608741",
  "trade_dt":"1459753933",
  "trade_type":"1",
  "order_type":"-1",
  "pair_id":"35",
  "quantity":"0.02100000",
  "price":"424.99900000"
  }
*/

  protected final Long tradeId;
  protected final Long orderId;
  protected final Long date;
  protected final String tradeType;
  protected final String orderType;
  protected final Integer pairId;
  protected final BigDecimal quantity;
  protected final BigDecimal price;

  public ExmoTrade(@JsonProperty("trade_id") Long tradeId,
                   @JsonProperty("order_id") Long orderId,
                   @JsonProperty("trade_dt") Long date,
                   @JsonProperty("trade_type") String tradeType,
                   @JsonProperty("order_type") String orderType,
                   @JsonProperty("pair_id") Integer pairId,
                   @JsonProperty("quantity") BigDecimal quantity,
                   @JsonProperty("price") BigDecimal price) {
    this.tradeId = tradeId;
    this.orderId = orderId;
    this.date = date;
    this.tradeType = tradeType;
    this.orderType = orderType;
    this.pairId = pairId;
    this.quantity = quantity;
    this.price = price;
  }

  public Long getTradeId() {
    return tradeId;
  }

  public Long getOrderId() {
    return orderId;
  }

  public Long getDate() {
    return date;
  }

  public String getTradeType() {
    return tradeType;
  }

  public String getOrderType() {
    return orderType;
  }

  public Integer getPairId() {
    return pairId;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }

  public BigDecimal getPrice() {
    return price;
  }

  @Override
  public String toString() {
    return "{" +
        "tradeId=" + tradeId +
        ", orderId=" + orderId +
        ", date=" + new Date(date * 1000) +
        ", tradeType='" + tradeType + '\'' +
        ", orderType='" + orderType + '\'' +
        ", pairId=" + pairId +
        ", quantity=" + quantity +
        ", price=" + price +
        '}';
  }
}
