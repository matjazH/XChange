package org.knowm.xchange.ccex.dto.ticker;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({"pairs"})
public class CCEXPairs {

  @JsonProperty("pairs")
  private List<String> pairs = new ArrayList<String>();
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonAnyGetter
  public Map<String,Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  public CCEXPairs withAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
    return this;
  }

  /**
   * No args constructor for use in serialization
   */
  public CCEXPairs() {
  }

  /**
   * @return The pairs
   */
  @JsonProperty("pairs")
  public List<String> getPairs() {
    return pairs;
  }

  /**
   * @param pairs
   */
  public CCEXPairs(List<String> pairs) {
    this.pairs = pairs;
  }

  /**
   * @param pairs The pairs
   */
  @JsonProperty("pairs")
  public void setPairs(List<String> pairs) {
    this.pairs = pairs;
  }

  public CCEXPairs withPairs(List<String> pairs) {
    this.pairs = pairs;
    return this;
  }
}