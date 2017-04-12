package org.knowm.xchange.bitmex.dto.marketdata;

import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.marketdata.OrderBook;

public class MarketData {
  Ticker ticker;
  Trades trades;
  OrderBook orderBook;

  public MarketData(Ticker ticker, OrderBook orderBook, Trades trades) {
    this.ticker = ticker;
    this.trades = trades;
    this.orderBook = orderBook;
  }

  public OrderBook getOrderBook() {
    return orderBook;
  }

  public Ticker getTicker() {
    return ticker;
  }

  public Trades getTrades() {
    return trades;
  }
}
