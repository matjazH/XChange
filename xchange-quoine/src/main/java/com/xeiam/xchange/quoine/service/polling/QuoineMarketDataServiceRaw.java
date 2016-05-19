package com.xeiam.xchange.quoine.service.polling;

import java.io.IOException;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.quoine.dto.marketdata.QuoineOrderBook;
import com.xeiam.xchange.quoine.dto.marketdata.QuoineProduct;
import com.xeiam.xchange.quoine.dto.marketdata.QuoineTradesList;

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
