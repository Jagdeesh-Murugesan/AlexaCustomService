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

public class PendedRatioIntentHandler extends SplunkIntentHandler implements IRequestHandler {

	@Override
	public boolean canHandle(RequestEnvelope input) {
		return input.getRequest() instanceof IntentRequest
				&& "PendedRatioIntent".equals(((IntentRequest) input.getRequest()).getIntent().getName());
	}

	@Override
	public Optional<Response> handle(RequestEnvelope input) {

		ResponseBuilder builder = new ResponseBuilder();
		String pendedClaimPercent = null;
		String totalClaims = null;
		String pendedCount = null;
		Service service = getService();
		Args queryArgs = new Args();
		queryArgs.put("exec_mode", "blocking");

		JobCollection jobs = service.getJobs();
		Job job = jobs.create(
				"search index=\"alexa*\" sourcetype=\"alexa*\" Status=\"*\" | rex \"DCN (?<DCN>.*)-SL\"|eval pended=if(Status=\"pended\",1,0) "
						+ "| eventstats sum(pended) as pendedcount count as totalClaims "
						+ "|eval pendedpercent=round(((pendedcount/totalClaims)*100),2) |stats values(pendedpercent) as PendedRatio by pendedcount,totalClaims",
						queryArgs);
		if (job.isDone()) {
			InputStream resultsNormalSearch = job.getResults();

			ResultsReaderXml resultsReaderNormalSearch;

			try {
				resultsReaderNormalSearch = new ResultsReaderXml(resultsNormalSearch);
				HashMap<String, String> event;

				while ((event = resultsReaderNormalSearch.getNextEvent()) != null) {
					pendedClaimPercent = event.get("PendedRatio") != null ? event.get("PendedRatio") : "zero";
					pendedCount = event.get("pendedcount") != null ? event.get("pendedcount") : "zero";
					totalClaims = event.get("totalClaims") != null ? event.get("totalClaims") : "zero";
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		String speechText = pendedCount + " out of " + totalClaims + " claims are pending, pended claims percentage is "
				+ pendedClaimPercent;

		return builder.withSpeech(speechText).build();
	}

}
