package org.knowm.xchange.bitbay.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitbay.BitbayAdapters;
import org.knowm.xchange.bitbay.dto.BitbayBaseResponse;
import org.knowm.xchange.bitbay.dto.trade.BitbayOrder;
import org.knowm.xchange.bitbay.dto.trade.BitbayTradeResponse;
import org.knowm.xchange.bitbay.dto.trade.BitbayTransaction;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.polling.trade.PollingTradeService;
import org.knowm.xchange.service.polling.trade.params.DefaultTradeHistoryParamCurrencyPair;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParams;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @author yarkh
 */
public class BitbayTradeService extends BitbayTradeServiceRaw implements PollingTradeService {


  public BitbayTradeService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public OpenOrders getOpenOrders() throws IOException {
    List<BitbayOrder> orders = getOrders();
    return BitbayAdapters.adaptOpenOrders(orders);
  }

  @Override
  public String placeMarketOrder(MarketOrder marketOrder) throws IOException {
    return null;
  }

  @Override
  public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
    BitbayTradeResponse response = placeBitbayOrder(limitOrder);
    if (response.getSuccess() == null || Integer.valueOf(response.getOrderId()) == 0) {
      throw new ExchangeException(response.getMessage());
    }
    return response.getOrderId();
  }

  @Override
  public boolean cancelOrder(String orderId) throws IOException {
    BitbayBaseResponse response = cancelBitbayOrder(orderId);
    if (response.getSuccess() == null) {
      throw new ExchangeException(response.getMessage());
    }
    return response.getSuccess() != null;
  }

  @Override
  public UserTrades getTradeHistory(TradeHistoryParams params) throws IOException {

    CurrencyPair pair = null;

    if (params instanceof DefaultTradeHistoryParamCurrencyPair) {
      DefaultTradeHistoryParamCurrencyPair paramCurrencyPair = (DefaultTradeHistoryParamCurrencyPair) params;
      pair = paramCurrencyPair.getCurrencyPair();
    }

    if (pair != null) {
      String market = pair.base.getCurrencyCode() + "-" + pair.counter.getCurrencyCode();
      List<BitbayTransaction> transactions = getTransactions(market);
      return BitbayAdapters.adaptTradeHistory(transactions);
    }
    return null;
  }

  @Override
  public TradeHistoryParams createTradeHistoryParams() {
    return new DefaultTradeHistoryParamCurrencyPair();
  }

  @Override
  public Collection<Order> getOrder(String... orderIds) throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    return null;
  }
}
