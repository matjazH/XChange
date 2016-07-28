package org.knowm.xchange.coincheck.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.coincheck.CoincheckAdapters;
import org.knowm.xchange.coincheck.dto.CoincheckOrderType;
import org.knowm.xchange.coincheck.dto.trade.*;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.exceptions.ExchangeException;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 03/06/16
 * Time: 17:11
 */
public class CoincheckTradeServiceRaw extends CoincheckBasePollingService {

  protected CoincheckTradeServiceRaw(Exchange exchange) {
    super(exchange);
  }

  protected List<CoincheckOrder> getCoincheckOrders() throws IOException {

    CoincheckOrders response = coincheck.getOpenOrders(apiKey, exchange.getNonceFactory(), signatureCreator);
    if (response.success) {
      return response.getOrders();
    }
    throw new ExchangeException(response.getError());
  }

  protected List<CoincheckTransaction> getCoincheckTransactions() throws IOException {

    CoincheckTransactions response = coincheck.getTransactions(apiKey, exchange.getNonceFactory(), signatureCreator);
    if (response.success) {
      return response.getTransactions();
    }
    throw new ExchangeException(response.getError());
  }

  protected String placeCoincheckOrder(LimitOrder limitOrder) throws IOException {

    CoincheckOrderType type = limitOrder.getType() == Order.OrderType.BID ? CoincheckOrderType.buy : CoincheckOrderType.sell;
    String pair = CoincheckAdapters.adaptCoincheckPair(limitOrder.getCurrencyPair());

    CoincheckOrderResponse orderResponse = coincheck.createNewOrder(apiKey, exchange.getNonceFactory(), signatureCreator,
        type, pair, limitOrder.getLimitPrice(), limitOrder.getTradableAmount(),
        null, null, null);

    if (orderResponse.getSuccess()) {
      return String.valueOf(orderResponse.getId());
    }
    throw new ExchangeException(orderResponse.getError());
  }

  protected boolean cancelCoincheckOrder(String orderId) throws IOException {

    CoincheckOrderResponse response = coincheck.cancelOrder(apiKey, exchange.getNonceFactory(), signatureCreator, Long.valueOf(orderId));
    return response.getSuccess();
  }

}
