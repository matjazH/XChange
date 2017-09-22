package org.knowm.xchange.ccex.dto.ticker;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.math.BigDecimal;
import org.knowm.xchange.ccex.dto.ticker.CCEXPrice.CCEXPriceDeserializer;

@JsonDeserialize(using = CCEXPriceDeserializer.class)
public class CCEXPrice {

  private final int updated;
  private final BigDecimal low;
  private final BigDecimal avg;
  private final BigDecimal buy;
  private final BigDecimal high;
  private final BigDecimal sell;
  private final BigDecimal lastbuy;
  private final BigDecimal lastsell;
  private final BigDecimal lastprice;

  private CCEXPrice(BigDecimal high, BigDecimal low, BigDecimal avg, BigDecimal lastbuy, BigDecimal lastsell,
                    BigDecimal buy, BigDecimal sell, BigDecimal lastprice, int updated) {

    this.low = low;
    this.avg = avg;
    this.buy = buy;
    this.high = high;
    this.sell = sell;
    this.updated = updated;
    this.lastbuy = lastbuy;
    this.lastsell = lastsell;
    this.lastprice = lastprice;

  }

  public int getUpdated() {
    return updated;
  }

  public BigDecimal getLow() {
    return low;
  }

  public BigDecimal getAvg() {
    return avg;
  }

  public BigDecimal getBuy() {
    return buy;
  }

  public BigDecimal getLastbuy() {
    return lastbuy;
  }

  public BigDecimal getSell() {
    return sell;
  }

  public BigDecimal getLastsell() {
    return lastsell;
  }

  public BigDecimal getHigh() {
    return high;
  }

  public BigDecimal getLastprice() {
    return lastprice;
  }

  static class CCEXPriceDeserializer extends JsonDeserializer<CCEXPrice> {
    private static BigDecimal getNumberIfPresent(JsonNode numberNode) {
      final String numberString = numberNode.asText();
      return numberString.isEmpty() ? null : new BigDecimal(numberString);
    }

    @Override
    public CCEXPrice deserialize(JsonParser jp,DeserializationContext ctxt) throws IOException,JsonProcessingException {

      final ObjectCodec oc = jp.getCodec();
      final JsonNode tickerNode = oc.readTree(jp);
      return deserializeFromNode(tickerNode);
    }

    public static CCEXPrice deserializeFromNode(JsonNode tickerNode) {

      final BigDecimal high = getNumberIfPresent(tickerNode.path("high"));
      final BigDecimal sell = getNumberIfPresent(tickerNode.path("sell"));
      final BigDecimal lastbuy = getNumberIfPresent(tickerNode.path("lastbuy"));
      final BigDecimal lastsell = getNumberIfPresent(tickerNode.path("lastsell"));
      final BigDecimal buy = getNumberIfPresent(tickerNode.path("buy"));
      final BigDecimal low = getNumberIfPresent(tickerNode.path("low"));
      final BigDecimal avg = getNumberIfPresent(tickerNode.path("avg"));
      final BigDecimal lastprice = getNumberIfPresent(tickerNode.path("lastprice"));
      final int updated = tickerNode.path("updated").asInt();

      return new CCEXPrice(high, low, avg, lastbuy, lastsell, buy, sell, lastprice, updated);
    }
  }

  @Override
  public String toString() {
    return "CCEXPrice [high=" + high + ", low=" + low + ", avg=" + avg + ", lastbuy="+lastbuy+ ", lastsell="
        + lastsell + ", buy=" + buy + ", sell=" + sell + ", lastprice=" + lastprice + ", updated=" + updated
        + "]";
  }
}
