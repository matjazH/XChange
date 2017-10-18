package org.knowm.xchange.btcup.service;

import java.util.List;
import java.io.IOException;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.btcup.dto.trade.BtcUpOrder;
import org.knowm.xchange.btcup.dto.trade.BtcUpTrade;

import static org.knowm.xchange.btcup.service.BtcUpPollingMarketDataServiceRaw.FULL_LIMIT_SIZE;
import static org.knowm.xchange.dto.Order.OrderType.BID;

/**
 * Created by VITTACH on 28/08/17.
 */
public class BtcUpPollingTradeServiceRaw extends BtcUpBaseService {

  public BtcUpPollingTradeServiceRaw(Exchange exchange) {
    super(exchange);
  }

  public List<BtcUpOrder> getBtcUpOpenOrders() throws IOException {
    return btcUp.openOrders(apiKey, signatureCreator, exchange.getNonceFactory(), "openorders").getResult();
  }

  public List<BtcUpTrade> getBtcUpTradeHistory(int limit) throws IOException {
    return getBtcUpTradeHistory(limit, null);
  }

  public List<BtcUpTrade> getBtcUpTradeHistory(int limit, CurrencyPair pair) throws IOException {

    if (limit < 1) {
      limit = 1;
    } else if (limit > FULL_LIMIT_SIZE) {
      limit = FULL_LIMIT_SIZE;
    }

    List<BtcUpTrade> tradehistory = null;
    if (pair != null) {
      tradehistory = btcUp.tradeHistory(apiKey, signatureCreator, exchange.getNonceFactory(),
          "tradehistory", limit, pair.base.getCurrencyCode() + "_" + pair.counter.getCurrencyCode()).getResult();
    } else {
      tradehistory = btcUp.tradeHistory(apiKey, signatureCreator, exchange.getNonceFactory(),
          "tradehistory", limit).getResult();
    }

    return tradehistory;
  }

  public String placeBtcUpMarketOrder(MarketOrder marketOrder) throws IOException {

    BtcUpOrder market = btcUp.placeMarketOrder(apiKey, signatureCreator, exchange.getNonceFactory(), "placemarket",
        marketOrder.getCurrencyPair().base.getCurrencyCode() + "_" + marketOrder.getCurrencyPair().counter.getCurrencyCode(),
        (marketOrder.getType() == BID? 0 : 1), marketOrder.getType() == BID? 1 : 0, marketOrder.getTradableAmount()).getResult();

    return String.valueOf(market.getId());
  }

  public BtcUpOrder getOrderInfo(String orderId) throws IOException {
    return btcUp.orderInfo(apiKey, signatureCreator, exchange.getNonceFactory(), "orderinfo", orderId).getResult();
  }

  public String placeBtcUpLimitOrder(LimitOrder limitOrder) throws IOException {
    BtcUpOrder order;
    order = btcUp.placeOrder(apiKey, signatureCreator, exchange.getNonceFactory(), "placelimit",
        limitOrder.getCurrencyPair().base.getCurrencyCode() + "_" + limitOrder.getCurrencyPair().counter.getCurrencyCode(),
        (limitOrder.getType() == BID ? 0 : 1), limitOrder.getTradableAmount(), limitOrder.getLimitPrice()).getResult();

    return String.valueOf(order.getId());
  }

  public boolean cancelOrder(String orderId) throws IOException {
    return btcUp.cancelOrder(apiKey, signatureCreator, exchange.getNonceFactory(), "cancelorder", orderId).getSuccess();
  }

}
