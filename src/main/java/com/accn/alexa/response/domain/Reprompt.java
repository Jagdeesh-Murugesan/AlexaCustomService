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
"outputSpeech"
})
public class Reprompt {

/**
* 
* (Required)
* 
*/
@JsonProperty("outputSpeech")
private OutputSpeech outputSpeech;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* 
* (Required)
* 
*/
@JsonProperty("outputSpeech")
public OutputSpeech getOutputSpeech() {
return outputSpeech;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("outputSpeech")
public void setOutputSpeech(OutputSpeech outputSpeech) {
this.outputSpeech = outputSpeech;
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
