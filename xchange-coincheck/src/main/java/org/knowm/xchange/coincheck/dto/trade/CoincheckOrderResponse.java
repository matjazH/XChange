package org.knowm.xchange.coincheck.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 06/06/16
 * Time: 13:47
 */
public class CoincheckOrderResponse {

  private Boolean success;
  private Long id;
  private BigDecimal rate;
  private BigDecimal amount;
  private String orderType;
  private BigDecimal stopLossRate;
  private String pair;
  private String createdAt;
  private String error;

  public CoincheckOrderResponse(@JsonProperty("success") Boolean success,
                                @JsonProperty("id") Long id,
                                @JsonProperty("rate") BigDecimal rate,
                                @JsonProperty("amount") BigDecimal amount,
                                @JsonProperty("order_type") String orderType,
                                @JsonProperty("stop_loss_rate") BigDecimal stopLossRate,
                                @JsonProperty("pair") String pair,
                                @JsonProperty("created_at") String createdAt,
                                @JsonProperty("error") String error) {
    this.success = success;
    this.id = id;
    this.rate = rate;
    this.amount = amount;
    this.orderType = orderType;
    this.stopLossRate = stopLossRate;
    this.pair = pair;
    this.createdAt = createdAt;
    this.error = error;
  }

  public Boolean getSuccess() {
    return success;
  }

  public Long getId() {
    return id;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public String getOrderType() {
    return orderType;
  }

  public BigDecimal getStopLossRate() {
    return stopLossRate;
  }

  public String getPair() {
    return pair;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getError() {
    return error;
  }
}
