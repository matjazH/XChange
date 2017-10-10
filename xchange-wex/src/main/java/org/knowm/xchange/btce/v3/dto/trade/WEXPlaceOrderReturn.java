package org.knowm.xchange.btce.v3.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.btce.v3.dto.WEXReturn;

/**
 * @author Matija Mazi
 */
public class WEXPlaceOrderReturn extends WEXReturn<WEXPlaceOrderResult> {

  /**
   * Constructor
   * 
   * @param success
   * @param value
   * @param error
   */
  public WEXPlaceOrderReturn(@JsonProperty("success") boolean success, @JsonProperty("return") WEXPlaceOrderResult value,
                             @JsonProperty("error") String error) {

    super(success, value, error);
  }
}
