package org.knowm.xchange.btcup;

import org.knowm.xchange.btcup.dto.account.BtcUpAccount;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;
import org.knowm.xchange.btcup.dto.trade.BtcUpTrade;
import org.knowm.xchange.btcup.dto.trade.BtcUpOrder;
import org.knowm.xchange.btcup.dto.BtcUpBaseResponse;

import javax.ws.rs.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.ws.rs.core.MediaType;

/**
 * Created by VITTACH on 28/08/17.
 */
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public interface BtcUpAuthenticated extends BtcUp {
  @POST
  BtcUpBaseResponse<Object> cancelOrder(@HeaderParam("key") String apiKey,
                                        @HeaderParam("sign") ParamsDigest signer,
                                        @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
                                        @FormParam("method") String method,
                                        @FormParam("order_id") String orderId) throws IOException;

  @POST
  BtcUpBaseResponse<BtcUpAccount> account(@HeaderParam("key") String apiKey,
                                          @HeaderParam("sign") ParamsDigest signer,
                                          @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
                                          @FormParam("method") String method) throws IOException;

  @POST
  BtcUpBaseResponse<BtcUpOrder> orderInfo(@HeaderParam("key") String apiKey,
                                          @HeaderParam("sign") ParamsDigest signer,
                                          @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
                                          @FormParam("method") String method,
                                          @FormParam("order_id") String orderId) throws IOException;

  @POST
  BtcUpBaseResponse<BtcUpOrder> placeOrder(@HeaderParam("key") String apiKey,
                                           @HeaderParam("sign") ParamsDigest signer,
                                           @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
                                           @FormParam("method") String method,
                                           @FormParam("pair") String pair,
                                           @FormParam("side") Integer side,
                                           @FormParam("amount") BigDecimal amount,
                                           @FormParam("price") BigDecimal price) throws IOException;

  @POST
  BtcUpBaseResponse<List<BtcUpOrder>> openOrders(@HeaderParam("key") String apiKey,
                                                 @HeaderParam("sign") ParamsDigest signer,
                                                 @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
                                                 @FormParam("method") String method) throws IOException;

  @POST
  BtcUpBaseResponse<BtcUpOrder> placeMarketOrder(@HeaderParam("key") String apiKey,
                                                 @HeaderParam("sign") ParamsDigest signer,
                                                 @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
                                                 @FormParam("method") String method,
                                                 @FormParam("pair") String pair,
                                                 @FormParam("side") Integer side,
                                                 @FormParam("base_cur_amount") Integer baseCurAmount,
                                                 @FormParam("amount") BigDecimal amount) throws IOException;

  @POST
  BtcUpBaseResponse<List<BtcUpTrade>> tradeHistory(@HeaderParam("key") String apiKey,
                                                   @HeaderParam("sign") ParamsDigest signer,
                                                   @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
                                                   @FormParam("method") String method,
                                                   @FormParam("limit") int limit) throws IOException;

  @POST
  BtcUpBaseResponse<List<BtcUpTrade>> tradeHistory(@HeaderParam("key") String apiKey,
                                                   @HeaderParam("sign") ParamsDigest signer,
                                                   @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
                                                   @FormParam("method") String method,
                                                   @FormParam("limit") int limit,
                                                   @FormParam("pair") String pair) throws IOException;

}