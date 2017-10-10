package org.knowm.xchange.examples.btce.account;

import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.btce.v3.dto.account.WEXAccountInfo;
import org.knowm.xchange.btce.v3.service.polling.WEXAccountServiceRaw;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.examples.btce.BTCEExamplesUtils;
import org.knowm.xchange.service.polling.account.PollingAccountService;

/**
 * Demo requesting account info at BTC-E
 */
public class BTCEAccountInfoDemo {

  public static void main(String[] args) throws IOException {

    Exchange btce = BTCEExamplesUtils.createExchange();
    generic(btce);
    raw(btce);
  }

  private static void generic(Exchange exchange) throws IOException {

    // Interested in the private account functionality (authentication)
    PollingAccountService accountService = exchange.getPollingAccountService();

    // Get the account information
    AccountInfo accountInfo = accountService.getAccountInfo();
    System.out.println("WEX AccountInfo as String: " + accountInfo.toString());
  }

  private static void raw(Exchange exchange) throws IOException {

    // Interested in the private account functionality (authentication)
    WEXAccountServiceRaw accountService = (WEXAccountServiceRaw) exchange.getPollingAccountService();

    // Get the account information
    WEXAccountInfo accountInfo = accountService.getBTCEAccountInfo(null, null, null, null, null, null, null);
    System.out.println("WEX Wallet as String: " + accountInfo.toString());
  }

}
