package org.knowm.xchange.bitmex.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.knowm.xchange.dto.Order;
import org.knowm.xchange.currency.CurrencyPair;

/**
 * Created by VITTACH on 31/03/17.
 */
public class YoBitorderInform extends Order {
  private BigDecimal rate;
  private OrderStatus stat;
  private BigDecimal startAmount;

  public BigDecimal getRate() {
    return rate;
  }

  @Override
  public OrderStatus getStatus() {
    return stat;
  }

  public BigDecimal getStartAmount() {
    return startAmount;
  }

  public YoBitorderInform(@JsonProperty("pair") String pair, @JsonProperty("type") String type,
                          @JsonProperty("start_amount") BigDecimal startAmount, @JsonProperty("amount") BigDecimal amount,
                          @JsonProperty("rate") BigDecimal rate, @JsonProperty("timestamp_created") BigDecimal timestampCreated,
                          @JsonProperty("status") Integer status)
      throws ParseException {

    super(type.equals("sell") ? OrderType.ASK : OrderType.BID, amount, new CurrencyPair(pair.replace("_", "/")),
        "null", new SimpleDateFormat("yyyyMMdd").parse(timestampCreated.toString()));

    this.stat = OrderStatus.values()[status];
    this.rate = rate;
    this.startAmount = startAmount;
  }

  @Override
  public String toString() {
    return "YoBitActiveOrder {" + "pair=" + getCurrencyPair() + ", type=" + getType() + ", startAmount=" + startAmount + ", amount=" + getTradableAmount() + ", rate=" + rate + ", timestampCreated=" + getTimestamp() + ", status=" + stat + '}';
  }
}