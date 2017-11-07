package org.knowm.xchange.gemini.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.gemini.dto.GeminiBaseRequest;

/**
 * Created by tabtrader on 05/10/2016.
 */
public class GeminiTradesRequest extends GeminiBaseRequest {

  @JsonProperty("symbol")
  private String symbol;

  @JsonProperty("limit_trades")
  private Long limitTrades;

  @JsonProperty("timestamp")
  private Long sinceTimestamp;

  public GeminiTradesRequest(String symbol) {
    this.symbol = symbol;
  }

  public GeminiTradesRequest(String symbol, Long limitTrades, Long sinceTimestamp) {
    this.symbol = symbol;
    this.limitTrades = limitTrades;
    this.sinceTimestamp = sinceTimestamp;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public Long getLimitTrades() {
    return limitTrades;
  }

  public void setLimitTrades(Long limitTrades) {
    this.limitTrades = limitTrades;
  }

  public Long getSinceTimestamp() {
    return sinceTimestamp;
  }

  public void setSinceTimestamp(Long sinceTimestamp) {
    this.sinceTimestamp = sinceTimestamp;
  }
}
