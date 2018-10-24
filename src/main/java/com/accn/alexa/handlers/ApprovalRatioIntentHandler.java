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

public class ApprovalRatioIntentHandler extends SplunkIntentHandler implements IRequestHandler {

	@Override
	public boolean canHandle(RequestEnvelope input) {
		
		return input.getRequest() instanceof IntentRequest
				&& "ApprovalRatioIntent".equals(((IntentRequest) input.getRequest()).getIntent().getName());

	}

	@Override
	public Optional<Response> handle(RequestEnvelope input) {
		
		ResponseBuilder builder = new ResponseBuilder();
		String approvedClaimPercent = null;
		String totalClaims = null;
		String approvedCount = null;
		Service service = getService();
		
		Args queryArgs = new Args();
		queryArgs.put("exec_mode", "blocking");

		JobCollection jobs = service.getJobs();
		Job job = jobs.create(
				"search index=\"alexa*\" sourcetype=\"alexa*\" Status=\"*\" | rex \"DCN (?<DCN>.*)-SL\"|eval approved=if(Status=\"approved\",1,0) | eventstats sum(approved) as approvedcount count as totalClaims |eval approvedpercent=round(((approvedcount/totalClaims)*100),2) |stats values(approvedpercent) as ApprovalRatio by approvedcount,totalClaims",
				queryArgs);
		if (job.isDone()) {
			InputStream resultsNormalSearch = job.getResults();

			ResultsReaderXml resultsReaderNormalSearch;

			try {
				resultsReaderNormalSearch = new ResultsReaderXml(resultsNormalSearch);
				HashMap<String, String> event;

				while ((event = resultsReaderNormalSearch.getNextEvent()) != null) {
					approvedClaimPercent = event.get("ApprovalRatio") != null ? event.get("ApprovalRatio") : "zero";
					approvedCount = event.get("approvedcount") != null ? event.get("approvedcount") : "zero";
					totalClaims = event.get("totalClaims") != null ? event.get("totalClaims") : "zero";
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		String speechText = approvedCount + " out of " + totalClaims
				+ " claims got approved, approved claims percentage is " + approvedClaimPercent;
		
		return builder.withSpeech(speechText).build();
	}

}
