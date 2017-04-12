package org.knowm.xchange.unocoin.dto.marketdata;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import org.knowm.xchange.currency.CurrencyPair;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.knowm.xchange.unocoin.UnoCoinAdapters;
import org.knowm.xchange.unocoin.dto.marketdata.UnoCoinPair.UnoCoinPairDeserializer;
import org.knowm.xchange.unocoin.dto.marketdata.UnoCoinPairs.UnoCoinPricesDeserializer;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.IOException;
import java.util.Map.Entry;

@JsonDeserialize(using = UnoCoinPricesDeserializer.class)
public class UnoCoinPairs {
  @Override
  public String toString() {
    return "UnoCoinPairs [pair=" + pair + "]";
  }

  private final Map<CurrencyPair, UnoCoinPair> pair;

  public Map<CurrencyPair, UnoCoinPair> getPrice() {
    return pair;
  }

  static class UnoCoinPricesDeserializer extends JsonDeserializer<UnoCoinPairs> {
    @Override
    public UnoCoinPairs deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {

      Map<CurrencyPair, UnoCoinPair> priceMap = new HashMap<CurrencyPair, UnoCoinPair>();
      ObjectCodec oc = jp.getCodec();
      JsonNode node = oc.readTree(jp);
      if (node.isObject()) {
        Iterator<Entry<String, JsonNode>> priceEntryIter = node.fields();
        while (priceEntryIter.hasNext()) {
          Entry<String, JsonNode> priceEntryNode = priceEntryIter.next();

          String pairString = priceEntryNode.getKey();
          CurrencyPair pair = UnoCoinAdapters.adaptCurrencyPair(pairString);

          JsonNode priceNode = priceEntryNode.getValue();
          UnoCoinPair price = UnoCoinPairDeserializer.deserializeFromNode(priceNode);

          priceMap.put(pair, price);
        }
      }
      return new UnoCoinPairs(priceMap);
    }
  }

  private UnoCoinPairs(Map<CurrencyPair, UnoCoinPair> pair) {
    this.pair = pair;
  }
}
