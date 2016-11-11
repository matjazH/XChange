package org.knowm.xchange.mercadobitcoin.dto.v3.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tabtrader on 01/11/2016.
 */
public class MercadoBitcoinOrderResponse {

  private MercadoBitcoinOrder order;

  public MercadoBitcoinOrderResponse(@JsonProperty("order") MercadoBitcoinOrder order) {
    this.order = order;
  }

  public MercadoBitcoinOrder getOrder() {
    return order;
  }
}
