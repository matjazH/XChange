package org.knowm.xchange.itbit.v1.dto.marketdata;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItBitTrade {

  private final BigDecimal amount;
  private final String timestamp;
  private final BigDecimal price;
  private final Long matchNumber;

  /*
  {"timestamp":"2016-06-03T08:57:57.5730000Z","matchNumber":682570,"price":"552.03000000","amount":"72.80640000"}
   */
  public ItBitTrade(@JsonProperty("amount") BigDecimal amount, @JsonProperty("date") String timestamp, @JsonProperty("price") BigDecimal price,
      @JsonProperty("tid") Long matchNumber) {

    this.amount = amount;
    this.timestamp = timestamp;
    this.price = price;
    this.matchNumber = matchNumber;
  }

  public BigDecimal getAmount() {

    return amount;
  }

  public String getTimestamp() {

    return timestamp;
  }

  public BigDecimal getPrice() {

    return price;
  }

  public long getMatchNumber() {

    return matchNumber;
  }

  @Override
  public String toString() {

    StringBuilder builder = new StringBuilder();
    builder.append("ItBitTrade [amount=");
    builder.append(amount);
    builder.append(", timestamp=");
    builder.append(timestamp);
    builder.append(", price=");
    builder.append(price);
    builder.append(", matchNumber=");
    builder.append(matchNumber);
    builder.append("]");
    return builder.toString();
  }
}
