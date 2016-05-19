package com.xeiam.xchange.exmo.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xeiam.xchange.exmo.dto.ExmoResult;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 22/12/15
 * Time: 16:13
 */
public class ExmoTradesResult extends ExmoResult {

  public final List<ExmoTrade> trades;

  public ExmoTradesResult(@JsonProperty("result") boolean result,
                          @JsonProperty("error") String error,
                          @JsonProperty("orders") List<ExmoTrade> trades) {
    super(result, error);

    this.trades = trades;
  }

  public List<ExmoTrade> getTrades() {
    return trades;
  }
}
