package org.knowm.xchange.bl3p.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 18/08/15
 * Time: 14:48
 */
public class Bl3pValue {

  private Integer valueInt;
  private String displayShort;
  private String display;
  private String currency;
  private BigDecimal value;

  public Bl3pValue(@JsonProperty("value_int") Integer valueInt,
                   @JsonProperty("display_short") String displayShort,
                   @JsonProperty("display") String display,
                   @JsonProperty("currency") String currency,
                   @JsonProperty("value") BigDecimal value) {
    this.valueInt = valueInt;
    this.displayShort = displayShort;
    this.display = display;
    this.currency = currency;
    this.value = value;
  }

  public Integer getValueInt() {
    return valueInt;
  }

  public String getDisplayShort() {
    return displayShort;
  }

  public String getDisplay() {
    return display;
  }

  public String getCurrency() {
    return currency;
  }

  public BigDecimal getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "Bl3pValue{" +
        "valueInt=" + valueInt +
        ", displayShort='" + displayShort + '\'' +
        ", display='" + display + '\'' +
        ", currency='" + currency + '\'' +
        ", value=" + value +
        '}';
  }
}
