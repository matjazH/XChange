package com.xeiam.xchange.vaultoro.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 17/05/16
 * Time: 16:13
 */
public class VaultoroMarket {

  private final String marketCurrency;
  private final String baseCurrency;
  private final String marketCurrencyLong;
  private final String baseCurrencyLong;
  private final BigDecimal minTradeSize;
  private final String marketName;
  private final Boolean isActive;
  private final BigDecimal minUnitQty;
  private final BigDecimal minPrice;
  private final BigDecimal lastPrice;
  private final BigDecimal low24h;
  private final BigDecimal high24h;
  private final BigDecimal volume24h;

  public VaultoroMarket(@JsonProperty("MarketCurrency") String marketCurrency,
                        @JsonProperty("BaseCurrency") String baseCurrency,
                        @JsonProperty("MarketCurrencyLong") String marketCurrencyLong,
                        @JsonProperty("BaseCurrencyLong") String baseCurrencyLong,
                        @JsonProperty("MinTradeSize") BigDecimal minTradeSize,
                        @JsonProperty("MarketName") String marketName,
                        @JsonProperty("IsActive") Boolean isActive,
                        @JsonProperty("MinUnitQty") BigDecimal minUnitQty,
                        @JsonProperty("MinPrice") BigDecimal minPrice,
                        @JsonProperty("LastPrice") BigDecimal lastPrice,
                        @JsonProperty("24hLow") BigDecimal low24h,
                        @JsonProperty("24hHigh") BigDecimal high24h,
                        @JsonProperty("24HVolume") BigDecimal volume24h) {

    this.marketCurrency = marketCurrency;
    this.baseCurrency = baseCurrency;
    this.marketCurrencyLong = marketCurrencyLong;
    this.baseCurrencyLong = baseCurrencyLong;
    this.minTradeSize = minTradeSize;
    this.marketName = marketName;
    this.isActive = isActive;
    this.minUnitQty = minUnitQty;
    this.minPrice = minPrice;
    this.lastPrice = lastPrice;
    this.low24h = low24h;
    this.high24h = high24h;
    this.volume24h = volume24h;
  }

  public String getMarketCurrency() {
    return marketCurrency;
  }

  public String getBaseCurrency() {
    return baseCurrency;
  }

  public String getMarketCurrencyLong() {
    return marketCurrencyLong;
  }

  public String getBaseCurrencyLong() {
    return baseCurrencyLong;
  }

  public BigDecimal getMinTradeSize() {
    return minTradeSize;
  }

  public String getMarketName() {
    return marketName;
  }

  public Boolean getActive() {
    return isActive;
  }

  public BigDecimal getMinUnitQty() {
    return minUnitQty;
  }

  public BigDecimal getMinPrice() {
    return minPrice;
  }

  public BigDecimal getLastPrice() {
    return lastPrice;
  }

  public BigDecimal getLow24h() {
    return low24h;
  }

  public BigDecimal getHigh24h() {
    return high24h;
  }

  public BigDecimal getVolume24h() {
    return volume24h;
  }
}
