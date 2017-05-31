package com.ning.exception.file;

/**
 * Created by wangn on 2017/5/20.
 */
public class FileException extends Exception{
    private String errorMessage;

    public FileException(String message) {
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
