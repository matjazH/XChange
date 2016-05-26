package org.knowm.xchange.quoine;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.knowm.xchange.quoine.dto.account.QuoineAccountInfo;
import org.knowm.xchange.quoine.dto.account.QuoineTradingAccountInfo;
import org.knowm.xchange.quoine.dto.marketdata.QuoineTradesList;
import org.knowm.xchange.quoine.dto.trade.QuoineNewOrderRequest;
import org.knowm.xchange.quoine.dto.trade.QuoineOrderDetailsResponse;
import org.knowm.xchange.quoine.dto.trade.QuoineOrderResponse;
import org.knowm.xchange.quoine.dto.trade.QuoineOrdersList;

import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface QuoineAuthenticated extends Quoine {

  @GET
  @Path("accounts")
  @Consumes(MediaType.APPLICATION_JSON)
  public QuoineAccountInfo getAccountInfo(@HeaderParam("X-Quoine-Vendor") String vendor,
                                          @HeaderParam("Date") String date,
                                          @HeaderParam("NONCE") SynchronizedValueFactory<String> nonce,
                                          @HeaderParam("Authorization") ParamsDigest digest) throws IOException;

  @GET
  @Path("trading_accounts")
  public QuoineTradingAccountInfo[] getTradingAccountInfo(@HeaderParam("Date") String date,
                                                          @HeaderParam("NONCE") SynchronizedValueFactory<String> nonce,
                                                          @HeaderParam("Authorization") ParamsDigest digest) throws IOException;

  @POST
  @Path("orders")
  @Consumes(MediaType.APPLICATION_JSON)
  public QuoineOrderResponse placeOrder(@HeaderParam("X-Quoine-Vendor") String vendor,
                                        @HeaderParam("Date") String date,
                                        @HeaderParam("NONCE") SynchronizedValueFactory<String> nonce,
                                        @HeaderParam("Authorization") ParamsDigest digest,
                                        QuoineNewOrderRequest quoineNewOrderRequest) throws IOException;

  @PUT
  @Path("orders/{order_id}/cancel")
  @Consumes(MediaType.APPLICATION_JSON)
  public QuoineOrderResponse cancelOrder(@HeaderParam("X-Quoine-Vendor") String vendor,
                                         @HeaderParam("Date") String date,
                                         @HeaderParam("NONCE") SynchronizedValueFactory<String> nonce,
                                         @HeaderParam("Authorization") ParamsDigest digest,
                                         @PathParam("order_id") String orderID) throws IOException;

  @GET
  @Path("orders/{order_id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public QuoineOrderDetailsResponse orderDetails(@HeaderParam("Date") String date,
                                                 @HeaderParam("NONCE") SynchronizedValueFactory<String> nonce,
                                                 @HeaderParam("Authorization") ParamsDigest digest,
                                                 @PathParam("order_id") String orderID) throws IOException;

  @GET
  @Path("orders")
  @Consumes(MediaType.APPLICATION_JSON)
  public QuoineOrdersList listOrders(@HeaderParam("Date") String date,
                                     @HeaderParam("NONCE") SynchronizedValueFactory<String> nonce,
                                     @HeaderParam("Authorization") ParamsDigest digest,
                                     @QueryParam("currency_pair_code") String currencyPair) throws IOException;

  @GET
  @Path("executions/me")
  @Consumes(MediaType.APPLICATION_JSON)
  QuoineTradesList listExecutions(@HeaderParam("X-Quoine-Vendor") String vendor,
                                  @HeaderParam("Date") String date,
                                  @HeaderParam("NONCE") SynchronizedValueFactory<String> nonce,
                                  @HeaderParam("Authorization") ParamsDigest digest,
                                  @QueryParam("currency_pair_code") String currencyPairCode,
                                  @QueryParam("limit") Integer limit,
                                  @QueryParam("page") Integer page) throws IOException;
}
