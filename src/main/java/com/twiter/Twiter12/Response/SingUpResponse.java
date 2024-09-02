package com.twiter.Twiter12.Response;

public class SingUpResponse implements Response{
    private boolean valid;
    private String message;
    private String token;
    private int code;

    public SingUpResponse(boolean valid, String message, String token, int code) {
        this.valid = valid;
        this.message = message;
        this.token = token;
        this.code = code;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
