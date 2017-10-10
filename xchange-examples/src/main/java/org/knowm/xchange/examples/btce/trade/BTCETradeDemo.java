package org.knowm.xchange.examples.btce.trade;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.btce.v3.dto.trade.WEXCancelOrderResult;
import org.knowm.xchange.btce.v3.dto.trade.WEXOrder;
import org.knowm.xchange.btce.v3.dto.trade.WEXPlaceOrderResult;
import org.knowm.xchange.btce.v3.service.polling.WEXTradeServiceRaw;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.examples.btce.BTCEExamplesUtils;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.polling.trade.PollingTradeService;

/**
 * @author Matija Mazi
 */
public class BTCETradeDemo {

  public static void main(String[] args) throws IOException {

    Exchange btce = BTCEExamplesUtils.createExchange();
    generic(btce);
    raw(btce);
  }

  private static void generic(Exchange exchange) throws IOException {

    PollingTradeService tradeService = exchange.getPollingTradeService();

    printOpenOrders(tradeService);

    // place a limit buy order
    LimitOrder limitOrder = new LimitOrder(Order.OrderType.ASK, new BigDecimal("0.1"), CurrencyPair.BTC_USD, "", null, new BigDecimal("1023.45"));

    String limitOrderReturnValue = null;
    try {
      limitOrderReturnValue = tradeService.placeLimitOrder(limitOrder);
      System.out.println("Limit Order return value: " + limitOrderReturnValue);

      printOpenOrders(tradeService);

      // Cancel the added order
      boolean cancelResult = tradeService.cancelOrder(limitOrderReturnValue);
      System.out.println("Canceling returned " + cancelResult);
    } catch (ExchangeException e) {
      System.out.println(e.getMessage());
    }

    printOpenOrders(tradeService);
  }

  private static void raw(Exchange exchange) throws IOException {

    WEXTradeServiceRaw tradeService = (WEXTradeServiceRaw) exchange.getPollingTradeService();

    printRawOpenOrders(tradeService);

    // place buy order
    WEXOrder.Type type = WEXOrder.Type.buy;
    String pair = "btc_usd";
    WEXOrder WEXOrder = new WEXOrder(0, null, new BigDecimal("1"), new BigDecimal("0.1"), type, pair);

    WEXPlaceOrderResult result = null;
    try {
      result = tradeService.placeBTCEOrder(WEXOrder);
      System.out.println("placeBTCEOrder return value: " + result);

      printRawOpenOrders(tradeService);

      // Cancel the added order
      WEXCancelOrderResult cancelResult = tradeService.cancelBTCEOrder(result.getOrderId());
      System.out.println("Canceling returned " + cancelResult);
    } catch (ExchangeException e) {
      System.out.println(e.getMessage());
    }

    printRawOpenOrders(tradeService);
  }

  private static void printOpenOrders(PollingTradeService tradeService) throws IOException {

    OpenOrders openOrders = tradeService.getOpenOrders();
    System.out.println("Open Orders: " + openOrders.toString());
  }

  private static void printRawOpenOrders(WEXTradeServiceRaw tradeService) throws IOException {

    Map<Long, WEXOrder> openOrders = tradeService.getBTCEActiveOrders(null);
    for (Map.Entry<Long, WEXOrder> entry : openOrders.entrySet()) {
      System.out.println("ID: " + entry.getKey() + ", Order:" + entry.getValue());
    }
  }

}
