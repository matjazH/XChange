package org.knowm.xchange.ccex.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ccex.dto.trade.CCEXGetorderinfoResponse;
import org.knowm.xchange.ccex.dto.trade.CCEXOrderInfo;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.ccex.CCEXAdapters;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.dto.marketdata.Trades.TradeSortType;
import org.knowm.xchange.service.polling.trade.PollingTradeService;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParams;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParamsZero;

public class CCEXTradeService extends CCEXTradeServiceRaw implements PollingTradeService {

  public CCEXTradeService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public TradeHistoryParams createTradeHistoryParams() {
    return new TradeHistoryParamsZero();
  }

  @Override
  public boolean cancelOrder(String orderId) throws IOException {
    return cancelCCEXLimitOrder(orderId);
  }

  @Override
  public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
    String orderId = placeCCEXLimitOrder(limitOrder);
    return orderId;
  }

  @Override
  public OpenOrders getOpenOrders() throws IOException {
    return new OpenOrders(CCEXAdapters.adaptOpenOrders(getCCEXOpenOrders()));
  }

  @Override
  public String placeMarketOrder(MarketOrder marketOrder)
    throws ExchangeException,NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    throw new NotAvailableFromExchangeException();
  }

  @Override
  public UserTrades getTradeHistory(TradeHistoryParams params) throws IOException {
    return new UserTrades(CCEXAdapters.adaptUserTrades(getCCEXTradeHistory()), TradeSortType.SortByTimestamp);
  }

  @Override
  public Collection<Order> getOrder(String... orderIds) throws ExchangeException, NotAvailableFromExchangeException,
      NotYetImplementedForExchangeException, IOException {
    List<CCEXOrderInfo> result = getOrderInfo(orderIds[0]).getResult();
    return new ArrayList<Order>(result);
  }

}