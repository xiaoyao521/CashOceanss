package com.jike.cashocean.ui.authentication.cacheapi;

import com.jike.cashocean.model.IdTypeEntity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.LifeCache;

public interface CacheIdentityWrapperApi {
    @LifeCache(duration = 10, timeUnit = TimeUnit.HOURS)
    Observable<IdTypeEntity> getIdTypeCache(Observable<IdTypeEntity> idTypeEntityObservable);
}
