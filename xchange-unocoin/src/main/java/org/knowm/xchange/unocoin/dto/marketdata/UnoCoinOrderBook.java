package org.knowm.xchange.unocoin.dto.marketdata;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.knowm.xchange.unocoin.dto.marketdata.UnoCoinOrderBook.UnoCoinOrderBookDeserializer;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.io.IOException;
import java.math.BigDecimal;

@JsonDeserialize(using = UnoCoinOrderBookDeserializer.class)
public class UnoCoinOrderBook {
  private List<UnoCoinAsksBidsData> asks;
  private List<UnoCoinAsksBidsData> bids;

  public UnoCoinOrderBook(List<UnoCoinAsksBidsData>asks,List<UnoCoinAsksBidsData>bids) {
    this.asks = asks;
    this.bids = bids;
  }

  public List<UnoCoinAsksBidsData> getAsks() {
    return asks;
  }

  public List<UnoCoinAsksBidsData> getBids() {
    return bids;
  }

  static class UnoCoinOrderBookDeserializer extends JsonDeserializer<UnoCoinOrderBook> {

    private List<UnoCoinAsksBidsData> asks = new ArrayList<UnoCoinAsksBidsData>();
    private List<UnoCoinAsksBidsData> bids = new ArrayList<UnoCoinAsksBidsData>();

    @Override
    public UnoCoinOrderBook deserialize(JsonParser jp,DeserializationContext ctxt)
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
                    asks.add(new UnoCoinAsksBidsData(BigDecimal.valueOf(arrNode.get(1).asDouble()),
                        BigDecimal.valueOf(arrNode.get(0).asDouble())));
                  } else {
                    bids.add(new UnoCoinAsksBidsData(BigDecimal.valueOf(arrNode.get(1).asDouble()),
                        BigDecimal.valueOf(arrNode.get(0).asDouble())));
                  }
                }
              }
            }
          }
        }
      }

      return new UnoCoinOrderBook(asks, bids);
    }
  }

}
