package org.knowm.xchange.okcoin.dto.trade;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;

public class OkCoinOrder extends Order {

  private final long orderId;

  private final int status;

  private final String symbol;

  private final String type;

  private final BigDecimal amount;

  private final BigDecimal dealAmount;

  private final Date createDate;

  private final BigDecimal price;

  public OkCoinOrder(@JsonProperty("order_id") final long orderId, @JsonProperty("status") final int status,
      @JsonProperty("symbol") final String symbol, @JsonProperty("type") final String type, @JsonProperty("price") final BigDecimal price,
      @JsonProperty("amount") final BigDecimal amount, @JsonProperty("deal_amount") final BigDecimal dealAmount,
      @JsonProperty("create_date") final Date createDate) {

    super(type.equals("sell")? Order.OrderType.ASK: Order.OrderType.BID, amount,
        new CurrencyPair(symbol.replace("_", "/")), String.valueOf(orderId), createDate);
    this.orderId = orderId;
    this.status = status;
    this.symbol = symbol;
    this.type = type;
    this.amount = amount;
    this.dealAmount = dealAmount;
    this.price = price;
    this.createDate = createDate;
  }

  public long getOrderId() {

    return orderId;
  }

  public int getMyStatus() {

    return status;
  }

  public String getSymbol() {

    return symbol;
  }

  public String getMyType() {

    return type;
  }

  public BigDecimal getAmount() {

    return amount;
  }

  public BigDecimal getDealAmount() {

    return dealAmount;
  }

  public Date getCreateDate() {

    return createDate;
  }

  public BigDecimal getPrice() {

    return price;
  }
}
