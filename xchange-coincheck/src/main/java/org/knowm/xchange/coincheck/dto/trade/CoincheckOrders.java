package org.knowm.xchange.coincheck.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 07/06/16
 * Time: 18:31
 */
public class CoincheckOrders {


  public final Boolean success;
  public final List<CoincheckOrder> orders;
  public final String error;

  public CoincheckOrders(@JsonProperty("success") Boolean success,
                         @JsonProperty("orders") List<CoincheckOrder> orders,
                         @JsonProperty("error") String error) {
    this.success = success;
    this.orders = orders;
    this.error = error;
  }

  public Boolean getSuccess() {
    return success;
  }

  public List<CoincheckOrder> getOrders() {
    return orders;
  }

  public String getError() {
    return error;
  }
}
