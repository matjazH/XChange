package org.knowm.xchange.gemini.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.gemini.dto.GeminiBaseRequest;
import org.knowm.xchange.gemini.dto.account.GeminiBalance;

import java.io.IOException;
import java.util.List;

/**
 * Created by tabtrader on 04/10/2016.
 */
public class GeminiAccountServiceRaw extends GeminiBasePollingService {
  /**
   * Constructor
   *
   * @param exchange
   */
  protected GeminiAccountServiceRaw(Exchange exchange) {
    super(exchange);
  }

  public List<GeminiBalance> getGeminiBalances() throws IOException {
    GeminiBaseRequest geminiBaseRequest = new GeminiBaseRequest();
    geminiBaseRequest.setNonce(exchange.getNonceFactory().createValue());
    return gemini.getBalances(apiKey, geminiBaseRequest, signatureCreator);
  }
}