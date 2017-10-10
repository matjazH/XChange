package org.knowm.xchange.btce.v3.dto.trade;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.btce.v3.dto.WEXReturn;

/**
 * @author Matija Mazi
 */
public class WEXOpenOrdersReturn extends WEXReturn<Map<Long, WEXOrder>> {

  /**
   * Constructor
   * 
   * @param success
   * @param value
   * @param error
   */
  public WEXOpenOrdersReturn(@JsonProperty("success") boolean success, @JsonProperty("return") Map<Long, WEXOrder> value,
                             @JsonProperty("error") String error) {

    super(success, value, error);
  }
}
