package org.knowm.xchange.therock.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;


public class TheRockFund {
  private String id;
  private String description;
  private String type;
  private String baseCurrency;
  private String tradeCurrency;
  private BigDecimal buyFee;
  private BigDecimal sellFee;
  private BigDecimal minimumPriceOffer;
  private BigDecimal minimumQuantityOffer;
  private BigDecimal baseCurrencyDecimals;
  private BigDecimal tradeCurrencyDecimals;

  public TheRockFund(@JsonProperty("id") String id, @JsonProperty("description") String description, @JsonProperty("type") String type, @JsonProperty("base_currency") String baseCurrency, @JsonProperty("trade_currency") String tradeCurrency, @JsonProperty("buy_fee") BigDecimal buyFee, @JsonProperty("sell_fee") BigDecimal sellFee, @JsonProperty("minimum_price_offer") BigDecimal minimumPriceOffer, @JsonProperty("minimum_quantity_offer") BigDecimal minimumQuantityOffer, @JsonProperty("base_currency_decimals") BigDecimal baseCurrencyDecimals, @JsonProperty("trade_currency_decimals") BigDecimal tradeCurrencyDecimals) {
    this.id = id;
    this.description = description;
    this.type = type;
    this.baseCurrency = baseCurrency;
    this.tradeCurrency = tradeCurrency;
    this.buyFee = buyFee;
    this.sellFee = sellFee;
    this.minimumPriceOffer = minimumPriceOffer;
    this.minimumQuantityOffer = minimumQuantityOffer;
    this.baseCurrencyDecimals = baseCurrencyDecimals;
    this.tradeCurrencyDecimals = tradeCurrencyDecimals;
  }

  public String getId() {
    return this.id;
  }

  public String getDescription() {
    return this.description;
  }

  public String getType() {
    return this.type;
  }

  public String getBaseCurrency() {
    return this.baseCurrency;
  }

  public String getTradeCurrency() {
    return this.tradeCurrency;
  }

  public BigDecimal getBuyFee() {
    return this.buyFee;
  }

  public BigDecimal getSellFee() {
    return this.sellFee;
  }

  public BigDecimal getMinimumPriceOffer() {
    return this.minimumPriceOffer;
  }

  public BigDecimal getMinimumQuantityOffer() {
    return this.minimumQuantityOffer;
  }

  public BigDecimal getBaseCurrencyDecimals() {
    return this.baseCurrencyDecimals;
  }

  public BigDecimal getTradeCurrencyDecimals() {
    return this.tradeCurrencyDecimals;
  }

  public String toString() {
    return "TheRockFund{id='" + this.id + '\'' + ", description='" + this.description + '\'' + ", type='" + this.type + '\'' + ", baseCurrency='" + this.baseCurrency + '\'' + ", tradeCurrency='" + this.tradeCurrency + '\'' + ", buyFee=" + this.buyFee + ", sellFee=" + this.sellFee + ", minimumPriceOffer=" + this.minimumPriceOffer + ", minimumQuantityOffer=" + this.minimumQuantityOffer + ", baseCurrencyDecimals=" + this.baseCurrencyDecimals + ", tradeCurrencyDecimals=" + this.tradeCurrencyDecimals + '}';
  }
}
