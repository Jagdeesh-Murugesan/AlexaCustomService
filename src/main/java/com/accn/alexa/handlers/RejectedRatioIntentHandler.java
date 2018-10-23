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

public class RejectedRatioIntentHandler extends SplunkIntentHandler implements IRequestHandler{
	
	public static float time;
    @Override
	public boolean canHandle(RequestEnvelope input) {
		return input.getRequest() instanceof IntentRequest
				&& "RejectedRatioIntent".equals(((IntentRequest) input.getRequest()).getIntent().getName());
	}

    @Override
	public Optional<Response> handle(RequestEnvelope input) {
    	
    	ResponseBuilder builder = new ResponseBuilder();
		String speechText = "";
		String rejectedClaimPercent = null;
		String totalClaims = null;
		String rejectedCount = null;
		System.out.println("Entering RejectedRatio Intent");
		Service service = getService();

		System.out.println("Connected");
		JobCollection jobs = service.getJobs();
		Args queryArgs = new Args();
		queryArgs.put("exec_mode", "blocking");

		Job job = jobs.create(
				"search index=\"alexa*\" sourcetype=\"alexa*\" Status=\"*\" | rex \"DCN (?<DCN>.*)-SL\"|eval rejected=if(Status=\"denied\",1,0) "
				+ "| eventstats sum(rejected) as rejectedcount count as totalClaims |eval rejectedpercent=round(((rejectedcount/totalClaims)*100),2) "
				+ "|stats values(rejectedpercent) as \"RejectedRatio\" by rejectedcount,totalClaims",
				queryArgs);
		InputStream resultsNormalSearch = job.getResults();

		ResultsReaderXml resultsReaderNormalSearch;

		try {
			resultsReaderNormalSearch = new ResultsReaderXml(resultsNormalSearch);
			HashMap<String, String> event;

			while ((event = resultsReaderNormalSearch.getNextEvent()) != null) {
				System.out.println(event.get("RejectedRatio") + " --  " + event.get("rejectedcount"));
				rejectedClaimPercent = event.get("RejectedRatio") !=null ? event.get("RejectedRatio") : "zero";
				rejectedCount = event.get("rejectedcount") !=null ? event.get("rejectedcount") : "zero";
				totalClaims = event.get("totalClaims") !=null ? event.get("totalClaims") : "zero";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		speechText = rejectedCount + " out of "+  totalClaims +" claims are rejected, rejected claims percentage is " + rejectedClaimPercent;
		System.out.println(speechText);
		
		return builder.withSpeech(speechText).build();
	}

}
