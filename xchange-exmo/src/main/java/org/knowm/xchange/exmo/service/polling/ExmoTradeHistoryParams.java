package org.knowm.xchange.exmo.service.polling;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 08/04/16
 * Time: 12:41
 */
public class ExmoTradeHistoryParams implements TradeHistoryParams {

  private CurrencyPair[] pairs;
  private Integer limit;
  private Integer offset;

  public ExmoTradeHistoryParams() {
  }

  public ExmoTradeHistoryParams(CurrencyPair[] pairs, Integer limit, Integer offset) {
    this.pairs = pairs;
    this.limit = limit;
    this.offset = offset;
  }

  public ExmoTradeHistoryParams(CurrencyPair[] pairs) {
    this(pairs, null, null);
  }

  public ExmoTradeHistoryParams(CurrencyPair pair, Integer limit, Integer offset) {
    this(new CurrencyPair[]{pair}, limit, offset);
  }

  public ExmoTradeHistoryParams(CurrencyPair pair) {
    this(new CurrencyPair[]{pair});
  }

  public CurrencyPair[] getPairs() {
    return pairs;
  }

  public void setPairs(CurrencyPair[] pairs) {
    this.pairs = pairs;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }
}
