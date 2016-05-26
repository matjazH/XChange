package org.knowm.xchange.bl3p.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.bl3p.dto.Bl3pError;
import org.knowm.xchange.bl3p.dto.Bl3pValue;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 17/08/15
 * Time: 19:17
 */
public class Bl3pAccountInfo extends Bl3pError {

  private Long userId;
  private BigDecimal tradingFee;
  private Map<String, Map<String, Bl3pValue>> wallets;

  public Bl3pAccountInfo(@JsonProperty("code") String code,
                         @JsonProperty("message") String message,
                         @JsonProperty("user_id") Long userId,
                         @JsonProperty("trade_fee") BigDecimal tradingFee,
                         @JsonProperty("wallets") Map<String, Map<String, Bl3pValue>> wallets) {
    super(code, message);

    this.userId = userId;
    this.tradingFee = tradingFee;
    this.wallets = wallets;
  }

  public Long getUserId() {
    return userId;
  }

  public BigDecimal getTradingFee() {
    return tradingFee;
  }

  public Map<String, Map<String, Bl3pValue>> getWallets() {
    return wallets;
  }

  @Override
  public String toString() {
    return "Bl3pAccountInfo{" +
        "userId=" + userId +
        ", tradingFee=" + tradingFee +
        ", wallets=" + wallets +
        '}';
  }
}
