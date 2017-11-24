package org.knowm.xchange.bitstamp.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BitstampSymbol {
  private Integer baseDecimal;
  private String minOrder;
  private String name;
  private Integer counterDecimal;
  private String trading;
  private String symbol;
  private String desc;

  public BitstampSymbol(
      @JsonProperty("base_decimals") Integer baseDecimal,
      @JsonProperty("minimum_order") String minOrder,
      @JsonProperty("name") String name,
      @JsonProperty("counter_decimals") Integer counterDecimal,
      @JsonProperty("trading") String trading,
      @JsonProperty("url_symbol") String symbol,
      @JsonProperty("description") String desc) {

    this.baseDecimal = baseDecimal;
    this.minOrder = minOrder;
    this.name = name;
    this.counterDecimal = counterDecimal;
    this.trading = trading;
    this.symbol = symbol;
    this.desc = desc;
  }

  public Integer getBaseDecimal() {
    return baseDecimal;
  }

  public String getMinOrder() {
    return minOrder;
  }

  public String getName() {
    return name;
  }

  public Integer getCounterDecimal() {
    return counterDecimal;
  }

  public String getTrading() {
    return trading;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getDesc() {
    return desc;
  }

  @Override
  public String toString() {
    return "BitstampSymbol{" +
        "baseDecimal=" + baseDecimal +
        ", minOrder='" + minOrder + '\'' +
        ", name='" + name + '\'' +
        ", counterDecimal=" + counterDecimal +
        ", trading='" + trading + '\'' +
        ", symbol='" + symbol + '\'' +
        ", desc='" + desc + '\'' +
        '}';
  }
}
