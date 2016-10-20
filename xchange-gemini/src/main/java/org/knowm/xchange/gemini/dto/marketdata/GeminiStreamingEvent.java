package org.knowm.xchange.gemini.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by tabtrader on 03/10/2016.
 */
public class GeminiStreamingEvent {

  /*
  COMMON FIELDS
  type	string	Either trade, change, or auction.

  Change event

  price	decimal	The price of this order book entry.
  side	string	Either bid or ask.
  reason	string	Either place, trade, cancel, or initial, to indicate why the change has occurred. initial is for the initial response message, which will show the entire existing state of the order book.
  remaining	decimal	The quantity remaining at that price level after this change occurred. May be zero if all orders at this price level have been filled or canceled.
  delta	decimal	The quantity changed. May be negative, if an order is filled or canceled. For initial messages, delta will equal remaining.

  Trade event

  price	decimal	The price this trade executed at.
  amount	decimal	The amount traded.
  makerSide	string	The side of the book the maker of the trade placed their order on, of if the trade occurred in an auction. Either bid, ask, or auction.

   */

  private Type type;
  private BigDecimal price;
  private Side side;
  private ChangeReason reason;
  private BigDecimal remaining;
  private BigDecimal delta;
  private BigDecimal amount;
  private MakerSide makerSide;

  public GeminiStreamingEvent(@JsonProperty("type") Type type,
                              @JsonProperty("price") BigDecimal price,
                              @JsonProperty("side") Side side,
                              @JsonProperty("reason") ChangeReason reason,
                              @JsonProperty("remaining") BigDecimal remaining,
                              @JsonProperty("delta") BigDecimal delta,
                              @JsonProperty("amount") BigDecimal amount,
                              @JsonProperty("makerSide") MakerSide makerSide) {
    this.type = type;
    this.price = price;
    this.side = side;
    this.reason = reason;
    this.remaining = remaining;
    this.delta = delta;
    this.amount = amount;
    this.makerSide = makerSide;
  }

  public Type getType() {
    return type;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public Side getSide() {
    return side;
  }

  public ChangeReason getReason() {
    return reason;
  }

  public BigDecimal getRemaining() {
    return remaining;
  }

  public BigDecimal getDelta() {
    return delta;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public MakerSide getMakerSide() {
    return makerSide;
  }

  @Override
  public String toString() {
    return "GeminiStreamingEvent{" +
        "type=" + type +
        ", price=" + price +
        ", side=" + side +
        ", reason=" + reason +
        ", remaining=" + remaining +
        ", delta=" + delta +
        ", amount=" + amount +
        ", makerSide=" + makerSide +
        '}';
  }

  public enum Type {
    change,
    trade,
    auction_open,
    auction_indicative,
    auction_result
  }

  public enum Side {
    bid,
    ask
  }

  public enum MakerSide {
    bid,
    ask,
    auction
  }

  public enum ChangeReason {
    place,
    trade,
    cancel,
    initial
  }
}
