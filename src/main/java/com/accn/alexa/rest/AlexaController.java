package com.accn.alexa.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accn.alexa.request.domain.AlexaRequest;
import com.accn.alexa.response.domain.AlexaResponse;
import com.accn.alexa.response.domain.Body;
import com.accn.alexa.response.domain.OutputSpeech;
import com.accn.alexa.response.domain.Response;

@RestController
public class AlexaController {
	
	
	  @PostMapping("/skill")
	public AlexaResponse process(@RequestBody AlexaRequest request) {

		System.out.println("--- HCSC HealthCare ----" + request.getRequest().getType());

		AlexaResponse alexaRes = new AlexaResponse();
		Body body = new Body();
		Response response = new Response();
		OutputSpeech os = new OutputSpeech();

		if ("LaunchRequest".equals(request.getRequest().getType())) {
			// Set OutputSpeech values
			os.setSsml("<speak>Welcome to India</speak>");
			os.setType("SSML");

			// Set Response
			response.setOutputSpeech(os);
			response.setShouldEndSession(false);
		}
		else if("SessionEndedRequest".equals(request.getRequest().getType())) {
			
		}
		// Set body
		body.setVersion("1.0");
		body.setResponse(response);
//		  body.setUserAgent("Apache-HttpClient/4.5.x (Java/1.8.0_172)");		  		  

		alexaRes.setBody(body);

		return alexaRes;
	}

}
