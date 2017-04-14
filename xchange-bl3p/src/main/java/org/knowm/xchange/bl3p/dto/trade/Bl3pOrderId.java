package org.knowm.xchange.bl3p.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.bl3p.dto.Bl3pError;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 18/08/15
 * Time: 19:48
 */
public class Bl3pOrderId extends Bl3pError {

  private String orderId;

  public Bl3pOrderId(@JsonProperty("code") String code,
                     @JsonProperty("message") String message,
                     @JsonProperty("order_id") String orderId) {
    super(code, message);
    this.orderId = orderId;
  }

  public String getOrderId() {
    return orderId;
  }
}
