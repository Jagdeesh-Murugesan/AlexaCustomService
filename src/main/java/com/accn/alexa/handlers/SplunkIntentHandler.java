package com.accn.alexa.handlers;

import com.splunk.HttpService;
import com.splunk.Job;
import com.splunk.SSLSecurityProtocol;
import com.splunk.Service;
import com.splunk.ServiceArgs;

public class SplunkIntentHandler {

	private Service service;
	
	public static float time;

	protected Service getService() {

		if (service == null) {
			System.out.println("Service is null");
			HttpService.setSslSecurityProtocol(SSLSecurityProtocol.TLSv1_2);
			ServiceArgs loginArgs = new ServiceArgs();
			// loginArgs.setUsername("admin");
			// loginArgs.setPassword("changeme");
			loginArgs.setHost("");
			loginArgs.setPort(8089);

			service = Service.connect(loginArgs);
		}
		return service;
	}
	
	public static void waitingTime(Job job) {
		while (!job.isDone()) {
        	System.out.println("...");
            try {
                Thread.sleep(500);
                time+=0.5;
            } catch (InterruptedException e) {
            	System.out.println("inside catch");
            }
        }
        System.out.println("It took approx. "+time+" seconds to fetch the results. \n");
        time=0;
		
	}

}
