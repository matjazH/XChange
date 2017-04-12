package org.knowm.xchange.gemini;

import org.knowm.xchange.gemini.dto.marketdata.GeminiOrderBook;
import org.knowm.xchange.gemini.dto.marketdata.GeminiTicker;
import org.knowm.xchange.gemini.dto.marketdata.GeminiTrade;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

/**
 * Created by tabtrader on 03/10/2016.
 */

@Path("v1")
@Produces(MediaType.APPLICATION_JSON)
public interface Gemini {

  @GET
  @Path("symbols")
  List<String> getSymbols() throws IOException;

  //GET https://api.gemini.com/v1/pubticker/:symbol
  @GET
  @Path("pubticker/{symbol}")
  GeminiTicker getTicker(@PathParam("symbol") String symbol) throws IOException;

  //GET https://api.gemini.com/v1/book/:symbol
  @GET
  @Path("book/{symbol}")
  GeminiOrderBook getOrderBook(@PathParam("symbol") String symbol,
                               @QueryParam("limit_bids") Integer limitBids,
                               @QueryParam("limit_asks") Integer limitAsks) throws IOException;


  //GET https://api.gemini.com/v1/trades/:symbol
  @GET
  @Path("trades/{symbol}")
  List<GeminiTrade> getTrades(@PathParam("symbol") String symbol,
                              @QueryParam("since") Long since,
                              @QueryParam("limit_trades") Integer limitTrades,
                              @QueryParam("include_breaks") Boolean includeBreaks) throws IOException;
}
