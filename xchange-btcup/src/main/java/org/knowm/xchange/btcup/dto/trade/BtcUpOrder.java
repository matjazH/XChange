package org.knowm.xchange.btcup.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by VITTACH on 29/08/17.
 */
public class BtcUpOrder {

  private Integer id;
  private Long ticks;
  private String pair;
  private Integer type;
  private Integer side;
  private Integer status;
  private Integer tsLevel;
  private BigDecimal leavesQty;
  private BigDecimal cachOrderQty;
  private BigDecimal price;

  public BtcUpOrder(@JsonProperty("id") Integer id,
                    @JsonProperty("pair") String pair,
                    @JsonProperty("side") Integer side,
                    @JsonProperty("cashOrderQty") BigDecimal cashOrderQty,
                    @JsonProperty("leavesQty") BigDecimal leavesQty,
                    @JsonProperty("price") BigDecimal price,
                    @JsonProperty("tsLevel") Integer tsLevel,
                    @JsonProperty("status") Integer status,
                    @JsonProperty("type") Integer type,
                    @JsonProperty("ticks") Long ticks) {
    this.id = id;
    this.pair = pair;
    this.type = type;
    this.side = side;
    this.status = status;
    this.tsLevel = tsLevel;
    this.leavesQty = leavesQty;
    this.cachOrderQty = cashOrderQty;
    this.price = price;
    this.ticks = ticks;
  }

  public Integer getId() {
    return id;
  }

  public String getPair() {
    int separatorPos = pair.indexOf("_");
    return pair.substring(separatorPos + 1) + "_" + pair.substring(0, separatorPos);
  }

  public Integer getType() {
    return type;
  }

  public Integer getSide() {
    return side;
  }

  public Integer getStatus() {
    return status;
  }

  public Integer getTsLevel() {
    return tsLevel;
  }

  public BigDecimal getLeavesQty() {
    return leavesQty;
  }

  public BigDecimal getCachOrderQty() {
    return cachOrderQty;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public Long getTicks() {
    return ticks;
  }

  @Override
  public String toString() {
    return "BtcUpOrder{" +
        "id=" + id +
        ", pair='" + pair + '\'' +
        ", type=" + type +
        ", side=" + side +
        ", status=" + status +
        ", tsLevel=" + tsLevel +
        ", leavesQty=" + leavesQty +
        ", cachOrderQty=" + cachOrderQty +
        ", price=" + price +
        ", ticks=" + ticks +
        '}';
  }
}
