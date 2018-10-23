package com.accn.alexa.handlers;

import java.util.Optional;

import com.accn.alexa.rest.IRequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;

public class LaunchRequestHandler implements IRequestHandler {

    @Override
    public boolean canHandle(RequestEnvelope input) {
    	return input.getRequest() instanceof LaunchRequest;
    }

    @Override
    public Optional<Response> handle(RequestEnvelope input) {
    	
    	ResponseBuilder builder = new ResponseBuilder();
        String speechText = "Welcome to Splunk tracker, you can get metrics from the splunk app using this skill. Now what can i do for you.";
        
        return builder.withSpeech(speechText)
                .withReprompt(speechText)
                .build();
    }

}
