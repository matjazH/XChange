package org.knowm.xchange.gemini.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by tabtrader on 03/10/2016.
 */
public class GeminiTicker {

  private BigDecimal bid;
  private BigDecimal ask;
  private BigDecimal last;
  private Map<String, BigDecimal> volume;

  public GeminiTicker(@JsonProperty("bid") BigDecimal bid,
                      @JsonProperty("ask") BigDecimal ask,
                      @JsonProperty("last") BigDecimal last,
                      @JsonProperty("volume") Map<String, BigDecimal> volume) {
    this.bid = bid;
    this.ask = ask;
    this.last = last;
    this.volume = volume;
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

  public Map<String, BigDecimal> getVolume() {
    return volume;
  }
}
