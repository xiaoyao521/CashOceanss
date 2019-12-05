package com.jike.cashocean.net.exception;

/**
 * 与服务器约定好的异常
 */
public interface SERVER_CODE {
    public static final int UNKNOWN = 1000;//未知错误
    public static final int PARSE_ERROR = 1001;//解析错误
    public static final int NETWORD_ERROR = 1002;//网络错误
    public static final int HTTP_ERROR = 1003;//协议出错
    public static final int SUCCESS = 200;//成功
    public static final int DATA_ERROR = 201;//异常
    public static final int DATA_LOADING = 202;//异常,交给loadService使用
    public static final int DATA_AGAIN_LOADING = 203;//异常,交给loadService使用


}
