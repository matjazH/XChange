package org.knowm.xchange.bitstamp.dto.marketdata;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

//This is to deal with an inconsistency in Bitstamp's APIs, where JsonProperty for
//trade ID is "id" when polling and "tid" when streaming.
public class BitstampStreamingTransaction extends BitstampTransaction {
  public BitstampStreamingTransaction(@JsonProperty("type") int type, @JsonProperty("date") long date, @JsonProperty("id") int tid, @JsonProperty("price") BigDecimal price,
      @JsonProperty("amount") BigDecimal amount) {
    super(type, date, tid, price, amount);
  }
}
