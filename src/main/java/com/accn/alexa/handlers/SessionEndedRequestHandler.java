package com.accn.alexa.handlers;

import java.util.Optional;

import com.accn.alexa.rest.IRequestHandler;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.SessionEndedRequest;
import com.amazon.ask.response.ResponseBuilder;

public class SessionEndedRequestHandler implements IRequestHandler {

    @Override
    public boolean canHandle(RequestEnvelope input) {
    	return input.getRequest() instanceof SessionEndedRequest;

    }

    @Override
    public Optional<Response> handle(RequestEnvelope input) {
    	
    	ResponseBuilder builder = new ResponseBuilder();
        // any cleanup logic goes here
        return builder.build();
    }

}
