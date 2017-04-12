package org.knowm.xchange.bl3p.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 14/08/15
 * Time: 19:22
 */
public class Bl3pVolume {

  private BigDecimal h24;
  private BigDecimal d30;

  public Bl3pVolume(@JsonProperty("24h") BigDecimal h24,
                    @JsonProperty("30d") BigDecimal d30) {
    this.h24 = h24;
    this.d30 = d30;
  }

  public BigDecimal getH24() {
    return h24;
  }

  public BigDecimal getD30() {
    return d30;
  }

  @Override
  public String toString() {
    return "Bl3pVolume{" +
        "h24=" + h24 +
        ", d30=" + d30 +
        '}';
  }
}
