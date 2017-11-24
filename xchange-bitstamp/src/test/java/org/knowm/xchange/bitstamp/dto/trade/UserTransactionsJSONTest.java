package org.knowm.xchange.bitstamp.dto.trade;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.knowm.xchange.bitstamp.dto.trade.BitstampUserTransaction.TransactionType;

/**
 * Test UserTransactions[] JSON parsing
 */
public class UserTransactionsJSONTest {

  @Test
  public void testUnmarshal() throws IOException {

    // Read in the JSON from the example resources
    InputStream is = UserTransactionsJSONTest.class.getResourceAsStream("/trade/example-user-transactions.json");

    // Use Jackson to parse it
    ObjectMapper mapper = new ObjectMapper();
    BitstampUserTransaction[] transactions = mapper.readValue(is, BitstampUserTransaction[].class);

    assertThat(transactions.length).isEqualTo(4);
  }
}
