package com.twiter.Twiter12.Utils;

public class PostToken {
    private String token;
    private String text;
    private String mediaName;
    private String mediaData;

    public PostToken(String token, String text, String mediaName, String mediaData) {
        this.token = token;
        this.text = text;
        this.mediaName = mediaName;
        this.mediaData = mediaData;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getMediaData() {
        return mediaData;
    }

    public void setMediaData(String mediaData) {
        this.mediaData = mediaData;
    }
}
