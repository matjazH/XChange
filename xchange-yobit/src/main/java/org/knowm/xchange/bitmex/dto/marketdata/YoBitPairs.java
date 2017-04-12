package org.knowm.xchange.bitmex.dto.marketdata;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.io.IOException;

import org.knowm.xchange.bitmex.YoBitAdapters;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.bitmex.dto.marketdata.YoBitPair.YoBitPairDeserializer;
import org.knowm.xchange.bitmex.dto.marketdata.YoBitPairs.YoBitPricesDeserializer;

@JsonDeserialize(using = YoBitPricesDeserializer.class)
public class YoBitPairs {
  @Override
  public String toString() {
    return "YoBitPairs [pair=" + pair + "]";
  }

  private final Map<CurrencyPair, YoBitPair> pair;

  public Map<CurrencyPair, YoBitPair> getPrice() {
    return pair;
  }

  static class YoBitPricesDeserializer extends JsonDeserializer<YoBitPairs> {
    @Override
    public YoBitPairs deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {

      Map<CurrencyPair, YoBitPair> priceMap = new HashMap<CurrencyPair, YoBitPair>();
      ObjectCodec oc = jp.getCodec();
      JsonNode node = oc.readTree(jp);
      if (node.isObject()) {
        Iterator<Entry<String, JsonNode>> priceEntryIter = node.fields();
        while (priceEntryIter.hasNext()) {
          Entry<String, JsonNode> priceEntryNode = priceEntryIter.next();

          String pairString = priceEntryNode.getKey();
          CurrencyPair pair = YoBitAdapters.adaptCurrencyPair(pairString);

          JsonNode priceNode = priceEntryNode.getValue();
          YoBitPair price = YoBitPairDeserializer.deserializeFromNode(priceNode);

          priceMap.put(pair, price);
        }
      }
      return new YoBitPairs(priceMap);
    }
  }

  private YoBitPairs(Map<CurrencyPair, YoBitPair> pair) {
    this.pair = pair;
  }
}
