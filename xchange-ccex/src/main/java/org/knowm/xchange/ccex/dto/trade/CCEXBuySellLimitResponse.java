package org.knowm.xchange.ccex.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CCEXBuySellLimitResponse {

  private String message;
  private boolean success;
  private CCEXUUIDResponse result;

  public CCEXBuySellLimitResponse(@JsonProperty("success") boolean success, @JsonProperty("message") String message,
                                  @JsonProperty("result") CCEXUUIDResponse result) {
    super();
    this.result = result;
    this.success = success;
    this.message = message;
  }

  public boolean isSuccess() {
    return success;
  }

  public String getMessage() {
    return message;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public CCEXUUIDResponse getResult() {
    return result;
  }

  public void setResult(CCEXUUIDResponse result) {
    this.result = result;
  }

  @Override
  public String toString() {
    return "CCEXBuySellLimitResponse [success=" + success + ", message=" + message + ", result=" + result + "]";
  }
}
