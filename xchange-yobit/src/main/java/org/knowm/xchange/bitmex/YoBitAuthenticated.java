package org.knowm.xchange.bitmex;

import javax.ws.rs.*;
import java.util.Map;

import si.mazi.rescu.SynchronizedValueFactory;
import org.knowm.xchange.bitmex.dto.YoBitResult;

import org.knowm.xchange.bitmex.dto.account.YoBitAccount;
import org.knowm.xchange.bitmex.dto.trade.YoBitActiveOrder;

import java.io.IOException;
import java.math.BigDecimal;
import javax.ws.rs.core.MediaType;
import si.mazi.rescu.ParamsDigest;
import org.knowm.xchange.bitmex.dto.trade.YoBitorderInform;
import org.knowm.xchange.bitmex.dto.trade.YoBitTradesResponse;

@Path("tapi")
@Produces(MediaType.APPLICATION_JSON)
public interface YoBitAuthenticated extends YoBit {

  @POST
  public YoBitResult<YoBitAccount> getInfo(@HeaderParam("Key") String key,
                                           @HeaderParam("Sign") ParamsDigest sign,
                                           @FormParam("method") String method,
                                           @FormParam("nonce") SynchronizedValueFactory<Long> nonce) throws IOException;

  @POST
  public YoBitResult<YoBitAccount> getAddress(@HeaderParam("Key") String key,
                                              @HeaderParam("Sign") ParamsDigest sign,
                                              @FormParam("method") String method,
                                              @FormParam("coinName") String coinName,
                                              @FormParam("need_new") Integer npw,
                                              @FormParam("nonce") SynchronizedValueFactory<Long> nonce) throws IOException;


  @POST
  public YoBitResult<YoBitTradesResponse> trade(@HeaderParam("Key") String key,
                                                @HeaderParam("Sign") ParamsDigest sign,
                                                @FormParam("pair") String pair,
                                                @FormParam("type") String type,
                                                @FormParam("rate") BigDecimal rate,
                                                @FormParam("amount") BigDecimal amount,
                                                @FormParam("method") String method,
                                                @FormParam("nonce")SynchronizedValueFactory<Long>nonce) throws IOException;

  @POST
  public YoBitResult<YoBitTradesResponse> cancelOrder(@HeaderParam("Key") String key,
                                                      @HeaderParam("Sign") ParamsDigest sign,
                                                      @FormParam("order_id") BigDecimal orderId,
                                                      @FormParam("method") String method,
                                                      @FormParam("nonce") SynchronizedValueFactory<Long> nonce) throws IOException;

  @POST
  public YoBitResult<Map<String, YoBitorderInform>> orderInfo(@HeaderParam("Key") String key,
                                                              @HeaderParam("Sign") ParamsDigest sign,
                                                              @FormParam("order_id") BigDecimal orderId,
                                                              @FormParam("method") String method,
                                                              @FormParam("nonce") SynchronizedValueFactory<Long> nonce) throws IOException;

  @POST
  public YoBitResult<Map<String, YoBitActiveOrder>> activeOrder(@HeaderParam("Key") String key,
                                                                @HeaderParam("Sign") ParamsDigest sign,
                                                                @FormParam("method") String method,
                                                                @FormParam("pair") String pair,
                                                                @FormParam("nonce")SynchronizedValueFactory<Long>nonce) throws IOException;
}

