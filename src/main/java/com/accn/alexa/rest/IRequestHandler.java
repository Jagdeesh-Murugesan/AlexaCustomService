package com.accn.alexa.rest;

import java.util.Optional;

import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;

public interface IRequestHandler {
	
    boolean canHandle(RequestEnvelope input);

    Optional<Response> handle(RequestEnvelope input);

}
