package org.knowm.xchange.yobit.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.yobit.YoBit;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.polling.BasePollingService;

import si.mazi.rescu.RestProxyFactory;

public class YoBitBaseService<T extends YoBit> extends BaseExchangeService implements BasePollingService {
  protected final T yobit;

  protected YoBitBaseService(Class<T> type, Exchange exchange) {
    super(exchange);

    this.yobit = RestProxyFactory.createProxy(type, exchange.getExchangeSpecification().getSslUri(), createClientConfig(exchange.getExchangeSpecification()));
  }

}
