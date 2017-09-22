package org.knowm.xchange.ccex.dto.marketdata;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CCEXMarket {
  private final String Created;
  private final Boolean IsActive;
  private final String MarketName;
  private final String BaseCurrency;
  private final String MarketCurrency;
  private final String BaseCurrencyLong;
  private final BigDecimal MinTradeSize;
  private final String MarketCurrencyLong;

  public CCEXMarket(
      @JsonProperty("MarketCurrency") String marketCurrency,
      @JsonProperty("BaseCurrency") String baseCurrency,
      @JsonProperty("MarketCurrencyLong") String marketCurrencyLong,
      @JsonProperty("BaseCurrencyLong") String baseCurrencyLong,
      @JsonProperty("MinTradeSize") BigDecimal minTradeSize,
      @JsonProperty("MarketName") String marketName,
      @JsonProperty("IsActive") Boolean isActive,
      @JsonProperty("Created") String created) {
    super();
    MarketCurrencyLong = marketCurrencyLong;
    BaseCurrencyLong = baseCurrencyLong;
    MarketCurrency = marketCurrency;
    MinTradeSize = minTradeSize;
    BaseCurrency = baseCurrency;
    MarketName = marketName;
    IsActive = isActive;
    Created = created;
  }

  public String getMarketCurrencyLong() {
    return MarketCurrencyLong;
  }

  public String getBaseCurrencyLong() {
    return BaseCurrencyLong;
  }

  public BigDecimal getMinTradeSize() {
    return MinTradeSize;
  }

  public String getMarketCurrency() {
    return MarketCurrency;
  }

  public String getBaseCurrency() {
    return BaseCurrency;
  }

  public String getMarketName() {
    return MarketName;
  }

  public Boolean getIsActive() {
    return IsActive;
  }

  public String getCreated() {
    return Created;
  }

  @Override
  public String toString() {
    return "CCEXMarket [MarketCurrency=" + MarketCurrency + ", BaseCurrency=" + BaseCurrency
        + ", MarketCurrencyLong=" + MarketCurrencyLong + ", BaseCurrencyLong=" + BaseCurrencyLong
        + ", MinTradeSize=" + MinTradeSize + ", MarketName=" + MarketName + ", IsActive=" + IsActive
        + ", Created=" + Created + "]";
  }
}
