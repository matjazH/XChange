package org.knowm.xchange.gemini.service.polling;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.polling.trade.params.DefaultTradeHistoryParamCurrencyPair;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParamCurrencyPair;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParams;

/**
 * Created by tabtrader on 05/10/2016.
 */
public class GeminiTradeHistoryParams implements TradeHistoryParamCurrencyPair {

  private CurrencyPair currencyPair;
  private Long limit;
  private Long since;

  public GeminiTradeHistoryParams() {
  }

  public GeminiTradeHistoryParams(CurrencyPair currencyPair) {
    this.currencyPair = currencyPair;
  }

  @Override
  public void setCurrencyPair(CurrencyPair currencyPair) {
    this.currencyPair = currencyPair;
  }

  @Override
  public CurrencyPair getCurrencyPair() {
    return currencyPair;
  }

  public Long getLimit() {
    return limit;
  }

  public void setLimit(Long limit) {
    this.limit = limit;
  }

  public Long getSince() {
    return since;
  }

  public void setSince(Long since) {
    this.since = since;
  }
}
