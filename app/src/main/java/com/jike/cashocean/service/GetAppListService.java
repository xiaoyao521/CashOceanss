package com.jike.cashocean.service;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.jike.cashocean.Content.KeyValue;
import com.jike.cashocean.model.BaseBean;
import com.jike.cashocean.net.BaseObserver;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.net.RetrofitInit;
import com.jike.cashocean.ui.home.api.ApiHome;
import com.jike.cashocean.util.MapUrlTools;
import com.jike.cashocean.util.PhoneReadUtils;

import java.util.TreeMap;

import okhttp3.ResponseBody;

public class GetAppListService extends IntentService {


    public GetAppListService() {
        super("getapplist");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        submitAppList();
    }

    private TreeMap<String, String> getParamesMap(String applist) {
        TreeMap<String, String> paramsMap = new TreeMap<String, String>();
        paramsMap.put("packages", applist);
        paramsMap.put(Key.SIGN, MapUrlTools.getSign(paramsMap));
        return paramsMap;
    }

    private void submitAppList() {
        //获取app列表
        String appList = PhoneReadUtils.getAppListNew();
        ApiHome apiHome = RetrofitInit.getRetrofit("").create(ApiHome.class);
        TreeMap<String, String> paramsMap = getParamesMap(appList);
        apiHome.uploadUserAppList(paramsMap)
                .subscribe(new BaseObserver<BaseBean>() {
                    @Override
                    public void onSuccess(BaseBean baseBean) throws Exception {
                        if (baseBean.getRet()==200) {
                            SPUtils.getInstance().put(KeyValue.IS_UPLOAD_APPLIST, true);
                        }
                    }

                    @Override
                    public void onFail(Throwable throwable) throws ClassNotFoundException {

                    }
                });
    }
}
