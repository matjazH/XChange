package org.knowm.xchange.mercadobitcoin.dto.v3.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tabtrader on 01/11/2016.
 */
public class MercadoBitcoinOrder {

  private Integer orderId;
  private String coinPair;
  private Integer orderType;
  private Integer status;
  private Boolean hasFills;
  private BigDecimal quantity;
  private BigDecimal limitPrice;
  private BigDecimal executedQuantity;
  private BigDecimal executedPriceAvg;
  private BigDecimal fee;
  private Long createdTimestamp;
  private Long updatedTimestamp;
  private List<MercadoBitcoinOperation> operations = new ArrayList<>();

  public MercadoBitcoinOrder(@JsonProperty("order_id") Integer orderId,
                             @JsonProperty("coin_pair") String coinPair,
                             @JsonProperty("order_type") Integer orderType,
                             @JsonProperty("status") Integer status,
                             @JsonProperty("has_fills") Boolean hasFills,
                             @JsonProperty("quantity") BigDecimal quantity,
                             @JsonProperty("limit_price") BigDecimal limitPrice,
                             @JsonProperty("executed_quantity") BigDecimal executedQuantity,
                             @JsonProperty("executed_price_avg") BigDecimal executedPriceAvg,
                             @JsonProperty("fee") BigDecimal fee,
                             @JsonProperty("created_timestamp") Long createdTimestamp,
                             @JsonProperty("updated_timestamp") Long updatedTimestamp,
                             @JsonProperty("operations") List<MercadoBitcoinOperation> operations) {
    this.orderId = orderId;
    this.coinPair = coinPair;
    this.orderType = orderType;
    this.status = status;
    this.hasFills = hasFills;
    this.quantity = quantity;
    this.limitPrice = limitPrice;
    this.executedQuantity = executedQuantity;
    this.executedPriceAvg = executedPriceAvg;
    this.fee = fee;
    this.createdTimestamp = createdTimestamp;
    this.updatedTimestamp = updatedTimestamp;
    this.operations = operations;
  }

  public Integer getOrderId() {
    return orderId;
  }

  public String getCoinPair() {
    return coinPair;
  }

  public Integer getOrderType() {
    return orderType;
  }

  public Integer getStatus() {
    return status;
  }

  public Boolean getHasFills() {
    return hasFills;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }

  public BigDecimal getLimitPrice() {
    return limitPrice;
  }

  public BigDecimal getExecutedQuantity() {
    return executedQuantity;
  }

  public BigDecimal getExecutedPriceAvg() {
    return executedPriceAvg;
  }

  public BigDecimal getFee() {
    return fee;
  }

  public Long getCreatedTimestamp() {
    return createdTimestamp;
  }

  public Long getUpdatedTimestamp() {
    return updatedTimestamp;
  }

  public List<MercadoBitcoinOperation> getOperations() {
    return operations;
  }
}
