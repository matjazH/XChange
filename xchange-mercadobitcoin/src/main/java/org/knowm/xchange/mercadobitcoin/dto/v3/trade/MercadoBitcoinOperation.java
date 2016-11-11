package org.knowm.xchange.mercadobitcoin.dto.v3.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by tabtrader on 01/11/2016.
 */
public class MercadoBitcoinOperation {

  private Integer operationId;
  private BigDecimal quantity;
  private BigDecimal price;
  private BigDecimal feeRate;
  private Long executedTimestamp;

  public MercadoBitcoinOperation(@JsonProperty("operation_id") Integer operationId,
                                 @JsonProperty("quantity") BigDecimal quantity,
                                 @JsonProperty("price") BigDecimal price,
                                 @JsonProperty("fee_rate") BigDecimal feeRate,
                                 @JsonProperty("executed_timestamp") Long executedTimestamp) {

    this.operationId = operationId;
    this.quantity = quantity;
    this.price = price;
    this.feeRate = feeRate;
    this.executedTimestamp = executedTimestamp;
  }

  public Integer getOperationId() {
    return operationId;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BigDecimal getFeeRate() {
    return feeRate;
  }

  public Long getExecutedTimestamp() {
    return executedTimestamp;
  }
}
