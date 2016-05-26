package org.knowm.xchange.quoine.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author timmolter
 */
public final class QuoineTradesList {

  private final List<QuoineTrade> trades;
  private final Integer currentPage;
  private final Integer totalPages;

  /**
   * Constructor
   *
   * @param trades
   * @param currentPage
   * @param totalPages
   */
  public QuoineTradesList(@JsonProperty("models") List<QuoineTrade> trades,
                          @JsonProperty("current_page") Integer currentPage,
                          @JsonProperty("total_pages") Integer totalPages) {
    this.trades = trades;
    this.currentPage = currentPage;
    this.totalPages = totalPages;
  }

  public List<QuoineTrade> getTrades() {
    return trades;
  }

  public Integer getCurrentPage() {
    return currentPage;
  }

  public Integer getTotalPages() {
    return totalPages;
  }

  @Override
  public String toString() {
    return "QuoineOrdersList [models=" + trades + ", currentPage=" + currentPage + ", totalPages=" + totalPages + "]";
  }

}
