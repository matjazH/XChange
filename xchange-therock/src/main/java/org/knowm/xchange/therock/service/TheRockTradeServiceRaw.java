package org.knowm.xchange.therock.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.therock.TheRock;
import org.knowm.xchange.therock.TheRockAuthenticated;
import org.knowm.xchange.therock.dto.TheRockException;
import org.knowm.xchange.therock.dto.trade.TheRockOrder;
import org.knowm.xchange.therock.dto.trade.TheRockOrders;
import si.mazi.rescu.RestProxyFactory;

public class TheRockTradeServiceRaw extends TheRockBaseService {
  private final TheRockAuthenticated theRockAuthenticated;
  private final TheRockDigest signatureCreator;

  public TheRockTradeServiceRaw(Exchange exchange) {

    super(exchange);

    ExchangeSpecification spec = exchange.getExchangeSpecification();

    this.theRockAuthenticated = ((TheRockAuthenticated) RestProxyFactory.createProxy(TheRockAuthenticated.class, spec.getSslUri()));

    this.signatureCreator = new TheRockDigest(spec.getSecretKey());
  }

  public TheRockOrder placeTheRockOrder(CurrencyPair currencyPair, BigDecimal amount, BigDecimal price, TheRockOrder.Side side, TheRockOrder.Type type) throws ExchangeException, IOException {

    return placeTheRockOrder(currencyPair, new TheRockOrder(new TheRock.Pair(currencyPair), side, type, amount, price, null, null, null, null, null, null, null, null, null, null));
  }

  public TheRockOrder placeTheRockOrder(CurrencyPair currencyPair, TheRockOrder order) throws ExchangeException, IOException {
    try {
      return this.theRockAuthenticated.placeOrder(new TheRock.Pair(currencyPair), this.exchange.getExchangeSpecification().getApiKey(), this.signatureCreator, this.exchange
          .getNonceFactory(), order);
    } catch (TheRockException e) {

      throw new ExchangeException(e.getMessage());
    }
  }

  public TheRockOrder cancelTheRockOrder(CurrencyPair currencyPair, Long orderId) throws TheRockException, IOException {
    try {
      return this.theRockAuthenticated.cancelOrder(new TheRock.Pair(currencyPair), orderId, this.exchange.getExchangeSpecification().getApiKey(), this.signatureCreator, this.exchange
          .getNonceFactory());
    } catch (TheRockException e) {

      throw new ExchangeException(e.getMessage());
    }
  }

  public TheRockOrders getTheRockOrders(CurrencyPair currencyPair) throws TheRockException, IOException {
    try {
      return this.theRockAuthenticated.orders(new TheRock.Pair(currencyPair), this.exchange.getExchangeSpecification().getApiKey(), this.signatureCreator, this.exchange

          .getNonceFactory());
    } catch (TheRockException e) {

      throw new ExchangeException(e.getMessage());
    }
  }

  public TheRockOrder showTheRockOrder(CurrencyPair currencyPair, Long orderId) throws TheRockException, IOException {
    try {
      return this.theRockAuthenticated.showOrder(new TheRock.Pair(currencyPair), orderId, this.exchange.getExchangeSpecification().getApiKey(), this.signatureCreator, this.exchange
          .getNonceFactory());
    } catch (TheRockException e) {

      throw new ExchangeException(e.getMessage());
    }
  }

  public org.knowm.xchange.therock.dto.trade.TheRockUserTrades getTheRockUserTrades(CurrencyPair currencyPair, Long sinceTradeId, Date after, Date before) throws IOException {
    try {
      return this.theRockAuthenticated.trades(new TheRock.Pair(currencyPair), this.exchange.getExchangeSpecification().getApiKey(), this.signatureCreator, this.exchange
          .getNonceFactory(), sinceTradeId, after, before, 200);
    } catch (Throwable e) {
      throw new ExchangeException(e.getMessage());
    }
  }

  public org.knowm.xchange.therock.dto.trade.TheRockTransaction[] getTheRockTransactions(String type, Date after, Date before) throws IOException {
    try {

      return this.theRockAuthenticated.transactions(this.exchange.getExchangeSpecification().getApiKey(), this.signatureCreator, this.exchange.getNonceFactory(), type, after, before).getTransactions();
    } catch (Throwable e) {

      throw new ExchangeException(e.getMessage());
    }
  }
}