package org.knowm.xchange.ccex.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CCEXGetopenordersResponse {

  private String message;
  private boolean success;
  private List<CCEXOpenorder> result;

  public CCEXGetopenordersResponse(@JsonProperty("success") boolean success, @JsonProperty("message") String message,
                                   @JsonProperty("result") List<CCEXOpenorder> result) {
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

  public List<CCEXOpenorder> getResult() {
    return result;
  }

  public void setResult(List<CCEXOpenorder> result) {
    this.result = result;
  }

  @Override
  public String toString() {
    return "CCEXGetopenordersResponse [success=" + success + ", message=" + message + ", result=" + result + "]";
  }
}
