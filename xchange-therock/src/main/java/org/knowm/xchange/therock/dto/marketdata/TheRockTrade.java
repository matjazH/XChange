package org.knowm.xchange.therock.dto.marketdata;

import java.math.BigDecimal;

public class TheRockTrade {
  private final BigDecimal amount;
  private final java.util.Date date;
  private final BigDecimal price;
  private final long id;
  private final Side side;

  public static enum Side {
    sell, buy, close_long, close_short;

    private Side() {
    }
  }

  public TheRockTrade(@com.fasterxml.jackson.annotation.JsonProperty("amount") BigDecimal amount, @com.fasterxml.jackson.annotation.JsonProperty("date") java.util.Date date, @com.fasterxml.jackson.annotation.JsonProperty("price") BigDecimal price, @com.fasterxml.jackson.annotation.JsonProperty("id") long id, @com.fasterxml.jackson.annotation.JsonProperty("side") Side tradeSide) {
    this.amount = amount;
    this.date = date;
    this.price = price;
    this.id = id;
    this.side = tradeSide;
  }

  public BigDecimal getAmount() {
    return this.amount;
  }

  public java.util.Date getDate() {
    return this.date;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public long getId() {
    return this.id;
  }

  public Side getSide() {
    return this.side;
  }

  public String toString() {
    return "TheRockTrade [amount=" + this.amount + ", date=" + this.date + ", price=" + this.price + ", id=" + this.id + ", side=" + this.side + "]";
  }
}