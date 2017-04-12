package org.knowm.xchange.bitmex.service.polling;


import org.knowm.xchange.Exchange;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.polling.BasePollingService;

public class YoBitBasePollingService extends BaseExchangeService implements BasePollingService {

  /**
   * Constructor class @param exchange
   */
  protected YoBitBasePollingService(Exchange exchange) {
    super(exchange);
  }
}
