package org.knowm.xchange.yobit;

import java.util.Map;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.knowm.xchange.yobit.dto.marketdata.YoBitInfo;
import org.knowm.xchange.yobit.dto.marketdata.YoBitTrades;
import org.knowm.xchange.yobit.dto.marketdata.YoBitTicker;
import org.knowm.xchange.yobit.dto.marketdata.YoBitOrderBook;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface YoBit {
  @GET
  @Path("api/3/info")
  YoBitInfo getProducts() throws IOException;

  @GET
  @Path("api/3/ticker/{baseCurrency}_{targetCurrency}")
  Map<String, YoBitTicker> getYoBitTicker(@PathParam("baseCurrency") String baseCurrency,
                                          @PathParam("targetCurrency") String targetCurrency) throws IOException;

  @GET
  @Path("api/3/depth/{baseCurrency}_{targetCurrency}")
  YoBitOrderBook getOrderBook(@PathParam("baseCurrency") String baseCurrency,
                              @PathParam("targetCurrency") String targetCurrency, @QueryParam("limit") long limit)
      throws IOException;

  @GET
  @Path("api/3/trades/{baseCurrency}_{targetCurrency}")
  YoBitTrades getTrades(@PathParam("baseCurrency") String baseCurrency, @PathParam("targetCurrency") String target, @QueryParam("limit") long limit)
      throws IOException;

}