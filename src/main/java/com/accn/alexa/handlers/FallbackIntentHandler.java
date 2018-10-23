package com.accn.alexa.handlers;

import java.util.Optional;

import com.accn.alexa.rest.IRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;

public class FallbackIntentHandler implements IRequestHandler {

	@Override
	public boolean canHandle(RequestEnvelope input) {
		
		return input.getRequest() instanceof IntentRequest
				&& "AMAZON.FallbackIntent".equals(((IntentRequest) input.getRequest()).getIntent().getName());
	}

	@Override
	public Optional<Response> handle(RequestEnvelope input) {

		ResponseBuilder builder = new ResponseBuilder();
		String speechText = "Sorry, I don't know that!";
		
		return builder.withSpeech(speechText).withReprompt("Sorry i didnt get that clearly!").build();
	}

}
