package com.jike.cashocean.ui.home.api;


import com.jike.cashocean.model.BaseBean;
import com.jike.cashocean.model.ClickAppListEntity;
import com.jike.cashocean.model.HomeBannerEntity;
import com.jike.cashocean.model.HomeListData;
import com.jike.cashocean.model.MessageBean;
import com.jike.cashocean.net.URL;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Yey on 2018/4/24.
 */

public interface ApiHome {
    /**
     * 首页Banner图接口
     */
    @FormUrlEncoded
    @POST(URL.APP_BANNER)
    Observable<HomeBannerEntity> getBannerUrl(@FieldMap Map<String, String> map);

    /**
     * 首页列表接口
     */
    @FormUrlEncoded
    @POST(URL.APP_HOME_LIST)
    Observable<HomeListData> getHomeListData(@FieldMap Map<String, String> map);

    /**
     * 首页列表接口
     */
    @FormUrlEncoded
    @POST(URL.APP_MESSAGE)
    Observable<MessageBean> getMessages(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST(URL.MY_APP_CLICK)
    Observable<ClickAppListEntity> clickItemApp(@FieldMap Map<String, String> mapInfo);

    //请求af链接
    @GET
    Observable<ResponseBody> RequestAf(@Url String url);

    //上传用户包信息
    @FormUrlEncoded
    @POST(URL.UPLOAD_USER_APP_LIST)
    Observable<BaseBean> uploadUserAppList(
            @FieldMap Map<String, String> mapInfo
    );
}
