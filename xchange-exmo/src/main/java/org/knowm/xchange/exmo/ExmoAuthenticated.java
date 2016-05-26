package org.knowm.xchange.exmo;

import org.knowm.xchange.exmo.dto.ExmoResult;
import org.knowm.xchange.exmo.dto.account.ExmoUserInfo;
import org.knowm.xchange.exmo.dto.trade.ExmoOrder;
import org.knowm.xchange.exmo.dto.trade.ExmoTradesResult;
import org.knowm.xchange.exmo.dto.trade.ExmoUserTrade;
import org.knowm.xchange.exmo.dto.trade.OrderResult;
import org.knowm.xchange.exmo.service.polling.ExmoBasePollingService;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 17/12/15
 * Time: 16:01
 */
@Path("v1")
@Produces(MediaType.APPLICATION_JSON)
public interface ExmoAuthenticated {

  @POST
  @Path("user_info")
  public ExmoUserInfo getUserInfo(@HeaderParam("Key") String apiKey,
                                  @HeaderParam("Sign") ParamsDigest signer,
                                  @FormParam("nonce") SynchronizedValueFactory<Long> nonce) throws IOException;

  @POST
  @Path("deposit_address")
  public Map<String, String> getDepositAddress(@HeaderParam("Key") String apiKey,
                                               @HeaderParam("Sign") ParamsDigest signer,
                                               @FormParam("nonce") SynchronizedValueFactory<Long> nonce) throws IOException;

  @POST
  @Path("user_open_orders")
  public Map<String, List<ExmoOrder>> getUserOpenOrders(@HeaderParam("Key") String apiKey,
                                                        @HeaderParam("Sign") ParamsDigest signer,
                                                        @FormParam("nonce") SynchronizedValueFactory<Long> nonce) throws IOException;

  @POST
  @Path("user_trades")
  public Map<String, List<ExmoUserTrade>> getUserTrades(@HeaderParam("Key") String apiKey,
                                                        @HeaderParam("Sign") ParamsDigest signer,
                                                        @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
                                                        @FormParam("pair") String pair,
                                                        @FormParam("offset") Integer offset,
                                                        @FormParam("limit") Integer limit) throws IOException;

  @POST
  @Path("order_create")
  public OrderResult createOrder(@HeaderParam("Key") String apiKey,
                                 @HeaderParam("Sign") ParamsDigest signer,
                                 @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
                                 @FormParam("pair") String pair,
                                 @FormParam("quantity") BigDecimal quantity,
                                 @FormParam("price") BigDecimal price,
                                 @FormParam("type") ExmoBasePollingService.ExmoOrderType type,
                                 @FormParam("tags") String tags) throws IOException;

  @POST
  @Path("order_cancel")
  public ExmoResult cancelOrder(@HeaderParam("Key") String apiKey,
                                @HeaderParam("Sign") ParamsDigest signer,
                                @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
                                @FormParam("order_id") String order_id) throws IOException;


  @POST
  @Path("get_trades_by_tag")
  public ExmoTradesResult getTradesByTag(@HeaderParam("Key") String apiKey,
                                         @HeaderParam("Sign") ParamsDigest signer,
                                         @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
                                         @FormParam("tag") String tag,
                                         @FormParam("begin") Long begin,
                                         @FormParam("end") Long end) throws IOException;

}
