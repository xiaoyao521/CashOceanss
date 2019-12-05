package com.jike.cashocean.ui.authentication.module;

import android.content.Context;

import com.jike.cashocean.net.RetrofitInit;
import com.jike.cashocean.ui.authentication.api.ApiAuthentication;
import com.jike.cashocean.ui.authentication.cacheapi.CacheIdentityWrapperApi;
import com.jike.cashocean.util.MakePath;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;


@Module
public class SaveAuthenticationInfoModule {
    private final ApiAuthentication apiAuthentication;
    private CacheIdentityWrapperApi cacheIdentityWrapperApi;
    private Context _context;

    public SaveAuthenticationInfoModule(String tag, Context context) {
        apiAuthentication = RetrofitInit.getRetrofit(tag).create(ApiAuthentication.class);
        cacheIdentityWrapperApi = new RxCache.Builder()
                .setMaxMBPersistenceCache(100)
                .persistence(MakePath.getRxCache(context), new GsonSpeaker())
                .using(CacheIdentityWrapperApi.class);
        _context = context;
    }

    @Singleton
    @Provides
    ApiAuthentication providerAuthenticationApi() {
        return apiAuthentication;
    }

    @Singleton
    @Provides
    CacheIdentityWrapperApi providerCacheApi() {
        return cacheIdentityWrapperApi;
    }

    @Singleton
    @Provides
    Context providerContext() {
        return _context;
    }
}
