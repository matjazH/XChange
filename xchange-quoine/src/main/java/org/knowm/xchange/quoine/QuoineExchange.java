package org.knowm.xchange.quoine;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.quoine.dto.marketdata.QuoineProduct;
import org.knowm.xchange.quoine.service.polling.QuoineAccountService;
import org.knowm.xchange.quoine.service.polling.QuoineMarketDataService;
import org.knowm.xchange.quoine.service.polling.QuoineMarketDataServiceRaw;
import org.knowm.xchange.quoine.service.polling.QuoineTradeService;

import si.mazi.rescu.SynchronizedValueFactory;

public class QuoineExchange extends BaseExchange implements Exchange {

  public static final String KEY_USER_ID = "KEY_USER_ID";
  public static final String KEY_DEVICE_NAME = "KEY_DEVICE_NAME";
  public static final String KEY_USER_TOKEN = "KEY_USER_TOKEN";


  @Override
  protected void initServices() {

    boolean useMargin = (Boolean) exchangeSpecification.getExchangeSpecificParametersItem("Use_Margin");

    this.pollingMarketDataService = new QuoineMarketDataService(this);
    this.pollingAccountService = new QuoineAccountService(this, useMargin);
    this.pollingTradeService = new QuoineTradeService(this, useMargin);
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://api.quoine.com");
    exchangeSpecification.setExchangeName("Quoine");
    exchangeSpecification.setExchangeSpecificParametersItem("Use_Margin", false);
    return exchangeSpecification;
  }


  @Override
  public void remoteInit() {
    try {
      QuoineMarketDataServiceRaw marketDataServiceRaw = (QuoineMarketDataServiceRaw) pollingMarketDataService;
      QuoineProduct[] quoineProducts = marketDataServiceRaw.getQuoineProducts();
      for (QuoineProduct product : quoineProducts) {
        if (!product.getDisabled()) {
          CurrencyPair pair = new CurrencyPair(product.getBaseCurrency(), product.getQuotedCurrency());
          QuoineUtils.CURRENCY_PAIR_2_ID_MAP.put(pair, product.getId());
        }
      }
    } catch (Exception e) {
      logger.warn("An exception occurred while loading the metadata file from the file. This may lead to unexpected results.", e);
    }
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {

    return null;
  }
}
