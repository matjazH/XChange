package org.knowm.xchange.bl3p.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.bl3p.dto.Bl3pValue;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 19/08/15
 * Time: 14:10
 */
public class Bl3pOrderTrade {


  private Bl3pValue amount;
  private String currency;
  private Long timestamp;
  private String item;
  private Bl3pValue price;
  private Long tradeId;

  public Bl3pOrderTrade(@JsonProperty("amount") Bl3pValue amount,
                        @JsonProperty("currency") String currency,
                        @JsonProperty("date") Long timestamp,
                        @JsonProperty("item") String item,
                        @JsonProperty("price") Bl3pValue price,
                        @JsonProperty("trade_id") Long tradeId) {
    this.amount = amount;
    this.currency = currency;
    this.timestamp = timestamp;
    this.item = item;
    this.price = price;
    this.tradeId = tradeId;
  }

  public Bl3pValue getAmount() {
    return amount;
  }

  public String getCurrency() {
    return currency;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public String getItem() {
    return item;
  }

  public Bl3pValue getPrice() {
    return price;
  }

  public Long getTradeId() {
    return tradeId;
  }
}
