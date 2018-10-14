package com.accn.alexa.request.domain;

public class Intent {
 private String name;
 private String confirmationStatus;


 // Getter Methods 

 public String getName() {
  return name;
 }

 public String getConfirmationStatus() {
  return confirmationStatus;
 }

 // Setter Methods 

 public void setName(String name) {
  this.name = name;
 }

 public void setConfirmationStatus(String confirmationStatus) {
  this.confirmationStatus = confirmationStatus;
 }
}
