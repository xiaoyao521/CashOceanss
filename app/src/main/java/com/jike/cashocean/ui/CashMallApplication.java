package com.jike.cashocean.ui;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.jike.cashocean.component.GlideApp;
import com.jike.cashocean.view.loadsircallback.EmptyCallback;
import com.jike.cashocean.view.loadsircallback.ErrorCallback;
import com.jike.cashocean.view.loadsircallback.LoadingCallback;
import com.kingja.loadsir.core.LoadSir;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

//import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Yey on 2018/4/18.
 */

public class CashMallApplication extends Application {
    public static Context context;

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        ;
        initUtilsCode();
        swipeSlide();
        initLoadSir();
        initGlide();
    }

    private void initGlide() {
        GlideApp.with(this);
    }

    public static Context getContext() {
        return context;
    }

    private void initLoadSir() {
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//数据错误视图
                .addCallback(new EmptyCallback())//空视图
//                .addCallback(new TimeoutCallback())//链接超时视图
//                .addCallback(new LottieEmptyCallback())//动画空视图
//                .addCallback(new LottieLoadingCallback())//动画加载视图
                .addCallback(new LoadingCallback())//动画加载视图
                .setDefaultCallback(LoadingCallback.class)//等待页面
                .commit();
    }

    private void swipeSlide() {
        BGASwipeBackHelper.init(this, null);
    }

    private void initUtilsCode() {
        Utils.init(this);
    }

}
