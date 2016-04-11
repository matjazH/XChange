package com.xeiam.xchange.bl3p.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 17/08/15
 * Time: 13:31
 */
public class Bl3pResult<T> {

  private String result;
  private T data;

  public Bl3pResult(@JsonProperty("result") String result,
                    @JsonProperty("data") T data) {
    this.result = result;
    this.data = data;
  }

  public String getResult() {
    return result;
  }

  public T getData() {
    return data;
  }

  @Override
  public String toString() {
    return "Bl3pResult{" +
        "result='" + result + '\'' +
        ", data=" + data +
        '}';
  }
}
