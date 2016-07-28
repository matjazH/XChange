package org.knowm.xchange.coincheck;

import org.knowm.xchange.coincheck.dto.CoincheckOrderType;
import org.knowm.xchange.coincheck.dto.account.CoincheckBalance;
import org.knowm.xchange.coincheck.dto.trade.*;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

import javax.ws.rs.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 02/06/16
 * Time: 16:31
 */

@Path("api")
public interface CoincheckAuthenticated extends Coincheck {

  String HEADER_KEY = "ACCESS-KEY";
  String HEADER_NONCE = "ACCESS-NONCE";
  String HEADER_SIGNATURE = "ACCESS-SIGNATURE";

  @GET
  @Path("accounts/balance")
  CoincheckBalance getBalance(@HeaderParam(HEADER_KEY) String apiKey,
                              @HeaderParam(HEADER_NONCE) SynchronizedValueFactory<Long> nonce,
                              @HeaderParam(HEADER_SIGNATURE) ParamsDigest sign) throws IOException;

  @GET
  @Path("exchange/orders/opens")
  CoincheckOrders getOpenOrders(@HeaderParam(HEADER_KEY) String apiKey,
                                @HeaderParam(HEADER_NONCE) SynchronizedValueFactory<Long> nonce,
                                @HeaderParam(HEADER_SIGNATURE) ParamsDigest sign) throws IOException;

  @GET
  @Path("exchange/orders/transactions")
  CoincheckTransactions getTransactions(@HeaderParam(HEADER_KEY) String apiKey,
                                        @HeaderParam(HEADER_NONCE) SynchronizedValueFactory<Long> nonce,
                                        @HeaderParam(HEADER_SIGNATURE) ParamsDigest sign) throws IOException;

  @POST
  @Path("exchange/orders")
  CoincheckOrderResponse createNewOrder(@HeaderParam(HEADER_KEY) String apiKey,
                                        @HeaderParam(HEADER_NONCE) SynchronizedValueFactory<Long> nonce,
                                        @HeaderParam(HEADER_SIGNATURE) ParamsDigest sign,
                                        @FormParam("order_type") CoincheckOrderType orderType,
                                        @FormParam("pair") String pair,
                                        @FormParam("rate") BigDecimal rate,
                                        @FormParam("amount") BigDecimal amount,
                                        @FormParam("market_buy_amount") BigDecimal marketBuyAmount,
                                        @FormParam("position_id") BigDecimal position_id,
                                        @FormParam("stop_loss_rate") BigDecimal stopLossRate) throws IOException;

  @DELETE
  @Path("exchange/orders/{id}")
  CoincheckOrderResponse cancelOrder(@HeaderParam(HEADER_KEY) String apiKey,
                                     @HeaderParam(HEADER_NONCE) SynchronizedValueFactory<Long> nonce,
                                     @HeaderParam(HEADER_SIGNATURE) ParamsDigest sign,
                                     @PathParam("id") Long id) throws IOException;
}
