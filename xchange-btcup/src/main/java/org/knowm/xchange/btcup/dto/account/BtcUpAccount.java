package org.knowm.xchange.btcup.dto.account;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by VITTACH on 28/08/17.
 */
public class BtcUpAccount {

  // crypto currency
  private BtcUpBalance usd;
  private BtcUpBalance btc;
  private BtcUpBalance ltc;
  private BtcUpBalance eth;
  private BtcUpBalance emc;
  private BtcUpBalance zec;
  private BtcUpBalance snm;
  private BtcUpBalance snt;

  // crypto currency pair
  private BtcUpBalance btcUsd;
  private BtcUpBalance ltcUsd;
  private BtcUpBalance ethUsd;
  private BtcUpBalance emcUsd;
  private BtcUpBalance zecUsd;
  private BtcUpBalance snmUsd;
  private BtcUpBalance sntUsd;

  private Integer margin;
  private Integer levelMC;
  private Integer levelFL;
  private Boolean marginCall;
  private BigDecimal equity;
  private Integer maxMargin;
  private Boolean suspended;
  private Integer marginLevel;
  private Integer maxLeverage;
  private BigDecimal freeMargin;

  public BtcUpAccount(@JsonProperty("USD") BtcUpBalance usd,
                      @JsonProperty("LTC") BtcUpBalance ltc,
                      @JsonProperty("BTC") BtcUpBalance btc,
                      @JsonProperty("ETH") BtcUpBalance eth,
                      @JsonProperty("EMC") BtcUpBalance emc,
                      @JsonProperty("ZEC") BtcUpBalance zec,
                      @JsonProperty("SNM") BtcUpBalance snm,
                      @JsonProperty("SNT") BtcUpBalance snt,

                      @JsonProperty("BTC_USD") BtcUpBalance btcUsd,
                      @JsonProperty("LTC_USD") BtcUpBalance ltcUsd,
                      @JsonProperty("ETH_USD") BtcUpBalance ethUsd,
                      @JsonProperty("EMC_USD") BtcUpBalance emcUsd,
                      @JsonProperty("ZEC_USD") BtcUpBalance zecUsd,
                      @JsonProperty("SNM_USD") BtcUpBalance snmUsd,
                      @JsonProperty("SNT_USD") BtcUpBalance sntUsd,

                      @JsonProperty("equity") BigDecimal equity,
                      @JsonProperty("freeMargin") BigDecimal freeMargin,
                      @JsonProperty("margin") Integer margin,
                      @JsonProperty("levelMC") Integer levelMC,
                      @JsonProperty("levelFL") Integer levelFL,
                      @JsonProperty("maxMargin") Integer maxMargin,
                      @JsonProperty("suspended") Boolean suspended,
                      @JsonProperty("marginCall") Boolean marginCall,
                      @JsonProperty("marginLevel") Integer marginLevel,
                      @JsonProperty("maxLeverage") Integer maxLeverage) {
    this.usd = usd;
    this.btc = btc;
    this.ltc = ltc;
    this.eth = eth;
    this.emc = emc;
    this.zec = zec;
    this.snm = snm;
    this.snt = snt;

    this.btcUsd = btcUsd;
    this.ltcUsd = ltcUsd;
    this.ethUsd = ethUsd;
    this.emcUsd = emcUsd;
    this.zecUsd = zecUsd;
    this.snmUsd = snmUsd;
    this.sntUsd = sntUsd;

    this.equity = equity;
    this.freeMargin = freeMargin;
    this.margin = margin;
    this.levelMC = levelMC;
    this.levelFL = levelFL;
    this.maxMargin = maxMargin;
    this.suspended = suspended;
    this.marginCall = marginCall;
    this.marginLevel = marginLevel;
    this.maxLeverage = maxLeverage;
  }

  public BtcUpBalance getUsd() {
    return usd;
  }

  public BtcUpBalance getBtc() {
    return btc;
  }

  public BtcUpBalance getLtc() {
    return ltc;
  }

  public BtcUpBalance getEth() {
    return eth;
  }

  public BtcUpBalance getEmc() {
    return emc;
  }

  public BtcUpBalance getZec() {
    return zec;
  }

  public BtcUpBalance getSnm() {
    return snm;
  }

  public BtcUpBalance getSnt() {
    return snt;
  }

  public BtcUpBalance getBtcUsd() {
    return btcUsd;
  }

  public BtcUpBalance getLtcUsd() {
    return ltcUsd;
  }

  public BtcUpBalance getEthUsd() {
    return ethUsd;
  }

  public BtcUpBalance getEmcUsd() {
    return emcUsd;
  }

  public BtcUpBalance getZecUsd() {
    return zecUsd;
  }

  public BtcUpBalance getSnmUsd() {
    return snmUsd;
  }

  public BtcUpBalance getSntUsd() {
    return sntUsd;
  }

  public Integer getMargin() {
    return margin;
  }

  public Integer getLevelMC() {
    return levelMC;
  }

  public Integer getLevelFL() {
    return levelFL;
  }

  public Boolean getMarginCall() {
    return marginCall;
  }

  public BigDecimal getEquity() {
    return equity;
  }

  public Integer getMaxMargin() {
    return maxMargin;
  }

  public Boolean getSuspended() {
    return suspended;
  }

  public Integer getMarginLevel() {
    return marginLevel;
  }

  public Integer getMaxLeverage() {
    return maxLeverage;
  }

  public BigDecimal getFreeMargin() {
    return freeMargin;
  }

  @Override
  public String toString() {
    return "BtcUpAccount{" +
        "usd=" + usd +
        ", btc=" + btc +
        ", ltc=" + ltc +
        ", eth=" + eth +
        ", emc=" + emc +
        ", zec=" + zec +
        ", snm=" + snm +
        ", snt=" + snt +
        ", btcUsd=" + btcUsd +
        ", ltcUsd=" + ltcUsd +
        ", ethUsd=" + ethUsd +
        ", emcUsd=" + emcUsd +
        ", zecUsd=" + zecUsd +
        ", snmUsd=" + snmUsd +
        ", sntUsd=" + sntUsd +
        ", margin=" + margin +
        ", levelMC=" + levelMC +
        ", levelFL=" + levelFL +
        ", marginCall=" + marginCall +
        ", equity=" + equity +
        ", maxMargin=" + maxMargin +
        ", suspended=" + suspended +
        ", marginLevel=" + marginLevel +
        ", maxLeverage=" + maxLeverage +
        ", freeMargin=" + freeMargin +
        '}';
  }
}
