package org.knowm.xchange.cexio.dto.account;

import java.text.MessageFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Author: brox Since: 2/7/14
 */
public class CexIOBalanceInfo {

  private final String error;
  private final long timestamp;
  private final String username;
  private final CexIOBalance balanceDASH;
  private final CexIOBalance balanceBCH;
  private final CexIOBalance balanceBTC;
  private final CexIOBalance balanceGHS;
  private final CexIOBalance balanceUSD;
  private final CexIOBalance balanceEUR;
  private final CexIOBalance balanceETH;
  private final CexIOBalance balanceGBP;
  private final CexIOBalance balanceRUB;
  private final CexIOBalance balanceZEC;

  public CexIOBalanceInfo(@JsonProperty("error") String error,
                          @JsonProperty("timestamp") long timestamp,
                          @JsonProperty("username") String username,
                          @JsonProperty("BCH") CexIOBalance balanceBCH,
                          @JsonProperty("BTC") CexIOBalance balanceBTC,
                          @JsonProperty("EUR") CexIOBalance balanceEUR,
                          @JsonProperty("GBP") CexIOBalance balanceGBP,
                          @JsonProperty("USD") CexIOBalance balanceUSD,
                          @JsonProperty("RUB") CexIOBalance balanceRUB,
                          @JsonProperty("DASH") CexIOBalance balanceDASH,
                          @JsonProperty("ETH") CexIOBalance balanceETH,
                          @JsonProperty("GHS") CexIOBalance balanceGHS,
                          @JsonProperty("ZEC") CexIOBalance balanceZEC) {

    this.error = error;
    this.timestamp = timestamp;
    this.username = username;
    this.balanceDASH = balanceDASH;
    this.balanceBCH = balanceBCH;
    this.balanceBTC = balanceBTC;
    this.balanceGHS = balanceGHS;
    this.balanceUSD = balanceUSD;
    this.balanceEUR = balanceEUR;
    this.balanceETH = balanceETH;
    this.balanceGBP = balanceGBP;
    this.balanceRUB = balanceRUB;
    this.balanceZEC = balanceZEC;
  }

  public String getError() {

    return error;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public String getUsername() {
    return username;
  }

  public CexIOBalance getBalanceDASH() {
    return balanceDASH;
  }

  public CexIOBalance getBalanceBCH() {
    return balanceBCH;
  }

  public CexIOBalance getBalanceBTC() {
    return balanceBTC;
  }

  public CexIOBalance getBalanceGHS() {
    return balanceGHS;
  }

  public CexIOBalance getBalanceUSD() {
    return balanceUSD;
  }

  public CexIOBalance getBalanceEUR() {
    return balanceEUR;
  }

  public CexIOBalance getBalanceETH() {
    return balanceETH;
  }

  public CexIOBalance getBalanceGBP() {
    return balanceGBP;
  }

  public CexIOBalance getBalanceRUB() {
    return balanceRUB;
  }

  public CexIOBalance getBalanceZEC() {
    return balanceZEC;
  }

  @Override
  public String toString() {
    return "CexIOBalanceInfo{" +
        "error='" + error + '\'' +
        ", timestamp=" + timestamp +
        ", username='" + username + '\'' +
        ", balanceDASH=" + balanceDASH +
        ", balanceBCH=" + balanceBCH +
        ", balanceBTC=" + balanceBTC +
        ", balanceGHS=" + balanceGHS +
        ", balanceUSD=" + balanceUSD +
        ", balanceEUR=" + balanceEUR +
        ", balanceETH=" + balanceETH +
        ", balanceGBP=" + balanceGBP +
        ", balanceRUB=" + balanceRUB +
        ", balanceZEC=" + balanceZEC +
        '}';
  }
}
