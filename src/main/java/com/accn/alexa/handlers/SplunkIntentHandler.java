package com.accn.alexa.handlers;

import com.splunk.HttpService;
import com.splunk.SSLSecurityProtocol;
import com.splunk.Service;
import com.splunk.ServiceArgs;

public class SplunkIntentHandler {

	private static Service service;
	
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
	
}
