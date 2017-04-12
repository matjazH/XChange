package org.knowm.xchange.bitmex.dto.marketdata;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author VITTACH
 */
public class BitMexTicker {
  private BigDecimal max;
  private BigDecimal min;
  private BigDecimal bid;
  private BigDecimal ask;
  private BigDecimal last;
  private BigDecimal vwap;
  private BigDecimal volume;
  private BigDecimal average;

  public BigDecimal getVolume() {
    return volume;
  }

  public BigDecimal getAverag() {
    return average;
  }

  public BitMexTicker(@JsonProperty("high") BigDecimal max, @JsonProperty("low") BigDecimal min,
                      @JsonProperty("last") BigDecimal last, @JsonProperty("buy") BigDecimal bid,
                      @JsonProperty("ask") BigDecimal ask, @JsonProperty("vol_cur") BigDecimal vwap,
                      @JsonProperty("avg") BigDecimal average, @JsonProperty("sell") BigDecimal volume) {
    this.max = max;
    this.min = min;
    this.bid = bid;
    this.ask = ask;
    this.last = last;
    this.vwap = vwap;
    this.volume = volume;
    this.average = average;
  }

  public BigDecimal getMax() {
    return max;
  }

  public BigDecimal getMin() {
    return min;
  }

  public BigDecimal getBid() {
    return bid;
  }

  public BigDecimal getAsk() {
    return ask;
  }

  public BigDecimal getLast() {
    return last;
  }

  public BigDecimal getVwap() {
    return vwap;
  }

  @Override
  public String toString() {
    return "BitMexTicker {" + "max=" + max + ", min=" + min + ", last=" + last + ", bid=" + bid + ", ask=" + ask + ", vwap=" + vwap + ", average=" + average + ", volume=" + volume + '}';
  }
}
