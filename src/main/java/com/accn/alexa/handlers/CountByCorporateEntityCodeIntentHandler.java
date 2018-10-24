package com.accn.alexa.handlers;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.accn.alexa.rest.IRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;
import com.splunk.Args;
import com.splunk.Job;
import com.splunk.JobCollection;
import com.splunk.ResultsReaderXml;
import com.splunk.Service;

public class CountByCorporateEntityCodeIntentHandler extends SplunkIntentHandler implements IRequestHandler {
	
	public static float time;
    @Override
	public boolean canHandle(RequestEnvelope input) {
		return input.getRequest() instanceof IntentRequest && "CountByCorporateEntityCodeIntent"
				.equals(((IntentRequest) input.getRequest()).getIntent().getName());

	}

    @Override
	public Optional<Response> handle(RequestEnvelope input) {
    	
    	ResponseBuilder builder = new ResponseBuilder();
		String resString="";
		Map<String, String> domainMap = new HashMap<String, String>();
		domainMap.put("IL1", "Illinois");
		domainMap.put("NM1", "New Mexico");
		domainMap.put("TX1", "Texas");
		domainMap.put("OK1", "Oklahoma");
		domainMap.put("MT1", "Montana");
		Service service = getService();
		Args queryArgs = new Args();
		queryArgs.put("exec_mode", "blocking");
		
		JobCollection jobs = service.getJobs();
		Job job = jobs.create(
				"search index=\"alexa*\" sourcetype=\"alexa*\" Status=\"*\" | rex \"DCN (?<DCN>.*)-SL\"|stats count as Claims by CorporateEntityCode",
				queryArgs);
		if (job.isDone()) {
			InputStream resultsNormalSearch = job.getResults();

			ResultsReaderXml resultsReaderNormalSearch;

			try {
				resultsReaderNormalSearch = new ResultsReaderXml(resultsNormalSearch);
				HashMap<String, String> event;

				while ((event = resultsReaderNormalSearch.getNextEvent()) != null) {
					resString = resString +event.get("Claims")+" claims processed under "+domainMap.get(event.get("CorporateEntityCode"))+". ";
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		String speechText = resString;
		
		return builder.withSpeech(speechText).build();
	}

	
}
