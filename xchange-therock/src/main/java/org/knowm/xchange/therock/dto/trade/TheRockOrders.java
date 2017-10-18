package org.knowm.xchange.therock.dto.trade;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;


@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class TheRockOrders
{
  private TheRockOrder[] orders;
  private TheRockMeta meta;
  
  protected TheRockOrders() {}
  
  public TheRockOrders(TheRockOrder[] orders)
  {
    this.orders = orders;
  }
  
  public TheRockOrder[] getOrders() {
    return this.orders;
  }
  
  public TheRockMeta getMeta() {
    return this.meta;
  }
}