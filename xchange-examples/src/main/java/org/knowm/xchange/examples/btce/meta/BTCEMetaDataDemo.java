package org.knowm.xchange.examples.btce.meta;

import java.io.IOException;
import java.math.BigDecimal;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.btce.v3.WEXExchange;
import org.knowm.xchange.btce.v3.dto.marketdata.WEXExchangeInfo;
import org.knowm.xchange.btce.v3.dto.meta.WEXMetaData;
import org.knowm.xchange.btce.v3.service.polling.WEXMarketDataService;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.meta.ExchangeMetaData;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.examples.btce.BTCEExamplesUtils;

/**
 * Demo requesting account info at BTC-E
 */
public class BTCEMetaDataDemo {

  public static void main(String[] args) throws IOException {

    WEXExchange btce = (WEXExchange) BTCEExamplesUtils.createExchange();
    rawLocal(btce);

    rawRemote(btce);

    generic(btce);
  }

  private static void rawLocal(WEXExchange exchange) throws IOException {
    WEXMetaData WEXMetaData = exchange.getWEXMetaData();
    System.out
        .println("WEX local meta data: amountScale=" + WEXMetaData.amountScale + " public data TTL seconds" + WEXMetaData.publicInfoCacheSeconds);
  }

  private static void rawRemote(Exchange btce) throws IOException {
    WEXExchangeInfo btceInfo = ((WEXMarketDataService) btce.getPollingMarketDataService()).getBTCEInfo();
    System.out.println("WEX remote meta data: " + btceInfo);

  }

  private static void generic(Exchange exchange) throws IOException {
    ExchangeMetaData metaData = (ExchangeMetaData) exchange.getMetaData();
    System.out.println("WEX generic meta data: " + metaData);

    exchange.getPollingTradeService()
        .verifyOrder(new MarketOrder.Builder(Order.OrderType.ASK, CurrencyPair.BTC_EUR).tradableAmount(BigDecimal.ONE).build());
  }

}
