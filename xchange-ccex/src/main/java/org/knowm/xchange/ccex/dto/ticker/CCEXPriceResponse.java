package org.knowm.xchange.ccex.dto.ticker;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CCEXPriceResponse {

  private int updated;
  private BigDecimal low;
  private BigDecimal avg;
  private BigDecimal buy;
  private BigDecimal high;
  private BigDecimal sell;
  private BigDecimal lastbuy;
  private BigDecimal lastsell;
  private BigDecimal lastprice;
  private BigDecimal buysupport;

  public CCEXPriceResponse(
      @JsonProperty("high") BigDecimal high,
      @JsonProperty("low") BigDecimal low,
      @JsonProperty("avg") BigDecimal avg,
      @JsonProperty("lastbuy") BigDecimal lastbuy,
      @JsonProperty("lastsell") BigDecimal lastsell,
      @JsonProperty("buy") BigDecimal buy,
      @JsonProperty("sell") BigDecimal sell,
      @JsonProperty("lastprice") BigDecimal lastprice,
      @JsonProperty("buysupport") BigDecimal buysupport,
      @JsonProperty("updated") int updated) {
    super();
    this.low = low;
    this.avg = avg;
    this.buy = buy;
    this.high = high;
    this.sell = sell;
    this.lastbuy = lastbuy;
    this.updated = updated;
    this.lastsell = lastsell;
    this.lastprice = lastprice;
    this.buysupport = buysupport;
  }

  public BigDecimal getLow() {
    return low;
  }

  public BigDecimal getAvg() {
    return avg;
  }

  public BigDecimal getBuy() {
    return buy;
  }

  public BigDecimal getSell() {
    return sell;
  }

  public BigDecimal getHigh() {
    return high;
  }

  public int getUpdated() {
    return updated;
  }

  public void setLow(BigDecimal low) {
    this.low = low;
  }

  public void setAvg(BigDecimal avg) {
    this.avg = avg;
  }

  public void setBuy(BigDecimal buy) {
    this.buy = buy;
  }

  public void setUpdated(int updated) {
    this.updated = updated;
  }

  public void setHigh(BigDecimal high) {
    this.high = high;
  }

  public void setSell(BigDecimal sell) {
    this.sell = sell;
  }

  public BigDecimal getLastbuy() {
    return lastbuy;
  }

  public void setLastbuy(BigDecimal lastbuy) {
    this.lastbuy = lastbuy;
  }

  public BigDecimal getLastsell() {
    return lastsell;
  }

  public void setLastsell(BigDecimal lastsell) {
    this.lastsell = lastsell;
  }

  public BigDecimal getLastprice() {
    return lastprice;
  }

  public void setLastprice(BigDecimal lastprice) {
    this.lastprice = lastprice;
  }

  public BigDecimal getBuysupport() {
    return buysupport;
  }

  public void setBuysupport(BigDecimal buysupport) {
    this.buysupport = buysupport;
  }

  @Override
  public String toString() {
    return "CCEXPriceResponse [high=" + high + ", low=" + low + ", avg=" + avg + ", lastbuy=" + lastbuy
        + ", lastsell=" + lastsell + ", buy=" + buy + ", sell=" + sell + ", lastprice=" + lastprice
        + ", buysupport=" + buysupport + ", updated=" + updated + "]";
  }
}
