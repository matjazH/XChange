package org.knowm.xchange.unocoin;

import org.knowm.xchange.unocoin.dto.marketdata.UnoCoinInfo;

import javax.ws.rs.*;
import java.io.IOException;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface UnoCoin {
  @GET
  @Path("trade?all")
  UnoCoinInfo getAllInfo() throws IOException;

}