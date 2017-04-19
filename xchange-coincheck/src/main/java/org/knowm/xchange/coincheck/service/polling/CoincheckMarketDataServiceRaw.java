package org.knowm.xchange.coincheck.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.coincheck.dto.marketdata.CoincheckOrderbook;
import org.knowm.xchange.coincheck.dto.marketdata.CoincheckTicker;
import org.knowm.xchange.coincheck.dto.marketdata.CoincheckTrade;
import org.knowm.xchange.currency.CurrencyPair;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 30/05/16
 * Time: 17:52
 */
public class CoincheckMarketDataServiceRaw  extends CoincheckBasePollingService {
  /**
   * Constructor
   *
   * @param exchange
   */
  protected CoincheckMarketDataServiceRaw(Exchange exchange) {
    super(exchange);
  }

  protected CoincheckTicker getCoincheckTicker() throws IOException {
    return coincheck.getTicker();
  }

  protected CoincheckOrderbook getCoincheckOrderbook() throws IOException {
    return coincheck.getOrderbook();
  }

  protected List<CoincheckTrade> getCoincheckTrades() throws IOException {
    return coincheck.getTrades();
  }
}
