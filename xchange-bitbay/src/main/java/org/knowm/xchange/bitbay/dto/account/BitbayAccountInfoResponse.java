package org.knowm.xchange.bitbay.dto.account;

import java.util.Map;

import org.knowm.xchange.bitbay.dto.BitbayBaseResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Z. Dolezal
 */
public class BitbayAccountInfoResponse extends BitbayBaseResponse {

  private final Map<String, String> addresses;
  private final Map<String, BitbayBalance> bitbayBalances;

  public BitbayAccountInfoResponse(@JsonProperty("balances")Map<String,BitbayBalance>balances,
                                   @JsonProperty("success") boolean success,
                                   @JsonProperty("code") int code,
                                   @JsonProperty("message") String errorMsg,
                                   @JsonProperty("addresses") Map<String, String> addresses) {
    super(success, code, errorMsg);

    this.addresses = addresses;
    this.bitbayBalances = balances;
  }

  public Map<String, BitbayBalance> getBitbayBalances() {
    return bitbayBalances;
  }

  public Map<String, String> getAddresses() {
    return addresses;
  }

  @Override
  public String toString() {
    return "BitbayAccount{" + "balances=" + bitbayBalances + ", addresses=" + addresses + '}';
  }
}
