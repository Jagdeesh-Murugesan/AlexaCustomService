package com.accn.alexa.handlers;

import java.io.InputStream;
import java.util.HashMap;
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

public class TotalCountIntentHandler extends SplunkIntentHandler implements IRequestHandler {
	
	public static float time;
    @Override
	public boolean canHandle(RequestEnvelope input) {
		return input.getRequest() instanceof IntentRequest
				&& "TotalClaimCountIntent".equals(((IntentRequest) input.getRequest()).getIntent().getName());
	}

    @Override
	public Optional<Response> handle(RequestEnvelope input) {
    	
    	ResponseBuilder builder = new ResponseBuilder();
		int total_count = 0;
		Service service = getService();

		JobCollection jobs = service.getJobs();
		Args queryArgs = new Args();
		queryArgs.put("exec_mode", "blocking");
		Job job = jobs.create(
				"search index=\"alexa*\" sourcetype=\"alexa*\" Status=\"*\" | rex \"DCN (?<DCN>.*)-SL\"|stats count as \"Total Number Of Claims\"",queryArgs);
		
		if (job.isDone()) {
			InputStream resultsNormalSearch = job.getResults();

			ResultsReaderXml resultsReaderNormalSearch;

			try {
				resultsReaderNormalSearch = new ResultsReaderXml(resultsNormalSearch);
				HashMap<String, String> event;

				while ((event = resultsReaderNormalSearch.getNextEvent()) != null) {
					total_count = total_count + Integer.parseInt(event.get("Total Number Of Claims"));
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		String speechText = "Total number of claims processed is " + String.valueOf(total_count);
		
		return builder.withSpeech(speechText).build();
	}


}
