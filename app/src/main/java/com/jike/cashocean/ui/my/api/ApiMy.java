package com.jike.cashocean.ui.my.api;


import com.jike.cashocean.model.ClickAppListEntity;
import com.jike.cashocean.model.IdentityInfoEntity;
import com.jike.cashocean.model.MyAppListEntity;
import com.jike.cashocean.net.URL;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Yey on 2018/4/24.
 */

public interface ApiMy {
    /**
     * 获取用户认证信息接口
     */

    @FormUrlEncoded
    @POST(URL.GET_IDENTITY_INFO)
    Observable<IdentityInfoEntity> getIdentityInfo(@FieldMap Map<String, String> mapInfo);


    @FormUrlEncoded
    @POST(URL.MY_APP_LIST)
    Observable<MyAppListEntity> getMyAppList(@FieldMap Map<String, String> mapInfo);

    @FormUrlEncoded
    @POST(URL.MY_APP_CLICK)
    Observable<ClickAppListEntity> clickItemApp(@FieldMap Map<String, String> mapInfo);







}
