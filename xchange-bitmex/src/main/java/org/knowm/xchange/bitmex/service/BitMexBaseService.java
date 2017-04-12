package org.knowm.xchange.bitmex.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitmex.BitMex;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.polling.BasePollingService;

import si.mazi.rescu.RestProxyFactory;

public class BitMexBaseService<T extends BitMex> extends BaseExchangeService implements BasePollingService {
  protected final T coinbaseEx;

  protected BitMexBaseService(Class<T> type, Exchange exchange) {
    super(exchange);

    this.coinbaseEx = RestProxyFactory.createProxy(type, exchange.getExchangeSpecification().getSslUri());
  }

}
