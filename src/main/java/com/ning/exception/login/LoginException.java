package com.ning.exception.login;

/**
 * Created by wangn on 2017/5/19.
 */
public class LoginException extends Exception {
    private String errorMessage;

    public LoginException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
