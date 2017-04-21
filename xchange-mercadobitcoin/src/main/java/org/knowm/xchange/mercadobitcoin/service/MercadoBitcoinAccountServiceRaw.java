package org.knowm.xchange.mercadobitcoin.service;

import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.mercadobitcoin.MercadoBitcoinAuthenticated;
import org.knowm.xchange.mercadobitcoin.dto.MercadoBitcoinBaseTradeApiResult;
import org.knowm.xchange.mercadobitcoin.dto.account.MercadoBitcoinAccountInfo;

import org.knowm.xchange.mercadobitcoin.dto.v3.MercadoBitcoinBaseResponse;
import org.knowm.xchange.mercadobitcoin.dto.v3.account.MercadoBitcoinAccount;
import si.mazi.rescu.RestProxyFactory;

/**
 * @author Felipe Micaroni Lalli
 */
public class MercadoBitcoinAccountServiceRaw extends MercadoBitcoinBaseService {

  private static final String GET_ACCOUNT_INFO = "getInfo";

  private final MercadoBitcoinAuthenticated mercadoBitcoinAuthenticated;

  /**
   * Constructor
   *
   * @param exchange
   */
  protected MercadoBitcoinAccountServiceRaw(Exchange exchange) {

    super(exchange);

    this.mercadoBitcoinAuthenticated = RestProxyFactory.createProxy(MercadoBitcoinAuthenticated.class,
        exchange.getExchangeSpecification().getSslUri(), createClientConfig(exchange.getExchangeSpecification()));
  }

  public MercadoBitcoinAccount getMercadoBitcoinAccountInfo() throws IOException {

    MercadoBitcoinBaseResponse<MercadoBitcoinAccount> response = mercadoBitcoinAuthenticated.getInfo(
        exchange.getExchangeSpecification().getApiKey(), signatureCreator, GET_ACCOUNT_INFO, exchange.getNonceFactory());

    checkResponse(response);
    return response.getResponseData();
  }
}
