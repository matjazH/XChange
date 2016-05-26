package org.knowm.xchange.quoine.dto.account;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.quoine.dto.account.CryptoAccount;

/**
 * @author timmolter
 */
public final class QuoineAccountInfo {

  private final CryptoAccount[] cryptoAccounts;
  private final FiatAccount[] fiatAccounts;

  /**
   * Constructor
   *
   * @param cryptoAccounts
   * @param fiatAccounts
   */
  public QuoineAccountInfo(@JsonProperty("crypto_accounts") CryptoAccount[] cryptoAccounts, @JsonProperty("fiat_accounts") FiatAccount[] fiatAccounts) {
    this.cryptoAccounts = cryptoAccounts;
    this.fiatAccounts = fiatAccounts;
  }

  public CryptoAccount[] getCryptoAccounts() {
    return cryptoAccounts;
  }

  public FiatAccount[] getFiatAccounts() {
    return fiatAccounts;
  }

  @Override
  public String toString() {
    return "QuoineAccountInfo [bitcoinAccount=" + Arrays.toString(cryptoAccounts) + ", fiatAccounts=" + Arrays.toString(fiatAccounts) + "]";
  }

}
