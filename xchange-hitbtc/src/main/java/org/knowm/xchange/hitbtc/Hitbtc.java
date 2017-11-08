package org.knowm.xchange.hitbtc;

import java.io.IOException;
import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.knowm.xchange.hitbtc.dto.HitbtcException;
import org.knowm.xchange.hitbtc.dto.marketdata.*;

/**
 * @author kpysniak
 */
@Path("/api/2/")
@Produces(MediaType.APPLICATION_JSON)
public interface Hitbtc {

  @GET
  @Path("public/symbol")
  public HitbtcSymbol[] getSymbols() throws IOException, HitbtcException;

  @GET
  @Path("public/ticker")
  public Map<String, HitbtcTicker> getHitbtcTickers() throws IOException, HitbtcException;

  @GET
  @Path("public/trades/{currencyPair}")
  public HitbtcTrade[] getTradesRecent(@PathParam("currencyPair") String currencyPair,
                                       @DefaultValue("1000") @QueryParam("max_results") String max_results) throws IOException, HitbtcException;

  @GET
  @Path("public/ticker/{currencyPair}")
  public HitbtcTicker getHitbtcTicker(@PathParam("currencyPair") String currencyPair) throws IOException, HitbtcException;

  @GET
  @Path("public/orderbook/{currencyPair}")
  public HitbtcOrderBookResponse getOrderBook(@PathParam("currencyPair") String currencyPair) throws IOException, HitbtcException;

  @GET
  @Path("public/trades/{currencyPair}/")
  public HitbtcTrade[] getTrades(@PathParam("currencyPair") String currencyPair,@QueryParam("from") String from, @QueryParam("by") String sortBy,
      @QueryParam("sort") String sort, @QueryParam("start_index") String startIndex,
      @DefaultValue("1000") @QueryParam("max_results") String max_results, @DefaultValue("object") @QueryParam("format_item") String format_item,
      @DefaultValue("true") @QueryParam("side") String side) throws IOException, HitbtcException;

}
