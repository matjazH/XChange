package org.knowm.xchange.mercadobitcoin.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.mercadobitcoin.MercadoBitcoinAdaptersV3;
import org.knowm.xchange.mercadobitcoin.dto.v3.trade.MercadoBitcoinOrderResponse;
import org.knowm.xchange.mercadobitcoin.dto.v3.trade.MercadoBitcoinOrdersResponse;
import org.knowm.xchange.service.polling.trade.PollingTradeService;
import org.knowm.xchange.service.polling.trade.params.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.knowm.xchange.mercadobitcoin.MercadoBitcoinAdaptersV3.adaptPair;

/**
 * @author Felipe Micaroni Lalli
 */
public class MercadoBitcoinTradeService extends MercadoBitcoinTradeServiceRaw implements PollingTradeService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public MercadoBitcoinTradeService(Exchange exchange) {

    super(exchange);
  }

  @Override
  public OpenOrders getOpenOrders() throws IOException {

    MercadoBitcoinOrdersResponse btcResponse = getMercadoBitcoinUserOrders("BRLBTC", ACTIVE_ORDERS);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    MercadoBitcoinOrdersResponse ltcResponse = getMercadoBitcoinUserOrders("BRLLTC", ACTIVE_ORDERS);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    MercadoBitcoinOrdersResponse bchResponse = getMercadoBitcoinUserOrders("BRLBCH", ACTIVE_ORDERS);
    List<LimitOrder> btcOrders = MercadoBitcoinAdaptersV3.adaptOrders(btcResponse.getOrders());
    List<LimitOrder> ltcOrders = MercadoBitcoinAdaptersV3.adaptOrders(ltcResponse.getOrders());
    List<LimitOrder> bchOrders = MercadoBitcoinAdaptersV3.adaptOrders(bchResponse.getOrders());
    btcOrders.addAll(ltcOrders);
    btcOrders.addAll(bchOrders);
    return new OpenOrders(btcOrders);
  }

  @Override
  public Collection<Order> getOrder(String... orderId) throws IOException {
    List<Order> order = new ArrayList();
    if (orderId.length > 1) {
      CurrencyPair currencyPair = new CurrencyPair(orderId[0]);
      order.add(MercadoBitcoinAdaptersV3.adaptOrder(mercadoBitcoinGetOrder(adaptPair(currencyPair), orderId[1]).getOrder()));
    }
    return order;
  }

  @Override
  public String placeMarketOrder(MarketOrder marketOrder) throws IOException {
    throw new NotAvailableFromExchangeException();
  }

  @Override
  public String placeLimitOrder(LimitOrder limitOrder) throws IOException {

    MercadoBitcoinOrderResponse mercadoBitcoinOrderResponse = mercadoBitcoinPlaceLimitOrder(limitOrder);
    return String.valueOf(mercadoBitcoinOrderResponse.getOrder().getOrderId());
  }

  @Override
  public boolean cancelOrder(String orderId) throws IOException {

    String[] pairAndId = orderId.split(":");
    return mercadoBitcoinCancelOrder(pairAndId[0], pairAndId[1]);
  }

  @Override
  public UserTrades getTradeHistory(TradeHistoryParams params) throws IOException {

    CurrencyPair pair = ((TradeHistoryParamCurrencyPair) params).getCurrencyPair();
    MercadoBitcoinOrdersResponse orders = getMercadoBitcoinUserOrders(adaptPair(pair), FILLED_ORDERS);
    return MercadoBitcoinAdaptersV3.toUserTrades(orders);
  }

  @Override
  public TradeHistoryParams createTradeHistoryParams() {
    return new MercadoTradeHistoryParams(CurrencyPair.BTC_BRL);
  }

  public static class MercadoTradeHistoryParams extends DefaultTradeHistoryParamCurrencyPair
      implements TradeHistoryParamsIdSpan, TradeHistoryParamsTimeSpan {
    private String startId;
    private String endId;
    private Date startTime;
    private Date endTime;

    public MercadoTradeHistoryParams(CurrencyPair pair) {
      super(pair);
    }

    @Override
    public void setStartId(String startId) {
      this.startId = startId;
    }

    @Override
    public String getStartId() {
      return startId;
    }

    @Override
    public void setEndId(String endId) {
      this.endId = endId;
    }

    @Override
    public String getEndId() {
      return endId;
    }

    @Override
    public void setStartTime(Date startTime) {
      this.startTime = startTime;
    }

    @Override
    public Date getStartTime() {
      return startTime;
    }

    @Override
    public void setEndTime(Date endTime) {
      this.endTime = endTime;
    }

    @Override
    public Date getEndTime() {
      return endTime;
    }
  }

}
