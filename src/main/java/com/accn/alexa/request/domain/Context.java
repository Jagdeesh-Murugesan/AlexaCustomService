package com.accn.alexa.request.domain;

public class Context {
 System SystemObject;
 AudioPlayer AudioPlayerObject;


 // Getter Methods 

 public System getSystem() {
  return SystemObject;
 }

 public AudioPlayer getAudioPlayer() {
  return AudioPlayerObject;
 }

 // Setter Methods 

 public void setSystem(System SystemObject) {
  this.SystemObject = SystemObject;
 }

 public void setAudioPlayer(AudioPlayer AudioPlayerObject) {
  this.AudioPlayerObject = AudioPlayerObject;
 }
}
