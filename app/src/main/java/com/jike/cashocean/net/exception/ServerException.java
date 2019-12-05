package com.jike.cashocean.net.exception;

/**
 * 该类只是作为一个判断的标识
 */
public class ServerException extends RuntimeException {
    String mesage;

    public ServerException(String mesage) {
        this.mesage = mesage;
    }

    public String getDataError() {
        return mesage;
    }
}
