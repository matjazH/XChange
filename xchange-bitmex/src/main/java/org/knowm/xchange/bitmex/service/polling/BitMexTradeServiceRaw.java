package org.knowm.xchange.bitmex.service.polling;

import java.util.Map;
import java.io.IOException;
import java.math.BigDecimal;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitmex.dto.BitMexResult;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.bitmex.BitMexAuthenticated;
import org.knowm.xchange.bitmex.service.BitMexDigest;
import org.knowm.xchange.bitmex.dto.trade.YoBitActiveOrder;
import org.knowm.xchange.bitmex.dto.trade.YoBitTradesResponse;
import org.knowm.xchange.bitmex.dto.trade.YoBitorderInform;

/**
 * Created by VITTACH on 31/03/17.
 */
public class BitMexTradeServiceRaw extends BitMexBasePollingService {

  protected final String apiKey;
  protected BitMexAuthenticated yobit;
  protected final ParamsDigest signatureCreator;

  protected BitMexTradeServiceRaw(Exchange exchange) {
    super(exchange);
    this.yobit = RestProxyFactory.createProxy(BitMexAuthenticated.class,
        exchange.getExchangeSpecification().getSslUri());
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.signatureCreator = BitMexDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
  }

  public BitMexResult<Map<String, YoBitorderInform>> getYoBitOrder(BigDecimal orderId) throws IOException {
    return yobit.orderInfo(apiKey, signatureCreator, orderId, "OrderInfo", exchange.getNonceFactory());
  }

  public BitMexResult cancelYoBitOrder(BigDecimal orderId) throws IOException {
    return yobit.cancelOrder(apiKey, signatureCreator, orderId, "CancelOrder", exchange.getNonceFactory());
  }

  public YoBitTradesResponse placeBitbayOrder(LimitOrder order) throws IOException {
    String type = "";
    switch (order.getType().toString().toLowerCase()) {
      case "ask":
        type = "sell";
        break;
      case "bid":
        type = "buy";
        break;
    }
    BitMexResult<YoBitTradesResponse> trade = yobit.trade(apiKey, signatureCreator,
        order.getCurrencyPair().base.getCurrencyCode().toLowerCase() + "_" +
            order.getCurrencyPair().counter.getCurrencyCode().toLowerCase(),
        type, order.getLimitPrice(), order.getTradableAmount(), "Trade", exchange.getNonceFactory());
    return trade.getResult();
  }

  public Map<String, YoBitActiveOrder> getYoBitActiveOrder(CurrencyPair currencyPair) throws IOException {
    BitMexResult<Map<String, YoBitActiveOrder>> activeOrders = yobit.activeOrder(apiKey, signatureCreator, "ActiveOrders",
        currencyPair.base.getCurrencyCode().toLowerCase() + "_" +
            currencyPair.counter.getCurrencyCode().toLowerCase(), exchange.getNonceFactory());
    return activeOrders.getResult();
  }
}
