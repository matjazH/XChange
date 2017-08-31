package org.knowm.xchange.btcup.service.params;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParamCurrencyPair;

import static org.knowm.xchange.btcup.service.BtcUpPollingMarketDataServiceRaw.FULL_LIMIT_SIZE;

/**
 * Created by VITTACH on 28/08/17.
 */
public class BtcUpHistoryParams implements TradeHistoryParamCurrencyPair {

  private Integer count;
  private CurrencyPair currencyPair;

  /**
   * Default constructor
   */
  public BtcUpHistoryParams() {

    this.count = FULL_LIMIT_SIZE;
    this.currencyPair = CurrencyPair.BTC_USD;
  }

  /**
   * Constructor
   *
   * @param currencyPair
   * @param count
   */
  public BtcUpHistoryParams(CurrencyPair currencyPair, Integer count) {

    this.currencyPair = currencyPair;
    this.count = count;
  }

  @Override
  public void setCurrencyPair(CurrencyPair currencyPair) {
    this.currencyPair = currencyPair;
  }

  @Override
  public CurrencyPair getCurrencyPair() {
    return currencyPair;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Integer getCount() {
    return count;
  }
}
