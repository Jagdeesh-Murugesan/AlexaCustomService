package com.accn.alexa.handlers;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Optional;

import com.accn.alexa.rest.IRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;
import com.splunk.Job;
import com.splunk.JobCollection;
import com.splunk.ResultsReaderXml;
import com.splunk.Service;

public class CountByProcedureCodeIntentHandler extends SplunkIntentHandler implements IRequestHandler {
	
	public static float time;
    @Override
	public boolean canHandle(RequestEnvelope input) {
		return input.getRequest() instanceof IntentRequest
				&& "CountByProcedureCodeIntent".equals(((IntentRequest) input.getRequest()).getIntent().getName());
	}

    @Override
	public Optional<Response> handle(RequestEnvelope input) {
    	
    	ResponseBuilder builder = new ResponseBuilder();
		String resString = "";
		System.out.println("Entering CountByProcedureCode Intent");
		Service service = getService();		
		
		JobCollection jobs = service.getJobs();
		Job job = jobs.create(
				"search index=\"alexa*\" sourcetype=\"alexa*\" Status=\"*\" | rex \"DCN (?<DCN>.*)-SL\"|top limit=3 ProcedureCode showperc=false");
		 waitingTime(job);
		if (job.isDone()) {
			InputStream resultsNormalSearch = job.getResults();

			ResultsReaderXml resultsReaderNormalSearch;

			try {
				resultsReaderNormalSearch = new ResultsReaderXml(resultsNormalSearch);
				HashMap<String, String> event;

				while ((event = resultsReaderNormalSearch.getNextEvent()) != null) {
					System.out.println(event.get("count") + " --  " + event.get("ProcedureCode"));
					System.out.println(event);					
					resString = resString +event.get("count")+" claims processed for procedure code "+event.get("ProcedureCode")+". ";
				}
				System.out.println("String output:" + resString);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		String speechText = resString;
		
		return builder.withSpeech(speechText).build();
	}
	
}
