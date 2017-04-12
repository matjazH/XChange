package org.knowm.xchange.coincheck.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 30/05/16
 * Time: 17:29
 */
public class CoincheckTicker {
/*
  {
    "last": 27390,
      "bid": 26900,
      "ask": 27390,
      "high": 27659,
      "low": 26400,
      "volume": "50.29627103",
      "timestamp": 1423377841
  }
*/

  private final BigDecimal last;
  private final BigDecimal bid;
  private final BigDecimal ask;
  private final BigDecimal high;
  private final BigDecimal low;
  private final BigDecimal volume;
  private final Long timestamp;

  public CoincheckTicker(@JsonProperty("last") BigDecimal last, 
                         @JsonProperty("bid") BigDecimal bid, 
                         @JsonProperty("ask") BigDecimal ask, 
                         @JsonProperty("high") BigDecimal high, 
                         @JsonProperty("low") BigDecimal low, 
                         @JsonProperty("volume") BigDecimal volume,
                         @JsonProperty("timestamp") Long timestamp){
    this.last = last;
    this.bid = bid;
    this.ask = ask;
    this.high = high;
    this.low = low;
    this.volume = volume;
    this.timestamp = timestamp;
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

  public BigDecimal getVolume() {
    return volume;
  }

  public Long getTimestamp() {
    return timestamp;
  }
}
