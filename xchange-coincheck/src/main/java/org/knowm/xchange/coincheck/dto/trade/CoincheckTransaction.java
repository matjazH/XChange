package org.knowm.xchange.coincheck.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.coincheck.dto.CoincheckOrderSide;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 02/06/16
 * Time: 18:53
 */
public class CoincheckTransaction {

  public final Long id;
  public final Long orderId;
  public final String createdAt;
  public final Map<String, BigDecimal> funds;
  public final String pair;
  public final BigDecimal rate;
  public final String feeCurrency;
  public final BigDecimal fee;
  public final String liquidity;
  public final CoincheckOrderSide side;

  public CoincheckTransaction(@JsonProperty("id") Long id,
                 @JsonProperty("order_id") Long orderId,
                 @JsonProperty("created_at") String createdAt,
                 @JsonProperty("funds") Map<String, BigDecimal> funds,
                 @JsonProperty("pair") String pair,
                 @JsonProperty("rate") BigDecimal rate,
                 @JsonProperty("fee_currency") String feeCurrency,
                 @JsonProperty("fee") BigDecimal fee,
                 @JsonProperty("liquidity") String liquidity,
                 @JsonProperty("side") CoincheckOrderSide side) {
    this.id = id;
    this.orderId = orderId;
    this.createdAt = createdAt;
    this.funds = funds;
    this.pair = pair;
    this.rate = rate;
    this.feeCurrency = feeCurrency;
    this.fee = fee;
    this.liquidity = liquidity;
    this.side = side;
  }

  public Long getId() {
    return id;
  }

  public Long getOrderId() {
    return orderId;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public Map<String, BigDecimal> getFunds() {
    return funds;
  }

  public String getPair() {
    return pair;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public String getFeeCurrency() {
    return feeCurrency;
  }

  public BigDecimal getFee() {
    return fee;
  }

  public String getLiquidity() {
    return liquidity;
  }

  public CoincheckOrderSide getSide() {
    return side;
  }
}
