package com.accn.alexa.handlers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

public class CountByDiagnosisCodeHandler extends SplunkIntentHandler implements IRequestHandler {
	
    @Override
	public boolean canHandle(RequestEnvelope input) {
		return input.getRequest() instanceof IntentRequest
				&& "CountByDiagnosisCodeIntent".equals(((IntentRequest) input.getRequest()).getIntent().getName());
	}

    @Override
	public Optional<Response> handle(RequestEnvelope input) {
    	
    	ResponseBuilder builder = new ResponseBuilder();
		Service service = getService();
		HashMap<String, String> result = new HashMap<String, String>();
		List<String> diagnosisCds = new ArrayList<String>();
		
		JobCollection jobs = service.getJobs();
		Args queryArgs = new Args();
		queryArgs.put("exec_mode", "blocking");
		Job job = jobs.create(
				"search index=\"alexa*\" sourcetype=\"alexa*\" Status=\"*\" | rex \"DCN (?<DCN>.*)-SL\"|stats count as Claims by DiagnosisCode",queryArgs);
		
		InputStream resultsNormalSearch = job.getResults();
		ResultsReaderXml resultsReaderNormalSearch;
		HashMap<String, String> event = null;

		try {
			resultsReaderNormalSearch = new ResultsReaderXml(resultsNormalSearch);

			while ((event = resultsReaderNormalSearch.getNextEvent()) != null) {
				result.put(event.get("DiagnosisCode"), event.get("Claims"));
				diagnosisCds.add(event.get("DiagnosisCode"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		String speechText = "There are " + result.get(diagnosisCds.get(0)) + " claims having Diagnosis Code <break time='0.2s'/> " + diagnosisCds.get(0);
		
		return builder.withSpeech(speechText).build();
	}


}
