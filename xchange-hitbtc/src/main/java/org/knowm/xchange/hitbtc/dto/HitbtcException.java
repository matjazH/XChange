package org.knowm.xchange.hitbtc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
public class HitbtcException extends RuntimeException {

  public static class HitbtcExecutionError {

    private final Integer code;
    private final String message;
    private final String description;

    HitbtcExecutionError(@JsonProperty("code") Integer code,
                         @JsonProperty("message") String message,
                         @JsonProperty("description") String description) {

      this.code = code;
      this.message = message;
      this.description = description;
    }

    public Integer getCode() {
      return code;
    }

    public String getMessage() {
      return message;
    }

    public String getDescription() {
      return description;
    }
  }

  private HitbtcExecutionError error;

  public HitbtcException(@JsonProperty("error") HitbtcExecutionError error) {

    super();
    this.error = error;
  }

  public String getCode() {

    return String.valueOf(error.getCode());
  }

  public String getMessage() {

    return error.getMessage();
  }

  public String getDescription() {

    return error.getDescription();
  }
}
