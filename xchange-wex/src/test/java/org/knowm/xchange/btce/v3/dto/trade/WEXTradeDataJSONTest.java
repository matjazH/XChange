package org.knowm.xchange.btce.v3.dto.trade;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.knowm.xchange.btce.v3.dto.WEXReturn;
import org.knowm.xchange.btce.v3.dto.trade.WEXTradeHistoryResult.Type;

/**
 * Test BTCETradeData JSON parsing
 */
public class WEXTradeDataJSONTest {

  @Test
  public void testOpenOrders() throws IOException {

    WEXOpenOrdersReturn result = getResult("/v3/trade/example-open-orders-data.json", WEXOpenOrdersReturn.class);
    // Verify that the example data was unmarshalled correctly
    Map<Long, WEXOrder> rv = result.getReturnValue();
    assertThat(rv.keySet()).containsAll(Arrays.asList(343152L));
    assertThat(rv.get(343152L).getTimestampCreated()).isEqualTo(1342448420L);
  }

  @Test
  public void testOwnTransactions() throws IOException {

    WEXTradeHistoryReturn result = getResult("/v3/trade/example-trade-history-data.json", WEXTradeHistoryReturn.class);
    // Verify that the example data was unmarshalled correctly
    Map<Long, WEXTradeHistoryResult> rv = result.getReturnValue();
    assertThat(rv.keySet()).containsAll(Arrays.asList(7258275L, 7160193L));

    WEXTradeHistoryResult trade = rv.get(7258275L);
    assertThat(trade.getPair()).isEqualTo("btc_usd");
    assertThat(trade.getType()).isEqualTo(Type.sell);
    assertThat(trade.getAmount()).isEqualTo(new BigDecimal("0.1"));
    assertThat(trade.getOrderId()).isEqualTo(34870919L);
    assertThat(trade.isYourOrder()).isEqualTo(false);
    assertThat(trade.getTimestamp()).isEqualTo(1378194574L);
  }

  @Test
  public void testCancelOrder() throws IOException {

    WEXCancelOrderReturn result = getResult("/v3/trade/example-cancel-order-data.json", WEXCancelOrderReturn.class);
    // Verify that the example data was unmarshalled correctly
    WEXCancelOrderResult rv = result.getReturnValue();
    Map<String, BigDecimal> funds = rv.getFunds();
    assertThat(funds.keySet().containsAll(Arrays.asList("btc", "nmc", "usd"))).isTrue();
    assertThat(funds.get("usd")).isEqualTo(new BigDecimal(325));
    assertThat(rv.getOrderId()).isEqualTo(343154L);
  }

  @Test
  public void testPlaceOrder() throws IOException {

    WEXPlaceOrderReturn result = getResult("/v3/trade/example-place-order-data.json", WEXPlaceOrderReturn.class);
    // Verify that the example data was unmarshalled correctly
    WEXPlaceOrderResult rv = result.getReturnValue();
    Map<String, BigDecimal> funds = rv.getFunds();
    assertThat(funds.keySet().containsAll(Arrays.asList("btc", "nmc", "usd"))).isTrue();
    assertThat(funds.get("btc")).isEqualTo(new BigDecimal("2.498"));
    assertThat(rv.getOrderId()).isEqualTo(0L);
  }

  private <RC extends WEXReturn> RC getResult(String file, Class<RC> resultClass) throws IOException {

    // Read in the JSON from the example resources
    InputStream is = WEXTradeDataJSONTest.class.getResourceAsStream(file);

    // Use Jackson to parse it
    return new ObjectMapper().readValue(is, resultClass);
  }
}
