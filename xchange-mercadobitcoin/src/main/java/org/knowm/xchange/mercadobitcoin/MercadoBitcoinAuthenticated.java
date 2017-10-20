package org.knowm.xchange.mercadobitcoin;

import java.io.IOException;
import java.math.BigDecimal;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.knowm.xchange.mercadobitcoin.dto.v3.MercadoBitcoinBaseResponse;
import org.knowm.xchange.mercadobitcoin.dto.v3.account.MercadoBitcoinAccount;
import org.knowm.xchange.mercadobitcoin.dto.v3.trade.MercadoBitcoinOrder;
import org.knowm.xchange.mercadobitcoin.dto.v3.trade.MercadoBitcoinOrderResponse;
import org.knowm.xchange.mercadobitcoin.dto.v3.trade.MercadoBitcoinOrdersResponse;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

/**
 * @author Felipe Micaroni Lalli
 * @see org.knowm.xchange.mercadobitcoin.MercadoBitcoin
 */
@Path("tapi")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
public interface MercadoBitcoinAuthenticated {
  @POST
  @Path("v3/")
  public MercadoBitcoinBaseResponse<MercadoBitcoinOrderResponse> getOrder(@HeaderParam("TAPI-ID") String key,
                                                                          @HeaderParam("TAPI-MAC") ParamsDigest sign,
                                                                          @FormParam("tapi_method") String method,
                                                                          @FormParam("tapi_nonce") SynchronizedValueFactory<Long> nonce,
                                                                          @FormParam("coin_pair") String coinPair,
                                                                          @FormParam("order_id") String id) throws IOException;

  @POST
  @Path("v3/")
  public MercadoBitcoinBaseResponse<MercadoBitcoinAccount> getInfo(@HeaderParam("TAPI-ID") String key,
                                                                   @HeaderParam("TAPI-MAC") ParamsDigest sign,
                                                                   @FormParam("tapi_method") String method,
                                                                   @FormParam("tapi_nonce") SynchronizedValueFactory<Long> nonce) throws IOException;

  @POST
  @Path("v3/")
  public MercadoBitcoinBaseResponse<MercadoBitcoinOrdersResponse> getOrderList(@HeaderParam("TAPI-ID") String key,
                                                                               @HeaderParam("TAPI-MAC") ParamsDigest sign,
                                                                               @FormParam("tapi_method") String method,
                                                                               @FormParam("tapi_nonce") SynchronizedValueFactory<Long> nonce,
                                                                               @FormParam("coin_pair") String coinPair,
                                                                               @FormParam("status_list") String statusList) throws IOException;

  @POST
  @Path("v3/")
  public MercadoBitcoinBaseResponse<MercadoBitcoinOrderResponse> placeLimitOrder(@HeaderParam("TAPI-ID") String key,
                                                                                 @HeaderParam("TAPI-MAC") ParamsDigest sign,
                                                                                 @FormParam("tapi_method") String method,
                                                                                 @FormParam("tapi_nonce") SynchronizedValueFactory<Long> nonce,
                                                                                 @FormParam("coin_pair") String pair,
                                                                                 @FormParam("quantity") BigDecimal quantity,
                                                                                 @FormParam("limit_price") BigDecimal limitPrice) throws IOException;

  @POST
  @Path("v3/")
  public MercadoBitcoinBaseResponse<MercadoBitcoinOrderResponse> cancelOrder(@HeaderParam("TAPI-ID") String key,
                                                                             @HeaderParam("TAPI-MAC") ParamsDigest sign,
                                                                             @FormParam("tapi_method") String method,
                                                                             @FormParam("tapi_nonce") SynchronizedValueFactory<Long> nonce,
                                                                             @FormParam("coin_pair") String coinPair,
                                                                             @FormParam("order_id") String id) throws IOException;
}
