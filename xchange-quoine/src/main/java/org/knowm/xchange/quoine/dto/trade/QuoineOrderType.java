package org.knowm.xchange.quoine.dto.trade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 29/12/15
 * Time: 14:04
 */
public enum QuoineOrderType {

  limit("Limit"),
  market("Market"),
  market_with_range("Market with range"),
  margin_limit("Margin limit"),
  margin_market("Margin market"),
  margin_market_with_range("Margin market with range");

  private String value;
  private String type;

  QuoineOrderType(String value) {

    this.value = value;
  }

  public String getValue() {

    return value;
  }

  @Override
  public String toString() {

    return this.getValue();
  }

  public static QuoineOrderType getOrderType(String inputName) {
    for (QuoineOrderType type : QuoineOrderType.values()) {
      String value = type.toString();
      if (value.equals(inputName)) {
        return type;
      }
    }
    return limit;
  }

  public static List<String> getOrderTypes() {

    List<String> list = new ArrayList<String>();
    for (QuoineOrderType type : QuoineOrderType.values()) {
      list.add(type.toString());
    }
    return list;
  }
}
