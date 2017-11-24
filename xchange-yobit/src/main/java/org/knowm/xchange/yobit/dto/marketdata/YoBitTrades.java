package org.knowm.xchange.yobit.dto.marketdata;

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

import org.knowm.xchange.yobit.dto.marketdata.YoBitTrades.YoBitTradesDeserializer;

@JsonDeserialize(using = YoBitTradesDeserializer.class)
public class YoBitTrades {
  private List<YoBitTrade> trades;

  public List<YoBitTrade> getTrades() {
    return trades;
  }

  public YoBitTrades(List<YoBitTrade> trades) {
    super();
    this.trades = trades;
  }

  static class YoBitTradesDeserializer extends JsonDeserializer<YoBitTrades> {

    @Override
    public YoBitTrades deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

      ObjectCodec oc = p.getCodec();
      JsonNode node = oc.readTree(p);

      List<YoBitTrade> trades = new ArrayList<>();

      Iterator<Entry<String, JsonNode>> priceEntryIter = node.fields();
      while (priceEntryIter.hasNext()) {
        Entry<String, JsonNode> priceEntryNode = priceEntryIter.next();

        JsonNode priceNode = priceEntryNode.getValue();

        for (JsonNode jsonNode : priceNode) {
          ObjectMapper jsonObjectMapper = new ObjectMapper();
          YoBitTrade yoBitTrade = jsonObjectMapper.convertValue(jsonNode, YoBitTrade.class);
          trades.add(yoBitTrade);
        }
      }

      return new YoBitTrades(trades);
    }
  }
}
