package com.jike.cashocean.net.exception;

/**
 * 所有得错误都会统一封装成同一种类型得Exception
 */
public class CommonException extends Throwable {
    private int code;
    private String errorMessage;

    public CommonException(Throwable throwable, int code, String errorMessage) {
        super(throwable);
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
