package org.knowm.xchange.bitmex.dto.marketdata;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.io.IOException;

import org.knowm.xchange.bitmex.BitMexAdapters;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.bitmex.dto.marketdata.BitMexPair.YoBitPairDeserializer;
import org.knowm.xchange.bitmex.dto.marketdata.BitMexPairs.YoBitPricesDeserializer;

@JsonDeserialize(using = YoBitPricesDeserializer.class)
public class BitMexPairs {
  @Override
  public String toString() {
    return "BitMexPairs [pair=" + pair + "]";
  }

  private final Map<CurrencyPair, BitMexPair> pair;

  public Map<CurrencyPair, BitMexPair> getPrice() {
    return pair;
  }

  static class YoBitPricesDeserializer extends JsonDeserializer<BitMexPairs> {
    @Override
    public BitMexPairs deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {

      Map<CurrencyPair, BitMexPair> priceMap = new HashMap<CurrencyPair, BitMexPair>();
      ObjectCodec oc = jp.getCodec();
      JsonNode node = oc.readTree(jp);
      if (node.isObject()) {
        Iterator<Entry<String, JsonNode>> priceEntryIter = node.fields();
        while (priceEntryIter.hasNext()) {
          Entry<String, JsonNode> priceEntryNode = priceEntryIter.next();

          String pairString = priceEntryNode.getKey();
          CurrencyPair pair = BitMexAdapters.adaptCurrencyPair(pairString);

          JsonNode priceNode = priceEntryNode.getValue();
          BitMexPair price = YoBitPairDeserializer.deserializeFromNode(priceNode);

          priceMap.put(pair, price);
        }
      }
      return new BitMexPairs(priceMap);
    }
  }

  private BitMexPairs(Map<CurrencyPair, BitMexPair> pair) {
    this.pair = pair;
  }
}
