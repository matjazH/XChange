package com.xeiam.xchange.exmo;

import com.xeiam.xchange.exmo.dto.marketdata.ExmoOrderbook;
import com.xeiam.xchange.exmo.dto.marketdata.ExmoPair;
import com.xeiam.xchange.exmo.dto.marketdata.ExmoTicker;
import com.xeiam.xchange.exmo.dto.marketdata.ExmoTrade;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 17/12/15
 * Time: 16:01
 */
@Path("v1")
@Produces(MediaType.APPLICATION_JSON)
public interface Exmo {

  @GET
  @Path("ticker")
  public Map<String, ExmoTicker> getTickers() throws IOException;

  @GET
  @Path("order_book")
  public Map<String, ExmoOrderbook> getOrderBook(@FormParam("pair") String pair) throws IOException;

  @GET
  @Path("trades")
  public Map<String, List<ExmoTrade>> getTrades(@FormParam("pair") String pair) throws IOException;

  @GET
  @Path("pair_settings")
  public Map<String, ExmoPair> getPairs() throws IOException;

}
