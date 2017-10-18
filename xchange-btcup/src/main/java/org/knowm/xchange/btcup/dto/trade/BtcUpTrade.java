package org.knowm.xchange.btcup.dto.trade;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by VITTACH on 28/08/17.
 */
public class BtcUpTrade {

  private final String pair;
  private final Integer orderId;
  private final Long id;
  private final Long ticks;
  private final Integer side;
  private final BigDecimal qty;
  private final BigDecimal price;

  public BtcUpTrade(@JsonProperty("id") Long id,
                    @JsonProperty("ticks") Long ticks,
                    @JsonProperty("side") Integer side,
                    @JsonProperty("qty") BigDecimal qty,
                    @JsonProperty("orderId") Integer orderId,
                    @JsonProperty("pair") String pair,
                    @JsonProperty("price") BigDecimal price) {
    super();
    this.pair = pair;
    this.orderId = orderId;
    this.id = id;
    this.qty = qty;
    this.side = side;
    this.ticks = ticks;
    this.price = price;
  }

  public Long getId() {
    return id;
  }

  public Long getTicks() {
    return ticks;
  }

  public Integer getSide() {
    return side;
  }

  public BigDecimal getQty() {
    return qty;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public String getPair() {
    return pair;
  }

  public Integer getOrderId() {
    return orderId;
  }

  @Override
  public String toString() {
    return "BtcUpTrade{" +
        "pair='" + pair + '\'' +
        ", orderId=" + orderId +
        ", id=" + id +
        ", ticks=" + ticks +
        ", side=" + side +
        ", qty=" + qty +
        ", price=" + price +
        '}';
  }
}
