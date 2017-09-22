package org.knowm.xchange.yobit.dto.trade;

import org.knowm.xchange.dto.Order;
import org.knowm.xchange.currency.CurrencyPair;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by VITTACH on 31/03/17.
 */
public class YoBitActiveOrder extends Order {
  private BigDecimal rate;
  private OrderStatus status;

  public BigDecimal getRate() {
    return rate;
  }

  @Override
  public OrderStatus getStatus() {
    return status;
  }

  public YoBitActiveOrder(@JsonProperty("pair") String pair, @JsonProperty("type") String type,
                          @JsonProperty("amount") BigDecimal amount, @JsonProperty("rate") BigDecimal rate,
                          @JsonProperty("timestamp_created") BigDecimal timestampCreated, @JsonProperty("status") Integer status)
      throws ParseException {

    super(type.equals("sell") ? OrderType.ASK : OrderType.BID, amount, new CurrencyPair(pair.replace("_", "/")),
        "null", new Date(timestampCreated.longValue() * 1000L));

    this.rate = rate;
    this.status = OrderStatus.values()[status];
  }

  @Override
  public String toString() {
    return "YoBitActiveOrder {" + "pair=" + getCurrencyPair() + ", type=" + getType() + ", amount=" + getTradableAmount() + ", rate=" + rate + ", timestampCreated=" + getTimestamp() + ", status=" + status + '}';
  }
}