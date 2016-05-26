package org.knowm.xchange.bitbay.dto.trade;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 09/07/15
 * Time: 13:13
 */
public class BitbayTransaction {

  /*
  {
    "date":"2015-01-13 16:06:09",
    "type":"BID",
    "market":"LTC-PLN",
    "amount":"1.2221",
    "rate":"7.98000000",
    "price":"9.75"
  }
   */

  private String date;
  private String type;
  private String market;
  private BigDecimal amount;
  private BigDecimal rate;
  private BigDecimal price;

  public BitbayTransaction(@JsonProperty("date") String date,
                           @JsonProperty("type") String type,
                           @JsonProperty("market") String market,
                           @JsonProperty("amount") BigDecimal amount,
                           @JsonProperty("rate") BigDecimal rate,
                           @JsonProperty("price") BigDecimal price) {
    this.date = date;
    this.type = type;
    this.market = market;
    this.amount = amount;
    this.rate = rate;
    this.price = price;
  }

  public String getDate() {
    return date;
  }

  public String getType() {
    return type;
  }

  public String getMarket() {
    return market;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public BigDecimal getPrice() {
    return price;
  }

  @Override
  public String toString() {
    return "BitbayTransaction{" +
        "date=" + date +
        ", type='" + type + '\'' +
        ", market='" + market + '\'' +
        ", amount=" + amount +
        ", rate=" + rate +
        ", price=" + price +
        '}';
  }
}
