package org.knowm.xchange.mercadobitcoin.dto.v3.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by tabtrader on 01/11/2016.
 */
public class MercadoBitcoinOrdersResponse {

  private List<MercadoBitcoinOrder> orders;

  public MercadoBitcoinOrdersResponse(@JsonProperty("orders") List<MercadoBitcoinOrder> orders) {
    this.orders = orders;
  }

  public List<MercadoBitcoinOrder> getOrders() {
    return orders;
  }
}
