package org.knowm.xchange.bl3p.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bl3p.Bl3pAdapters;
import org.knowm.xchange.bl3p.dto.marketdata.Bl3pOrderbook;
import org.knowm.xchange.bl3p.dto.Bl3pResult;
import org.knowm.xchange.bl3p.dto.marketdata.Bl3pTicker;
import org.knowm.xchange.bl3p.dto.marketdata.Bl3pTrades;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 14/08/15
 * Time: 19:18
 */
public class Bl3pMarketDataService extends Bl3pMarketDataServiceRaw implements MarketDataService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public Bl3pMarketDataService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws ExchangeException, IOException {

    Bl3pTicker bl3pTicker = getBl3pTicker(getPair(currencyPair));
    return Bl3pAdapters.adaptBl3pTicker(bl3pTicker, currencyPair);
  }

  @Override
  public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws ExchangeException, IOException {

    Bl3pResult<Bl3pOrderbook> bl3pResult = getBl3pOrderbook(getPair(currencyPair));
    return Bl3pAdapters.adaptBl3pOrderbook(bl3pResult.getData(), currencyPair);
  }

  @Override
  public Trades getTrades(CurrencyPair currencyPair, Object... args) throws ExchangeException, IOException {

    Long tradeId = null;
    if (args != null && args.length > 0) {
      if (args[0] != null && args[0] instanceof Long) {
        tradeId = (Long)args[0];
      }
    }

    Bl3pResult<Bl3pTrades> bl3pResult = getBl3pTrades(getPair(currencyPair), tradeId);
    checkResult(bl3pResult);
    return Bl3pAdapters.adaptBl3pTrades(bl3pResult.getData(), currencyPair);
  }
}
