package org.knowm.xchange.unocoin.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.unocoin.UnoCoin;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.polling.BasePollingService;

import si.mazi.rescu.RestProxyFactory;

public class UnoCoinBaseService<T extends UnoCoin> extends BaseExchangeService implements BasePollingService {
  protected final T coinbaseEx;

  protected UnoCoinBaseService(Class<T> type, Exchange exchange) {
    super(exchange);

    this.coinbaseEx = RestProxyFactory.createProxy(type, exchange.getExchangeSpecification().getSslUri());
  }

}
