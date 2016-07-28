package org.knowm.xchange.coincheck.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.coincheck.CoincheckAdapters;
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
 * Date: 03/06/16
 * Time: 17:11
 */
public class CoincheckTradeService extends CoincheckTradeServiceRaw implements PollingTradeService {

  public CoincheckTradeService(Exchange exchange) {
    super(exchange);
  }

  public OpenOrders getOpenOrders() throws ExchangeException, NotAvailableFromExchangeException,
      NotYetImplementedForExchangeException, IOException {

    return CoincheckAdapters.adaptOrders(getCoincheckOrders());
  }

  public String placeMarketOrder(MarketOrder marketOrder) throws ExchangeException, NotAvailableFromExchangeException,
      NotYetImplementedForExchangeException, IOException {

    return null;
  }

  public String placeLimitOrder(LimitOrder limitOrder) throws ExchangeException, NotAvailableFromExchangeException,
      NotYetImplementedForExchangeException, IOException {

    return placeCoincheckOrder(limitOrder);
  }

  public boolean cancelOrder(String orderId) throws ExchangeException, NotAvailableFromExchangeException,
      NotYetImplementedForExchangeException, IOException {

    return cancelCoincheckOrder(orderId);
  }

  public UserTrades getTradeHistory(TradeHistoryParams params) throws IOException {

    return CoincheckAdapters.adaptTradeHistory(getCoincheckTransactions());
  }

  public TradeHistoryParams createTradeHistoryParams() {
    return null;
  }

  public Collection<Order> getOrder(String... orderIds) throws ExchangeException, NotAvailableFromExchangeException,
      NotYetImplementedForExchangeException, IOException {
    return null;
  }
}
