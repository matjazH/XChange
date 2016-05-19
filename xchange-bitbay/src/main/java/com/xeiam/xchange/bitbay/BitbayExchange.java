package com.xeiam.xchange.bitbay;

import com.xeiam.xchange.BaseExchange;
import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.ExchangeSpecification;
import com.xeiam.xchange.bitbay.service.polling.BitbayAccountService;
import com.xeiam.xchange.bitbay.service.polling.BitbayMarketDataService;
import com.xeiam.xchange.bitbay.service.polling.BitbayTradeService;
import com.xeiam.xchange.utils.nonce.CurrentTime1000NonceFactory;
import si.mazi.rescu.SynchronizedValueFactory;

/**
 * @author kpysniak
 */
public class BitbayExchange extends BaseExchange implements Exchange {


  private SynchronizedValueFactory<Long> nonceFactory = new CurrentTime1000NonceFactory();

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://market.bitbay.pl");
    exchangeSpecification.setHost("bitbay.pl");
    exchangeSpecification.setPort(80);
    exchangeSpecification.setExchangeName("Bitbay");
    exchangeSpecification.setExchangeDescription("Bitbay is a Bitcoin exchange based in Katowice, Poland.");

    return exchangeSpecification;
  }

  @Override
  protected void initServices() {
    this.pollingMarketDataService = new BitbayMarketDataService(this);
    this.pollingTradeService = new BitbayTradeService(this);
    this.pollingAccountService = new BitbayAccountService(this);
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    return nonceFactory;
  }
}
