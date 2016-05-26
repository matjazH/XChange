package org.knowm.xchange.bl3p.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bl3p.Bl3pAdapters;
import org.knowm.xchange.bl3p.dto.trade.Bl3pOpenOrders;
import org.knowm.xchange.bl3p.dto.trade.Bl3pOrder;
import org.knowm.xchange.bl3p.dto.trade.Bl3pOrderId;
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
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParams;

import java.io.IOException;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 18/08/15
 * Time: 18:47
 */
public class Bl3pTradeService extends Bl3pTradeServiceRaw implements PollingTradeService {
  /**
   * Constructor
   *
   * @param exchange
   */
  public Bl3pTradeService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public OpenOrders getOpenOrders() throws ExchangeException, IOException {

    Bl3pOpenOrders bl3pOpenOrders = getBl3pOpenOrders(MARKET);
    return Bl3pAdapters.adaptOpenOprder(bl3pOpenOrders);
  }

  @Override
  public String placeMarketOrder(MarketOrder marketOrder) throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    return null;
  }

  @Override
  public String placeLimitOrder(LimitOrder limitOrder) throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {

    CurrencyPair currencyPair = limitOrder.getCurrencyPair();
    String market = getPair(currencyPair);
    String type = limitOrder.getType().toString().toLowerCase();
    Integer priceInt = limitOrder.getLimitPrice().movePointRight(5).intValue();
    Integer amountInt = limitOrder.getTradableAmount().movePointRight(8).intValue();

    Bl3pOrderId bl3pOrderId = addBl3pOrder(market, type, amountInt, priceInt, "BTC");

    return bl3pOrderId.getOrderId();
  }

  @Override
  public boolean cancelOrder(String orderId) throws ExchangeException, NotAvailableFromExchangeException, IOException {
    return cancelBl3pOrder(MARKET, orderId);
  }

  public boolean cancelOrder(CurrencyPair currencyPair, String orderId) throws ExchangeException, IOException {

    return cancelBl3pOrder(getPair(currencyPair), orderId);
  }

  public Bl3pOrder getOrderDetails(String market, String orderId) throws ExchangeException, IOException {
    return getBl3pOrder(market, orderId);
  }

  @Override
  public UserTrades getTradeHistory(TradeHistoryParams params) throws IOException {
    return null;
  }

  @Override
  public TradeHistoryParams createTradeHistoryParams() {
    return null;
  }

  @Override
  public Collection<Order> getOrder(String... orderIds) throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    return null;
  }
}
