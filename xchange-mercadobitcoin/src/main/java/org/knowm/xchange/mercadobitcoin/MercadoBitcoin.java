package org.knowm.xchange.mercadobitcoin;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.knowm.xchange.mercadobitcoin.dto.marketdata.MercadoBitcoinOrderBook;
import org.knowm.xchange.mercadobitcoin.dto.marketdata.MercadoBitcoinTicker;
import org.knowm.xchange.mercadobitcoin.dto.marketdata.MercadoBitcoinTransaction;

/**
 * @author Matija Mazi
 * @author Felipe Micaroni Lalli - See https://www.mercadobitcoin.net/api/ and https://www.mercadobitcoin.net/trade-api/ for up-to-date docs.
 * @see org.knowm.xchange.mercadobitcoin.MercadoBitcoinAuthenticated
 */
@Path("api")
@Produces(MediaType.APPLICATION_JSON)
public interface MercadoBitcoin {

  /**
   * Returns "bids" and "asks". Each is a list of open orders and each order is represented as a list of price and amount.
   */
  @GET
  @Path("/BTC/orderbook/")
  public MercadoBitcoinOrderBook getOrderBookBTC() throws IOException;

  /**
   * Returns "bids" and "asks". Each is a list of open orders and each order is represented as a list of price and amount.
   */
  @GET
  @Path("/LTC/orderbook/")
  public MercadoBitcoinOrderBook getOrderBookLTC() throws IOException;

  @GET
  @Path("/BCH/orderbook/")
  public MercadoBitcoinOrderBook getOrderBookBCH() throws IOException;

  @GET
  @Path("/BTC/ticker/")
  public MercadoBitcoinTicker getTickerBTC() throws IOException;

  @GET
  @Path("/LTC/ticker/")
  public MercadoBitcoinTicker getTickerLTC() throws IOException;

  @GET
  @Path("/BCH/ticker/")
  public MercadoBitcoinTicker getTickerBCH() throws IOException;

  @GET
  @Path("/BTC/trades/")
  public MercadoBitcoinTransaction[] getTransactionsBTC() throws IOException;

  @GET
  @Path("/LTC/trades/")
  public MercadoBitcoinTransaction[] getTransactionsLTC() throws IOException;

  @GET
  @Path("/BCH/trades/")
  public MercadoBitcoinTransaction[] getTransactionsBCH() throws IOException;

  @GET
  @Path("/BTC/trades/{start_timestamp: [0-9]}/")
  public MercadoBitcoinTransaction[] getTransactionsBTC(@PathParam("start_timestamp") Long startTimestamp) throws IOException;

  @GET
  @Path("/LTC/trades/{start_timestamp: [0-9]}/")
  public MercadoBitcoinTransaction[] getTransactionsLTC(@PathParam("start_timestamp") Long startTimestamp) throws IOException;

  @GET
  @Path("/BCH/trades/{start_timestamp: [0-9]}/")
  public MercadoBitcoinTransaction[] getTransactionsBCH(@PathParam("start_timestamp") Long startTimestamp) throws IOException;

  @GET
  @Path("/BTC/trades/{start_timestamp: [0-9]}/{end_timestamp: [0-9]}/")
  public MercadoBitcoinTransaction[] getTransactionsBTC(@PathParam("start_timestamp") Long startTimestamp,
      @PathParam("end_timestamp") Long endTimestamp) throws IOException;

  @GET
  @Path("/BCH/trades/{start_timestamp: [0-9]}/{end_timestamp: [0-9]}/")
  public MercadoBitcoinTransaction[] getTransactionsBCH(@PathParam("start_timestamp") Long startTimestamp,
                                                        @PathParam("end_timestamp") Long endTimestamp) throws IOException;

  @GET
  @Path("/LTC/trades/{start_timestamp: [0-9]}/{end_timestamp: [0-9]}/")
  public MercadoBitcoinTransaction[] getTransactionsLTC(@PathParam("start_timestamp") Long startTimestamp,
      @PathParam("end_timestamp") Long endTimestamp) throws IOException;
}
