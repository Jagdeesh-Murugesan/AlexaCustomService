package com.accn.alexa.request.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Session {

	private boolean newSession;
	private String sessionId;
	Application ApplicationObject;
	Attributes AttributesObject;
	User UserObject;

	// Getter Methods

	@JsonProperty("new")
	public boolean getNewSession() {
		return newSession;
	}

	public String getSessionId() {
		return sessionId;
	}

	public Application getApplication() {
		return ApplicationObject;
	}

	public Attributes getAttributes() {
		return AttributesObject;
	}

	public User getUser() {
		return UserObject;
	}

	// Setter Methods

	public void setNewSession(boolean newflag) {
		this.newSession = newflag;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void setApplication(Application applicationObject) {
		this.ApplicationObject = applicationObject;
	}

	public void setAttributes(Attributes attributesObject) {
		this.AttributesObject = attributesObject;
	}

	public void setUser(User userObject) {
		this.UserObject = userObject;
	}
}
