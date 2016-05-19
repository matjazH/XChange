package com.xeiam.xchange.quoine.service.polling;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import com.xeiam.xchange.quoine.service.QuoineHmacPostBodyDigest;
import com.xeiam.xchange.utils.nonce.Random32CharsStringNonceFactory;
import si.mazi.rescu.HttpStatusIOException;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.exceptions.ExchangeException;
import com.xeiam.xchange.quoine.QuoineAuthenticated;
import com.xeiam.xchange.quoine.QuoineExchange;
import com.xeiam.xchange.service.BaseExchangeService;
import com.xeiam.xchange.service.polling.BasePollingService;
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

    quoine = RestProxyFactory.createProxy(QuoineAuthenticated.class, exchange.getExchangeSpecification().getSslUri());
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
}
