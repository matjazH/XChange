package org.knowm.xchange.btce.v3;

import java.io.IOException;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.knowm.xchange.btce.v3.dto.marketdata.WEXDepthWrapper;
import org.knowm.xchange.btce.v3.dto.marketdata.WEXExchangeInfo;
import org.knowm.xchange.btce.v3.dto.marketdata.WEXTickerWrapper;
import org.knowm.xchange.btce.v3.dto.marketdata.WEXTradesWrapper;

/**
 * @author timmolter
 */
@Path("/")
public interface WEX {

  @GET
  @Path("api/3/info")
  WEXExchangeInfo getInfo() throws IOException;

  @GET
  @Path("api/3/ticker/{pairs}")
  @Produces(MediaType.APPLICATION_JSON)
  WEXTickerWrapper getTicker(@PathParam("pairs") String pairs, @DefaultValue("1") @QueryParam("ignore_invalid") int ignoreInvalid)
      throws IOException;

  @GET
  @Path("api/3/depth/{pairs}")
  @Produces(MediaType.APPLICATION_JSON)
  WEXDepthWrapper getDepth(@PathParam("pairs") String pairs, @DefaultValue("150") @QueryParam("limit") int limit,
                           @DefaultValue("1") @QueryParam("ignore_invalid") int ignoreInvalid) throws IOException;

  @GET
  @Path("api/3/trades/{pairs}")
  @Produces(MediaType.APPLICATION_JSON)
  WEXTradesWrapper getTrades(@PathParam("pairs") String pairs, @DefaultValue("1") @QueryParam("limit") int limit,
                             @DefaultValue("1") @QueryParam("ignore_invalid") int ignoreInvalid) throws IOException;

}
