package com.ning.exception.defaults;

/**
 * Created by wangn on 2017/5/19.
 */
public class DefaultException extends Exception{
    private String errorMessage;

    public DefaultException(String message) {
        super(message);
        this.errorMessage=message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
