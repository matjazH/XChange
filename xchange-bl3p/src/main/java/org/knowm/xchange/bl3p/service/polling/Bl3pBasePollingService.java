package org.knowm.xchange.bl3p.service.polling;

import org.knowm.xchange.Exchange;

import org.knowm.xchange.bl3p.dto.Bl3pError;
import org.knowm.xchange.bl3p.dto.Bl3pResult;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.polling.BasePollingService;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 14/08/15
 * Time: 19:14
 */
public class Bl3pBasePollingService extends BaseExchangeService implements BasePollingService {

  // TODO: Temp hack, until LTCEUR be added
  protected static final String MARKET = "BTCEUR";

  /**
   * Constructor
   *
   * @param exchange
   */
  protected Bl3pBasePollingService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public List<CurrencyPair> getExchangeSymbols() throws IOException {
    return null;
  }

  protected void checkResult(Bl3pResult result) {
    if (result == null || "error".equals(result.getResult())) {
      Object data = result.getData();
      String msg = data != null && data instanceof Bl3pError ? ((Bl3pError)data).getMessage() : "Bl3p result error";
      throw new ExchangeException(msg);
    }
  }

  public static String getPair(CurrencyPair currencyPair) {
    return currencyPair.base.getCurrencyCode().toUpperCase() + currencyPair.counter.getCurrencyCode().toUpperCase();
  }
}
