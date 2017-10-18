package org.knowm.xchange.exmo.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.exmo.dto.ExmoResult;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 21/12/15
 * Time: 18:34
 */
public class ExmoUserInfo extends ExmoResult {

  protected final Long uid;
  protected final Long serverDate;
  protected final Map<String, BigDecimal> balances;
  protected final Map<String, BigDecimal> reserved;

  public ExmoUserInfo(@JsonProperty("result") boolean result,
                      @JsonProperty("error") String error,
                      @JsonProperty("uid") Long uid,
                      @JsonProperty("server_date") Long serverDate,
                      @JsonProperty("balances") Map<String, BigDecimal> balances,
                      @JsonProperty("reserved") Map<String, BigDecimal> reserved) {
    super(result, error);

    this.uid = uid;
    this.serverDate = serverDate;
    this.balances = balances;
    this.reserved = reserved;
  }

  public Long getUid() {
    return uid;
  }

  public Long getServerDate() {
    return serverDate;
  }

  public Map<String, BigDecimal> getBalances() {
    return balances;
  }

  public Map<String, BigDecimal> getReserved() {
    return reserved;
  }

  @Override
  public String toString() {
    return "ExmoUserInfo{" +
        "uid=" + uid +
        ", serverDate=" + serverDate +
        ", balances=" + balances +
        ", reserved=" + reserved +
        '}';
  }
}
