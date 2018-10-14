package com.accn.alexa.request.domain;

public class System {
 Device DeviceObject;
 Application ApplicationObject;
 User UserObject;
 private String apiEndpoint;
 private String apiAccessToken;


 // Getter Methods 

 public Device getDevice() {
  return DeviceObject;
 }

 public Application getApplication() {
  return ApplicationObject;
 }

 public User getUser() {
  return UserObject;
 }

 public String getApiEndpoint() {
  return apiEndpoint;
 }

 public String getApiAccessToken() {
  return apiAccessToken;
 }

 // Setter Methods 

 public void setDevice(Device deviceObject) {
  this.DeviceObject = deviceObject;
 }

 public void setApplication(Application applicationObject) {
  this.ApplicationObject = applicationObject;
 }

 public void setUser(User userObject) {
  this.UserObject = userObject;
 }

 public void setApiEndpoint(String apiEndpoint) {
  this.apiEndpoint = apiEndpoint;
 }

 public void setApiAccessToken(String apiAccessToken) {
  this.apiAccessToken = apiAccessToken;
 }
}
