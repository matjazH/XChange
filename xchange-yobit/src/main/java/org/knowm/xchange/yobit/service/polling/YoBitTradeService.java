package org.knowm.xchange.yobit.service.polling;

import java.util.*;
import java.io.IOException;
import java.math.BigDecimal;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.*;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.trade.*;

import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.polling.trade.params.DefaultTradeHistoryParamCurrencyPair;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParamCurrencyPair;
import org.knowm.xchange.yobit.YoBitExchange;
import org.knowm.xchange.yobit.dto.trade.YoBitActiveOrder;
import org.knowm.xchange.yobit.dto.trade.YoBitTradesOrder;
import org.knowm.xchange.yobit.dto.trade.YoBitTradesResponse;
import org.knowm.xchange.yobit.dto.trade.YoBitorderInform;
import org.knowm.xchange.yobit.dto.YoBitResult;
import org.knowm.xchange.service.polling.trade.PollingTradeService;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParams;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;

/**
 * Created by developer on 31/03/17.
 */
public class YoBitTradeService extends YoBitTradeServiceRaw implements PollingTradeService {

  public YoBitTradeService(Exchange exchange) {
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
    CurrencyPair currencyPair = (CurrencyPair) exchange.getExchangeSpecification().getExchangeSpecificParameters().get("CURRENCY_PAIR");
    Map<String, YoBitActiveOrder> yoBitActiveOrder = getYoBitActiveOrder(currencyPair);

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
    TradeHistoryParamCurrencyPair pairParams = (TradeHistoryParamCurrencyPair) params;
    Map<String, YoBitTradesOrder> yoBitTradeHistory=getYoBitTradeHistory(pairParams.getCurrencyPair());

    long lastTradeId = 0;
    List<UserTrade> tradesList = new ArrayList<>();
    if (yoBitTradeHistory!=null) {
      Iterator<Map.Entry<String, YoBitTradesOrder>> itOrders = yoBitTradeHistory.entrySet().iterator();

      while (itOrders.hasNext()) {
        Map.Entry<String, YoBitTradesOrder> pairOrders = itOrders.next();
        long tradeId = Long.valueOf(String.valueOf(pairOrders.getValue().getOrderId()));
        YoBitTradesOrder trade = pairOrders.getValue();
        if (tradeId > lastTradeId)
          lastTradeId = tradeId;
        tradesList.add(new UserTrade.Builder().id(String.valueOf(tradeId)).tradableAmount(trade.getAmount()).currencyPair(pairParams.getCurrencyPair()).price(trade.getRate())
            .timestamp(new Date(trade.getTimestampCreated().longValue()*1000L)).orderId(String.valueOf(trade.getOrderId()))
            .type(trade.getType())
            .build());
      }
    }

    return new UserTrades(tradesList, lastTradeId, Trades.TradeSortType.SortByID);
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
    YoBitResult response = cancelYoBitOrder(myOrderId);
    return response.isSuccess();
  }

  @Override
  public DefaultTradeHistoryParamCurrencyPair createTradeHistoryParams() {
    return new DefaultTradeHistoryParamCurrencyPair();
  }

  @Override
  public Collection<Order> getOrder(String... orderIds)
      throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    YoBitResult<Map<String, YoBitorderInform>> yoBitOrder = getYoBitOrder(new BigDecimal(orderIds[0]));
    ArrayList<Order> orders = new ArrayList<>();
    orders.add(yoBitOrder.getResult().get(orderIds[0]));
    return orders;
  }

}
