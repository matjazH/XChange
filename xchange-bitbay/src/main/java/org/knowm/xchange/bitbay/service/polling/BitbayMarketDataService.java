package org.knowm.xchange.bitbay.service.polling;

import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitbay.BitbayAdapters;
import org.knowm.xchange.bitbay.dto.marketdata.BitbayMarketAll;
import org.knowm.xchange.bitbay.dto.marketdata.BitbayOrderBook;
import org.knowm.xchange.bitbay.dto.marketdata.MarketData;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.service.polling.marketdata.PollingMarketDataService;

/**
 * @author kpysniak
 */
public class BitbayMarketDataService extends BitbayMarketDataServiceRaw implements PollingMarketDataService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public BitbayMarketDataService(Exchange exchange) {

    super(exchange);
  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {

    return BitbayAdapters.adaptTicker(getBitbayTicker(currencyPair), currencyPair);
  }

  @Override
  public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {

    return BitbayAdapters.adaptOrderBook(getBitbayOrderBook(currencyPair), currencyPair);
  }

  @Override
  public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {

      Long sinceTid = null;
      if (args != null && args.length > 0) {
          if (args[0] instanceof Long) {
              sinceTid = (Long)args[0];
          }
      }

    return BitbayAdapters.adaptTrades(getBitbayTrades(currencyPair, sinceTid, "desc"), currencyPair);
  }

    public MarketData getAllMarketData(CurrencyPair currencyPair) throws IOException {

        BitbayMarketAll marketData = getBitbatAllMarketData(currencyPair);

        Ticker ticker = BitbayAdapters.adaptTicker(marketData, currencyPair);

        BitbayOrderBook bitbayOrderBook = new BitbayOrderBook(marketData.getAsks(), marketData.getBids());
        OrderBook orderBook = BitbayAdapters.adaptOrderBook(bitbayOrderBook, currencyPair);

        Trades trades = BitbayAdapters.adaptTrades(marketData.getTrades(), currencyPair);

        return new MarketData(ticker, orderBook, trades);
    }
}
