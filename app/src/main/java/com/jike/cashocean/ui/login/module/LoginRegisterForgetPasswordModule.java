package com.jike.cashocean.ui.login.module;

import com.jike.cashocean.net.RetrofitInit;
import com.jike.cashocean.ui.login.api.ApiLoginCashMall;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class LoginRegisterForgetPasswordModule {
    private final ApiLoginCashMall apiLoginCashMall;

    public LoginRegisterForgetPasswordModule(String tag) {
        apiLoginCashMall = RetrofitInit.getRetrofit(tag).create(ApiLoginCashMall.class);
    }

    @Singleton
    @Provides
    ApiLoginCashMall providerCacheApi() {
        return apiLoginCashMall;
    }
}
