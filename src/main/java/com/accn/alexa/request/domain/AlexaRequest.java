package com.accn.alexa.request.domain;

public class AlexaRequest {
	 private String version;
	 Session SessionObject;
	 Context ContextObject;
	 Request RequestObject;


	 // Getter Methods 

	 public String getVersion() {
	  return version;
	 }

	 public Session getSession() {
	  return SessionObject;
	 }

	 public Context getContext() {
	  return ContextObject;
	 }

	 public Request getRequest() {
	  return RequestObject;
	 }

	 // Setter Methods 

	 public void setVersion(String version) {
	  this.version = version;
	 }

	 public void setSession(Session sessionObject) {
	  this.SessionObject = sessionObject;
	 }

	 public void setContext(Context contextObject) {
	  this.ContextObject = contextObject;
	 }

	 public void setRequest(Request requestObject) {
	  this.RequestObject = requestObject;
	 }
	}
	
	
	