package org.knowm.xchange.bl3p.service.polling;

import com.xeiam.xchange.Exchange;
import org.knowm.xchange.bl3p.Bl3p;
import org.knowm.xchange.bl3p.Bl3pAuthenticated;
import org.knowm.xchange.bl3p.dto.marketdata.Bl3pOrderbook;
import org.knowm.xchange.bl3p.dto.Bl3pResult;
import org.knowm.xchange.bl3p.dto.marketdata.Bl3pTicker;
import org.knowm.xchange.bl3p.dto.marketdata.Bl3pTrades;
import org.knowm.xchange.bl3p.service.Bl3pDigest;
import si.mazi.rescu.RestProxyFactory;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 14/08/15
 * Time: 19:13
 */
public class Bl3pMarketDataServiceRaw extends Bl3pBasePollingService {

  protected final Bl3p bl3p;

  private Bl3pAuthenticated bl3pAuthenticated;
  private Bl3pDigest signatureCreator;
  private String apiKey;

  /**
   * Constructor
   *
   * @param exchange
   */
  public Bl3pMarketDataServiceRaw(Exchange exchange) {
    super(exchange);

    this.bl3p = RestProxyFactory.createProxy(Bl3p.class, exchange.getExchangeSpecification().getSslUri());

    if (exchange.getExchangeSpecification().getApiKey() != null
        && exchange.getExchangeSpecification().getSecretKey() != null) {

      this.bl3pAuthenticated = RestProxyFactory.createProxy(Bl3pAuthenticated.class, exchange.getExchangeSpecification().getSslUri());
      this.signatureCreator = Bl3pDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
      this.apiKey = exchange.getExchangeSpecification().getApiKey();
    }
  }

  public Bl3pTicker getBl3pTicker(String market) throws IOException {
    return bl3p.getTicker(market);
  }

  public Bl3pResult<Bl3pOrderbook> getBl3pOrderbook(String market) throws IOException {
    return bl3p.getOrderbook(market);
  }

  public Bl3pResult<Bl3pTrades> getBl3pTrades(String market, Long tradeId) throws IOException {
    if (bl3pAuthenticated != null) {
      return bl3pAuthenticated.getTrades(apiKey, signatureCreator, market, tradeId);
    } else {
      return bl3p.getLastTrades(market);
    }
  }
}
