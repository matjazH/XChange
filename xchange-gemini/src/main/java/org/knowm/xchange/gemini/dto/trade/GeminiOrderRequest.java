package org.knowm.xchange.gemini.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.gemini.dto.GeminiBaseRequest;

/**
 * Created by tabtrader on 05/10/2016.
 */
public class GeminiOrderRequest extends GeminiBaseRequest {


  @JsonProperty("order_id")
  private Long orderId;

  public GeminiOrderRequest(Long orderId) {
    this.orderId = orderId;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }
}
