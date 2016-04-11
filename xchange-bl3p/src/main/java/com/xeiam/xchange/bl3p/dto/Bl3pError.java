package com.xeiam.xchange.bl3p.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 17/08/15
 * Time: 19:18
 */
public class Bl3pError {

  /*
  "code": "NOT_AUTHENTICATED",    "message": "Could not validate call"
   */

  private String code;
  private String message;

  public Bl3pError(@JsonProperty("code") String code,
                   @JsonProperty("message") String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
