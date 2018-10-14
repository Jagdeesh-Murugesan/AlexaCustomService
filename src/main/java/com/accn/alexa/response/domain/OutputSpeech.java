package com.accn.alexa.response.domain;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"type",
"ssml"
})
public class OutputSpeech {

/**
* 
* (Required)
* 
*/
@JsonProperty("type")
private String type;
/**
* 
* (Required)
* 
*/
@JsonProperty("ssml")
private String ssml;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* 
* (Required)
* 
*/
@JsonProperty("type")
public String getType() {
return type;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("type")
public void setType(String type) {
this.type = type;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("ssml")
public String getSsml() {
return ssml;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("ssml")
public void setSsml(String ssml) {
this.ssml = ssml;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
