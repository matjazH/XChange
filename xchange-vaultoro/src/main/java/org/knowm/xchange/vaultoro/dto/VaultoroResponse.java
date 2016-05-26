package org.knowm.xchange.vaultoro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 17/05/16
 * Time: 16:47
 */
public class VaultoroResponse<T> {

  private final String status;
  private final T data;

  public VaultoroResponse(@JsonProperty("status") String status,
                          @JsonProperty("data") T data) {
    this.status = status;
    this.data = data;
  }

  public String getStatus() {
    return status;
  }

  public T getData() {
    return data;
  }
}
