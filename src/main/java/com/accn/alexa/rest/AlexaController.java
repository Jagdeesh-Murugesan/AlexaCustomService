package com.accn.alexa.rest;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.ResponseEnvelope;



@RestController
public class AlexaController {
	
    Logger logger = LoggerFactory.getLogger(AlexaController.class);
	
	
	@PostMapping("/skill")
	public @ResponseBody ResponseEnvelope process(@RequestBody RequestEnvelope requestEnvlop) {

		Optional<Response> response = null;

		Optional<IRequestHandler> handler = HandlerFactory.INSTANCE.getHandler(requestEnvlop);
		if (handler.isPresent()) {
			response = handler.get().handle(requestEnvlop);
		}

		return ResponseEnvelope.builder().withVersion("1.0").withResponse(response.get()).build();
	}

}
