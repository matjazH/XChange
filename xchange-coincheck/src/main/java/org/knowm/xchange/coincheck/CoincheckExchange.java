package org.knowm.xchange.coincheck;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.coincheck.service.polling.CoincheckAccountService;
import org.knowm.xchange.coincheck.service.polling.CoincheckMarketDataService;
import org.knowm.xchange.coincheck.service.polling.CoincheckTradeService;
import org.knowm.xchange.utils.nonce.TimestampIncrementingNonceFactory;
import si.mazi.rescu.SynchronizedValueFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 02/06/16
 * Time: 13:06
 */
public class CoincheckExchange extends BaseExchange implements Exchange {

  private SynchronizedValueFactory<Long> nonceFactory = new TimestampIncrementingNonceFactory();

  @Override
  protected void initServices() {
    this.pollingMarketDataService = new CoincheckMarketDataService(this);
    this.pollingAccountService = new CoincheckAccountService(this);
    this.pollingTradeService = new CoincheckTradeService(this);
  }

  public SynchronizedValueFactory<Long> getNonceFactory() {
    return nonceFactory;
  }

  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://coincheck.jp");
    exchangeSpecification.setHost("coincheck.jp");
    exchangeSpecification.setPort(80);
    exchangeSpecification.setExchangeName("Coincheck");

    return exchangeSpecification;
  }
}
