package org.knowm.xchange.therock.dto.trade;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;


@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class TheRockMeta {
  private long totalCount;
  private TheRockMetaItem first;
  private TheRockMetaItem previous;
  private TheRockMetaItem current;
  private TheRockMetaItem next;
  private TheRockMetaItem last;

  public long getTotalCount() {

    return this.totalCount;
  }

  public TheRockMetaItem getFirst() {

    return this.first;
  }

  public TheRockMetaItem getPrevious() {

    return this.previous;
  }

  public TheRockMetaItem getCurrent() {

    return this.current;
  }

  public TheRockMetaItem getNext() {

    return this.next;
  }

  public TheRockMetaItem getLast() {

    return this.last;
  }
}