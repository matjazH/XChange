package com.xeiam.xchange.quoine;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.xeiam.xchange.quoine.dto.marketdata.QuoineOrderBook;
import com.xeiam.xchange.quoine.dto.marketdata.QuoineProduct;
import com.xeiam.xchange.quoine.dto.marketdata.QuoineTradesList;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface Quoine {

  @GET
  @Path("products/code/CASH/{currency_pair_code}")
  QuoineProduct getQuoineProduct(@PathParam("currency_pair_code") String currencyPairCode);

  @GET
  @Path("products")
  QuoineProduct[] getQuoineProducts();

  @GET
  @Path("products/{product_id}/price_levels")
  QuoineOrderBook getOrderBook(@PathParam("product_id") int currencyPairCode);

  @GET
  @Path("executions")
  QuoineTradesList getExecutions(@QueryParam("currency_pair_code") String currencyPairCode,
                                 @QueryParam("limit") Integer limit,
                                 @QueryParam("page") Integer page);
}
