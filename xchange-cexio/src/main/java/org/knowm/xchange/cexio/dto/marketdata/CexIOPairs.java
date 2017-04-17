package org.knowm.xchange.cexio.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 17/05/16
 * Time: 16:06
 */
public class CexIOPairs {

  private final List<CexIOCurrency> pairs;

  public CexIOPairs(@JsonProperty("pairs") List<CexIOCurrency> pairs) {
    this.pairs = pairs;
  }

  public List<CexIOCurrency> getPairs() {
    return pairs;
  }
}
