package org.knowm.xchange.mercadobitcoin.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.mercadobitcoin.MercadoBitcoinAdaptersV3;
import org.knowm.xchange.mercadobitcoin.MercadoBitcoinAuthenticated;
import org.knowm.xchange.mercadobitcoin.dto.v3.MercadoBitcoinBaseResponse;
import org.knowm.xchange.mercadobitcoin.dto.v3.trade.MercadoBitcoinOrderResponse;
import org.knowm.xchange.mercadobitcoin.dto.v3.trade.MercadoBitcoinOrdersResponse;
import si.mazi.rescu.RestProxyFactory;

import java.io.IOException;

/**
 * @author Felipe Micaroni Lalli
 */
public class MercadoBitcoinTradeServiceRaw extends MercadoBitcoinBasePollingService {

  private static final String GET_ORDER = "get_order";
  private static final String GET_ORDER_LIST = "list_orders";
  private static final String PLACE_BUY_ORDER = "place_buy_order";
  private static final String PLACE_SELL_ORDER = "place_sell_order";
  private static final String CANCEL_ORDER = "cancel_order";

  protected static final String ACTIVE_ORDERS = "[2]";
  protected static final String FILLED_ORDERS = "[4]";

  private final MercadoBitcoinAuthenticated mercadoBitcoinAuthenticated;

  /**
   * Constructor
   *
   * @param exchange
   */
  public MercadoBitcoinTradeServiceRaw(Exchange exchange) {

    super(exchange);
    this.mercadoBitcoinAuthenticated = RestProxyFactory.createProxy(MercadoBitcoinAuthenticated.class,
        exchange.getExchangeSpecification().getSslUri(),
        createClientConfig(exchange.getExchangeSpecification()));
  }

  public MercadoBitcoinOrdersResponse getMercadoBitcoinUserOrders(String pair, String statusList) throws IOException {

    MercadoBitcoinBaseResponse<MercadoBitcoinOrdersResponse> response = mercadoBitcoinAuthenticated.getOrderList(
        exchange.getExchangeSpecification().getApiKey(), signatureCreator, GET_ORDER_LIST,
        exchange.getNonceFactory(), pair, statusList);

    checkResponse(response);
    return response.getResponseData();
  }

  public MercadoBitcoinOrderResponse mercadoBitcoinPlaceLimitOrder(LimitOrder limitOrder) throws IOException {

    String method = limitOrder.getType() == Order.OrderType.BID ? PLACE_BUY_ORDER : PLACE_SELL_ORDER;

    MercadoBitcoinBaseResponse<MercadoBitcoinOrderResponse> response = mercadoBitcoinAuthenticated
        .placeLimitOrder(exchange.getExchangeSpecification().getApiKey(), signatureCreator, method,
            exchange.getNonceFactory(), MercadoBitcoinAdaptersV3.adaptPair(limitOrder.getCurrencyPair()),
            limitOrder.getTradableAmount(), limitOrder.getLimitPrice());

    checkResponse(response);
    return response.getResponseData();
  }

  public boolean mercadoBitcoinCancelOrder(String pair, String orderId) throws IOException {

    MercadoBitcoinBaseResponse<MercadoBitcoinOrderResponse> response = mercadoBitcoinAuthenticated.cancelOrder(
        exchange.getExchangeSpecification().getApiKey(), signatureCreator, CANCEL_ORDER,
        exchange.getNonceFactory(), pair, orderId);

    checkResponse(response);
    return response.getStatusCode() == 100;
  }

  public MercadoBitcoinOrderResponse mercadoBitcoinGetOrder(String pair, String orderId) throws IOException  {
    MercadoBitcoinBaseResponse<MercadoBitcoinOrderResponse> response = mercadoBitcoinAuthenticated.getOrder(
        exchange.getExchangeSpecification().getApiKey(), signatureCreator, GET_ORDER,
        exchange.getNonceFactory(), pair, orderId);

    checkResponse(response);
    return response.getResponseData();
  }

}
