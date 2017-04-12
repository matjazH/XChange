package org.knowm.xchange.coincheck.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.coincheck.dto.CoincheckOrderSide;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 30/05/16
 * Time: 17:44
 */
public class CoincheckTrade {

  /*
  {"id":642439,"amount":"0.0245769","rate":60396,"order_type":"buy","created_at":"2016-05-30T14:45:11.000Z"}
   */

  private final Long id;
  private final BigDecimal amount;
  private final BigDecimal rate;
  private final CoincheckOrderSide orderType;
  private final String createdAt;

  public CoincheckTrade(@JsonProperty("id") Long id,
                        @JsonProperty("amount") BigDecimal amount,
                        @JsonProperty("rate") BigDecimal rate,
                        @JsonProperty("order_type") CoincheckOrderSide orderType,
                        @JsonProperty("created_at") String createdAt) {
    this.id = id;
    this.amount = amount;
    this.rate = rate;
    this.orderType = orderType;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public CoincheckOrderSide getOrderType() {
    return orderType;
  }

  public String getCreatedAt() {
    return createdAt;
  }
}
