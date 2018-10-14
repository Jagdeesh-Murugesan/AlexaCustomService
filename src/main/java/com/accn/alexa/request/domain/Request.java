package com.accn.alexa.request.domain;

public class Request {
 private String type;
 private String requestId;
 private String timestamp;
 private String locale;
 Intent IntentObject;


 // Getter Methods 

 public String getType() {
  return type;
 }

 public String getRequestId() {
  return requestId;
 }

 public String getTimestamp() {
  return timestamp;
 }

 public String getLocale() {
  return locale;
 }

 public Intent getIntent() {
  return IntentObject;
 }

 // Setter Methods 

 public void setType(String type) {
  this.type = type;
 }

 public void setRequestId(String requestId) {
  this.requestId = requestId;
 }

 public void setTimestamp(String timestamp) {
  this.timestamp = timestamp;
 }

 public void setLocale(String locale) {
  this.locale = locale;
 }

 public void setIntent(Intent intentObject) {
  this.IntentObject = intentObject;
 }
}
