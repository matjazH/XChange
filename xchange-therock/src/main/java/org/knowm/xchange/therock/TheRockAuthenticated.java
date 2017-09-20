package org.knowm.xchange.therock;

import java.io.IOException;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.knowm.xchange.therock.dto.TheRockException;
import org.knowm.xchange.therock.dto.account.TheRockBalance;
import org.knowm.xchange.therock.dto.account.TheRockBalances;
import org.knowm.xchange.therock.dto.account.TheRockWithdrawal;
import org.knowm.xchange.therock.dto.account.TheRockWithdrawalResponse;
import org.knowm.xchange.therock.dto.trade.TheRockOrder;
import org.knowm.xchange.therock.dto.trade.TheRockOrders;
import org.knowm.xchange.therock.dto.trade.TheRockTransactions;
import org.knowm.xchange.therock.dto.trade.TheRockUserTrades;
import org.knowm.xchange.therock.service.TheRockDigest;
import si.mazi.rescu.SynchronizedValueFactory;

@Path("v1")
@Produces({"application/json"})
public abstract interface TheRockAuthenticated
{
  public static final String X_TRT_SIGN = "X-TRT-SIGN";
  public static final String X_TRT_KEY = "X-TRT-KEY";
  public static final String X_TRT_NONCE = "X-TRT-NONCE";
  
  @GET
  @Path("balances/{currency}")
  public abstract TheRockBalance balance(@HeaderParam("X-TRT-KEY") String paramString1, @HeaderParam("X-TRT-SIGN") TheRockDigest paramTheRockDigest, @HeaderParam("X-TRT-NONCE") SynchronizedValueFactory<Long> paramSynchronizedValueFactory, @PathParam("currency") String paramString2)
    throws TheRockException, IOException;
  
  @GET
  @Path("balances")
  public abstract TheRockBalances balances(@HeaderParam("X-TRT-KEY") String paramString, @HeaderParam("X-TRT-SIGN") TheRockDigest paramTheRockDigest, @HeaderParam("X-TRT-NONCE") SynchronizedValueFactory<Long> paramSynchronizedValueFactory)
    throws TheRockException, IOException;
  
  @POST
  @Consumes({"application/json"})
  @Path("atms/withdraw")
  public abstract TheRockWithdrawalResponse withdraw(@HeaderParam("X-TRT-KEY") String paramString, @HeaderParam("X-TRT-SIGN") TheRockDigest paramTheRockDigest, @HeaderParam("X-TRT-NONCE") SynchronizedValueFactory<Long> paramSynchronizedValueFactory, TheRockWithdrawal paramTheRockWithdrawal)
    throws TheRockException, IOException;
  
  @POST
  @Consumes({"application/json"})
  @Path("funds/{fund_id}/orders")
  public abstract TheRockOrder placeOrder(@PathParam("fund_id") TheRock.Pair paramPair, @HeaderParam("X-TRT-KEY") String paramString, @HeaderParam("X-TRT-SIGN") TheRockDigest paramTheRockDigest, @HeaderParam("X-TRT-NONCE") SynchronizedValueFactory<Long> paramSynchronizedValueFactory, TheRockOrder paramTheRockOrder)
    throws TheRockException, IOException;
  
  @DELETE
  @Path("funds/{fund_id}/orders/{id}")
  public abstract TheRockOrder cancelOrder(@PathParam("fund_id") TheRock.Pair paramPair, @PathParam("id") Long paramLong, @HeaderParam("X-TRT-KEY") String paramString, @HeaderParam("X-TRT-SIGN") TheRockDigest paramTheRockDigest, @HeaderParam("X-TRT-NONCE") SynchronizedValueFactory<Long> paramSynchronizedValueFactory)
    throws TheRockException, IOException;
  
  @GET
  @Path("funds/{fund_id}/orders")
  public abstract TheRockOrders orders(@PathParam("fund_id") TheRock.Pair paramPair, @HeaderParam("X-TRT-KEY") String paramString, @HeaderParam("X-TRT-SIGN") TheRockDigest paramTheRockDigest, @HeaderParam("X-TRT-NONCE") SynchronizedValueFactory<Long> paramSynchronizedValueFactory)
    throws TheRockException, IOException;
  
  @GET
  @Path("funds/{fund_id}/orders/{id}")
  public abstract TheRockOrder showOrder(@PathParam("fund_id") TheRock.Pair paramPair, @PathParam("id") Long paramLong, @HeaderParam("X-TRT-KEY") String paramString, @HeaderParam("X-TRT-SIGN") TheRockDigest paramTheRockDigest, @HeaderParam("X-TRT-NONCE") SynchronizedValueFactory<Long> paramSynchronizedValueFactory)
    throws TheRockException, IOException;
  
  @GET
  @Path("funds/{fund_id}/trades")
  public abstract TheRockUserTrades trades(@PathParam("fund_id") TheRock.Pair paramPair, @HeaderParam("X-TRT-KEY") String paramString, @HeaderParam("X-TRT-SIGN") TheRockDigest paramTheRockDigest, @HeaderParam("X-TRT-NONCE") SynchronizedValueFactory<Long> paramSynchronizedValueFactory, @QueryParam("trade_id") Long paramLong, @QueryParam("after") Date paramDate1, @QueryParam("before") Date paramDate2, @QueryParam("per_page") int paramInt)
    throws TheRockException, IOException;
  
  @GET
  @Path("transactions")
  public abstract TheRockTransactions transactions(@HeaderParam("X-TRT-KEY") String paramString1, @HeaderParam("X-TRT-SIGN") TheRockDigest paramTheRockDigest, @HeaderParam("X-TRT-NONCE") SynchronizedValueFactory<Long> paramSynchronizedValueFactory, @QueryParam("type") String paramString2, @QueryParam("after") Date paramDate1, @QueryParam("before") Date paramDate2)
    throws TheRockException, IOException;
}


/* Location:              /Users/developer/Documents/tabtrader-android/app/libs/xchange-therock.jar!/org/knowm/xchange/therock/TheRockAuthenticated.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */