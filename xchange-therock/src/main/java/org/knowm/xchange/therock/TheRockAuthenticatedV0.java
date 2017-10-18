package org.knowm.xchange.therock;

import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.knowm.xchange.therock.dto.TheRockException;
import org.knowm.xchange.therock.dto.trade.TheRockOrder;
import org.knowm.xchange.therock.service.TheRockDigest;
import si.mazi.rescu.SynchronizedValueFactory;

@Path("api")
@Consumes({"application/json"})
@Produces({"application/json"})
public abstract interface TheRockAuthenticatedV0
{
  public static final String X_TRT_NONCE = "X-TRT-NONCE";
  
  @POST
  @Path("get_orders")
  public abstract TheRockOrder placeOrder(@HeaderParam("X-TRT-KEY") String paramString, @HeaderParam("X-TRT-SIGN") TheRockDigest paramTheRockDigest, @HeaderParam("X-TRT-NONCE") SynchronizedValueFactory<Long> paramSynchronizedValueFactory, TheRockOrder paramTheRockOrder)
    throws TheRockException, IOException;
}