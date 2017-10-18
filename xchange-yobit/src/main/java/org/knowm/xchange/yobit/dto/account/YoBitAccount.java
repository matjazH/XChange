package org.knowm.xchange.yobit.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.math.BigDecimal;

/**
 * Created by VITTACH on 31/03/17.
 */
public class YoBitAccount {
  private Rights rights;
  private Long serverTime;
  private Integer openOrders;
  private String depositAddress;

  private Integer processedAmount;
  private Integer transactionCount;
  private Map<String, BigDecimal> funds;
  private Map<String, BigDecimal> fundsInclOrders;

  public YoBitAccount(@JsonProperty("funds") Map<String, BigDecimal> funds,
                      @JsonProperty("funds_incl_orders") Map<String, BigDecimal> fundsInclOrders,
                      @JsonProperty("rights") Rights rights,
                      @JsonProperty("address") String depositAddress,
                      @JsonProperty("open_orders")Integer openOrders,
                      @JsonProperty("processed_amount") Integer processedAmount,
                      @JsonProperty("transaction_count") Integer transactionCount,
                      @JsonProperty("server_time") Long serverTime) {
    this.funds = funds;
    this.rights = rights;
    this.openOrders = openOrders;
    this.serverTime = serverTime;
    this.depositAddress = depositAddress;
    this.fundsInclOrders = fundsInclOrders;
    this.processedAmount = processedAmount;
    this.transactionCount = transactionCount;
  }

  public Map<String, BigDecimal> getFunds() {
    return funds;
  }

  public Rights getRights() {
    return rights;
  }

  public String getAddresses() {
    return depositAddress;
  }

  public Integer getOpenOrders() {
    return openOrders;
  }

  public Integer getProcessedAmount() {
    return processedAmount;
  }

  public Integer getTransactionCount() {
    return transactionCount;
  }

  public Map<String, BigDecimal> getFundsInclOrders() {
    return fundsInclOrders;
  }

  public Long getServerTime() {
    return serverTime;
  }

  public static class Rights {
    Integer info;
    Integer trade;
    Integer withdraw;

    public Rights(@JsonProperty("getInfo") Integer info, @JsonProperty("trade") Integer trade, @JsonProperty("withdraw") Integer withdraw) {
      this.info = info;
      this.trade = trade;
      this.withdraw = withdraw;
    }

    public Integer getWithdraw() {
      return withdraw;
    }

    public Integer getInfo() {
      return info;
    }

    public Integer getTrade() {
      return trade;
    }
  }

}
