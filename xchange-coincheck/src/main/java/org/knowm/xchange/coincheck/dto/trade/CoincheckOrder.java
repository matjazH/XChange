package org.knowm.xchange.coincheck.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.coincheck.dto.CoincheckOrderSide;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 02/06/16
 * Time: 18:09
 */
public class CoincheckOrder {

  public Long id;
  public CoincheckOrderSide orderType;
  public String pair;
  public BigDecimal rate;
  public BigDecimal pendingAmount;
  public BigDecimal pendingMarketBuyAmount;
  public BigDecimal stopLossRate;
  public String createdAt;


  public CoincheckOrder(@JsonProperty("id") Long id,
                        @JsonProperty("order_type") CoincheckOrderSide orderType,
                        @JsonProperty("rate") BigDecimal rate,
                        @JsonProperty("pair") String pair,
                        @JsonProperty("pending_amount") BigDecimal pendingAmount,
                        @JsonProperty("pending_market_buy_amount") BigDecimal pendingMarketBuyAmount,
                        @JsonProperty("stop_loss_rate") BigDecimal stopLossRate,
                        @JsonProperty("created_at") String createdAt){
    this.id = id;
    this.orderType = orderType;
    this.rate = rate;
    this.pair = pair;
    this.pendingAmount = pendingAmount;
    this.pendingMarketBuyAmount = pendingMarketBuyAmount;
    this.stopLossRate = stopLossRate;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public CoincheckOrderSide getOrderType() {
    return orderType;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public String getPair() {
    return pair;
  }

  public BigDecimal getPendingAmount() {
    return pendingAmount;
  }

  public BigDecimal getPendingMarketBuyAmount() {
    return pendingMarketBuyAmount;
  }

  public BigDecimal getStopLossRate() {
    return stopLossRate;
  }

  public String getCreatedAt() {
    return createdAt;
  }

}
