package org.knowm.xchange.exmo.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 11/07/16
 * Time: 16:17
 */
public class ExmoOrderTrades {

  /*
  {
  "type": "buy",
  "in_currency": "BTC",
  "in_amount": "1",
  "out_currency": "USD",
  "out_amount": "100",
  "trades": [
    {
      "trade_id": 3,
      "date": 1435488248,
      "type": "buy",
      "pair": "BTC_USD",
      "order_id": 12345,
      "quantity": 1,
      "price": 100,
      "amount": 100
    }
  ]
}
   */


  private String type;
  private String inCurrency;
  private BigDecimal inAmount;
  private String outCurrency;
  private BigDecimal outAmount;
  private List<Trade> trades = new ArrayList<Trade>();

  public ExmoOrderTrades(@JsonProperty("type") String type,
                         @JsonProperty("in_currency")String inCurrency,
                         @JsonProperty("in_amount") BigDecimal inAmount,
                         @JsonProperty("out_currency") String outCurrency,
                         @JsonProperty("out_amount") BigDecimal outAmount,
                         @JsonProperty("trades") List<Trade> trades) {
    this.type = type;
    this.inCurrency = inCurrency;
    this.inAmount = inAmount;
    this.outCurrency = outCurrency;
    this.outAmount = outAmount;
    this.trades = trades;
  }

  public String getType() {
    return type;
  }

  public String getInCurrency() {
    return inCurrency;
  }

  public BigDecimal getInAmount() {
    return inAmount;
  }

  public String getOutCurrency() {
    return outCurrency;
  }

  public BigDecimal getOutAmount() {
    return outAmount;
  }

  public List<Trade> getTrades() {
    return trades;
  }

  @Override
  public String toString() {
    return "ExmoOrderTrades{" +
        "type='" + type + '\'' +
        ", inCurrency='" + inCurrency + '\'' +
        ", inAmount=" + inAmount +
        ", outCurrency='" + outCurrency + '\'' +
        ", outAmount=" + outAmount +
        "}";
  }

  public static class Trade {

    private String tradeId;
    private Long date;
    private String type;
    private String pair;
    private String orderId;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal amount;

    public Trade(@JsonProperty("trade_id") String tradeId,
                 @JsonProperty("date") Long date,
                 @JsonProperty("type") String type,
                 @JsonProperty("pair") String pair,
                 @JsonProperty("order_id") String orderId,
                 @JsonProperty("quantity") BigDecimal quantity,
                 @JsonProperty("price") BigDecimal price,
                 @JsonProperty("amount") BigDecimal amount) {
      this.tradeId = tradeId;
      this.date = date;
      this.type = type;
      this.pair = pair;
      this.orderId = orderId;
      this.quantity = quantity;
      this.price = price;
      this.amount = amount;
    }

    public String getTradeId() {
      return tradeId;
    }

    public Long getDate() {
      return date;
    }

    public String getType() {
      return type;
    }

    public String getPair() {
      return pair;
    }

    public String getOrderId() {
      return orderId;
    }

    public BigDecimal getQuantity() {
      return quantity;
    }

    public BigDecimal getPrice() {
      return price;
    }

    public BigDecimal getAmount() {
      return amount;
    }

    @Override
    public String toString() {
      return "Trade{" +
          "tradeId='" + tradeId + '\'' +
          ", date=" + date +
          ", type='" + type + '\'' +
          ", pair='" + pair + '\'' +
          ", orderId='" + orderId + '\'' +
          ", quantity=" + quantity +
          ", price=" + price +
          ", amount=" + amount +
          '}';
    }
  }
}
