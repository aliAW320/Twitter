package com.twiter.Twiter12.Response;

public class ValidationResponse implements Response{
    private boolean validation;
    private String massage;
    private int code;

    public ValidationResponse(boolean validation, String massage, int code) {
        this.validation = validation;
        this.massage = massage;
        this.code = code;
    }

    public boolean isValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
