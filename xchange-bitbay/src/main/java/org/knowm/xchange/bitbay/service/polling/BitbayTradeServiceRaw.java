package org.knowm.xchange.bitbay.service.polling;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.ExchangeSpecification;
import org.knowm.xchange.bitbay.BitbayAuthentiacated;
import com.xeiam.xchange.bitbay.dto.BitbayBaseResponse;
import org.knowm.xchange.bitbay.dto.trade.BitbayOrder;
import org.knowm.xchange.bitbay.dto.trade.BitbayTradeResponse;
import org.knowm.xchange.bitbay.dto.trade.BitbayTransaction;
import org.knowm.xchange.bitbay.service.BitbayDigest;
import com.xeiam.xchange.dto.trade.LimitOrder;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

import java.io.IOException;
import java.util.List;

/**
 * @author yarkh
 */
public class BitbayTradeServiceRaw extends BitbayBasePollingService {

  protected BitbayAuthentiacated bitbay;
  protected final ParamsDigest signatureCreator;
  protected final String apiKey;

  protected BitbayTradeServiceRaw(Exchange exchange) {

    super(exchange);
    this.bitbay = RestProxyFactory.createProxy(BitbayAuthentiacated.class, exchange.getExchangeSpecification().getSslUri());
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.signatureCreator = BitbayDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
  }

  public List<BitbayOrder> getOrders() throws IOException {
    return bitbay.orders(apiKey, signatureCreator, exchange.getNonceFactory());
  }


  public BitbayTradeResponse placeBitbayOrder(LimitOrder order) throws IOException {
    return bitbay.trade(apiKey, signatureCreator, exchange.getNonceFactory(),
        order.getType().toString().toLowerCase(),
        order.getCurrencyPair().base.getCurrencyCode(), order.getCurrencyPair().counter.getCurrencyCode(),
        order.getTradableAmount(), order.getTradableAmount().multiply(order.getLimitPrice()), order.getLimitPrice());
  }

  public BitbayBaseResponse cancelBitbayOrder(String orderId) throws IOException {
    return bitbay.cancel(apiKey, signatureCreator, exchange.getNonceFactory(), orderId);
  }


  public List<BitbayTransaction> getTransactions(String market) throws IOException {
    return bitbay.transactions(apiKey, signatureCreator, exchange.getNonceFactory(), market);
  }
}
