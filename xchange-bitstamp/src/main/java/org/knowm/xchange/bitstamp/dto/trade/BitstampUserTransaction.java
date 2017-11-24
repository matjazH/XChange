package org.knowm.xchange.bitstamp.dto.trade;

import java.math.BigDecimal;

import org.knowm.xchange.bitstamp.util.BitstampTransactionTypeDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Matija Mazi
 */
public final class BitstampUserTransaction {

  private final String datetime;
  private final long id;
  private final long order_id;
  private final TransactionType type;
  /** USD amount, negative -> BID, positive -> ASK */
  private final BigDecimal usd;
  private final BigDecimal eur;
  private final BigDecimal btc;
  private final BigDecimal xrp;
  private final BigDecimal ltc;
  private final BigDecimal eth;

  private final BigDecimal btc_usd;
  private final BigDecimal btc_eur;

  private final BigDecimal xrp_usd;
  private final BigDecimal xrp_eur;
  private final BigDecimal xrp_btc;

  private final BigDecimal ltc_btc;
  private final BigDecimal ltc_eur;
  private final BigDecimal ltc_usd;

  private final BigDecimal eur_usd;

  private final BigDecimal eth_usd;
  private final BigDecimal eth_eur;
  private final BigDecimal eth_btc;

  private final BigDecimal fee;

  /**
   * Constructor
   *
   * @param datetime
   * @param id
   * @param order_id
   * @param type
   * @param usd
   * @param btc
   * @param btc_usd
   * @param fee
   */
  public BitstampUserTransaction(@JsonProperty("datetime") String datetime,
                                 @JsonProperty("id") long id,
                                 @JsonProperty("order_id") long order_id,
                                 @JsonProperty("type") @JsonDeserialize(using = BitstampTransactionTypeDeserializer.class) TransactionType type,
                                 @JsonProperty("usd") BigDecimal usd,
                                 @JsonProperty("eur") BigDecimal eur,
                                 @JsonProperty("btc") BigDecimal btc,
                                 @JsonProperty("xrp") BigDecimal xrp,
                                 @JsonProperty("ltc") BigDecimal ltc,
                                 @JsonProperty("eth") BigDecimal eth,
                                 @JsonProperty("btc_usd") BigDecimal btc_usd,
                                 @JsonProperty("xrp_usd") BigDecimal xrp_usd,
                                 @JsonProperty("btc_eur") BigDecimal btc_eur,
                                 @JsonProperty("xrp_eur") BigDecimal xrp_eur,
                                 @JsonProperty("xrp_btc") BigDecimal xrp_btc,
                                 @JsonProperty("ltc_btc") BigDecimal ltc_btc,
                                 @JsonProperty("ltc_eur") BigDecimal ltc_eur,
                                 @JsonProperty("ltc_usd") BigDecimal ltc_usd,
                                 @JsonProperty("eur_usd") BigDecimal eur_usd,
                                 @JsonProperty("eth_usd") BigDecimal eth_usd,
                                 @JsonProperty("eth_eur") BigDecimal eth_eur,
                                 @JsonProperty("eth_btc") BigDecimal eth_btc,
                                 @JsonProperty("fee") BigDecimal fee) {

    this.datetime = datetime;
    this.id = id;
    this.order_id = order_id;
    this.type = type;
    this.fee = fee;

    this.usd = usd;
    this.eur = eur;
    this.btc = btc;
    this.xrp = xrp;
    this.ltc = ltc;
    this.eth = eth;

    this.btc_usd = btc_usd;
    this.btc_eur = btc_eur;

    this.xrp_usd = xrp_usd;
    this.xrp_eur = xrp_eur;
    this.xrp_btc = xrp_btc;

    this.ltc_btc = ltc_btc;
    this.ltc_eur = ltc_eur;
    this.ltc_usd = ltc_usd;

    this.eur_usd = eur_usd;

    this.eth_usd = eth_usd;
    this.eth_eur = eth_eur;
    this.eth_btc = eth_btc;
  }

  public String getDatetime() {

    return datetime;
  }

  public long getId() {

    return id;
  }

  public long getOrderId() {

    return order_id;
  }

  public TransactionType getType() {

    return type;
  }

  public boolean isDeposit() {

    return type == TransactionType.deposit;
  }

  public boolean isWithdrawal() {

    return type == TransactionType.withdrawal;
  }

  public boolean isMarketTrade() {

    return type == TransactionType.trade;
  }

  public BigDecimal getUsd() {

    return usd;
  }

  public BigDecimal getEur() {
    return eur;
  }

  public BigDecimal getBtc() {

    return btc;
  }

  public BigDecimal getXrp() {

    return xrp;
  }

  public BigDecimal getLtc() {

    return ltc;
  }

  public BigDecimal getEth() {

    return eth;
  }

  public BigDecimal getCounterAmount() {
    if (isBtc()) {
      return btc;
    } else if (isEur()) {
      return eur;
    } else if (isXrp()) {
      return xrp;
    } else if (isUsd()) {
      return usd;
    } else if (isLtc()) {
      return ltc;
    } else {
      return eth;
    }
  }

  private boolean isBtc() {
    return btc != null && btc.compareTo(BigDecimal.ZERO) != 0;
  }

  private boolean isEur() {
    return eur != null && eur.compareTo(BigDecimal.ZERO) != 0;
  }

  private boolean isXrp() {
    return xrp != null && xrp.compareTo(BigDecimal.ZERO) != 0;
  }

  private boolean isUsd() {
    return usd != null && usd.compareTo(BigDecimal.ZERO) != 0;
  }

  private boolean isLtc() {
    return ltc != null && ltc.compareTo(BigDecimal.ZERO) != 0;
  }

  private boolean isEth() {
    return eth != null && eth.compareTo(BigDecimal.ZERO) != 0;
  }

  public BigDecimal getPrice() {
    if (isBtcUsd()) {
      return btc_usd;
    } else if (isBtcEur()) {
      return btc_eur;
    } else if (isXrpUsd()) {
      return xrp_usd;
    } else if (isXrpEur()) {
      return xrp_eur;
    } else if (isXrpBtc()) {
      return xrp_btc;
    } else if (isLtcBtc()) {
      return ltc_btc;
    } else if (isLtcEur()) {
      return ltc_eur;
    } else if (isLtcUsd()) {
      return ltc_usd;
    } else if (isEurUsd()) {
      return eur_usd;
    } else if (isEthEur()) {
      return eth_eur;
    } else if (isEthUsd()) {
      return eth_usd;
    } else {
      return eth_btc;
    }
  }

  private boolean isBtcUsd() {
    return btc_usd != null && btc_usd.compareTo(BigDecimal.ZERO) != 0;
  }

  private boolean isBtcEur() {
    return btc_eur != null && btc_eur.compareTo(BigDecimal.ZERO) != 0;
  }

  private boolean isXrpUsd() {
    return xrp_usd != null && xrp_usd.compareTo(BigDecimal.ZERO) != 0;
  }

  private boolean isXrpEur() {
    return xrp_eur != null && xrp_eur.compareTo(BigDecimal.ZERO) != 0;
  }

  private boolean isXrpBtc() {
    return xrp_btc != null && xrp_btc.compareTo(BigDecimal.ZERO) != 0;
  }

  private boolean isLtcBtc() {
    return ltc_btc != null && ltc_btc.compareTo(BigDecimal.ZERO) != 0;
  }

  private boolean isLtcEur() {
    return ltc_eur != null && ltc_eur.compareTo(BigDecimal.ZERO) != 0;
  }

  private boolean isLtcUsd() {
    return ltc_usd != null && ltc_usd.compareTo(BigDecimal.ZERO) != 0;
  }

  private boolean isEurUsd() {
    return eur_usd != null && eur_usd.compareTo(BigDecimal.ZERO) != 0;
  }

  private boolean isEthEur() {
    return eth_eur != null && eth_eur.compareTo(BigDecimal.ZERO) != 0;
  }

  private boolean isEthUsd() {
    return eth_usd != null && eth_usd.compareTo(BigDecimal.ZERO) != 0;
  }

  private boolean isEthBtc() {
    return eth_btc != null && eth_btc.compareTo(BigDecimal.ZERO) != 0;
  }

  public String getCounterCurrency() {
    return isUsd() ? "USD" : "EUR";
  }

  public String getBaseCurrency() {
    return isXrp() ? "XRP" : "BTC";
  }

  public BigDecimal getAmount() {
    return isBtc() ? btc : xrp;
  }

  public BigDecimal getFee() {

    return fee;
  }

  @Override
  public String toString() {

    return String.format("UserTransaction{datetime=%s, id=%d, type=%s, usd=%s, btc=%s, xrp=%s, ltc=%s, eth=%s, eur=%s, fee=%s}",
        datetime, id, type, usd, btc, xrp, ltc, eth, eur, fee);
  }

  public enum TransactionType {
    deposit, withdrawal, trade, rippleWithdrawal, rippleDeposit, type5_reseverd, type6_reseved, type7_reserved /*
                                                                                                                * reseved so parsing won 't break in
                                                                                                                * case Bitstamp adds new types
                                                                                                                */
  }
}
