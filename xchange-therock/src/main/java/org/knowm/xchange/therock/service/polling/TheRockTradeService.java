package org.knowm.xchange.therock.service.polling;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.polling.trade.params.DefaultTradeHistoryParamCurrencyPair;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParamCurrencyPair;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParamsIdSpan;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParamsTimeSpan;
import org.knowm.xchange.therock.TheRockAdapters;
import org.knowm.xchange.therock.TheRockExchange;
import org.knowm.xchange.therock.dto.trade.TheRockOrder;
import org.knowm.xchange.therock.service.TheRockTradeServiceRaw;

public class TheRockTradeService extends TheRockTradeServiceRaw implements org.knowm.xchange.service.polling.trade.PollingTradeService {
  public TheRockTradeService(Exchange exchange) {
    super(exchange);
  }

  public String placeMarketOrder(MarketOrder order) throws IOException, ExchangeException {
    TheRockOrder placedOrder = placeTheRockOrder(order.getCurrencyPair(), order.getTradableAmount(), null,
        TheRockAdapters.adaptSide(order.getType()), TheRockOrder.Type.market);
    return placedOrder.getId().toString();
  }

  public String placeLimitOrder(LimitOrder order) throws IOException, ExchangeException {
    TheRockOrder placedOrder = placeTheRockOrder(order.getCurrencyPair(), order.getTradableAmount(), order.getLimitPrice(),
        TheRockAdapters.adaptSide(order.getType()), TheRockOrder.Type.limit);
    return placedOrder.getId().toString();
  }


  public OpenOrders getOpenOrders()
      throws IOException {
    List<LimitOrder> openOrders = new ArrayList();

    for (CurrencyPair currencyPair : ((TheRockExchange) this.exchange).getRockExchangeSymbols()) {
      TheRockOrder[] orders = getTheRockOrders(currencyPair).getOrders();

      if (orders != null) {
        for (TheRockOrder theRockOrder : orders) {
          try {
            openOrders.add(new LimitOrder(Objects.equals(theRockOrder.getSide().toString(), "buy") ? Order.OrderType.BID : Order.OrderType.ASK, theRockOrder
                .getAmount(), currencyPair, String.valueOf(theRockOrder.getId()), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .parse(theRockOrder.getDate()), theRockOrder
                .getPrice()));
          } catch (ParseException e) {
            e.printStackTrace();
          }
        }
      }
    }
    return new OpenOrders(openOrders);
  }


  public boolean cancelOrder(String orderId)
      throws IOException {
    boolean result = false;
    for (CurrencyPair currencyPair : ((TheRockExchange) this.exchange).getRockExchangeSymbols()) {

      try {


        result |= "deleted".equals(cancelTheRockOrder(currencyPair, Long.valueOf(Long.parseLong(orderId))).getStatus());
      } catch (Exception localException) {
      }
    }
    return result;
  }


  public java.util.Collection<Order> getOrder(String... orderIds)
      throws ExchangeException, org.knowm.xchange.exceptions.NotAvailableFromExchangeException, org.knowm.xchange.exceptions.NotYetImplementedForExchangeException, IOException {

    ArrayList<Order> orders = new ArrayList();

    CurrencyPair currencyPair = (CurrencyPair) this.exchange.getExchangeSpecification().getExchangeSpecificParameters().get("CURRENCY_PAIR");

    TheRockOrder theRockOrder = showTheRockOrder(currencyPair, Long.valueOf(orderIds[0]));
    try {

      orders.add(new LimitOrder(Objects.equals(theRockOrder.getSide().toString(), "buy") ? Order.OrderType.BID : Order.OrderType.ASK, theRockOrder
          .getAmount(), currencyPair, String.valueOf(theRockOrder.getId()), new SimpleDateFormat("YYYY-MM-DD'T'HH:MM:SS.SSS'Z'", java.util.Locale.ENGLISH)
          .parse(theRockOrder.getDate()), theRockOrder
          .getPrice()));
    } catch (ParseException e) {

      e.printStackTrace();
    }

    return orders;
  }

  public UserTrades getTradeHistory(TradeHistoryParams params) throws IOException {

    if (!(params instanceof TradeHistoryParamCurrencyPair)) {

      throw new ExchangeException("TheRock API recquires " + TradeHistoryParamCurrencyPair.class.getName());
    }

    TradeHistoryParamCurrencyPair pairParams = (TradeHistoryParamCurrencyPair) params;

    Long sinceTradeId = null;

    if ((params instanceof TradeHistoryParamsIdSpan)) {

      TradeHistoryParamsIdSpan trId = (TradeHistoryParamsIdSpan) params;
      try {

        sinceTradeId = Long.valueOf(trId.getStartId());
      } catch (Throwable localThrowable) {
      }
    }

    Date after = null;

    Date before = null;


    if ((params instanceof TradeHistoryParamsTimeSpan)) {

      TradeHistoryParamsTimeSpan time = (TradeHistoryParamsTimeSpan) params;

      after = time.getStartTime();

      before = time.getEndTime();
    }

    return TheRockAdapters.adaptUserTrades(getTheRockUserTrades(pairParams.getCurrencyPair(), sinceTradeId, after, before), pairParams.getCurrencyPair());
  }

  public DefaultTradeHistoryParamCurrencyPair createTradeHistoryParams() {

    return new DefaultTradeHistoryParamCurrencyPair();
  }
}
