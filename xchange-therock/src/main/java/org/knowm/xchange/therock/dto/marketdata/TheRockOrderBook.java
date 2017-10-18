package org.knowm.xchange.therock.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.Date;
import java.util.List;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.utils.jackson.CurrencyPairDeserializer;


@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class TheRockOrderBook
{
  @JsonProperty("fund_id")
  @JsonDeserialize(using=CurrencyPairDeserializer.class)
  private CurrencyPair currencyPair;
  private Date date;
  private List<TheRockBid> bids;
  private List<TheRockBid> asks;
  
  public CurrencyPair getCurrencyPair()
  {
    return this.currencyPair;
  }
  
  public Date getDate() {
    return this.date;
  }
  
  public List<TheRockBid> getBids() {
    return this.bids;
  }
  
  public List<TheRockBid> getAsks() {
    return this.asks;
  }
}