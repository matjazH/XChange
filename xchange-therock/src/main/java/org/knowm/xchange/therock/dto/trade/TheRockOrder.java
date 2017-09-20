package org.knowm.xchange.therock.dto.trade;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;

import org.knowm.xchange.therock.TheRock;


@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class TheRockOrder {
  private Long id;
  private TheRock.Pair fundId;
  private Side side;
  private Type type;
  private String status;
  private BigDecimal amount;
  private BigDecimal amountUnfilled;
  private BigDecimal price;
  private String conditionalType;
  private BigDecimal conditionalPrice;
  private String date;
  private String closeOn;
  private Boolean dark;
  private BigDecimal leverage;
  private Long positionId;

  protected TheRockOrder() {
  }

  public TheRockOrder(TheRock.Pair fundId, Side side, Type type, BigDecimal amount, BigDecimal price, Long id, String status, BigDecimal amountUnfilled, String conditionalType, BigDecimal conditionalPrice, String date, String closeOn, Boolean dark, BigDecimal leverage, Long positionId) {
    this.positionId = new Long(0L);
    this.dark = new Boolean(false);
    this.fundId = fundId;
    this.side = side;
    this.type = type;
    this.amount = amount;
    this.price = price;

    if (id != null) {
      this.id = id;
      this.status = status;
      this.amountUnfilled = amountUnfilled;
      this.conditionalType = conditionalType;
      this.conditionalPrice = conditionalPrice;
      this.date = date;
      this.closeOn = closeOn;
      this.dark = dark;
      this.leverage = leverage;
      this.positionId = positionId;
    }
  }

  public Long getId() {

    return this.id;
  }

  public TheRock.Pair getFundId() {

    return this.fundId;
  }

  public Side getSide() {

    return this.side;
  }

  public Type getType() {

    return this.type;
  }

  public String getStatus() {

    return this.status;
  }

  public BigDecimal getAmount() {

    return this.amount;
  }

  public BigDecimal getAmountUnfilled() {

    return this.amountUnfilled;
  }

  public BigDecimal getPrice() {

    return this.price;
  }

  public String getConditionalType() {

    return this.conditionalType;
  }

  public BigDecimal getConditionalPrice() {

    return this.conditionalPrice;
  }

  public String getDate() {

    return this.date;
  }

  public String getCloseOn() {

    return this.closeOn;
  }

  public boolean isDark() {

    return this.dark.booleanValue();
  }

  public BigDecimal getLeverage() {

    return this.leverage;
  }

  public long getPositionId() {

    return this.positionId.longValue();
  }

  public String toString() {

    return String.format("TheRockOrder{id=%d, side=%s, type=%s, amount=%s, amountUnfilled=%s, price=%s, fundId=%s, status='%s'}", new Object[]{this.id, this.side, this.type, this.amount, this.amountUnfilled, this.price, this.fundId, this.status});
  }

  public static enum Side {
    buy, sell;

    private Side() {
    }
  }

  public static enum Type {
    market, limit;

    private Type() {
    }
  }
}