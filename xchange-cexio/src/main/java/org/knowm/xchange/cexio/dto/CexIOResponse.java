package org.knowm.xchange.cexio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 17/05/16
 * Time: 16:02
 */
public class CexIOResponse<T> {

  private final String event;
  private final String ok;
  private final T data;

  public CexIOResponse(@JsonProperty("e") String e,
                       @JsonProperty("ok") String ok,
                       @JsonProperty("data") T data) {
    this.event = e;
    this.ok = ok;
    this.data = data;
  }

  public String getEvent() {
    return event;
  }

  public String getOk() {
    return ok;
  }

  public T getData() {
    return data;
  }
}
