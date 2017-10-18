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
 * Test WEXDepth JSON parsing
 */
public class WEXDepthJSONTest {

  @Test
  public void testUnmarshal() throws IOException {

    // Read in the JSON from the example resources
    InputStream is = WEXDepthJSONTest.class.getResourceAsStream("/v3/marketdata/example-depth-data.json");

    // Use Jackson to parse it
    ObjectMapper mapper = new ObjectMapper();
    WEXDepthWrapper bTCEDepthWrapper = mapper.readValue(is, WEXDepthWrapper.class);

    // Verify that the example data was unmarshalled correctly
    assertThat(bTCEDepthWrapper.getDepth(WEXAdapters.getPair(CurrencyPair.BTC_USD)).getAsks().get(0)[0]).isEqualTo(new BigDecimal("760.98"));
    assertThat(bTCEDepthWrapper.getDepth(WEXAdapters.getPair(CurrencyPair.BTC_USD)).getAsks()).hasSize(30);
  }
}
