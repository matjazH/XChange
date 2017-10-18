package org.knowm.xchange.yobit.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.currency.CurrencyPair;

import java.util.ArrayList;
import java.util.List;

public class YoBitInfo {
  private List<CurrencyPair> pairsList = new ArrayList<>();
  private Long server_time;
  private YoBitPairs pairs;

  public YoBitInfo(
      @JsonProperty("server_time") Long server_time,
      @JsonProperty("pairs") YoBitPairs bitpairs) {
    super();
    this.pairs = bitpairs;
    this.server_time = server_time;
  }

  public List<CurrencyPair> getExchangeSymbols() {
    return new ArrayList<CurrencyPair>(pairs.getPrice().keySet());
  }

  public Long getServer_time() {
    return server_time;
  }

  public YoBitPairs getPairs() {
    return pairs;
  }

  @Override
  public String toString() {
    return "YoBitInfo [server_time=" + server_time + ", pairs=" + pairs + "]";
  }

}