package com.ning.exception.login;

/**
 * 登陆相关异常
 *
 * @author wangn
 * @date 2017/5/19
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
