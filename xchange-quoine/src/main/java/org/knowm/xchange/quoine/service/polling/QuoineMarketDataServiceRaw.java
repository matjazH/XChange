package org.knowm.xchange.quoine.service.polling;

import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.quoine.dto.marketdata.QuoineOrderBook;
import org.knowm.xchange.quoine.dto.marketdata.QuoineProduct;
import org.knowm.xchange.quoine.dto.marketdata.QuoineTradesList;

public class QuoineMarketDataServiceRaw extends QuoineBasePollingService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public QuoineMarketDataServiceRaw(Exchange exchange) {

    super(exchange);
  }

  public QuoineProduct getQuoineProduct(String currencyPair) throws IOException {

    return quoine.getQuoineProduct(currencyPair);
  }

  public QuoineProduct[] getQuoineProducts() throws IOException {

    return quoine.getQuoineProducts();
  }

  public QuoineOrderBook getOrderBook(int id) throws IOException {

    return quoine.getOrderBook(id);
  }

  public QuoineTradesList getExecutions(String symbol) throws IOException {

    return quoine.getExecutions(symbol, 50, null);
  }

}
