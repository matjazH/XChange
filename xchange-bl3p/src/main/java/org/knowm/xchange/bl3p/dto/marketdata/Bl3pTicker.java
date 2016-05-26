package org.knowm.xchange.bl3p.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 14/08/15
 * Time: 19:06
 */
public class Bl3pTicker {

  /*
  {"currency":"BTC",
  "last":240.99,
  "bid":240.43,
  "ask":241.01,
  "high":240.99,
  "low":229.10891,
  "timestamp":1439565730,
  "volume":{"24h":164.41836827,"30d":3537.23019378}}
   */

  private String currency;
  private BigDecimal last;
  private BigDecimal bid;
  private BigDecimal ask;
  private BigDecimal high;
  private BigDecimal low;
  private Long timestamp;
  private Bl3pVolume volume;

  public Bl3pTicker(@JsonProperty("currency") String currency,
                    @JsonProperty("last") BigDecimal last,
                    @JsonProperty("bid") BigDecimal bid,
                    @JsonProperty("ask") BigDecimal ask,
                    @JsonProperty("high") BigDecimal high,
                    @JsonProperty("low") BigDecimal low,
                    @JsonProperty("timestamp") Long timestamp,
                    @JsonProperty("volume") Bl3pVolume volume) {
    this.currency = currency;
    this.last = last;
    this.bid = bid;
    this.ask = ask;
    this.high = high;
    this.low = low;
    this.timestamp = timestamp;
    this.volume = volume;
  }

  public String getCurrency() {
    return currency;
  }

  public BigDecimal getLast() {
    return last;
  }

  public BigDecimal getBid() {
    return bid;
  }

  public BigDecimal getAsk() {
    return ask;
  }

  public BigDecimal getHigh() {
    return high;
  }

  public BigDecimal getLow() {
    return low;
  }

  public Bl3pVolume getVolume() {
    return volume;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return "Bl3pTicker{" +
        "currency='" + currency + '\'' +
        ", last=" + last +
        ", bid=" + bid +
        ", ask=" + ask +
        ", high=" + high +
        ", low=" + low +
        ", timestamp=" + timestamp +
        ", volume=" + volume +
        '}';
  }
}
