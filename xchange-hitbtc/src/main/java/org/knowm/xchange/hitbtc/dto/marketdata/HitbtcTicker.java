package org.knowm.xchange.hitbtc.dto.marketdata;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author kpysniak
 */
public final class HitbtcTicker {

  private final BigDecimal ask;
  private final BigDecimal bid;
  private final BigDecimal last;
  private final BigDecimal low;
  private final BigDecimal high;
  private final BigDecimal open;
  private final BigDecimal volume;
  private final BigDecimal volume_quote;
  private Date timestamp = null;

  /**
   * Constructor
   *
   * @param ask
   * @param bid
   * @param last
   * @param low
   * @param high
   * @param volume
   */
  public HitbtcTicker(@JsonProperty("ask") BigDecimal ask, @JsonProperty("bid") BigDecimal bid, @JsonProperty("last") BigDecimal last,
                      @JsonProperty("low") BigDecimal low, @JsonProperty("high") BigDecimal high, @JsonProperty("open") BigDecimal open,
                      @JsonProperty("volume") BigDecimal volume, @JsonProperty("volumeQuote") BigDecimal volume_quote,
                      @JsonProperty("timestamp") String timestamp) {

    this.volume_quote = volume_quote;
    this.ask = ask;
    this.bid = bid;
    this.low = low;
    this.last = last;
    this.high = high;
    this.open = open;

    try {
      this.timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(timestamp);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    this.volume = volume;
  }

  public BigDecimal getAsk() {

    return ask;
  }

  public BigDecimal getBid() {

    return bid;
  }

  public BigDecimal getLast() {

    return last;
  }

  public BigDecimal getLow() {

    return low;
  }

  public BigDecimal getHigh() {

    return high;
  }

  public BigDecimal getOpen() {

    return open;
  }

  public BigDecimal getVolume() {

    return volume;
  }

  public BigDecimal getVolumeQuote() {

    return volume_quote;
  }

  public long getTimestamp() {

    return timestamp.getTime();
  }

  @Override
  public String toString() {

    return "HitbtcTicker{" + "ask=" + ask + ", bid=" + bid + ", last=" + last + ", low=" + low + ", high=" + high + ", volume=" + volume
        + ", timestamp=" + timestamp + '}';
  }
}
