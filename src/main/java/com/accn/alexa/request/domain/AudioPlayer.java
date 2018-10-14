package com.accn.alexa.request.domain;

public class AudioPlayer {
 private String playerActivity;
 private String token;
 private float offsetInMilliseconds;


 // Getter Methods 

 public String getPlayerActivity() {
  return playerActivity;
 }

 public String getToken() {
  return token;
 }

 public float getOffsetInMilliseconds() {
  return offsetInMilliseconds;
 }

 // Setter Methods 

 public void setPlayerActivity(String playerActivity) {
  this.playerActivity = playerActivity;
 }

 public void setToken(String token) {
  this.token = token;
 }

 public void setOffsetInMilliseconds(float offsetInMilliseconds) {
  this.offsetInMilliseconds = offsetInMilliseconds;
 }
}
