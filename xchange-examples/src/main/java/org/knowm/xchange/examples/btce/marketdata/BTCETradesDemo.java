package org.knowm.xchange.examples.btce.marketdata;

import java.io.IOException;
import java.util.Map;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.btce.v3.WEXExchange;
import org.knowm.xchange.btce.v3.dto.marketdata.WEXTrade;
import org.knowm.xchange.btce.v3.service.polling.WEXMarketDataServiceRaw;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.service.polling.marketdata.PollingMarketDataService;

/**
 * Demonstrate requesting Order Book at BTC-E
 */
public class BTCETradesDemo {

  public static void main(String[] args) throws IOException {

    // Use the factory to get BTC-E exchange API using default settings
    Exchange btce = ExchangeFactory.INSTANCE.createExchange(WEXExchange.class.getName());
    generic(btce);
    raw(btce);
  }

  private static void generic(Exchange exchange) throws IOException {

    // Interested in the public polling market data feed (no authentication)
    PollingMarketDataService marketDataService = exchange.getPollingMarketDataService();

    // Get the latest trade data for BTC/EUR
    Trades trades = marketDataService.getTrades(CurrencyPair.BTC_EUR);

    System.out.println(trades.toString());
  }

  private static void raw(Exchange exchange) throws IOException {

    // Interested in the public polling market data feed (no authentication)
    WEXMarketDataServiceRaw marketDataService = (WEXMarketDataServiceRaw) exchange.getPollingMarketDataService();

    // Get the latest trade data for BTC/USD
    Map<String, WEXTrade[]> trades = marketDataService.getBTCETrades("btc_usd", 7).getTradesMap();

    for (Map.Entry<String, WEXTrade[]> entry : trades.entrySet()) {
      System.out.println("Pair: " + entry.getKey() + ", Trades:");
      for (WEXTrade trade : entry.getValue()) {
        System.out.println(trade.toString());
      }
    }
  }

}
