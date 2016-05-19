package com.xeiam.xchange.cexio.service.polling;

import java.io.IOException;
import java.util.List;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.cexio.CexIO;
import com.xeiam.xchange.cexio.dto.CexIOResponse;
import com.xeiam.xchange.cexio.dto.marketdata.*;
import com.xeiam.xchange.currency.CurrencyPair;

import com.xeiam.xchange.exceptions.ExchangeException;
import si.mazi.rescu.RestProxyFactory;

/**
 * @author timmolter
 */
public class CexIOMarketDataServiceRaw extends CexIOBasePollingService {

  private final CexIO cexio;

  /**
   * Constructor
   *
   * @param exchange
   */
  public CexIOMarketDataServiceRaw(Exchange exchange) {

    super(exchange);

    this.cexio = RestProxyFactory.createProxy(CexIO.class, exchange.getExchangeSpecification().getSslUri());
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

  public List<CexIOCurrency> getCexIOCurrencies() throws IOException {
    CexIOResponse<CexIOPairs> response = cexio.getCurrencyBoundaries();
    if ("ok".equals(response.getOk())) {
      return response.getData().getPairs();
    }
    throw new ExchangeException("CexIO response error");
  }


}
