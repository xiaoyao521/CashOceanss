package com.jike.cashocean.ui.home.module;

import android.content.Context;

import com.jike.cashocean.net.RetrofitInit;
import com.jike.cashocean.ui.home.api.ApiHome;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class HomeModule {
    private final ApiHome apiHome;
    private Context _context;


    public HomeModule(String tag, Context context) {
        apiHome = RetrofitInit.getRetrofit(tag).create(ApiHome.class);
        _context = context;
    }

    @Singleton
    @Provides
    ApiHome providerCacheApi() {
        return apiHome;
    }

    @Singleton
    @Provides
    Context providerContext() {
        return _context;
    }
}
