package org.knowm.xchange.btcup.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.btcup.BtcUpAdapters;
import org.knowm.xchange.btcup.dto.trade.BtcUpOrder;
import org.knowm.xchange.btcup.dto.trade.BtcUpTrade;
import org.knowm.xchange.btcup.service.params.BtcUpHistoryParams;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.service.polling.trade.PollingTradeService;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParamCurrencyPair;

import java.io.IOException;
import java.util.*;

import static org.knowm.xchange.btcup.service.BtcUpPollingMarketDataServiceRaw.FULL_LIMIT_SIZE;

/**
 * Created by VITTACH on 28/08/17.
 */
public class BtcUpPollingTradeService extends BtcUpPollingTradeServiceRaw implements PollingTradeService {

  public BtcUpPollingTradeService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public TradeHistoryParamCurrencyPair createTradeHistoryParams() {

    return new BtcUpHistoryParams();
  }

  @Override
  public Collection<Order> getOrder(String... orderIds) throws IOException {

    BtcUpOrder orderInfo = super.getOrderInfo(orderIds[0]);
    ArrayList<Order> orders = new ArrayList<>();
    orders.add(new LimitOrder(orderInfo.getSide() == 0? Order.OrderType.BID : Order.OrderType.ASK,
        orderInfo.getCachOrderQty(), new CurrencyPair(orderInfo.getPair().replace("_","/")),
        String.valueOf(orderInfo.getId()), new Date(BtcUpAdapters.adaptTicksToMilliseconds(orderInfo.getTicks())),
        orderInfo.getPrice()));

    return orders;
  }

  @Override
  public String placeLimitOrder(LimitOrder limitOrder) throws IOException {

    return placeBtcUpLimitOrder(limitOrder);
  }

  @Override
  public OpenOrders getOpenOrders() throws IOException {

    return new OpenOrders(BtcUpAdapters.adaptOpenOrders(getBtcUpOpenOrders()));
  }

  @Override
  public String placeMarketOrder(MarketOrder marketOrder) throws IOException {

    return placeBtcUpMarketOrder(marketOrder);
  }

  @Override
  public boolean cancelOrder(String orderId) throws IOException {

    return super.cancelOrder(orderId);
  }

  @Override
  public UserTrades getTradeHistory(TradeHistoryParams params) throws IOException {

    int limitNumberOfItems = -1;
    CurrencyPair currencyPair = null;

    if (params instanceof BtcUpHistoryParams) {
      limitNumberOfItems = ((BtcUpHistoryParams) params).getCount();
      currencyPair = ((BtcUpHistoryParams)params).getCurrencyPair();
    }

    List<BtcUpTrade> BTCUPTrades;
    if (limitNumberOfItems == -1) {
      BTCUPTrades =  getBtcUpTradeHistory(FULL_LIMIT_SIZE);
    } else {
      BTCUPTrades = getBtcUpTradeHistory(limitNumberOfItems, currencyPair);
    }

    return new UserTrades(BtcUpAdapters.adaptUserTrades(BTCUPTrades), Trades.TradeSortType.SortByTimestamp);
  }
}