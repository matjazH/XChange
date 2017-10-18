package org.knowm.xchange.btcup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by VITTACH on 28/08/17.
 */
public class BtcUpBaseResponse<T> {

  private final Integer success;
  private final T result;

  public BtcUpBaseResponse(@JsonProperty("success") Integer success, @JsonProperty("result") T result) {
    this.success = success;
    this.result = result;
  }

  public Boolean getSuccess() {
    return success == 1;
  }

  public T getResult() {
    return result;
  }
}
