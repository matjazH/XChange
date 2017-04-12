package org.knowm.xchange.bitmex.dto.marketdata;

import java.util.List;
import java.util.Iterator;
import java.math.BigDecimal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.knowm.xchange.bitmex.dto.marketdata.YoBitOrderBook.YoBitOrderBookDeserializer;

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

    private List<YoBitAsksBidsData> asks = new ArrayList<YoBitAsksBidsData>();
    private List<YoBitAsksBidsData> bids = new ArrayList<YoBitAsksBidsData>();

    @Override
    public YoBitOrderBook deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {

      ObjectCodec oc = jp.getCodec();
      JsonNode node = oc.readTree(jp);

      if (node.isObject()) {
        Iterator<Entry<String, JsonNode>> priceEntryIter = node.fields();
        while (priceEntryIter.hasNext()) {
          Entry<String, JsonNode> priceEntryNode = priceEntryIter.next();

          JsonNode priceNode = priceEntryNode.getValue();

          if (priceNode.isObject()) {
            Iterator<Entry<String, JsonNode>> data = priceNode.fields();

            while (data.hasNext()) {

              Entry<String, JsonNode> tmp = data.next();

              Iterator<JsonNode> dd = tmp.getValue().elements();

              while (dd.hasNext()) {

                JsonNode arrNode = dd.next();

                if (arrNode.isArray()) {
                  if (tmp.getKey().equals("asks")) {
                    asks.add(new YoBitAsksBidsData(BigDecimal.valueOf(arrNode.get(1).asDouble()),
                        BigDecimal.valueOf(arrNode.get(0).asDouble())));
                  } else {
                    bids.add(new YoBitAsksBidsData(BigDecimal.valueOf(arrNode.get(1).asDouble()),
                        BigDecimal.valueOf(arrNode.get(0).asDouble())));
                  }
                }
              }
            }
          }
        }
      }

      return new YoBitOrderBook(asks, bids);
    }
  }

}
