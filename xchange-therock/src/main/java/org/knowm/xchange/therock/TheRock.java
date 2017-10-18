package org.knowm.xchange.therock;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.therock.dto.TheRockException;
import org.knowm.xchange.therock.dto.marketdata.TheRockFunds;
import org.knowm.xchange.therock.dto.marketdata.TheRockOrderBook;
import org.knowm.xchange.therock.dto.marketdata.TheRockTicker;
import org.knowm.xchange.therock.dto.marketdata.TheRockTrades;
import org.knowm.xchange.utils.jackson.CurrencyPairDeserializer;

@Path("v1")
@Produces({"application/json"})
public abstract interface TheRock {
  @GET
  @Path("funds/{id}/ticker")
  public abstract TheRockTicker getTicker(@PathParam("id") Pair paramPair)
      throws TheRockException, IOException;

  @GET
  @Path("funds/{id}/orderbook")
  public abstract TheRockOrderBook getOrderbook(@PathParam("id") Pair paramPair)
      throws TheRockException, IOException;

  @GET
  @Path("funds")
  public abstract TheRockFunds getFunds()
      throws TheRockException, IOException;

  @GET
  @Path("funds/{id}/trades")
  public abstract TheRockTrades getTrades(@PathParam("id") Pair paramPair, @QueryParam("after") Date paramDate) throws IOException;

  public static class Pair {
    public final CurrencyPair pair;

    public Pair(CurrencyPair pair) {
      if (pair == null) {
        throw new IllegalArgumentException("Currency pair required.");
      }
      this.pair = pair;
    }

    public Pair(String pair) {
      this(CurrencyPairDeserializer.getCurrencyPairFromString(pair));
    }

    public boolean equals(Object o) {
      return (this == o) || ((o != null) && (getClass() == o.getClass()) && (Objects.equals(this.pair, ((Pair) o).pair)));
    }

    public int hashCode() {
      return Objects.hash(new Object[]{this.pair});
    }

    public String toString() {
      return String.format("%s%s", new Object[]{this.pair.base.getCurrencyCode(), this.pair.counter.getCurrencyCode()});
    }
  }
}