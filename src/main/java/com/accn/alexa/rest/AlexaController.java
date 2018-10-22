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
import com.amazon.ask.model.SessionEndedRequest;
import com.amazon.ask.response.ResponseBuilder;



@RestController
public class AlexaController {
	
    Logger logger = LoggerFactory.getLogger(AlexaController.class);
	
	
	@PostMapping("/skill")
	public @ResponseBody ResponseEnvelope process(@RequestBody RequestEnvelope requestEnvlop) {

		Optional<Response> response;
		String reqType = requestEnvlop.getRequest().getType();
	    ResponseBuilder builder = new ResponseBuilder();
		  
	    logger.info("--- HCSC HealthCare ----" + reqType);
		if("LaunchRequest".equals(reqType)) {
			response = builder.withSpeech("Hi All, Welcome to offshore HCSC").withShouldEndSession(Boolean.TRUE).build();
			
		}
		else if ("SessionEndedRequest".equals(reqType)) {
			response = builder.withShouldEndSession(Boolean.TRUE).build();
			SessionEndedRequest sr = (SessionEndedRequest) requestEnvlop.getRequest();
			if (sr.getError() != null) {
				logger.debug("---Error Msg--- " + sr.getError().getMessage());
			}
			if (sr.getReason() != null) {
				logger.debug("---Reason--- " + sr.getReason().getValue());
			}
		}
		else {
			response = builder.withSpeech("Sorry, I dont know that").withShouldEndSession(Boolean.TRUE).build();
		}
		
		return ResponseEnvelope.builder().withVersion("1.0").withResponse(response.get()).build();
	}

}
