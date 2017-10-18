package org.knowm.xchange.therock.dto.trade;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

import org.knowm.xchange.therock.dto.marketdata.TheRockTrade;

public class TheRockUserTrade {
  private final long id;
  private final String fundId;
  private final BigDecimal amount;
  private final Date date;
  private final BigDecimal price;
  private final TheRockTrade.Side side;
  private final long orderId;
  private final TheRockUserTradeTransaction feeTransaction;

  public TheRockUserTrade(@JsonProperty("id") long id, @JsonProperty("fund_id") String fundId, @JsonProperty("amount") BigDecimal amount, @JsonProperty("price") BigDecimal price, @JsonProperty("date") Date date, @JsonProperty("side") TheRockTrade.Side tradeSide, @JsonProperty("order_id") long orderId, @JsonProperty("transactions") TheRockUserTradeTransaction[] transactions) {
    this.id = id;
    this.fundId = fundId;
    this.amount = amount;
    this.price = price;
    this.date = date;
    this.side = tradeSide;
    this.orderId = orderId;

    TheRockUserTradeTransaction ft = null;
    for (TheRockUserTradeTransaction t : transactions) {
      if (t.type == TransactionType.paid_commission) {
        ft = t;
        break;
      }
    }
    this.feeTransaction = ft;
  }

  public long getId() {
    return this.id;
  }

  public String getFundId() {
    return this.fundId;
  }

  public BigDecimal getAmount() {
    return this.amount;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public Date getDate() {
    return this.date;
  }

  public TheRockTrade.Side getSide() {
    return this.side;
  }

  public long getOrderId() {
    return this.orderId;
  }

  public BigDecimal getFeeAmount() {
    return this.feeTransaction == null ? BigDecimal.ZERO : this.feeTransaction.price;
  }

  public String getFeeCurrency() {
    return this.feeTransaction == null ? null : this.feeTransaction.currency;
  }


  public String toString() {
    return "TheRockTrade [amount=" + this.amount + ", date=" + this.date + ", price=" + this.price + ", id=" + this.id + ", side=" + this.side + "]";
  }


  private static class TheRockUserTradeTransaction {
    private final long id;

    private final Date date;
    private final TheRockUserTrade.TransactionType type;
    private final BigDecimal price;
    private final String currency;

    public TheRockUserTradeTransaction(@JsonProperty("id") long id, @JsonProperty("date") Date date, @JsonProperty("type") TheRockUserTrade.TransactionType type, @JsonProperty("price") BigDecimal price, @JsonProperty("currency") String currency) {

      this.id = id;

      this.date = date;

      this.type = type;

      this.price = price;

      this.currency = currency;
    }
  }

  private static enum TransactionType {
    sold_currency_to_fund, released_currency_to_fund, paid_commission, bought_currency_from_fund, acquired_currency_from_fund, unknown;

    private TransactionType() {
    }

    @JsonCreator
    public static TransactionType fromString(String string) {
      try {
        return valueOf(string);
      } catch (Throwable e) {
      }

      return unknown;
    }
  }
}