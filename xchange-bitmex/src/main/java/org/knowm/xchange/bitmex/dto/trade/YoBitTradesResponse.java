package org.knowm.xchange.bitmex.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.math.BigDecimal;

/**
 * Created by VITTACH on 31/03/17.
 */
public class YoBitTradesResponse {
  private Long received;
  private String orderId;
  private Integer remains;
  private Map<String, BigDecimal> funds;

  public Map<String, BigDecimal> getFunds() {
    return funds;
  }

  public YoBitTradesResponse
      (@JsonProperty("funds") Map<String, BigDecimal> funds, @JsonProperty("received") Long received,
       @JsonProperty("remains") Integer remains, @JsonProperty("order_id") String orderId) {
    this.funds = funds;
    this.remains = remains;
    this.orderId = orderId;
    this.received = received;
  }

  public Long getReceived() {
    return received;
  }

  public String getOrderId() {
    return orderId;
  }

  public Integer getRemains() {
    return remains;
  }
}
