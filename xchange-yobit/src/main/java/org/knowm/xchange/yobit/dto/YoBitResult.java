package org.knowm.xchange.yobit.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class YoBitResult<V> {

  private final V result;
  private Integer success;
  private final String[] error;

  @JsonCreator
  public YoBitResult(@JsonProperty("return") V result, @JsonProperty("error") String[] error, @JsonProperty("success") Integer success) {
    this.success = success;
    this.result = result;
    this.error = error;
  }

  public boolean isSuccess() {
    if (error != null) {
      if (error.length != 0)
        return false;
      if (success.equals(1))
        return true;
      else
        return false;
    }
    else {
      if (success.equals(1))
        return true;
      else
        return false;
    }
  }

  public String[] getError() {
    return error;
  }

  @Override
  public String toString() {
    return String.format("YoBitResult[%s: %s]", isSuccess() ? "OK" : "error", isSuccess() ? result.toString() : error);
  }

  public V getResult() {
    return result;
  }
}
