package com.xeiam.xchange.bl3p.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xeiam.xchange.bl3p.dto.Bl3pError;
import com.xeiam.xchange.bl3p.dto.marketdata.Bl3pTrade;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 17/08/15
 * Time: 13:44
 */
public class Bl3pOpenOrders extends Bl3pError {

  private List<Bl3pOpenOrder> orders;

  public Bl3pOpenOrders(@JsonProperty("code") String code,
                        @JsonProperty("message") String message,
                        @JsonProperty("orders") List<Bl3pOpenOrder> orders) {
    super(code, message);

    this.orders = orders;
  }

  public List<Bl3pOpenOrder> getOrders() {
    return orders;
  }
}
