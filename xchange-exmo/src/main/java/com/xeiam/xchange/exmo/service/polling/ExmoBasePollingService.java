package com.xeiam.xchange.exmo.service.polling;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.service.BaseExchangeService;
import com.xeiam.xchange.service.polling.BasePollingService;

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
