package com.xeiam.xchange.exmo.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 17/12/15
 * Time: 16:16
 */
public class ExmoTicker {

  /*
  {"buyPrice":"441.80226","sell_price":"444.5","last_trade":"444.499999","high":"447.99","low":"436.002","avg":"441.44583075","vol":"206.27803727","vol_curr":"90845.08535107","updated":1450358278}
   */

  protected final BigDecimal buyPrice;
  protected final BigDecimal sellPrice;
  protected final BigDecimal lastTrade;
  protected final BigDecimal high;
  protected final BigDecimal low;
  protected final BigDecimal avg;
  protected final BigDecimal vol;
  protected final BigDecimal volCurr;
  protected final Long updated;

  public ExmoTicker(@JsonProperty("buyPrice") BigDecimal buyPrice,
                @JsonProperty("sell_price") BigDecimal sellPrice,
                @JsonProperty("last_trade") BigDecimal lastTrade,
                @JsonProperty("high") BigDecimal high,
                @JsonProperty("low") BigDecimal low,
                @JsonProperty("avg") BigDecimal avg,
                @JsonProperty("vol") BigDecimal vol,
                @JsonProperty("vol_curr") BigDecimal volCurr,
                @JsonProperty("updated") long updated)
  {
    this.buyPrice = buyPrice;
    this.sellPrice = sellPrice;
    this.lastTrade = lastTrade;
    this.high = high;
    this.low = low;
    this.avg = avg;
    this.vol = vol;
    this.volCurr = volCurr;
    this.updated = updated;
  }

  public BigDecimal getBuyPrice() {
    return buyPrice;
  }

  public BigDecimal getSellPrice() {
    return sellPrice;
  }

  public BigDecimal getLastTrade() {
    return lastTrade;
  }

  public BigDecimal getHigh() {
    return high;
  }

  public BigDecimal getLow() {
    return low;
  }

  public BigDecimal getAvg() {
    return avg;
  }

  public BigDecimal getVol() {
    return vol;
  }

  public BigDecimal getVolCurr() {
    return volCurr;
  }

  public Long getUpdated() {
    return updated;
  }

  @Override
  public String toString() {
    return "ExmoTicker{" +
        "buyPrice=" + buyPrice +
        ", sellPrice=" + sellPrice +
        ", lastTrade=" + lastTrade +
        ", high=" + high +
        ", low=" + low +
        ", avg=" + avg +
        ", vol=" + vol +
        ", volCurr=" + volCurr +
        ", updated=" + updated +
        '}';
  }
}
