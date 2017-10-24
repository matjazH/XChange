package org.knowm.xchange.bitbay.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitbay.Bitbay;
import org.knowm.xchange.bitbay.dto.marketdata.BitbayMarketAll;
import org.knowm.xchange.bitbay.dto.marketdata.BitbayOrderBook;
import org.knowm.xchange.bitbay.dto.marketdata.BitbayTicker;
import org.knowm.xchange.bitbay.dto.marketdata.BitbayTrade;
import org.knowm.xchange.currency.CurrencyPair;
import si.mazi.rescu.RestProxyFactory;

import java.io.IOException;

/**
 * @author kpysniak
 */
public class BitbayMarketDataServiceRaw extends BitbayBasePollingService {

  private final Bitbay bitBay;

  /**
   * Constructor
   *
   * @param exchange
   */
  protected BitbayMarketDataServiceRaw(Exchange exchange) {

    super(exchange);
    this.bitBay = RestProxyFactory.createProxy(Bitbay.class, exchange.getExchangeSpecification().getSslUri(), createClientConfig(exchange.getExchangeSpecification()));
  }

  public BitbayTicker getBitbayTicker(CurrencyPair currencyPair) throws IOException {

    return bitBay.getBitbayTicker(currencyPair.base.getCurrencyCode() + currencyPair.counter.getCurrencyCode());
  }

  public BitbayOrderBook getBitbayOrderBook(CurrencyPair currencyPair) throws IOException {

    return bitBay.getBitbayOrderBook(currencyPair.base.getCurrencyCode().toUpperCase() + currencyPair.counter.getCurrencyCode().toString());
  }

  public BitbayTrade[] getBitbayTrades(CurrencyPair currencyPair, Long since, String sort) throws IOException {

    return bitBay.getBitbayTrades(currencyPair.base.getCurrencyCode() + currencyPair.counter.getCurrencyCode(), since, sort);
  }

  public BitbayMarketAll getBitbatAllMarketData(CurrencyPair currencyPair) throws IOException {

    return bitBay.getAll(currencyPair.base.getCurrencyCode() + currencyPair.counter.getCurrencyCode());
  }
}
