package org.knowm.xchange.hitbtc.dto.marketdata;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author kpysniak
 */
public class HitbtcTrade {

  private Date date = null;
  private final BigDecimal price;
  private final BigDecimal amount;
  private final String tid;
  private final HitbtcTradeSide side;

  /**
   * Constructor
   *
   * @param date
   * @param price
   * @param amount
   * @param tid
   * @param side
   */
  public HitbtcTrade(@JsonProperty("timestamp") String date, @JsonProperty("price") BigDecimal price,
                     @JsonProperty("quantity") BigDecimal amount, @JsonProperty("id") String tid,
                     @JsonProperty("side") HitbtcTradeSide side) {

    SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    try {
      this.date = parser.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    this.price = price;
    this.amount = amount;
    this.tid = tid;
    this.side = side;
  }

  public long getDate() {

    return date.getTime();
  }

  public BigDecimal getPrice() {

    return price;
  }

  public BigDecimal getAmount() {

    return amount;
  }

  public String getTid() {

    return tid;
  }

  public HitbtcTradeSide getSide() {

    return side;
  }

  @Override
  public String toString() {

    return "HitbtcTrade{" + "date=" + date + ", price=" + price + ", amount=" + amount + ", tid='" + tid + "', side='" + side + "'" + "}";
  }

  public static enum HitbtcTradeSide {

    BUY("buy"), SELL("sell");

    private final String hitbtcTradeSide;

    HitbtcTradeSide(String hitbtcTradeSide) {

      this.hitbtcTradeSide = hitbtcTradeSide;
    }

    @Override
    @JsonValue
    public String toString() {

      return hitbtcTradeSide;
    }
  }
}
