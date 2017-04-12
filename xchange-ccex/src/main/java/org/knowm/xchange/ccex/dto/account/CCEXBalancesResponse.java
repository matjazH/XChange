package org.knowm.xchange.ccex.dto.account;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CCEXBalancesResponse {

  private String message;
  private boolean success;
  private List<CCEXBalance> result;

  public CCEXBalancesResponse(@JsonProperty("success")boolean success,
                              @JsonProperty("message") String message,
                              @JsonProperty("result") List<CCEXBalance> result) {
    super();
    this.success = success;
    this.message = message;
    this.result = result;
  }

  public void setResult(List<CCEXBalance> result) {
    this.result = result;
  }

  public boolean isSuccess() {
    return success;
  }

  public String getMessage() {
    return message;
  }

  public List<CCEXBalance> getResult() {
    return result;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setSuccess(boolean success){
    this.success = success;
  }

  @Override
  public String toString() {
    return "CCEXBalancesResponse [success=" + success + ", message=" + message + ", result=" + result + "]";
  }
}
