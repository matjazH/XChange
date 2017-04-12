package org.knowm.xchange.ccex.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CCEXOpenorder {

  private String Opened;
  private String Closed;
  private String Exchange;
  private String Condition;
  private String OrderUuid;
  private String OrderType;
  private BigDecimal Limit;
  private BigDecimal Price;
  private BigDecimal Quantity;
  private boolean IsConditional;
  private String ConditionTarget;
  private BigDecimal PricePerUnit;
  private boolean CancelInitiated;
  private boolean ImmediateOrCancel;
  private BigDecimal CommissionPaid;
  private BigDecimal QuantityRemaining;

  public CCEXOpenorder(
      @JsonProperty("OrderUuid") String orderUuid,
      @JsonProperty("Exchange") String exchange,
      @JsonProperty("OrderType") String orderType,
      @JsonProperty("Quantity") BigDecimal quantity,
      @JsonProperty("QuantityRemaining") BigDecimal quantityRemaining,
      @JsonProperty("Limit") BigDecimal limit,
      @JsonProperty("CommissionPaid") BigDecimal commissionPaid,
      @JsonProperty("Price") BigDecimal price,
      @JsonProperty("PricePerUnit") BigDecimal pricePerUnit,
      @JsonProperty("Opened") String opened,
      @JsonProperty("Closed") String closed,
      @JsonProperty("CancelInitiated") boolean cancelInitiated,
      @JsonProperty("ImmediateOrCancel") boolean immediateOrCancel,
      @JsonProperty("IsConditional") boolean isConditional,
      @JsonProperty("Condition") String condition,
      @JsonProperty("ConditionTarget") String conditionTarget) {
    super();
    Limit = limit;
    Price = price;
    Opened = opened;
    Closed = closed;
    Exchange = exchange;
    Quantity = quantity;
    OrderUuid = orderUuid;
    OrderType = orderType;
    Condition = condition;
    PricePerUnit = pricePerUnit;
    IsConditional = isConditional;
    CommissionPaid = commissionPaid;
    CancelInitiated = cancelInitiated;
    ConditionTarget = conditionTarget;
    ImmediateOrCancel = immediateOrCancel;
    QuantityRemaining = quantityRemaining;
  }

  public String getExchange() {
    return Exchange;
  }

  public String getOrderUuid() {
    return OrderUuid;
  }

  public String getOrderType() {
    return OrderType;
  }

  public BigDecimal getQuantity() {
    return Quantity;
  }

  public BigDecimal getQuantityRemaining() {
    return QuantityRemaining;
  }

  public void setExchange(String exchange) {
    Exchange = exchange;
  }

  public void setOrderUuid(String orderUuid) {
    OrderUuid = orderUuid;
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

  public String getConditionTarget() {
    return ConditionTarget;
  }

  public void setQuantityRemaining(BigDecimal quantityRemaining) {
    QuantityRemaining = quantityRemaining;
  }

  public BigDecimal getLimit() {
    return Limit;
  }

  public BigDecimal getCommissionPaid() {
    return CommissionPaid;
  }

  public void setLimit(BigDecimal limit) {
    Limit = limit;
  }

  public void setPrice(BigDecimal price) {
    Price = price;
  }

  public void setCancelInitiated(boolean cancelInitiated) {
    CancelInitiated = cancelInitiated;
  }

  public void setCommissionPaid(BigDecimal commissionPaid){
    CommissionPaid = commissionPaid;
  }

  public BigDecimal getPrice() {
    return Price;
  }

  public boolean isCancelInitiated() {
    return CancelInitiated;
  }

  public BigDecimal getPricePerUnit() {
    return PricePerUnit;
  }

  public void setPricePerUnit(BigDecimal pricePerUnit) {
    PricePerUnit = pricePerUnit;
  }

  public String getOpened() {
    return Opened;
  }

  public String getClosed() {
    return Closed;
  }

  public void setOpened(String opened) {
    Opened = opened;
  }

  public void setClosed(String closed) {
    Closed = closed;
  }

  public boolean isImmediateOrCancel() {
    return ImmediateOrCancel;
  }

  public void setConditionTarget(String conditionTarget) {
    ConditionTarget = conditionTarget;
  }

  public boolean isIsConditional() {
    return IsConditional;
  }

  public void setIsConditional(boolean isConditional) {
    IsConditional = isConditional;
  }

  public String getCondition() {
    return Condition;
  }

  public void setImmediateOrCancel(boolean immediateOrCancel) {
    ImmediateOrCancel = immediateOrCancel;
  }

  @Override
  public String toString() {
    return "CCEXOpenorder [OrderUuid=" + OrderUuid + ", Exchange=" + Exchange + ", OrderType=" + OrderType
        + ", Quantity=" + Quantity + ", QuantityRemaining=" + QuantityRemaining + ", Limit=" + Limit
        + ", CommissionPaid=" + CommissionPaid + ", Price=" + Price + ", PricePerUnit=" + PricePerUnit
        + ", Opened=" + Opened + ", Closed=" + Closed + ", CancelInitiated=" + CancelInitiated
        + ", ImmediateOrCancel=" + ImmediateOrCancel + ", IsConditional=" + IsConditional + ", Condition="
        + Condition + ", ConditionTarget=" + ConditionTarget + "]";
  }
}
