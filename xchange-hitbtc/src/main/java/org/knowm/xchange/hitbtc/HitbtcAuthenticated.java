package org.knowm.xchange.hitbtc;

import java.io.IOException;
import java.math.BigDecimal;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.knowm.xchange.hitbtc.dto.HitbtcException;
import org.knowm.xchange.hitbtc.dto.trade.HitbtcOrder;
import org.knowm.xchange.hitbtc.dto.trade.HitbtcOwnTrade;
import org.knowm.xchange.hitbtc.dto.account.HitbtcBalance;
import org.knowm.xchange.hitbtc.dto.trade.HitbtcExecutionReport;
import org.knowm.xchange.hitbtc.dto.account.HitbtcDepositAddressResponse;

import si.mazi.rescu.ParamsDigest;

@Path("/api/2/")
public interface HitbtcAuthenticated extends Hitbtc {

  @GET
  @Path("history/trades")
  public HitbtcOwnTrade[] getHitbtcTrades(@HeaderParam("Authorization") ParamsDigest signature,
      @QueryParam("by") String by, @QueryParam("offset") int start_index,@QueryParam("limit") int max_results,
      @QueryParam("symbols") String symbols, @QueryParam("sort") String sort, @QueryParam("from") String from,
      @QueryParam("till") String till) throws IOException, HitbtcException;

  @GET
  @Path("order")
  public HitbtcOrder[] getHitbtcActiveOrders(@HeaderParam("Authorization") ParamsDigest signature) throws IOException, HitbtcException;

  @POST
  @Path("order")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public HitbtcExecutionReport postHitbtcNewOrder(@HeaderParam("Authorization") ParamsDigest signature,
      @FormParam("clientOrderId") String clientOrderId, @FormParam("symbol") String symbol,
      @FormParam("side") String side, @FormParam("price") BigDecimal price,
      @FormParam("quantity") BigDecimal quantity, @FormParam("type") String type,
      @FormParam("timeInForce") String timeInForce)
      throws IOException, HitbtcException;

  @DELETE
  @Path("order/{clientOrderId}")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public HitbtcExecutionReport postHitbtcCancelOrder(@HeaderParam("Authorization") ParamsDigest signature,
      @PathParam("clientOrderId") String clientOrderId) throws IOException, HitbtcException;

  @GET
  @Path("trading/balance")
  public HitbtcBalance[] getHitbtcBalance(@HeaderParam("Authorization") ParamsDigest signature) throws IOException, HitbtcException;

  @GET
  @Path("payment/address/{currency}")
  public HitbtcDepositAddressResponse getHitbtcDepositAddress(@PathParam("currency") String currency,
      @HeaderParam("Authorization") ParamsDigest signature) throws IOException, HitbtcException;
}
