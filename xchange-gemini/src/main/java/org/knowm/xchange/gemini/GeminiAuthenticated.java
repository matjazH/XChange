package org.knowm.xchange.gemini;

import org.knowm.xchange.gemini.dto.GeminiBaseRequest;
import org.knowm.xchange.gemini.dto.account.GeminiBalance;
import org.knowm.xchange.gemini.dto.trade.*;
import si.mazi.rescu.ParamsDigest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

/**
 * Created by tabtrader on 04/10/2016.
 */

@Path("v1")
@Produces(MediaType.APPLICATION_JSON)
public interface GeminiAuthenticated extends Gemini {

  String HEADER_APIKEY = "X-GEMINI-APIKEY";
  String HEADER_PAYLOAD = "X-GEMINI-PAYLOAD";
  String HEADER_SIGNATURE = "X-GEMINI-SIGNATURE";


  @POST
  @Path("balances")
  List<GeminiBalance> getBalances(@HeaderParam(HEADER_APIKEY) String apiKey,
                                  @HeaderParam(HEADER_PAYLOAD) GeminiBaseRequest request,
                                  @HeaderParam(HEADER_SIGNATURE) ParamsDigest signer) throws IOException;

  @POST
  @Path("orders")
  List<GeminiOrder> getOrders(@HeaderParam(HEADER_APIKEY) String apiKey,
                              @HeaderParam(HEADER_PAYLOAD) GeminiBaseRequest request,
                              @HeaderParam(HEADER_SIGNATURE) ParamsDigest signer) throws IOException;

  @POST
  @Path("mytrades")
  List<GeminiUserTrade> getTrades(@HeaderParam(HEADER_APIKEY) String apiKey,
                                  @HeaderParam(HEADER_PAYLOAD) GeminiTradesRequest request,
                                  @HeaderParam(HEADER_SIGNATURE) ParamsDigest signer) throws IOException;

  @POST
  @Path("order/status")
  GeminiOrder getOrderStatus(@HeaderParam(HEADER_APIKEY) String apiKey,
                             @HeaderParam(HEADER_PAYLOAD) GeminiOrderRequest request,
                             @HeaderParam(HEADER_SIGNATURE) ParamsDigest signer) throws IOException;

  @POST
  @Path("order/new")
  GeminiOrder postNewOrder(@HeaderParam(HEADER_APIKEY) String apiKey,
                           @HeaderParam(HEADER_PAYLOAD) GeminiNewOrderRequest request,
                           @HeaderParam(HEADER_SIGNATURE) ParamsDigest signer) throws IOException;

  @POST
  @Path("order/cancel")
  GeminiOrder cancelOrder(@HeaderParam(HEADER_APIKEY) String apiKey,
                          @HeaderParam(HEADER_PAYLOAD) GeminiOrderRequest request,
                          @HeaderParam(HEADER_SIGNATURE) ParamsDigest signer) throws IOException;
}
