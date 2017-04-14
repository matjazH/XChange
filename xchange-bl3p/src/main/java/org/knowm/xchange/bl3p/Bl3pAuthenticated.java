package org.knowm.xchange.bl3p;

import org.knowm.xchange.bl3p.dto.Bl3pError;
import org.knowm.xchange.bl3p.dto.Bl3pResult;
import org.knowm.xchange.bl3p.dto.account.Bl3pAccountInfo;
import org.knowm.xchange.bl3p.dto.marketdata.Bl3pTrades;
import org.knowm.xchange.bl3p.dto.trade.Bl3pOpenOrders;
import org.knowm.xchange.bl3p.dto.trade.Bl3pOrder;
import org.knowm.xchange.bl3p.dto.trade.Bl3pOrderId;
import si.mazi.rescu.ParamsDigest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 17/08/15
 * Time: 16:23
 */

@Path("1")
@Produces(MediaType.APPLICATION_JSON)
public interface Bl3pAuthenticated extends Bl3p {

  @GET
  @Path("GENMKT/money/info")
  Bl3pResult<Bl3pAccountInfo> getAccountInfo(@HeaderParam("Rest-Key") String apiKey,
                                             @HeaderParam("Rest-Sign") ParamsDigest signer) throws IOException;

  @GET
  @Path("{market}/money/order/add")
  Bl3pResult<Bl3pOrderId> addOrder(@HeaderParam("Rest-Key") String apiKey,
                                   @HeaderParam("Rest-Sign") ParamsDigest signer,
                                   @PathParam("market") String market,
                                   @FormParam("type") String type,
                                   @FormParam("amount_int") Integer amountInt,
                                   @FormParam("price_int") Integer priceInt,
                                   @FormParam("fee_currency") String feeCurrency) throws IOException;

  @GET
  @Path("{market}/money/order/cancel")
  Bl3pResult<Bl3pError> cancelOrder(@HeaderParam("Rest-Key") String apiKey,
                                    @HeaderParam("Rest-Sign") ParamsDigest signer,
                                    @PathParam("market") String market,
                                    @FormParam("order_id") String orderId) throws IOException;

  @GET
  @Path("{market}/money/order/result")
  Bl3pResult<Bl3pOrder> orderResult(@HeaderParam("Rest-Key") String apiKey,
                                    @HeaderParam("Rest-Sign") ParamsDigest signer,
                                    @PathParam("market") String market,
                                    @FormParam("order_id") String orderId) throws IOException;

  @GET
  @Path("{market}/money/orders")
  Bl3pResult<Bl3pOpenOrders> openOrders(@HeaderParam("Rest-Key") String apiKey,
                                        @HeaderParam("Rest-Sign") ParamsDigest signer,
                                        @PathParam("market") String market) throws IOException;


  @GET
  @Path("{market}/money/trades/fetch")
  Bl3pResult<Bl3pTrades> getTrades(@HeaderParam("Rest-Key") String apiKey,
                                   @HeaderParam("Rest-Sign") ParamsDigest signer,
                                   @PathParam("market") String market,
                                   @FormParam("trade_id") Long tradeId) throws IOException;
}

