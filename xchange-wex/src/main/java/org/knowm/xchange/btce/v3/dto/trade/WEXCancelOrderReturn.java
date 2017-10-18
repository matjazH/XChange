package org.knowm.xchange.btce.v3.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.btce.v3.dto.WEXReturn;

/**
 * @author Matija Mazi
 */
public class WEXCancelOrderReturn extends WEXReturn<WEXCancelOrderResult> {

  /**
   * Constructor
   * 
   * @param success
   * @param value
   * @param error
   */
  public WEXCancelOrderReturn(@JsonProperty("success") boolean success, @JsonProperty("return") WEXCancelOrderResult value,
                              @JsonProperty("error") String error) {

    super(success, value, error);
  }
}
