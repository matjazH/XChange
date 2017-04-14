package org.knowm.xchange.bittrex.v1.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bittrex.v1.BittrexAdapters;
import org.knowm.xchange.bittrex.v1.BittrexAuthenticated;
import org.knowm.xchange.bittrex.v1.dto.marketdata.BittrexSymbol;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;

import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BittrexBaseService extends BaseExchangeService implements BaseService {

  protected final String apiKey;
  protected final BittrexAuthenticated bittrexAuthenticated;
  protected final ParamsDigest signatureCreator;

  /**
   * Constructor
   *
   * @param exchange
   */
  public BittrexBaseService(Exchange exchange) {

    super(exchange);

    this.bittrexAuthenticated = RestProxyFactory.createProxy(BittrexAuthenticated.class, exchange.getExchangeSpecification().getSslUri(), createClientConfig(exchange.getExchangeSpecification()));
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.signatureCreator = BittrexDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
  }

  public List<CurrencyPair> getExchangeSymbols() throws IOException {

    List<CurrencyPair> currencyPairs = new ArrayList<CurrencyPair>();
    for (BittrexSymbol symbol : bittrexAuthenticated.getSymbols().getSymbols()) {
      currencyPairs.add(BittrexAdapters.adaptCurrencyPair(symbol));
    }
    return currencyPairs;
  }
}
