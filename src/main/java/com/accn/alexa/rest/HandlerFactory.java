package com.accn.alexa.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.accn.alexa.handlers.ApprovalRatioIntentHandler;
import com.accn.alexa.handlers.CancelandStopIntentHandler;
import com.accn.alexa.handlers.CountByClaimTypeIntentHandler;
import com.accn.alexa.handlers.CountByCorporateEntityCodeIntentHandler;
import com.accn.alexa.handlers.CountByDiagnosisCodeHandler;
import com.accn.alexa.handlers.CountByPlaceOfTmtHandler;
import com.accn.alexa.handlers.CountByProcedureCodeIntentHandler;
import com.accn.alexa.handlers.FallbackIntentHandler;
import com.accn.alexa.handlers.HelpIntentHandler;
import com.accn.alexa.handlers.LaunchRequestHandler;
import com.accn.alexa.handlers.PendedRatioIntentHandler;
import com.accn.alexa.handlers.RejectedRatioIntentHandler;
import com.accn.alexa.handlers.SessionEndedRequestHandler;
import com.accn.alexa.handlers.TotalCountIntentHandler;
import com.amazon.ask.model.RequestEnvelope;

public enum HandlerFactory {
	
	INSTANCE;
	
	private List<IRequestHandler> handlers;
	
	private HandlerFactory() {
		
		handlers = new ArrayList<IRequestHandler>();
		
		handlers.add(new ApprovalRatioIntentHandler());
		handlers.add(new CancelandStopIntentHandler());
		handlers.add(new CountByClaimTypeIntentHandler());
		handlers.add(new CountByCorporateEntityCodeIntentHandler());
		handlers.add(new CountByDiagnosisCodeHandler());
		handlers.add(new CountByPlaceOfTmtHandler());
		handlers.add(new CountByProcedureCodeIntentHandler());
		handlers.add(new FallbackIntentHandler());
		handlers.add(new HelpIntentHandler());
		handlers.add(new LaunchRequestHandler());
		handlers.add(new PendedRatioIntentHandler());
		handlers.add(new RejectedRatioIntentHandler());
		handlers.add(new SessionEndedRequestHandler());
		handlers.add(new TotalCountIntentHandler());
		
	}
	
	
	public Optional<IRequestHandler> getHandler(RequestEnvelope requestEvlp)
	{
		return handlers.stream().filter(x->x.canHandle(requestEvlp)).findFirst();
	}

}
