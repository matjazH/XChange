package org.knowm.xchange.bitmex;

import java.util.Map;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.knowm.xchange.bitmex.dto.marketdata.BitMexInfo;
import org.knowm.xchange.bitmex.dto.marketdata.BitMexTrades;
import org.knowm.xchange.bitmex.dto.marketdata.BitMexTicker;
import org.knowm.xchange.bitmex.dto.marketdata.BitMexOrderBook;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface BitMex {
  @GET
  @Path("api/3/depth/{baseCurrency}_{targetCurrency}")
  BitMexOrderBook getOrderBook(@PathParam("baseCurrency") String baseCurrency,
                               @PathParam("targetCurrency") String targetCurrency, @QueryParam("limit") long limit)
      throws IOException;

  @GET
  @Path("api/v1/trade")
  BitMexTrades getTrades(@PathParam("baseCurrency") String baseCurrency, @PathParam("targetCurrency") String target)
      throws IOException;

}