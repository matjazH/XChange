package org.knowm.xchange.btce.v3.service.polling;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Test;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.btce.v3.ExchangeUtils;
import org.knowm.xchange.btce.v3.dto.trade.WEXTransHistoryResult;
import org.knowm.xchange.btce.v3.service.polling.trade.params.WEXTransHistoryParams;

/**
 * Integration tests for transaction history retrieval. For these tests to function, a file 'v3/exchangeConfiguration.json' must be on the classpath
 * and contain valid api and secret keys.
 *
 * @author Peter N. Steinmetz Date: 4/3/15 Time: 8:47 AM
 */
public class TransHistoryFetchIntegrationTest {

  @Test
  public void defaultFetchTest() throws Exception {
    Exchange exchange = ExchangeUtils.createExchangeFromJsonConfiguration();
    if (exchange == null)
      return; // forces pass if not configuration is available
    WEXTradeService service = (WEXTradeService) exchange.getPollingTradeService();
    assertNotNull(service);
    WEXTransHistoryParams params = new WEXTransHistoryParams();
    Map<Long, WEXTransHistoryResult> btceTransHistory = service.getTransHistory(params);
    assertNotNull(btceTransHistory);
  }
}
