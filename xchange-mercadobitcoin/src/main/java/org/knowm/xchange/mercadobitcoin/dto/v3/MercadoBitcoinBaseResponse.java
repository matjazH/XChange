package org.knowm.xchange.mercadobitcoin.dto.v3;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by tabtrader on 01/11/2016.
 */
public class MercadoBitcoinBaseResponse<T> {

  private T responseData;
  private Integer statusCode;
  private String errorMessage;
  private Long serverUnixTimestamp;

  public MercadoBitcoinBaseResponse(@JsonProperty("response_data") T responseData,
                                    @JsonProperty("status_code") Integer statusCode,
                                    @JsonProperty("error_message") String errorMessage,
                                    @JsonProperty("server_unix_timestamp") Long serverUnixTimestamp) {
    this.responseData = responseData;
    this.statusCode = statusCode;
    this.errorMessage = errorMessage;
    this.serverUnixTimestamp = serverUnixTimestamp;
  }

  public T getResponseData() {
    return responseData;
  }

  public Integer getStatusCode() {
    return statusCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public Long getServerUnixTimestamp() {
    return serverUnixTimestamp;
  }
}
