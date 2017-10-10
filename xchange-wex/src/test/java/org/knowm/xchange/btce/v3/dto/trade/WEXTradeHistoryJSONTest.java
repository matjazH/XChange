package org.knowm.xchange.btce.v3.dto.trade;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Benedikt Bünz Test WEXTradeHistoryReturn JSON parsing
 */
public class WEXTradeHistoryJSONTest {

  @Test
  public void testUnmarshal() throws IOException {

    // Read in the JSON from the example resources
    InputStream is = WEXTradeHistoryJSONTest.class.getResourceAsStream("/v3/trade/example-trade-history-data.json");

    // Use Jackson to parse it
    ObjectMapper mapper = new ObjectMapper();
    WEXTradeHistoryReturn transactions = mapper.readValue(is, WEXTradeHistoryReturn.class);
    Map<Long, WEXTradeHistoryResult> result = transactions.getReturnValue();
    assertThat(result.size()).isEqualTo(32);
    Entry<Long, WEXTradeHistoryResult> firstEntry = result.entrySet().iterator().next();
    // Verify that the example data was unmarshalled correctly
    assertThat(firstEntry.getKey()).isEqualTo(7258275L);
    assertThat(firstEntry.getValue().getAmount()).isEqualTo(new BigDecimal("0.1"));
    assertThat(firstEntry.getValue().getOrderId()).isEqualTo(34870919L);
    assertThat(firstEntry.getValue().getPair()).isEqualTo("btc_usd");
    assertThat(firstEntry.getValue().getRate()).isEqualTo(new BigDecimal("125.75"));
    assertThat(firstEntry.getValue().getTimestamp()).isEqualTo(1378194574L);
    assertThat(firstEntry.getValue().getType()).isEqualTo(WEXTradeHistoryResult.Type.sell);
    assertThat(firstEntry.getValue().isYourOrder()).isEqualTo(false);

  }
}
