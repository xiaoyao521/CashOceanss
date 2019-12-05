package com.jike.cashocean.net;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.JsonParseException;
import com.jike.cashocean.BuildConfig;
import com.jike.cashocean.R;
import com.jike.cashocean.net.exception.ServerException;
import com.jike.cashocean.ui.CashMallApplication;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by Yey on 2018/4/27.
 */

public abstract class BaseObserver<T> implements Observer<T> {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public abstract void onSuccess(T t) throws Exception;

    public abstract void onFail(Throwable throwable) throws ClassNotFoundException;

    @Override
    public void onSubscribe(@NonNull Disposable d) {
    }

    @Override
    public void onNext(@NonNull T t) {
        try {
            onSuccess(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        try {
//            if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException || e instanceof IOException) {
//                ToastUtils.showLong(R.string.data_fail);
//                onFail(e);
//            } else if (e instanceof SocketTimeoutException) {
//                onFail(e);
//                ToastUtils.showLong(R.string.net_connect_fail);
//            }else {
//                onFail(e);
//            }

            if (e instanceof HttpException) {             //HTTP错误
                HttpException httpException = (HttpException) e;
                switch (httpException.code()) {
                    case NOT_FOUND:
                    case SERVICE_UNAVAILABLE:
                    case INTERNAL_SERVER_ERROR:
                        ToastUtils.showLong(CashMallApplication.context.getString(R.string.data_fail));
                        onFail(e);
                        break;
                    case UNAUTHORIZED:
                    case FORBIDDEN:
                    case REQUEST_TIMEOUT:
                    case GATEWAY_TIMEOUT:
                    case BAD_GATEWAY:
                    default:
                        onFail(e);
                        ToastUtils.showLong(CashMallApplication.context.getString(R.string.net_connect_fail));
                        break;
                }
            } else if (e instanceof ServerException) {
                ToastUtils.showLong(CashMallApplication.context.getString(R.string.data_fail));
                onFail(e);//服务器返回的错误
            } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof java.text.ParseException) {
                ToastUtils.showLong(CashMallApplication.context.getString(R.string.data_parsing_error));//均视为解析错误
                onFail(e);
            } else if (e instanceof ConnectException || e instanceof SocketTimeoutException || e instanceof IOException || e instanceof UnknownHostException) {
                onFail(e);
                ToastUtils.showLong(CashMallApplication.context.getString(R.string.net_connect_fail));//网络链接失败
            } else {
                onFail(e);
                if (BuildConfig.DEBUG) {
                    ToastUtils.showLong("未知错误", e.getMessage());
                }
                ToastUtils.showLong(CashMallApplication.context.getString(R.string.unknown_data_hint));//未知错误
            }
        } catch (ClassNotFoundException e1) {
            ToastUtils.showLong(e.getMessage());
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {

    }
}

//        LogUtils.e("打印的错误日志   " + e.getMessage());
//        if (e instanceof HttpException) {
//            HttpException httpException = (HttpException) e;
//            switch (httpException.code()) {
//                case UNAUTHORIZED:
//                case FORBIDDEN:
//                case NOT_FOUND:
//                case REQUEST_TIMEOUT:
//                case GATEWAY_TIMEOUT:
//                case INTERNAL_SERVER_ERROR:
//                case BAD_GATEWAY:
//                case SERVICE_UNAVAILABLE:
//                default:
//                    ToastUtils.showLong(R.string.net_fail);
//                    break;
//            }
//        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
//        } else if (e instanceof ConnectException || e instanceof SocketTimeoutException) {
//            ToastUtils.showLong(R.string.net_connect_fail);
//        }
//        else if (e instanceof javax.net.ssl.SSLHandshakeException) {
//
//        } else {
//        }