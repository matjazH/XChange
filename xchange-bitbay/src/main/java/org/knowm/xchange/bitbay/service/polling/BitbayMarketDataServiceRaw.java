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

  private final Bitbay bitbay;

  /**
   * Constructor
   *
   * @param exchange
   */
  protected BitbayMarketDataServiceRaw(Exchange exchange) {

    super(exchange);
    this.bitbay = RestProxyFactory.createProxy(Bitbay.class, exchange.getExchangeSpecification().getSslUri(), createClientConfig(exchange.getExchangeSpecification()));
  }

  public BitbayTicker getBitbayTicker(CurrencyPair currencyPair) throws IOException {

    return bitbay.getBitbayTicker(currencyPair.base.getCurrencyCode() + currencyPair.counter.getCurrencyCode());
  }

  public BitbayOrderBook getBitbayOrderBook(CurrencyPair currencyPair) throws IOException {

    return bitbay.getBitbayOrderBook(currencyPair.base.getCurrencyCode().toUpperCase() + currencyPair.counter.getCurrencyCode().toString());
  }

  public BitbayTrade[] getBitbayTrades(CurrencyPair currencyPair, Long since) throws IOException {

    return bitbay.getBitbayTrades(currencyPair.base.getCurrencyCode() + currencyPair.counter.getCurrencyCode(), since);
  }

  public BitbayMarketAll getBitbatAllMarketData(CurrencyPair currencyPair) throws IOException {

    return bitbay.getAll(currencyPair.base.getCurrencyCode() + currencyPair.counter.getCurrencyCode());
  }
}
