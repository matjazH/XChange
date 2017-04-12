package org.knowm.xchange.ccex;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.ccex.service.CCEXTradeService;
import org.knowm.xchange.ccex.service.CCEXAccountService;
import org.knowm.xchange.ccex.service.CCEXMarketDataService;
import org.knowm.xchange.utils.nonce.CurrentTimeNonceFactory;

import si.mazi.rescu.SynchronizedValueFactory;

public class CCEXExchange extends BaseExchange implements Exchange {

	private SynchronizedValueFactory<Long> nonceFactory = new CurrentTimeNonceFactory();

	@Override
	public ExchangeSpecification getDefaultExchangeSpecification() {
		ExchangeSpecification exchangeSpecification= new ExchangeSpecification(this.getClass().getCanonicalName());
		exchangeSpecification.setExchangeDescription("C-CEX.com - Crypto-currency BitCoin exchange / MultiWallet");
		exchangeSpecification.setSslUri("https://c-cex.com");
		exchangeSpecification.setExchangeName("C-CEX");
		exchangeSpecification.setHost("c-cex.com");
		exchangeSpecification.setPort(80);

	    return exchangeSpecification;
	}

	@Override
	protected void initServices() {
		this.pollingMarketDataService = new CCEXMarketDataService(this);
		this.pollingAccountService = new CCEXAccountService(this);
		this.pollingTradeService = new CCEXTradeService(this);
	}

	@Override
	public SynchronizedValueFactory<Long> getNonceFactory() {
		return nonceFactory;
	}
}
