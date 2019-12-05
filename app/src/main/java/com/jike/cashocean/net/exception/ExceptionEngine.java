package com.jike.cashocean.net.exception;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.text.ParseException;

import retrofit2.HttpException;

public class ExceptionEngine {

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static CommonException handleException(Throwable e) {
        CommonException commonException;
        if (e instanceof HttpException) {             //HTTP错误
            HttpException httpException = (HttpException) e;
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    commonException = new CommonException(e, SERVER_CODE.HTTP_ERROR, "Network error, please check the network");//均视为网络错误
                    break;
            }
            return commonException;
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            return new CommonException(e, SERVER_CODE.PARSE_ERROR, "Data parsing error, please contact customer service");//均视为解析错误  服务器返回的错误
        } else if (e instanceof ConnectException || e instanceof SocketTimeoutException || e instanceof IOException) {
            return new CommonException(e, SERVER_CODE.NETWORD_ERROR, "Network link error, please check the network");//均视为网络错误
        } else if (e instanceof ServerException) {
            return new CommonException(e, SERVER_CODE.DATA_ERROR, ((ServerException) e).getDataError());
        } else {
            return new CommonException(e, SERVER_CODE.UNKNOWN, "Unknown error, please contact customer service");//未知错误
        }
    }
}
