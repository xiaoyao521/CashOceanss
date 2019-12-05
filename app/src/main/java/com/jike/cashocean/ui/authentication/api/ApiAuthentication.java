package com.jike.cashocean.ui.authentication.api;


import com.jike.cashocean.model.IdTypeEntity;
import com.jike.cashocean.model.IdentityInfoEntity;
import com.jike.cashocean.model.OssBackEntity;
import com.jike.cashocean.model.SaveIdentityInfoEntity;
import com.jike.cashocean.net.URL;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Yey on 2018/4/24.
 */

public interface ApiAuthentication {
    /**
     * 保存用户认证信息接口
     */
    @FormUrlEncoded
    @POST(URL.SAVE_IDENTITY_INFO)
    Observable<SaveIdentityInfoEntity> saveIdentityInfo(@FieldMap Map<String, String> mapInfo);

    /**
     * 获取用户认证信息接口
     */

    @FormUrlEncoded
    @POST(URL.GET_IDENTITY_INFO)
    Observable<IdentityInfoEntity> getIdentityInfo(@FieldMap Map<String, String> mapInfo);

    /**
     * 获取OSSTOKEN
     *
     * @param mapInfo
     * @return
     */
    @FormUrlEncoded
    @POST(URL.GET_OSS_TOKEN)
    Observable<OssBackEntity> getOssToken(@FieldMap Map<String, String> mapInfo);

    /**
     * 获取证件类型
     */

    @FormUrlEncoded
    @POST(URL.GET_ID_TYPE)
    Observable<IdTypeEntity> getIdType(@FieldMap Map<String, String> mapInfo);


}
