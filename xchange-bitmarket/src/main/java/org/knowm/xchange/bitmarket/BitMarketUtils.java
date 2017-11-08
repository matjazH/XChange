package org.knowm.xchange.bitmarket;

import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;

/**
 * @author kfonal
 */
public class BitMarketUtils {
  public static String CurrencyPairToBitMarketCurrencyPair(CurrencyPair currencyPair) {

    if (currencyPair.base.getCurrencyCode().equals("BTC") && currencyPair.counter.getCurrencyCode().equals("PLN")) {
      return "BTCPLN";
    } else if (currencyPair.base.getCurrencyCode().equals("BTC") && currencyPair.counter.getCurrencyCode().equals("EUR")) {
      return "BTCEUR";
    } else if (currencyPair.base.getCurrencyCode().equals("LTC") && currencyPair.counter.getCurrencyCode().equals("PLN")) {
      return "LTCPLN";
    } else if (currencyPair.base.getCurrencyCode().equals("LTC") && currencyPair.counter.getCurrencyCode().equals("BTC")) {
      return "LTCBTC";
    } else if (currencyPair.base.getCurrencyCode().equalsIgnoreCase("LiteMineX") && currencyPair.counter.getCurrencyCode().equals("BTC")) {
      return "LiteMineXBTC";
    } else {
      return null;
    }
  }

  public static CurrencyPair BitMarketCurrencyPairToCurrencyPair(String currencyPair) {

    if (currencyPair.equals("BTCPLN")) {
      return CurrencyPair.BTC_PLN;
    } else if (currencyPair.equals("BTCEUR")) {
      return CurrencyPair.BTC_EUR;
    } else if (currencyPair.equals("LTCPLN")) {
      return new CurrencyPair("LTC", "PLN");
    } else if (currencyPair.equals("LTCBTC")) {
      return CurrencyPair.LTC_BTC;
    } else if (currencyPair.equalsIgnoreCase("neXBTCLiteMi")) {
      return new CurrencyPair("LiteMineX", "BTC");
    } else {
      return null;
    }
  }

  public static Order.OrderType BitMarketOrderTypeToOrderType(String bitmarketOrderType) {

    return bitmarketOrderType.equals("sell") ? Order.OrderType.ASK : Order.OrderType.BID;
  }

  public static String OrderTypeToBitMarketOrderType(Order.OrderType orderType) {

    return orderType == Order.OrderType.ASK ? "sell" : "buy";
  }
}
