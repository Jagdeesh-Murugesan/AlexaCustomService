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
"version",
"response",
"userAgent"
})
public class Body {

/**
* 
* (Required)
* 
*/
@JsonProperty("version")
private String version;
/**
* 
* (Required)
* 
*/
@JsonProperty("response")
private Response response;
/**
* 
* (Required)
* 
*/
@JsonIgnore
@JsonProperty("userAgent")
private String userAgent;

@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* 
* (Required)
* 
*/
@JsonProperty("version")
public String getVersion() {
return version;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("version")
public void setVersion(String version) {
this.version = version;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("response")
public Response getResponse() {
return response;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("response")
public void setResponse(Response response) {
this.response = response;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("userAgent")
public String getUserAgent() {
return userAgent;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("userAgent")
public void setUserAgent(String userAgent) {
this.userAgent = userAgent;
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
