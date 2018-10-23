package com.accn.alexa;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import com.accn.alexa.handlers.ApprovalRatioIntentHandler;
import com.accn.alexa.handlers.CountByClaimTypeIntentHandler;
import com.accn.alexa.handlers.CountByCorporateEntityCodeIntentHandler;
import com.accn.alexa.handlers.CountByDiagnosisCodeHandler;
import com.accn.alexa.handlers.CountByPlaceOfTmtHandler;
import com.accn.alexa.handlers.CountByProcedureCodeIntentHandler;
import com.accn.alexa.handlers.LaunchRequestHandler;
import com.accn.alexa.handlers.PendedRatioIntentHandler;
import com.accn.alexa.handlers.RejectedRatioIntentHandler;
import com.accn.alexa.handlers.TotalCountIntentHandler;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.ui.SsmlOutputSpeech;

public class AlexaSplunkTest {

	private RequestEnvelope handleInput = RequestEnvelope.builder().build();

	// 1
	@Test
	public void testLaunchSplunkTracker() {
		LaunchRequestHandler handler = new LaunchRequestHandler();
		String speech = "<speak>Welcome to Splunk tracker, you can get metrics from the splunk app using this skill. Now what can i do for you.</speak>";
		Optional<Response> response = handler.handle(handleInput);
		SsmlOutputSpeech outputSpeech = (SsmlOutputSpeech) response.get().getOutputSpeech();
		Assert.assertEquals(speech, outputSpeech.getSsml());
	}
	// 2
	@Test
	public void testCountByPlaceOfTmtHandler() {
		CountByPlaceOfTmtHandler handler = new CountByPlaceOfTmtHandler();
		String speech = "<speak>There are 79 claims having Place of Treatment value as 1, 69 claims have Place of Treatment value as 2 <break time='0.2s'/> and 92 claims have Place of Treatment value as 3</speak>";
		Optional<Response> response = handler.handle(handleInput);
		SsmlOutputSpeech outputSpeech = (SsmlOutputSpeech) response.get().getOutputSpeech();
		Assert.assertEquals(speech, outputSpeech.getSsml());
	}

	// 3
	@Test
	public void testCountByClaimtype() {

		CountByClaimTypeIntentHandler handler = new CountByClaimTypeIntentHandler();
		String speech = "<speak>There are 82 claims having Claim Type value as Facility, 77 claims have Claim Type value as Professional <break time='0.2s'/> and 81 claims have Claim Type value as In-patient</speak>";
		Optional<Response> response = handler.handle(handleInput);
		SsmlOutputSpeech outputSpeech = (SsmlOutputSpeech) response.get().getOutputSpeech();
		Assert.assertEquals(speech, outputSpeech.getSsml());
	}

	// 4
	@Test
	public void testApprovalRatio() {

		ApprovalRatioIntentHandler handler = new ApprovalRatioIntentHandler();
		String speech = "<speak>118 out of 240 claims got approved, approved claims percentage is 49.17</speak>";
		Optional<Response> response = handler.handle(handleInput);
		SsmlOutputSpeech outputSpeech = (SsmlOutputSpeech) response.get().getOutputSpeech();
		Assert.assertEquals(speech, outputSpeech.getSsml());
	}

	// 5
	@Test
	public void testRejectedRatio() {

		RejectedRatioIntentHandler handler = new RejectedRatioIntentHandler();
		String speech = "<speak>63 out of 240 claims are rejected, rejected claims percentage is 26.25</speak>";
		Optional<Response> response = handler.handle(handleInput);
		SsmlOutputSpeech outputSpeech = (SsmlOutputSpeech) response.get().getOutputSpeech();
		Assert.assertEquals(speech, outputSpeech.getSsml());
	}

	// 6
	@Test
	public void testPendedRatio() {

		PendedRatioIntentHandler handler = new PendedRatioIntentHandler();
		String speech = "<speak>59 out of 240 claims are pending, pended claims percentage is 24.58</speak>";
		Optional<Response> response = handler.handle(handleInput);
		SsmlOutputSpeech outputSpeech = (SsmlOutputSpeech) response.get().getOutputSpeech();
		Assert.assertEquals(speech, outputSpeech.getSsml());
	}

	// 7
	@Test
	public void testCountyByCorpEntity() {

		CountByCorporateEntityCodeIntentHandler handler = new CountByCorporateEntityCodeIntentHandler();
		String speech = "<speak>63 claims processed under Illinois. 62 claims processed under Montana. 53 claims processed under New Mexico. 62 claims processed under Texas.</speak>";
		Optional<Response> response = handler.handle(handleInput);
		SsmlOutputSpeech outputSpeech = (SsmlOutputSpeech) response.get().getOutputSpeech();
		Assert.assertEquals(speech, outputSpeech.getSsml());
	}

	// 8
	@Test
	public void testCountyByProcedureCode() {

		CountByProcedureCodeIntentHandler handler = new CountByProcedureCodeIntentHandler();
		String speech = "<speak>2 claims processed for procedure code 91758. 1 claims processed for procedure code 99449. 1 claims processed for procedure code 99252.</speak>";
		Optional<Response> response = handler.handle(handleInput);
		SsmlOutputSpeech outputSpeech = (SsmlOutputSpeech) response.get().getOutputSpeech();
		Assert.assertEquals(speech, outputSpeech.getSsml());
	}

	// 9
	@Test
	public void testCountyByDiagnosisCode() {

		CountByDiagnosisCodeHandler handler = new CountByDiagnosisCodeHandler();
		String speech = "<speak>There are 240 claims having Diagnosis Code <break time='0.2s'/> M6281</speak>";
		Optional<Response> response = handler.handle(handleInput);
		SsmlOutputSpeech outputSpeech = (SsmlOutputSpeech) response.get().getOutputSpeech();
		Assert.assertEquals(speech, outputSpeech.getSsml());
	}

	// 10
	@Test
	public void testTotalCount() {

		TotalCountIntentHandler handler = new TotalCountIntentHandler();
		String speech = "<speak>Total number of claims processed is 240</speak>";
		Optional<Response> response = handler.handle(handleInput);
		SsmlOutputSpeech outputSpeech = (SsmlOutputSpeech) response.get().getOutputSpeech();
		Assert.assertEquals(speech, outputSpeech.getSsml());
	}

}
