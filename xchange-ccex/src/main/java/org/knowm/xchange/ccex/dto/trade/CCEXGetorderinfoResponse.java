package org.knowm.xchange.ccex.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CCEXGetorderinfoResponse {

  private String message;
  private boolean success;
  private List<CCEXOrderInfo> result;

  public CCEXGetorderinfoResponse(@JsonProperty("success") boolean success, @JsonProperty("message") String message,
                                  @JsonProperty("result") List<CCEXOrderInfo> result) {
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

  public void setSuccess(boolean success){
    this.success = success;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<CCEXOrderInfo> getResult() {
    return result;
  }

  public void setResult(List<CCEXOrderInfo> result) {
    this.result = result;
  }

  @Override
  public String toString() {
    return "CCEXGetorderinfoResponse [success=" + success + ", message=" + message + ", result=" + result + "]";
  }
}
