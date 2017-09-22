package org.knowm.xchange.yobit.dto.trade;

import org.knowm.xchange.dto.Order;
import org.knowm.xchange.currency.CurrencyPair;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.math.BigDecimal;
import java.text.ParseException;

/**
 * Created by developer on 22/05/17.
 */

public class YoBitTradesOrder extends Order {
  private BigDecimal rate;
  private String pair;
  private BigDecimal amount;
  private BigDecimal orderId;
  private BigDecimal timestampCreated;

  public BigDecimal getRate() {
    return rate;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public BigDecimal getOrderId() {
    return orderId;
  }

  public BigDecimal getTimestampCreated() {
    return timestampCreated;
  }

  public YoBitTradesOrder(@JsonProperty("pair") String pair, @JsonProperty("type") String type,
                          @JsonProperty("amount") BigDecimal amount, @JsonProperty("rate") BigDecimal rate,
                          @JsonProperty("order_id") BigDecimal orderId, @JsonProperty("timestamp") BigDecimal timestamp,
                          @JsonProperty("is_your_order") Integer isYourOrder)
      throws ParseException {

    super(type.equals("sell") ? OrderType.ASK : OrderType.BID, amount, new CurrencyPair(pair.replace("_", "/")),
        "null", new Date(timestamp.longValue() * 1000L));

    this.rate = rate;
    this.pair = pair;
    this.amount = amount;
    this.orderId = orderId;

    this.timestampCreated = timestamp;
  }
}