package org.knowm.xchange.therock.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;


public class TheRockTransaction {
  private final long id;
  private final Date date;
  private final String type;
  private final BigDecimal price;
  private final String currency;
  private final Long orderId;
  private final Long tradeId;
  private final TransferDetail transferDetail;

  public TheRockTransaction(@JsonProperty("id") long id, @JsonProperty("date") Date date, @JsonProperty("type") String type, @JsonProperty("price") BigDecimal price, @JsonProperty("currency") String currency, @JsonProperty("order_id") Long orderId, @JsonProperty("trade_id") Long tradeId, @JsonProperty("transfer_detail") TransferDetail transferDetail) {
    this.id = id;
    this.date = date;
    this.type = type;
    this.price = price;
    this.currency = currency;
    this.orderId = orderId;
    this.tradeId = tradeId;
    this.transferDetail = transferDetail;
  }

  public long getId() {
    return this.id;
  }

  public Date getDate() {
    return this.date;
  }

  public String getType() {
    return this.type;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public String getCurrency() {
    return this.currency;
  }

  public long getOrderId() {
    return this.orderId.longValue();
  }

  public Long getTradeId() {
    return this.tradeId;
  }

  public TransferDetail getTransferDetail() {
    return this.transferDetail;
  }

  public String toString() {
    return "TheRockTransaction [id=" + this.id + ", date=" + this.date + ", type=" + this.type + ", price=" + this.price + ", currency=" + this.currency + ", orderId=" + this.orderId + ", tradeId=" + this.tradeId + ", transferDetail=" + this.transferDetail + "]";
  }

  public static class TransferDetail {
    private final String method;
    private final String id;
    private final String recipient;
    private final int confirmations;

    public TransferDetail(@JsonProperty("method") String method, @JsonProperty("id") String id, @JsonProperty("recipient") String recipient, @JsonProperty("confirmations") int confirmations) {
      this.method = method;
      this.id = id;
      this.recipient = recipient;
      this.confirmations = confirmations;
    }

    public String toString() {
      return "TransferDetail [method=" + this.method + ", id=" + this.id + ", recipient=" + this.recipient + ", confirmations=" + this.confirmations + "]";
    }


    public String getMethod() {
      return this.method;
    }

    public String getId() {
      return this.id;
    }

    public String getRecipient() {
      return this.recipient;
    }

    public int getConfirmations() {
      return this.confirmations;
    }
  }
}