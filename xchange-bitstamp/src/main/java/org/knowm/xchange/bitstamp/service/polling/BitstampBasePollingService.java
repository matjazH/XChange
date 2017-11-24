package org.knowm.xchange.bitstamp.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitstamp.Bitstamp;
import org.knowm.xchange.bitstamp.BitstampV2;
import org.knowm.xchange.bitstamp.dto.marketdata.BitstampSymbol;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.polling.BasePollingService;
import si.mazi.rescu.RestProxyFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author timmolter
 */
public class BitstampBasePollingService extends BaseExchangeService implements BasePollingService {

  private final BitstampV2 bitstampV2;

  public BitstampBasePollingService(Exchange exchange) {

    super(exchange);
    this.bitstampV2 = RestProxyFactory.createProxy(BitstampV2.class, exchange.getExchangeSpecification().getSslUri(), createClientConfig(exchange.getExchangeSpecification()));
  }

  @Override
  public List<CurrencyPair> getExchangeSymbols() throws IOException {

    List<CurrencyPair> currencyPairs = new ArrayList<CurrencyPair>();
    for (BitstampSymbol symbol : bitstampV2.getSymbols()) {
      String base = symbol.getSymbol().substring(0, 3);
      String counter = symbol.getSymbol().substring(3);
      currencyPairs.add(new CurrencyPair(base, counter));
    }
    return currencyPairs;
  }
}
