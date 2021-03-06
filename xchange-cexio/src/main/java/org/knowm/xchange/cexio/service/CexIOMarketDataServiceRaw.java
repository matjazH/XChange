package org.knowm.xchange.cexio.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.cexio.CexIO;
import org.knowm.xchange.cexio.dto.CexIOResponse;
import org.knowm.xchange.cexio.dto.marketdata.*;
import org.knowm.xchange.currency.CurrencyPair;

import org.knowm.xchange.exceptions.ExchangeException;
import si.mazi.rescu.RestProxyFactory;

/**
 * @author timmolter
 */
public class CexIOMarketDataServiceRaw extends CexIOBaseService {

  private final CexIO cexio;

  /**
   * Constructor
   *
   * @param exchange
   */
  public CexIOMarketDataServiceRaw(Exchange exchange) {

    super(exchange);

    this.cexio = RestProxyFactory.createProxy(CexIO.class, exchange.getExchangeSpecification().getSslUri(), createClientConfig(exchange.getExchangeSpecification()));
  }

  public CexIOTicker getCexIOTicker(CurrencyPair currencyPair) throws IOException {

    CexIOTicker cexIOTicker = cexio.getTicker(currencyPair.base.getCurrencyCode(), currencyPair.counter.getCurrencyCode());

    return cexIOTicker;
  }

  public CexIODepth getCexIOOrderBook(CurrencyPair currencyPair) throws IOException {

    CexIODepth cexIODepth = cexio.getDepth(currencyPair.base.getCurrencyCode(), currencyPair.counter.getCurrencyCode());

    return cexIODepth;
  }

  public CexIOTrade[] getCexIOTrades(CurrencyPair currencyPair, Long since) throws IOException {

    CexIOTrade[] trades;

    if (since != null) {
      trades = cexio.getTradesSince(currencyPair.base.getCurrencyCode(), currencyPair.counter.getCurrencyCode(), since);
    } else { // default to full available trade history
      trades = cexio.getTrades(currencyPair.base.getCurrencyCode(), currencyPair.counter.getCurrencyCode());
    }

    return trades;
  }

  public ArrayList<CexIOCurrency> getCexIOCurrencies() throws IOException {
    CexIOResponse<CexIOPairs> response = cexio.getCurrencyBoundaries();
    if ("ok".equals(response.getOk())) {
      return new ArrayList<CexIOCurrency>(response.getData().getPairs());
    }
    throw new ExchangeException("CexIO response error");
  }


}
