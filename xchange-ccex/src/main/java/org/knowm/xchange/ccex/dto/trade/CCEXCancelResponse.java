package org.knowm.xchange.ccex.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CCEXCancelResponse {

  private String result;
  private String message;
  private boolean success;

  public CCEXCancelResponse(@JsonProperty("success") boolean success, @JsonProperty("message") String message,
                            @JsonProperty("result") String result) {
    super();
    this.result = result;
    this.success = success;
    this.message = message;
  }

  public String getResult() {
    return result;
  }

  public boolean isSuccess() {
    return success;
  }

  public String getMessage() {
    return message;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public void setSuccess(boolean success){
    this.success = success;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "CCEXCancelResponse [success=" + success + ", message=" + message + ", result=" + result + "]";
  }

}
