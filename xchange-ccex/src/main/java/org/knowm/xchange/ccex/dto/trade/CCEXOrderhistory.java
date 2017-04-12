package org.knowm.xchange.ccex.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CCEXOrderhistory {

  private String Exchange;
  private String OrderUuid;
  private String TimeStamp;
  private String ConditionTarget;
  private BigDecimal PricePerUnit;
  private boolean ImmediateOrCancel;
  private String OrderType;
  private BigDecimal Limit;
  private BigDecimal Price;
  private String Condition;
  private BigDecimal Quantity;
  private BigDecimal Commission;
  private boolean IsConditional;
  private BigDecimal QuantityRemaining;

  public CCEXOrderhistory(
      @JsonProperty("OrderUuid") String orderUuid,
      @JsonProperty("Exchange") String exchange,
      @JsonProperty("TimeStamp") String timeStamp,
      @JsonProperty("OrderType") String orderType,
      @JsonProperty("Limit") BigDecimal limit,
      @JsonProperty("Quantity") BigDecimal quantity,
      @JsonProperty("QuantityRemaining") BigDecimal quantityRemaining,
      @JsonProperty("Commission") BigDecimal commission,
      @JsonProperty("Price") BigDecimal price,
      @JsonProperty("PricePerUnit") BigDecimal pricePerUnit,
      @JsonProperty("IsConditional") boolean isConditional,
      @JsonProperty("Condition") String condition,
      @JsonProperty("ConditionTarget") String conditionTarget,
      @JsonProperty("ImmediateOrCancel") boolean immediateOrCancel
  ) {
    super();
    Limit = limit;
    Price = price;
    Quantity = quantity;
    Exchange = exchange;
    Commission = commission;
    PricePerUnit = pricePerUnit;
    IsConditional = isConditional;
    ConditionTarget = conditionTarget;
    OrderUuid = orderUuid;
    TimeStamp = timeStamp;
    OrderType = orderType;
    Condition = condition;
    QuantityRemaining = quantityRemaining;
    ImmediateOrCancel = immediateOrCancel;
  }

  public String getExchange() {
    return Exchange;
  }

  public String getOrderUuid() {
    return OrderUuid;
  }

  public BigDecimal getQuantityRemaining() {
    return QuantityRemaining;
  }

  public void setExchange(String exchange) {
    Exchange = exchange;
  }

  public String getTimeStamp() {
    return TimeStamp;
  }

  public void setOrderUuid(String orderUuid) {
    OrderUuid = orderUuid;
  }

  public void setTimeStamp(String timeStamp) {
    TimeStamp = timeStamp;
  }

  public String getOrderType() {
    return OrderType;
  }

  public void setOrderType(String orderType) {
    OrderType = orderType;
  }

  public void setCondition(String condition) {
    Condition = condition;
  }

  public void setQuantity(BigDecimal quantity) {
    Quantity = quantity;
  }

  public void setCommission(BigDecimal commission) {
    Commission = commission;
  }

  public void setIsConditional(boolean isConditional) {
    IsConditional = isConditional;
  }

  public void setPricePerUnit(BigDecimal pricePerUnit) {
    PricePerUnit = pricePerUnit;
  }

  public void setConditionTarget(String conditionTarget) {
    ConditionTarget = conditionTarget;
  }

  public void setImmediateOrCancel(boolean immediateOrCancel) {
    ImmediateOrCancel = immediateOrCancel;
  }

  public BigDecimal getLimit() {
    return Limit;
  }

  public BigDecimal getPrice() {
    return Price;
  }

  public String getCondition() {
    return Condition;
  }

  public BigDecimal getQuantity() {
    return Quantity;
  }

  public boolean isIsConditional() {
    return IsConditional;
  }

  public BigDecimal getCommission() {
    return Commission;
  }

  public String getConditionTarget() {
    return ConditionTarget;
  }

  public BigDecimal getPricePerUnit() {
    return PricePerUnit;
  }

  public boolean isImmediateOrCancel() {
    return ImmediateOrCancel;
  }

  public void setPrice(BigDecimal price) {
    Price = price;
  }

  public void setLimit(BigDecimal limit) {
    Limit = limit;
  }

  public void setQuantityRemaining(BigDecimal quantityRemaining) {
    QuantityRemaining = quantityRemaining;
  }

  @Override
  public String toString() {
    return "CCEXOrderhistory [OrderUuid=" + OrderUuid + ", Exchange=" + Exchange + ", TimeStamp=" + TimeStamp
        + ", OrderType=" + OrderType + ", Limit=" + Limit + ", Quantity=" + Quantity + ", QuantityRemaining="
        + QuantityRemaining + ", Commission=" + Commission + ", Price=" + Price + ", PricePerUnit="
        + PricePerUnit + ", IsConditional=" + IsConditional + ", Condition="+Condition + ", ConditionTarget="
        + ConditionTarget + ", ImmediateOrCancel=" + ImmediateOrCancel + "]";
  }
}
