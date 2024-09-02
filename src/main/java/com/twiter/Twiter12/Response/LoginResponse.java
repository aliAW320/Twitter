package com.twiter.Twiter12.Response;

public class LoginResponse implements Response{
    private boolean valid ;
    private String massage;
    private String token;
    private int code;

    public LoginResponse(boolean valid, String massage, String token, int code) {
        this.valid = valid;
        this.massage = massage;
        this.token = token;
        this.code = code;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
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
