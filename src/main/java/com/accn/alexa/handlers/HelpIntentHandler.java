package com.accn.alexa.handlers;

import java.util.Optional;

import com.accn.alexa.rest.IRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;

public class HelpIntentHandler implements IRequestHandler {

    @Override
	public boolean canHandle(RequestEnvelope input) {
		return input.getRequest() instanceof IntentRequest
				&& "AMAZON.HelpIntent".equals(((IntentRequest) input.getRequest()).getIntent().getName());
	}

    @Override
    public Optional<Response> handle(RequestEnvelope input) {
    	
    	ResponseBuilder builder = new ResponseBuilder();
        String speechText = "You can ask for metrics like what is the total number of claims processed, what is the approved claim ratio etc. ";
        return builder.withSpeech(speechText)
                .build();
    }
}
