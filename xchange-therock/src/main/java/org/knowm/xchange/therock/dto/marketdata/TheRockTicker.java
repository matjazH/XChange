package org.knowm.xchange.therock.dto.marketdata;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.math.BigDecimal;
import java.util.Date;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.utils.jackson.CurrencyPairDeserializer;

@com.fasterxml.jackson.databind.annotation.JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class TheRockTicker {
  @com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = CurrencyPairDeserializer.class)
  private CurrencyPair fundId;
  private Date date;
  private BigDecimal bid;
  private BigDecimal ask;
  private BigDecimal last;
  private BigDecimal volume;
  private BigDecimal volumeTraded;
  private BigDecimal open;
  private BigDecimal high;
  private BigDecimal low;
  private BigDecimal close;

  public CurrencyPair getFundId() {
    return this.fundId;
  }

  public Date getDate() {
    return this.date;
  }

  public BigDecimal getBid() {
    return this.bid;
  }

  public BigDecimal getAsk() {
    return this.ask;
  }

  public BigDecimal getLast() {
    return this.last;
  }

  public BigDecimal getVolume() {
    return this.volume;
  }

  public BigDecimal getVolumeTraded() {
    return this.volumeTraded;
  }

  public BigDecimal getOpen() {
    return this.open;
  }

  public BigDecimal getHigh() {
    return this.high;
  }

  public BigDecimal getLow() {
    return this.low;
  }

  public BigDecimal getClose() {
    return this.close;
  }

  public String toString() {
    return String.format("TheRockTicker{currencyPair=%s, date=%s, bid=%s, ask=%s, last=%s, volume=%s, volumeTraed=%s, open=%s, high=%s, low=%s, close=%s}", new Object[]{this.fundId, this.date, this.bid, this.ask, this.last, this.volume, this.volumeTraded, this.open, this.high, this.low, this.close});
  }
}