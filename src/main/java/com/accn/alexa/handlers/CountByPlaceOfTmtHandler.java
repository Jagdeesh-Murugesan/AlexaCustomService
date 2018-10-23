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

public class CountByPlaceOfTmtHandler extends SplunkIntentHandler implements IRequestHandler {
	
	public static float time;
    @Override
	public boolean canHandle(RequestEnvelope input) {
		return input.getRequest() instanceof IntentRequest
				&& "CountByPlaceOfTreatmentIntent".equals(((IntentRequest) input.getRequest()).getIntent().getName());
	}

    @Override
	public Optional<Response> handle(RequestEnvelope input) {
    	
    	ResponseBuilder builder = new ResponseBuilder();
		String speechText = "";
		HashMap<String, String> result = new HashMap<String, String>();
		System.out.println("Entering CountByPlaceOfTreatment Intent");
		Service service = getService();

		System.out.println("Connected");
		JobCollection jobs = service.getJobs();
		Args queryArgs = new Args();
		queryArgs.put("exec_mode", "blocking");

		Job job = jobs.create(
				"search index=\"alexa*\" sourcetype=\"alexa*\" Status=\"*\" | rex \"DCN (?<DCN>.*)-SL\"|stats count as Claims by PlaceOfTreatment",
				queryArgs);
		InputStream resultsNormalSearch = job.getResults();

		ResultsReaderXml resultsReaderNormalSearch;

		try {
			resultsReaderNormalSearch = new ResultsReaderXml(resultsNormalSearch);
			HashMap<String, String> event;

			while ((event = resultsReaderNormalSearch.getNextEvent()) != null) {
				System.out.println(event.get("Claims") + " --  " + event.get("PlaceOfTreatment"));
				result.put(event.get("PlaceOfTreatment"), event.get("Claims"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		speechText = "There are " + result.get("1") + " claims having Place of Treatment value as 1, " + result.get("2")
				+ " claims have Place of Treatment value as 2 <break time='0.2s'/> and " + result.get("3")
				+ " claims have Place of Treatment value as 3";
		System.out.println(speechText);
		
		return builder.withSpeech(speechText).build();
	}


}
