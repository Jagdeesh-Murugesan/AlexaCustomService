package com.accn.alexa.request.domain;

public class Device {
 private String deviceId;
 SupportedInterfaces SupportedInterfacesObject;


 // Getter Methods 

 public String getDeviceId() {
  return deviceId;
 }

 public SupportedInterfaces getSupportedInterfaces() {
  return SupportedInterfacesObject;
 }

 // Setter Methods 

 public void setDeviceId(String deviceId) {
  this.deviceId = deviceId;
 }

 public void setSupportedInterfaces(SupportedInterfaces supportedInterfacesObject) {
  this.SupportedInterfacesObject = supportedInterfacesObject;
 }
}
