package com.xl.qqmessagedemo.po;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String userNSpeak;
    private String speakTime;
    private int imgId;
    private int type;

    public User(String userName, String userNSpeak, String speakTime, int imgId) {
        this.userName = userName;
        this.userNSpeak = userNSpeak;
        this.speakTime = speakTime;
        this.imgId = imgId;
    }


    public User(String userNSpeak, int imgId, int type) {
        this.userNSpeak = userNSpeak;
        this.imgId = imgId;
        this.type = type;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNSpeak() {
        return userNSpeak;
    }

    public void setUserNSpeak(String userNSpeak) {
        this.userNSpeak = userNSpeak;
    }

    public String getSpeakTime() {
        return speakTime;
    }

    public void setSpeakTime(String speakTime) {
        this.speakTime = speakTime;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
