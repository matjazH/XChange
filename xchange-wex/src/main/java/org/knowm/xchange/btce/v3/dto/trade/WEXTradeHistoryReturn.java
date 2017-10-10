package org.knowm.xchange.btce.v3.dto.trade;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.btce.v3.dto.WEXReturn;

/**
 * @author Raphael Voellmy
 */
public class WEXTradeHistoryReturn extends WEXReturn<Map<Long, WEXTradeHistoryResult>> {

  /**
   * Constructor
   * 
   * @param success
   * @param value
   * @param error
   */
  public WEXTradeHistoryReturn(@JsonProperty("success") boolean success, @JsonProperty("return") Map<Long, WEXTradeHistoryResult> value,
                               @JsonProperty("error") String error) {

    super(success, value, error);
  }
}
