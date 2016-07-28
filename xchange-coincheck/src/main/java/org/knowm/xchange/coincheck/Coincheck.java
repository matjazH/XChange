package org.knowm.xchange.coincheck;

import org.knowm.xchange.coincheck.dto.marketdata.CoincheckOrderbook;
import org.knowm.xchange.coincheck.dto.marketdata.CoincheckTicker;
import org.knowm.xchange.coincheck.dto.marketdata.CoincheckTrade;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 */

@Path("api")
public interface Coincheck {


  @GET
  @Path("ticker")
  CoincheckTicker getTicker() throws IOException;

  @GET
  @Path("trades")
  List<CoincheckTrade> getTrades() throws IOException;

  @GET
  @Path("order_books")
  CoincheckOrderbook getOrderbook() throws IOException;

}
