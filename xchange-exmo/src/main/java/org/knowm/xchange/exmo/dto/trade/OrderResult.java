package org.knowm.xchange.exmo.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.exmo.dto.ExmoResult;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 22/12/15
 * Time: 16:13
 */
public class OrderResult extends ExmoResult {

  public final Long orderId;

  public OrderResult(@JsonProperty("result") boolean result,
                     @JsonProperty("error") String error,
                     @JsonProperty("order_id") Long orderId) {
    super(result, error);

    this.orderId = orderId;
  }

  public Long getOrderId() {
    return orderId;
  }
}
