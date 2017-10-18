package org.knowm.xchange.ccex.dto.marketdata;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CCEXTrades {
  private final String message;
  private final Boolean success;
  private List<CCEXTrade> result = new ArrayList<CCEXTrade>();

  /**
   * @param message
   * @param result
   * @param success
   */
  public CCEXTrades(@JsonProperty("success") Boolean success, @JsonProperty("message") String message,
                    @JsonProperty("result") List<CCEXTrade> result) {
    this.success = success;
    this.message = message;
    this.result = result;
  }

  /**
   * @return The success
   */
  public Boolean getSuccess(){
    return success;
  }

  /**
   * @return The message
   */
  public String getMessage() {
    return message;
  }

  /**
   * @return The message
   */
  public List<CCEXTrade> getResult() {
    return result;
  }
}
