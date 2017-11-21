package org.knowm.xchange.hitbtc.service.polling;

import java.io.IOException;
import java.math.BigDecimal;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.hitbtc.HitbtcAdapters;
import org.knowm.xchange.dto.meta.MarketMetaData;
import org.knowm.xchange.hitbtc.dto.HitbtcException;
import org.knowm.xchange.hitbtc.dto.trade.HitbtcOrder;
import org.knowm.xchange.hitbtc.dto.trade.HitbtcOwnTrade;
import org.knowm.xchange.hitbtc.dto.trade.HitbtcExecutionReport;

public class HitbtcTradeServiceRaw extends HitbtcBasePollingService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public HitbtcTradeServiceRaw(Exchange exchange) {

    super(exchange);
  }

  public HitbtcOrder[] getOpenOrdersRaw() throws IOException {

    return hitbtc.getHitbtcActiveOrders(signatureCreator);
  }

  public  HitbtcExecutionReport placeMarketOrderRawBaseResponse(MarketOrder marketOrder) throws IOException {

    String symbol = marketOrder.getCurrencyPair().base.getCurrencyCode() + marketOrder.getCurrencyPair().counter.getCurrencyCode();

    long nonce = exchange.getNonceFactory().createValue();
    String side = HitbtcAdapters.getSide(marketOrder.getType()).toString();
    String orderId = HitbtcAdapters.createOrderId(marketOrder, nonce);

    try {
       HitbtcExecutionReport response = hitbtc.postHitbtcNewOrder(signatureCreator, orderId, symbol, side,
          null, getLots(marketOrder), "market", "IOC");

      return response;
    } catch (HitbtcException e) {
      handleException(e);
      return null;
    }
  }

  public HitbtcExecutionReport placeMarketOrderRaw(MarketOrder marketOrder) throws IOException {

     return placeMarketOrderRawBaseResponse(marketOrder);
  }

  public HitbtcExecutionReport placeLimitOrderRaw(LimitOrder limitOrder) throws IOException {

    try {
      return fillHitbtcExecutionReportResponse(limitOrder);
    } catch (HitbtcException e) {
      handleException(e);
      return null;
    }
  }

  public  HitbtcExecutionReport placeLimitOrderRawReturningHitbtcExecutionReportResponse(LimitOrder limitOrder) throws IOException {

     HitbtcExecutionReport postHitbtcNewOrder = fillHitbtcExecutionReportResponse(limitOrder);
    return postHitbtcNewOrder;
  }

  private HitbtcExecutionReport fillHitbtcExecutionReportResponse(LimitOrder limitOrder) throws IOException {

    String symbol = HitbtcAdapters.adaptCurrencyPair(limitOrder.getCurrencyPair());
    long nonce = exchange.getNonceFactory().createValue();
    String side = HitbtcAdapters.getSide(limitOrder.getType()).toString();
    String orderId = HitbtcAdapters.createOrderId(limitOrder, nonce);

    try {
      return hitbtc.postHitbtcNewOrder(signatureCreator, orderId, symbol, side, limitOrder.getLimitPrice(),
          getLots(limitOrder), "limit", "GTC");
    } catch (HitbtcException e) {
      handleException(e);
      return null;
    }
  }

  public HitbtcExecutionReport cancelOrderRaw(String orderId) throws IOException {

    // extract symbol and side from original order id: buy/sell
    String originalSide = HitbtcAdapters.getSide(HitbtcAdapters.readOrderType(orderId)).toString();
    String symbol = HitbtcAdapters.readSymbol(orderId);

    try {
      return hitbtc.postHitbtcCancelOrder(signatureCreator, orderId);
    } catch (HitbtcException e) {
      handleException(e);
      return null;
    }
  }

  public  HitbtcExecutionReport cancelOrderRaw(String clientOrderId, String cancelRequestClientOrderId, String symbol, String side)
      throws IOException {

    try {
      return hitbtc.postHitbtcCancelOrder(signatureCreator, clientOrderId);
    } catch (HitbtcException e) {
      handleException(e);
      return null;
    }
  }

  public HitbtcOwnTrade[] getTradeHistoryRaw(int startIndex, int maxResults, String symbols) throws IOException {

    return hitbtc.getHitbtcTrades(signatureCreator, "timestamp", startIndex, maxResults, symbols, "desc",
        null, null);
  }

  /**
   * Represent tradableAmount in lots
   *
   * @throws java.lang.IllegalArgumentException if the result were to be less than lot size for given currency pair
   */
  protected BigDecimal getLots(Order order) {

    /*
    CurrencyPair pair = order.getCurrencyPair();
    MarketMetaData marketMetaData = exchange.getMetaData().getMarketMetaDataMap().get(pair);
    if (marketMetaData != null) {
      BigDecimal lotDivisor = marketMetaData.getMinimumAmount();
      if (lotDivisor != null) {
        BigDecimal lots = order.getTradableAmount().divide(lotDivisor, BigDecimal.ROUND_UNNECESSARY);
        if (lots.compareTo(BigDecimal.ONE) < 0) {
          throw new IllegalArgumentException("Tradable amount too low");
        }
        return lots;
      }
    }
    throw new IllegalArgumentException("HitBtc lots error");
    */
    return order.getTradableAmount();
  }

}
