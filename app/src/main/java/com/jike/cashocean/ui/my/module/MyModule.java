package com.jike.cashocean.ui.my.module;

import android.content.Context;

import com.jike.cashocean.net.RetrofitInit;
import com.jike.cashocean.ui.my.api.ApiMy;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class MyModule {
    private final ApiMy apiMy;
    private Context _context;

    public MyModule(String tag, Context context) {
        apiMy = RetrofitInit.getRetrofit(tag).create(ApiMy.class);
        _context = context;
    }

    @Singleton
    @Provides
    ApiMy providerCacheApi() {
        return apiMy;
    }

    @Singleton
    @Provides
    Context providerContext() {
        return _context;
    }
}
