package org.knowm.xchange.btce.v3.service.polling.trade.params;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.polling.trade.params.TradeHistoryParamCurrencyPair;

/**
 * @author Peter N. Steinmetz Date: 4/2/15 Time: 6:54 PM
 */
public class WEXTradeHistoryParams extends WEXTransHistoryParams implements TradeHistoryParamCurrencyPair {

  private CurrencyPair pair;

  @Override
  public void setCurrencyPair(CurrencyPair pair) {

    this.pair = pair;
  }

  @Override
  public CurrencyPair getCurrencyPair() {

    return pair;
  }

}
