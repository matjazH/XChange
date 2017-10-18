package org.knowm.xchange.yobit.service.polling;

import java.util.Map;
import java.io.IOException;
import java.math.BigDecimal;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.yobit.dto.trade.YoBitTradesOrder;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.yobit.dto.YoBitResult;
import org.knowm.xchange.yobit.YoBitAuthenticated;
import org.knowm.xchange.yobit.service.YoBitDigest;
import org.knowm.xchange.yobit.dto.trade.YoBitActiveOrder;
import org.knowm.xchange.yobit.dto.trade.YoBitTradesResponse;
import org.knowm.xchange.yobit.dto.trade.YoBitorderInform;

/**
 * Created by VITTACH on 31/03/17.
 */
public class YoBitTradeServiceRaw extends YoBitBasePollingService {

  protected final String apiKey;
  protected YoBitAuthenticated yobit;
  protected final ParamsDigest signatureCreator;

  protected YoBitTradeServiceRaw(Exchange exchange) {
    super(exchange);
    this.yobit = RestProxyFactory.createProxy(YoBitAuthenticated.class,
        exchange.getExchangeSpecification().getSslUri());
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.signatureCreator = YoBitDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
  }

  public YoBitResult<Map<String, YoBitorderInform>> getYoBitOrder(BigDecimal orderId) throws IOException {
    return yobit.orderInfo(apiKey, signatureCreator, orderId, "OrderInfo", exchange.getNonceFactory());
  }

  public YoBitResult cancelYoBitOrder(BigDecimal orderId) throws IOException {
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
    YoBitResult<YoBitTradesResponse> trade = yobit.trade(apiKey, signatureCreator,
        order.getCurrencyPair().base.getCurrencyCode().toLowerCase() + "_" +
            order.getCurrencyPair().counter.getCurrencyCode().toLowerCase(),
        type, order.getLimitPrice(), order.getTradableAmount(), "Trade", exchange.getNonceFactory());
    return trade.getResult();
  }

  public Map<String, YoBitActiveOrder> getYoBitActiveOrder(CurrencyPair currencyPair) throws IOException {
    YoBitResult<Map<String, YoBitActiveOrder>> activeOrders = yobit.activeOrder(apiKey, signatureCreator, "ActiveOrders",
        currencyPair.base.getCurrencyCode().toLowerCase() + "_" +
            currencyPair.counter.getCurrencyCode().toLowerCase(), exchange.getNonceFactory());
    return activeOrders.getResult();
  }

  public Map<String, YoBitTradesOrder> getYoBitTradeHistory(CurrencyPair currencyPair) throws IOException {
    YoBitResult<Map<String, YoBitTradesOrder>> tradeHistory = yobit.tradeHistory(apiKey, signatureCreator, "TradeHistory",
        currencyPair.base.getCurrencyCode().toLowerCase() + "_" +
            currencyPair.counter.getCurrencyCode().toLowerCase(), exchange.getNonceFactory());
    return tradeHistory.getResult();
  }
}
