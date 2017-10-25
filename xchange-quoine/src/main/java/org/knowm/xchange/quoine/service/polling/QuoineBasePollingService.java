package org.knowm.xchange.quoine.service.polling;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.quoine.QuoineUtils;
import org.knowm.xchange.quoine.service.QuoineHmacPostBodyDigest;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.quoine.QuoineAuthenticated;
import org.knowm.xchange.quoine.QuoineExchange;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.polling.BasePollingService;

import org.knowm.xchange.utils.nonce.Random32CharsStringNonceFactory;
import si.mazi.rescu.HttpStatusIOException;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;
import si.mazi.rescu.SynchronizedValueFactory;

public class QuoineBasePollingService extends BaseExchangeService implements BasePollingService {

  protected static final String VENDOR = "TABTRADER";

  protected QuoineAuthenticated quoine;

  protected final String userID;
  protected final String secret;

  protected final ParamsDigest signatureCreator;
  protected final SynchronizedValueFactory<String> nonceFactory = new Random32CharsStringNonceFactory();

  // “Sat, 27 Sep 2014 04:55:17 GMT”
  private final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'z", Locale.US);

  /**
   * Constructor
   *
   * @param exchange
   */
  public QuoineBasePollingService(Exchange exchange) {

    super(exchange);

    quoine = RestProxyFactory.createProxy(QuoineAuthenticated.class, exchange.getExchangeSpecification().getSslUri(), createClientConfig(exchange.getExchangeSpecification()));
    userID = (String) exchange.getExchangeSpecification().getExchangeSpecificParameters().get(QuoineExchange.KEY_USER_ID);
    secret = exchange.getExchangeSpecification().getSecretKey();
    signatureCreator = QuoineHmacPostBodyDigest.createInstance(userID, secret);
    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
  }

  protected RuntimeException handleHttpError(HttpStatusIOException exception) throws IOException {
    throw new ExchangeException(exception.getHttpBody(), exception);
  }

  protected String getDate() {
    String format = dateFormat.format(new Date());
    if (format.contains("+00:00")) {
      format = format.replace("+00:00", "");
    }
    return format;
  }

  @Override
  public List<CurrencyPair> getExchangeSymbols() throws IOException {
    List<CurrencyPair> symbols = new ArrayList<>();
    symbols.addAll(QuoineUtils.CURRENCY_PAIR_2_ID_MAP.keySet());
    return symbols;
  }
}
