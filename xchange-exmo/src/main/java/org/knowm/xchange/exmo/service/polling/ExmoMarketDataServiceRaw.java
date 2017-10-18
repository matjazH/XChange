package org.knowm.xchange.exmo.service.polling;

import java.io.IOException;
import java.util.*;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.exmo.Exmo;

import org.knowm.xchange.exmo.dto.marketdata.ExmoOrderbook;
import org.knowm.xchange.exmo.dto.marketdata.ExmoPair;
import org.knowm.xchange.exmo.dto.marketdata.ExmoTicker;
import org.knowm.xchange.exmo.dto.marketdata.ExmoTrade;
import si.mazi.rescu.RestProxyFactory;

public class ExmoMarketDataServiceRaw extends ExmoBasePollingService {

  private final Exmo exmo;

  /**
   * Constructor
   *
   * @param exchange
   */
  public ExmoMarketDataServiceRaw(Exchange exchange) {

    super(exchange);
    this.exmo = RestProxyFactory.createProxy(Exmo.class, exchange.getExchangeSpecification().getSslUri());
  }

  public Map<String, ExmoTicker> getExmoTickers() throws IOException {

    return exmo.getTickers();
  }

  public Map<String, ExmoOrderbook> getExmoOrderBook(String pair) throws IOException {

    return exmo.getOrderBook(pair);
  }

  public Map<String, List<ExmoTrade>> getExmoTrades(String pair) throws IOException {
    return exmo.getTrades(pair);
  }

  public Map<String, ExmoPair> getExmoPairs() throws IOException {
    return exmo.getPairs();
  }
}
