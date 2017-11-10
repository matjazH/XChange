package org.knowm.xchange.quoine;

import java.util.HashMap;
import java.util.Map;

import org.knowm.xchange.currency.CurrencyPair;

/**
 * A central place for shared Quoine util methods
 */
public final class QuoineUtils {
  /**
   * private Constructor
   */
  private QuoineUtils() {
  }

  public static Map<CurrencyPair, Integer> CURRENCY_PAIR_2_ID_MAP = new HashMap<>();

  public static int toID(CurrencyPair currencyPair) {
    return CURRENCY_PAIR_2_ID_MAP.get(currencyPair);
  }

  public static String toPairString(CurrencyPair currencyPair) {
    return currencyPair.base.getCurrencyCode() + currencyPair.counter.getCurrencyCode();
  }
}
