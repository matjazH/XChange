package com.xeiam.xchange.exmo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 21/12/15
 * Time: 19:03
 */
public class ExmoResult {

  /*
  {"result":false,"error":"Error 40005: Authorization error, Incorrect signature"}
  */

  protected final boolean result;
  protected final String error;

  public ExmoResult(@JsonProperty("result") boolean result,
                  @JsonProperty("error") String error){
    this.result = result;
    this.error = error;
  }

  public boolean isResult() {
    return result;
  }

  public String getError() {
    return error;
  }
}
