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
"outputSpeech",
"reprompt",
"shouldEndSession"
})
public class Response {


@JsonIgnore
@JsonProperty("outputSpeech")
private OutputSpeech outputSpeech;

@JsonIgnore
@JsonProperty("reprompt")
private Reprompt reprompt;

@JsonIgnore
@JsonProperty("shouldEndSession")
private Boolean shouldEndSession;

@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();


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

/**
* 
* (Required)
* 
*/
@JsonProperty("reprompt")
public Reprompt getReprompt() {
return reprompt;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("reprompt")
public void setReprompt(Reprompt reprompt) {
this.reprompt = reprompt;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("shouldEndSession")
public Boolean getShouldEndSession() {
return shouldEndSession;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("shouldEndSession")
public void setShouldEndSession(Boolean shouldEndSession) {
this.shouldEndSession = shouldEndSession;
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
