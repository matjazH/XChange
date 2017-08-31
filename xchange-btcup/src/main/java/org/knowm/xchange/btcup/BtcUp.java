package org.knowm.xchange.btcup;

import org.knowm.xchange.btcup.dto.BtcUpBaseResponse;
import org.knowm.xchange.btcup.dto.marketdata.BtcUpOrderBook;
import org.knowm.xchange.btcup.dto.marketdata.BtcUpPrice;
import org.knowm.xchange.btcup.dto.trade.BtcUpTrade;

import java.io.IOException;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;

/**
 * Created by VITTACH on 28/08/17.
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface BtcUp {
  @GET
  @Path("trades/{base}_{counter}")
  BtcUpBaseResponse<BtcUpTrade[]> getTrades(@PathParam("base") String base,
                                            @PathParam("counter") String counter,
                                            @QueryParam("limit") int limit) throws IOException;

  @GET
  @Path("ticker/{base}_{counter}")
  BtcUpBaseResponse<BtcUpPrice> getTicker(@PathParam("base") String base,
                                          @PathParam("counter") String counter) throws IOException;

  @GET
  @Path("depth/{base}_{counter}")
  BtcUpBaseResponse<BtcUpOrderBook> getOrderBook(@PathParam("base") String base,
                                                 @PathParam("counter") String counter,
                                                 @QueryParam("limit") int limit) throws IOException;
}