package org.knowm.xchange.therock.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.service.BaseExchangeService;

public class TheRockBaseService extends BaseExchangeService implements org.knowm.xchange.service.polling.BasePollingService {
  public TheRockBaseService(Exchange exchange) {
    super(exchange);
  }
}