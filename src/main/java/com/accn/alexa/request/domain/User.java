package com.accn.alexa.request.domain;

public class User {
 private String userId;
 private String accessToken;
 Permissions PermissionsObject;


 // Getter Methods 

 public String getUserId() {
  return userId;
 }

 public String getAccessToken() {
  return accessToken;
 }

 public Permissions getPermissions() {
  return PermissionsObject;
 }

 // Setter Methods 

 public void setUserId(String userId) {
  this.userId = userId;
 }

 public void setAccessToken(String accessToken) {
  this.accessToken = accessToken;
 }

 public void setPermissions(Permissions permissionsObject) {
  this.PermissionsObject = permissionsObject;
 }
}
