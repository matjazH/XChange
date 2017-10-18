package org.knowm.xchange.btce.v3.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.btce.v3.dto.WEXReturn;

/**
 * @author Ondřej Novotný
 */
public class WEXWithDrawInfoReturn extends WEXReturn<WEXWithdrawInfo> {

  /**
   * Constructor
   * 
   * @param success True if successful
   * @param value The BTC-e account info
   * @param error Any error
   */
  public WEXWithDrawInfoReturn(@JsonProperty("success") boolean success, @JsonProperty("return") WEXWithdrawInfo value,
                               @JsonProperty("error") String error) {
    super(success, value, error);
  }
}
