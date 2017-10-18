package org.knowm.xchange.exmo.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.polling.BasePollingService;

/**
 * @author timmolter
 */
public class ExmoBasePollingService extends BaseExchangeService implements BasePollingService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public ExmoBasePollingService(Exchange exchange) {

    super(exchange);
  }

  public enum ExmoOrderType {
    buy,
    sell,
    market_buy,
    market_sell,
    market_buy_total,
    market_sell_total,
  }

}
