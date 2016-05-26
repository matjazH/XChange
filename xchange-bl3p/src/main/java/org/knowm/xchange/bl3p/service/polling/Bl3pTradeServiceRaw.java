package org.knowm.xchange.bl3p.service.polling;

import com.xeiam.xchange.Exchange;
import org.knowm.xchange.bl3p.Bl3pAuthenticated;
import org.knowm.xchange.bl3p.dto.Bl3pError;
import org.knowm.xchange.bl3p.dto.Bl3pResult;
import org.knowm.xchange.bl3p.dto.trade.Bl3pOpenOrders;
import org.knowm.xchange.bl3p.dto.trade.Bl3pOrder;
import org.knowm.xchange.bl3p.dto.trade.Bl3pOrderId;
import org.knowm.xchange.bl3p.service.Bl3pDigest;
import si.mazi.rescu.RestProxyFactory;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 18/08/15
 * Time: 18:47
 */
public class Bl3pTradeServiceRaw extends Bl3pBasePollingService {

  private final Bl3pAuthenticated bl3pAuthenticated;
  private final Bl3pDigest signatureCreator;
  private final String apiKey;

  /**
   * Constructor
   *
   * @param exchange
   */
  protected Bl3pTradeServiceRaw(Exchange exchange) {
    super(exchange);

    this.bl3pAuthenticated = RestProxyFactory.createProxy(Bl3pAuthenticated.class, exchange.getExchangeSpecification().getSslUri());
    this.signatureCreator = Bl3pDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
  }

  protected Bl3pOrderId addBl3pOrder(String market, String type, Integer amountInt, Integer priceInt, String feeCurrency) throws IOException {

    Bl3pResult<Bl3pOrderId> bl3pResult = bl3pAuthenticated.addOrder(apiKey, signatureCreator, market, type, amountInt, priceInt, feeCurrency);
    checkResult(bl3pResult);
    return bl3pResult.getData();
  }

  protected boolean cancelBl3pOrder(String market, String orderId) throws IOException {
    Bl3pResult<Bl3pError> bl3pResult = bl3pAuthenticated.cancelOrder(apiKey, signatureCreator, market, orderId);
    checkResult(bl3pResult);
    return true;
  }

  protected Bl3pOrder getBl3pOrder(String market, String orderId) throws IOException {
    Bl3pResult<Bl3pOrder> bl3pResult = bl3pAuthenticated.orderResult(apiKey, signatureCreator, market, orderId);
    checkResult(bl3pResult);
    return bl3pResult.getData();
  }

  protected Bl3pOpenOrders getBl3pOpenOrders(String market) throws IOException {
    Bl3pResult<Bl3pOpenOrders> result = bl3pAuthenticated.openOrders(apiKey, signatureCreator, market);
    checkResult(result);
    return result.getData();
  }

}
