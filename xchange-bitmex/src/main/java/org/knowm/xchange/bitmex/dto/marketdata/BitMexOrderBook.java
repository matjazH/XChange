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

import org.knowm.xchange.bitmex.dto.marketdata.BitMexOrderBook.YoBitOrderBookDeserializer;

@JsonDeserialize(using = YoBitOrderBookDeserializer.class)
public class BitMexOrderBook {
  private List<BitMexAsksBidsData> asks;
  private List<BitMexAsksBidsData> bids;

  public BitMexOrderBook(List<BitMexAsksBidsData> asks, List<BitMexAsksBidsData> bids) {
    this.asks = asks;
    this.bids = bids;
  }

  public List<BitMexAsksBidsData> getAsks() {
    return asks;
  }

  public List<BitMexAsksBidsData> getBids() {
    return bids;
  }

  static class YoBitOrderBookDeserializer extends JsonDeserializer<BitMexOrderBook> {

    private List<BitMexAsksBidsData> asks = new ArrayList<BitMexAsksBidsData>();
    private List<BitMexAsksBidsData> bids = new ArrayList<BitMexAsksBidsData>();

    @Override
    public BitMexOrderBook deserialize(JsonParser jp, DeserializationContext ctxt)
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
                    asks.add(new BitMexAsksBidsData(BigDecimal.valueOf(arrNode.get(1).asDouble()),
                        BigDecimal.valueOf(arrNode.get(0).asDouble())));
                  } else {
                    bids.add(new BitMexAsksBidsData(BigDecimal.valueOf(arrNode.get(1).asDouble()),
                        BigDecimal.valueOf(arrNode.get(0).asDouble())));
                  }
                }
              }
            }
          }
        }
      }

      return new BitMexOrderBook(asks, bids);
    }
  }

}
