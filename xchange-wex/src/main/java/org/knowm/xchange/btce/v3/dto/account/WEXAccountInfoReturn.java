package org.knowm.xchange.btce.v3.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.btce.v3.dto.WEXReturn;

/**
 * @author Matija Mazi
 */
public class WEXAccountInfoReturn extends WEXReturn<WEXAccountInfo> {

  /**
   * Constructor
   * 
   * @param success True if successful
   * @param value The BTC-e account info
   * @param error Any error
   */
  public WEXAccountInfoReturn(@JsonProperty("success") boolean success, @JsonProperty("return") WEXAccountInfo value,
                              @JsonProperty("error") String error) {

    super(success, value, error);
  }
}
