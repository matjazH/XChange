package org.knowm.xchange.gemini.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by tabtrader on 03/10/2016.
 */
public class GeminiStreamingResponse {

  private Type type;
  private Long eventId;
  private List<GeminiStreamingEvent> events;

  public GeminiStreamingResponse(@JsonProperty("type") Type type,
                                 @JsonProperty("eventId") Long eventId,
                                 @JsonProperty("events") List<GeminiStreamingEvent> events) {
    this.type = type;
    this.eventId = eventId;
    this.events = events;
  }

  public Type getType() {
    return type;
  }

  public Long getEventId() {
    return eventId;
  }

  public List<GeminiStreamingEvent> getEvents() {
    return events;
  }

  @Override
  public String toString() {
    return "GeminiStreamingResponse{" +
        "type=" + type +
        ", eventId=" + eventId +
        ", events=" + events +
        '}';
  }

  public enum Type {
    update,
    heartbeat
  }
}

