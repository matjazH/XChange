package org.knowm.xchange.unocoin.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.unocoin.UnoCoin;
import org.knowm.xchange.unocoin.dto.marketdata.UnoCoinInfo;
import org.knowm.xchange.unocoin.service.UnoCoinBaseService;

import java.io.IOException;

public class UnoCoinMarketDataServiceRaw extends UnoCoinBaseService<UnoCoin> {
  protected UnoCoinMarketDataServiceRaw(Exchange exchange) {
    super(UnoCoin.class, exchange);
  }

  /*
  public UnoCoinTicker getUnoCoinTicker(CurrencyPair currencyPair) throws IOException {
    Map<String, UnoCoinTicker> UnoCoinTicker =
        coinbaseEx.getUnoCoinTicker(currencyPair.base.getCurrencyCode().toLowerCase(), currencyPair.counter.getCurrencyCode().toLowerCase());
    return UnoCoinTicker.get(currencyPair.base.getCurrencyCode().toLowerCase() + "_" + currencyPair.counter.getCurrencyCode().toLowerCase());
  }
  */

  public UnoCoinInfo getInfo() throws IOException {
    UnoCoinInfo allData = coinbaseEx.getAllInfo();
    return allData;
  }

	/*
  public UnoCoinOrderBook getOrderBookA(CurrencyPair currencyPair,Long limit)
			throws IOException {
	return coinbaseEx.getOrderBook(currencyPair.base.getCurrencyCode().toLowerCase(), currencyPair.counter.getCurrencyCode().toLowerCase(),limit);
	}

	public UnoCoinTrades getTrades(CurrencyPair currencyPair)throws IOException
	{
		return this.coinbaseEx.getTrades(currencyPair.base.getCurrencyCode().toLowerCase(), currencyPair.counter.getCurrencyCode().toLowerCase());
	}
	*/
}
