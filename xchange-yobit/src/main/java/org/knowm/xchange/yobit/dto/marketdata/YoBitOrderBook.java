package org.knowm.xchange.yobit.dto.marketdata;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.knowm.xchange.yobit.dto.marketdata.YoBitOrderBook.YoBitOrderBookDeserializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@JsonDeserialize(using = YoBitOrderBookDeserializer.class)
public class YoBitOrderBook {

  private List<YoBitAsksBidsData> asks;
  private List<YoBitAsksBidsData> bids;

  public YoBitOrderBook(List<YoBitAsksBidsData> asks, List<YoBitAsksBidsData> bids) {
    this.asks = asks;
    this.bids = bids;
  }

  public List<YoBitAsksBidsData> getAsks() {
    return asks;
  }

  public List<YoBitAsksBidsData> getBids() {
    return bids;
  }

  static class YoBitOrderBookDeserializer extends JsonDeserializer<YoBitOrderBook> {

    @Override
    public YoBitOrderBook deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

      List<YoBitAsksBidsData> askList = new ArrayList<>();
      List<YoBitAsksBidsData> bidList = new ArrayList<>();

      ObjectCodec oc = jp.getCodec();
      JsonNode node = oc.readTree(jp);

      Iterator<Map.Entry<String, JsonNode>> pricesEntry = node.fields();
      if (pricesEntry.hasNext()) {
        JsonNode priceNode = pricesEntry.next().getValue();
        Iterator<Map.Entry<String, JsonNode>> data = priceNode.fields();

        while (data.hasNext()) {
          Map.Entry<String, JsonNode> tmp = data.next();
          Iterator<JsonNode> dd = tmp.getValue().elements();

          while (dd.hasNext()) {
            JsonNode arrNode = dd.next();
            if (tmp.getKey().equals("asks")) {
              askList.add(new YoBitAsksBidsData(BigDecimal.valueOf(arrNode.get(1).asDouble()),
                  BigDecimal.valueOf(arrNode.get(0).asDouble())));
            } else {
              bidList.add(new YoBitAsksBidsData(BigDecimal.valueOf(arrNode.get(1).asDouble()),
                  BigDecimal.valueOf(arrNode.get(0).asDouble())));
            }
          }
        }
      }

      return new YoBitOrderBook(askList, bidList);
    }
  }
}