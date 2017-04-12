package org.knowm.xchange.bl3p;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.knowm.xchange.bl3p.dto.Bl3pResult;
import org.knowm.xchange.bl3p.dto.marketdata.Bl3pOrderbook;
import org.knowm.xchange.bl3p.dto.marketdata.Bl3pTicker;
import org.knowm.xchange.bl3p.dto.marketdata.Bl3pTrades;

@Path("1")
@Produces(MediaType.APPLICATION_JSON)
public interface Bl3p {

  @GET
  @Path("/{market}/ticker")
  Bl3pTicker getTicker(@PathParam("market") String market) throws IOException;

  @GET
  @Path("/{market}/orderbook")
  Bl3pResult<Bl3pOrderbook> getOrderbook(@PathParam("market") String market) throws IOException;

  @GET
  @Path("/{market}/trades")
  Bl3pResult<Bl3pTrades> getLastTrades(@PathParam("market") String market) throws IOException;

}
