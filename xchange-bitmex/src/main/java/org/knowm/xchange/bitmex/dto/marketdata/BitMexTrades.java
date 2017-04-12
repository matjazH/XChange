package org.knowm.xchange.bitmex.dto.marketdata;

import java.util.List;
import java.util.Iterator;
import java.util.Map.Entry;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.knowm.xchange.bitmex.dto.marketdata.BitMexTrades.YoBitTradesDeserializer;

@JsonDeserialize(using = YoBitTradesDeserializer.class)
public class BitMexTrades {
  private List<BitMexTrade> trades;

  public List<BitMexTrade> getTrades() {
    return trades;
  }

  public BitMexTrades(List<BitMexTrade> trades) {
    super();
    this.trades = trades;
  }

  static class YoBitTradesDeserializer extends JsonDeserializer<BitMexTrades> {

    private List<BitMexTrade> trades = new ArrayList<>();

    @Override
    public BitMexTrades deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {

      ObjectCodec oc = p.getCodec();
      JsonNode node = oc.readTree(p);

      if (node.isObject()) {
        Iterator<Entry<String, JsonNode>> priceEntryIter = node.fields();
        while (priceEntryIter.hasNext()) {
          Entry<String, JsonNode> priceEntryNode = priceEntryIter.next();

          JsonNode priceNode = priceEntryNode.getValue();

          if (priceNode.isArray()) {
            for (JsonNode jsonNode : priceNode) {
              ObjectMapper jsonObjectMapper = new ObjectMapper();
              BitMexTrade yoBitTrade = jsonObjectMapper.convertValue(jsonNode, BitMexTrade.class);
              trades.add(yoBitTrade);
            }
          }
        }
      }

      return new BitMexTrades(trades);
    }
  }
}
