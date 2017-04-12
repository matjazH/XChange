package org.knowm.xchange.unocoin.dto.marketdata;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.knowm.xchange.unocoin.dto.marketdata.UnoCoinTrades.UnoCoinTradesDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

@JsonDeserialize(using = UnoCoinTradesDeserializer.class)
public class UnoCoinTrades {
  private List<UnoCoinTrade> trades;

  public List<UnoCoinTrade> getTrades() {
    return trades;
  }

  public UnoCoinTrades(List<UnoCoinTrade> trades) {
    super();
    this.trades = trades;
  }

  static class UnoCoinTradesDeserializer extends JsonDeserializer<UnoCoinTrades> {

    private List<UnoCoinTrade> trades = new ArrayList<>();

    @Override
    public UnoCoinTrades deserialize(JsonParser p, DeserializationContext ctxt)
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
              UnoCoinTrade UnoCoinTrade = jsonObjectMapper.convertValue(jsonNode, UnoCoinTrade.class);
              trades.add(UnoCoinTrade);
            }
          }
        }
      }

      return new UnoCoinTrades(trades);
    }
  }
}
