package org.knowm.xchange.btce.v3.dto.marketdata;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.knowm.xchange.btce.v3.WEXAdapters;
import org.knowm.xchange.currency.CurrencyPair;

/**
 * Test WEXTrade[] JSON parsing
 */
public class WEXTradesJSONTest {

  @Test
  public void testUnmarshal() throws IOException {

    // Read in the JSON from the example resources
    InputStream is = WEXTradesJSONTest.class.getResourceAsStream("/v3/marketdata/example-trades-data.json");

    // Use Jackson to parse it
    ObjectMapper mapper = new ObjectMapper();
    WEXTradesWrapper bTCETradesWrapper = mapper.readValue(is, WEXTradesWrapper.class);

    // Verify that the example data was unmarshalled correctly
    assertThat(bTCETradesWrapper.getTrades(WEXAdapters.getPair(CurrencyPair.BTC_USD))[0].getPrice()).isEqualTo(new BigDecimal("758.5"));
    assertThat(bTCETradesWrapper.getTrades(WEXAdapters.getPair(CurrencyPair.BTC_USD)).length).isEqualTo(100);
  }
}
