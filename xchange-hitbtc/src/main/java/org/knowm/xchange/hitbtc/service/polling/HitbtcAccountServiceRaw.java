package org.knowm.xchange.hitbtc.service.polling;

import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.hitbtc.dto.HitbtcException;
import org.knowm.xchange.hitbtc.dto.account.HitbtcBalance;

public class HitbtcAccountServiceRaw extends HitbtcBasePollingService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public HitbtcAccountServiceRaw(Exchange exchange) {

    super(exchange);
  }

  public HitbtcBalance[] getWalletRaw() throws IOException {

    try {
      return hitbtc.getHitbtcBalance(signatureCreator);
    } catch (HitbtcException e) {
      handleException(e);
      return null;
    }
  }

  public String getDepositAddress(String currency) throws IOException {

    try {
      return hitbtc.getHitbtcDepositAddress(currency, signatureCreator).getAddress();
    } catch (HitbtcException e) {
      handleException(e);
      return null;
    }
  }
}
