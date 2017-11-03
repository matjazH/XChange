package org.knowm.xchange.kraken.service.polling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.FrequencyLimitExceededException;
import org.knowm.xchange.exceptions.NonceException;
import org.knowm.xchange.kraken.KrakenAdapters;
import org.knowm.xchange.kraken.KrakenAuthenticated;
import org.knowm.xchange.kraken.dto.KrakenResult;
import org.knowm.xchange.kraken.dto.marketdata.KrakenAssetPair;
import org.knowm.xchange.kraken.dto.marketdata.KrakenAssetPairs;
import org.knowm.xchange.kraken.dto.marketdata.KrakenAssets;
import org.knowm.xchange.kraken.dto.marketdata.KrakenServerTime;
import org.knowm.xchange.kraken.dto.marketdata.results.KrakenAssetPairsResult;
import org.knowm.xchange.kraken.dto.marketdata.results.KrakenAssetsResult;
import org.knowm.xchange.kraken.dto.marketdata.results.KrakenServerTimeResult;
import org.knowm.xchange.kraken.dto.trade.KrakenOrderFlags;
import org.knowm.xchange.kraken.service.KrakenDigest;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.polling.BasePollingService;
import si.mazi.rescu.Interceptor;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

public class KrakenBasePollingService extends BaseExchangeService implements BasePollingService {
  private Map<CurrencyPair, String> krakenCurrencyPairMap = new ConcurrentHashMap();
  private Map<Currency, String> krakenCurrencyMap = new ConcurrentHashMap();
  protected KrakenAuthenticated kraken;
  protected ParamsDigest signatureCreator;

  /**
   * Constructor
   *
   * @param exchange
   */
  public KrakenBasePollingService(Exchange exchange) {
    super(exchange);

    kraken = RestProxyFactory.createProxy(KrakenAuthenticated.class, exchange.getExchangeSpecification().getSslUri(),
        createClientConfig(exchange.getExchangeSpecification()), new Interceptor[0]);
    signatureCreator = KrakenDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
  }

  public List<CurrencyPair> getExchangeSymbols() throws IOException {
    List<CurrencyPair> currencyPairs = new ArrayList();

    Set<Map.Entry<String, KrakenAssetPair>> entries = getKrakenAssetPairs(new CurrencyPair[0]).getAssetPairMap().entrySet();
    for (Map.Entry<String, KrakenAssetPair> entry : entries) {
      if (!entry.getKey().endsWith(".d")) {
        String krakenTradeCurrency = entry.getValue().getBase();
        String krakenPriceCurrency = entry.getValue().getQuote();

        Currency tradeCurrency = KrakenAdapters.adaptCurrency(krakenTradeCurrency);
        Currency priceCurrency = KrakenAdapters.adaptCurrency(krakenPriceCurrency);

        CurrencyPair currencyPair = new CurrencyPair(tradeCurrency, priceCurrency);

        krakenCurrencyPairMap.put(currencyPair, entry.getKey());
        krakenCurrencyMap.put(currencyPair.base, krakenTradeCurrency);
        krakenCurrencyMap.put(currencyPair.counter, krakenPriceCurrency);

        currencyPairs.add(currencyPair);
      }
    }
    return currencyPairs;
  }

  protected String createKrakenCurrencyPair(CurrencyPair currencyPair) throws IOException {
    if (this.krakenCurrencyMap.isEmpty()) {
      getExchangeSymbols();
    }
    String krakenCurrencyCode = krakenCurrencyPairMap.get(currencyPair);
    if (krakenCurrencyCode == null) {
      throw new ExchangeException("Kraken does not support the currency code " + currencyPair);
    }
    return krakenCurrencyCode;
  }

  protected String createKrakenCurrencyPair(Currency tradableIdentifier, Currency currency) throws IOException {
    String krakenCurrencyCode = krakenCurrencyPairMap.get(new CurrencyPair(tradableIdentifier, currency));
    if (krakenCurrencyCode == null) {
      throw new ExchangeException("Kraken does not support the currency code " + tradableIdentifier + " " + currency);
    }
    return krakenCurrencyCode;
  }

  protected String getKrakenCurrencyCode(Currency currency) throws IOException {
    String krakenCurrencyCode = krakenCurrencyMap.get(currency);
    if (krakenCurrencyCode == null) {
      throw new ExchangeException("Kraken does not support the currency code " + currency);
    }
    return krakenCurrencyCode;
  }

  public KrakenServerTime getServerTime() throws IOException {

    KrakenServerTimeResult timeResult = kraken.getServerTime();

    return checkResult(timeResult);
  }

  public KrakenAssets getKrakenAssets(Currency... assets) throws IOException {

    KrakenAssetsResult assetPairsResult = kraken.getAssets(null, delimitAssets(assets));

    return new KrakenAssets(checkResult(assetPairsResult));
  }

  public KrakenAssetPairs getKrakenAssetPairs(CurrencyPair... currencyPairs) throws IOException {

    KrakenAssetPairsResult assetPairsResult = kraken.getAssetPairs(delimitAssetPairs(currencyPairs));

    return new KrakenAssetPairs(checkResult(assetPairsResult));
  }

  protected <R> R checkResult(KrakenResult<R> krakenResult) {

    if (!krakenResult.isSuccess()) {
      String[] errors = krakenResult.getError();
      if (errors.length == 0) {
        throw new ExchangeException("Missing error message");
      }
      String error = errors[0];
      if ("EAPI:Invalid nonce".equals(error)) {
        throw new NonceException(error);
      } else if ("EGeneral:Temporary lockout".equals(error)) {
        throw new FrequencyLimitExceededException(error);
      }
      throw new ExchangeException(Arrays.toString(errors));
    }
    return krakenResult.getResult();
  }

  protected String createDelimitedString(String[] items) {

    StringBuilder commaDelimitedString = null;
    if (items != null) {
      for (String item : items) {
        if (commaDelimitedString == null) {
          commaDelimitedString = new StringBuilder(item);
        } else {
          commaDelimitedString.append(",").append(item);
        }
      }
    }
    return commaDelimitedString == null ? null : commaDelimitedString.toString();
  }

  protected String delimitAssets(Currency[] assets) throws IOException {

    StringBuilder commaDelimitedAssets = new StringBuilder();
    if (assets != null && assets.length > 0) {
      boolean started = false;
      for (Currency asset : assets) {
        commaDelimitedAssets.append(started ? "," : "").append(getKrakenCurrencyCode(asset));
        started = true;
      }
      return commaDelimitedAssets.toString();
    }
    return null;
  }

  protected String delimitAssetPairs(CurrencyPair[] currencyPairs) throws IOException {

    String assetPairsString = null;
    if (currencyPairs != null && currencyPairs.length > 0) {
      StringBuilder delimitStringBuilder = null;
      for (CurrencyPair currencyPair : currencyPairs) {
        String krakenAssetPair = createKrakenCurrencyPair(currencyPair);
        if (delimitStringBuilder == null) {
          delimitStringBuilder = new StringBuilder(krakenAssetPair);
        } else {
          delimitStringBuilder.append(",").append(krakenAssetPair);
        }
      }
      assetPairsString = delimitStringBuilder.toString();
    }
    return assetPairsString;
  }

  protected String delimitSet(Set<Order.IOrderFlags> items) {

    String delimitedSetString = null;
    if (items != null && !items.isEmpty()) {
      StringBuilder delimitStringBuilder = null;
      for (Object item : items) {
        if (item instanceof KrakenOrderFlags) {
          if (delimitStringBuilder == null) {
            delimitStringBuilder = new StringBuilder(item.toString());
          } else {
            delimitStringBuilder.append(",").append(item.toString());
          }
        }
      }
      if (delimitStringBuilder != null) {
        delimitedSetString = delimitStringBuilder.toString();
      }
    }
    return delimitedSetString;
  }
}
