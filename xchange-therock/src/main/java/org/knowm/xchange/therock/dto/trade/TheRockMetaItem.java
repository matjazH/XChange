package org.knowm.xchange.therock.dto.trade;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;


@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class TheRockMetaItem {
  private int page;
  private String href;

  public int getPage() {
    return this.page;
  }

  public String getHref() {
    return this.href;
  }
}