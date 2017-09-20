package org.knowm.xchange.exmo.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 17/12/15
 * Time: 16:53
 */
public class ExmoOrderbook {
  /*
  {"BTC_USD":{"ask_quantity":"356.76112524",
  "ask_amount":"188103.10935261",
  "ask_top":"444.4999",
  "bid_quantity":"32007.85537875",
  "bid_amount":"93408.65511056",
  "bid_top":"441.820011",
  "ask":[["444.4999","0.15143277","67.31185112"],
   */
  protected final BigDecimal askQuantity;
  protected final BigDecimal askAmount;
  protected final BigDecimal askTop;
  protected final BigDecimal bidQuantity;
  protected final BigDecimal bidAmount;
  protected final BigDecimal bidTop;
  protected final List<List<BigDecimal>> asks;
  protected final List<List<BigDecimal>> bids;

  public ExmoOrderbook(@JsonProperty("ask_quantity") BigDecimal askQuantity,
                       @JsonProperty("ask_amount") BigDecimal askAmount,
                       @JsonProperty("ask_top") BigDecimal askTop,
                       @JsonProperty("bid_quantity") BigDecimal bidQuantity,
                       @JsonProperty("bid_amount") BigDecimal bidAmount,
                       @JsonProperty("bid_top") BigDecimal bidTop,
                       @JsonProperty("ask") List<List<BigDecimal>> asks,
                       @JsonProperty("bid") List<List<BigDecimal>> bids)
  {
    this.askQuantity = askQuantity;
    this.askAmount = askAmount;
    this.askTop = askTop;
    this.bidQuantity = bidQuantity;
    this.bidAmount = bidAmount;
    this.bidTop = bidTop;
    this.asks = asks;
    this.bids = bids;
  }

  public BigDecimal getAskQuantity() {
    return askQuantity;
  }

  public BigDecimal getAskAmount() {
    return askAmount;
  }

  public BigDecimal getAskTop() {
    return askTop;
  }

  public BigDecimal getBidQuantity() {
    return bidQuantity;
  }

  public BigDecimal getBidAmount() {
    return bidAmount;
  }

  public BigDecimal getBidTop() {
    return bidTop;
  }

  public List<List<BigDecimal>> getAsks() {
    return asks;
  }

  public List<List<BigDecimal>> getBids() {
    return bids;
  }

  @Override
  public String toString() {
    return "ExmoOrderbook{" +
        "askQuantity=" + askQuantity +
        ", askAmount=" + askAmount +
        ", askTop=" + askTop +
        ", bidQuantity=" + bidQuantity +
        ", bidAmount=" + bidAmount +
        ", bidTop=" + bidTop +
        ", asks=" + asks +
        ", bids=" + bids +
        '}';
  }
}

