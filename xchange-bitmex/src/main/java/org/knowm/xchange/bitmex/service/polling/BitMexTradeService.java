package org.knowm.xchange.bitmex.service.polling;

import java.util.*;
import java.io.IOException;
import java.math.BigDecimal;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrades;

import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.bitmex.dto.trade.YoBitActiveOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.bitmex.dto.trade.YoBitTradesResponse;
import org.knowm.xchange.bitmex.dto.trade.YoBitorderInform;
import org.knowm.xchange.bitmex.dto.BitMexResult;
import org.knowm.xchange.service.polling.trade.PollingTradeService;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParams;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;

/**
 * Created by developer on 31/03/17.
 */
public class BitMexTradeService extends BitMexTradeServiceRaw implements PollingTradeService {

  public BitMexTradeService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public void verifyOrder(LimitOrder limitOrder) {

  }

  @Override
  public void verifyOrder(MarketOrder marketOrder) {

  }

  @Override
  public OpenOrders getOpenOrders()
      throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {

    List<LimitOrder> openOrders = new LinkedList<>();
    // TODO replace CONSTANT CurrencyPair to variable
    Map<String, YoBitActiveOrder> yoBitActiveOrder = getYoBitActiveOrder(CurrencyPair.BTC_RUR);

    if (yoBitActiveOrder != null) {
      Iterator<Map.Entry<String, YoBitActiveOrder>> itOrders = yoBitActiveOrder.entrySet().iterator();

      while (itOrders.hasNext() ) {
        Map.Entry<String, YoBitActiveOrder> pairOrders = itOrders.next();

        openOrders.add(new LimitOrder(pairOrders.getValue().getType(), pairOrders.getValue().getTradableAmount(),
                       pairOrders.getValue().getCurrencyPair(),pairOrders.getKey(),pairOrders.getValue().getTimestamp(),
                       pairOrders.getValue().getRate()));
      }
    }
    return new OpenOrders(openOrders);
  }

  @Override
  public UserTrades getTradeHistory(TradeHistoryParams params) throws IOException {
    return null;
  }

  @Override
  public String placeMarketOrder(MarketOrder marketOrder)
      throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    return null;
  }

  @Override
  public String placeLimitOrder(LimitOrder limitOrder)
      throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    YoBitTradesResponse yoBitTradeResponse = placeBitbayOrder(limitOrder);
    return yoBitTradeResponse.getOrderId();
  }

  @Override
  public boolean cancelOrder(String orderId)
      throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    BigDecimal myOrderId = new BigDecimal(orderId);
    BitMexResult response = cancelYoBitOrder(myOrderId);
    return response.isSuccess();
  }

  @Override
  public TradeHistoryParams createTradeHistoryParams() {
    return null;
  }

  @Override
  public Collection<Order> getOrder(String... orderIds)
      throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    BitMexResult<Map<String, YoBitorderInform>> yoBitOrder = getYoBitOrder(new BigDecimal(orderIds[0]));
    ArrayList<Order> orders = new ArrayList<>();
    orders.add(yoBitOrder.getResult().get(orderIds[0]));
    return orders;
  }

}
