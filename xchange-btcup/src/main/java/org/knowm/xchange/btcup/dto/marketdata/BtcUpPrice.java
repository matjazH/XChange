package org.knowm.xchange.btcup.dto.marketdata;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by VITTACH on 28/08/17.
 */
public class BtcUpPrice {

  private BigDecimal high;
  private BigDecimal low;
  private BigDecimal daily_volume;
  private BigDecimal daily_change;
  private BigDecimal bid;
  private BigDecimal ask;
  private BigDecimal last;

  public BtcUpPrice(
      @JsonProperty("high") BigDecimal high,
      @JsonProperty("low") BigDecimal low,
      @JsonProperty("daily_volume") BigDecimal daily_volume,
      @JsonProperty("daily_change") BigDecimal daily_change,
      @JsonProperty("bid") BigDecimal bid,
      @JsonProperty("ask") BigDecimal ask,
      @JsonProperty("last") BigDecimal last) {
    super();
    this.low = low;
    this.bid = bid;
    this.ask = ask;
    this.high = high;
    this.last = last;
    this.daily_volume = daily_volume;
    this.daily_change = daily_change;
  }

  public BigDecimal getHigh() {
    return high;
  }

  public void setHigh(BigDecimal high) {
    this.high = high;
  }

  public BigDecimal getLow() {
    return low;
  }

  public void setLow(BigDecimal low) {
    this.low = low;
  }

  public BigDecimal getDaily_volume() {
    return daily_volume;
  }

  public void setDaily_volume(BigDecimal daily_volume) {
    this.daily_volume = daily_volume;
  }

  public BigDecimal getDaily_change() {
    return daily_change;
  }

  public void setDaily_change(BigDecimal daily_change) {
    this.daily_change = daily_change;
  }

  public BigDecimal getBid() {
    return bid;
  }

  public void setBid(BigDecimal bid) {
    this.bid = bid;
  }

  public BigDecimal getAsk() {
    return ask;
  }

  public void setAsk(BigDecimal ask) {
    this.ask = ask;
  }

  public BigDecimal getLast() {
    return last;
  }

  public void setLast(BigDecimal last) {
    this.last = last;
  }

  @Override
  public String toString() {
    return "BtcUpPrice{" +
        "high=" + high +
        ", low=" + low +
        ", daily_volume=" + daily_volume +
        ", daily_change=" + daily_change +
        ", bid=" + bid +
        ", ask=" + ask +
        ", last=" + last +
        '}';
  }
}
