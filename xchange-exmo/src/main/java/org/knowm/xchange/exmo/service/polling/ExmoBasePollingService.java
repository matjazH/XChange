package org.knowm.xchange.exmo.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.service.BaseService;
import org.knowm.xchange.service.BaseExchangeService;

/**
 * @author timmolter
 */
public class ExmoBasePollingService extends BaseExchangeService implements BaseService {

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
