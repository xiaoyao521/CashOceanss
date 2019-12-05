package com.jike.cashocean.ui.home.presenter;

import android.content.Context;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.jike.cashocean.model.ClickAppListEntity;
import com.jike.cashocean.model.HomeListData;
import com.jike.cashocean.model.MessageBean;
import com.jike.cashocean.net.BaseObserver;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.net.RxSchedulers;
import com.jike.cashocean.ui.MainActivity;
import com.jike.cashocean.ui.base.BasePresenter;
import com.jike.cashocean.ui.home.HomeFragment;
import com.jike.cashocean.ui.home.api.ApiHome;
import com.jike.cashocean.ui.home.contract.HomeContract;
import com.jike.cashocean.util.MapUrlTools;

import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;

/**
 * Created by Yey on 2018/5/5.
 */

public class HomePresenter extends BasePresenter<HomeFragment> implements HomeContract.Presenter {
    private ApiHome _apiHome;
    private Context _context;

    @Inject
    public HomePresenter(ApiHome apiHome, Context context) {
        this._apiHome = apiHome;
        this._context = context;
    }

    @Override
    public void getBannerUrl(Map<String, String> map) {

    }

    @Override
    public void getMessages() {

    }


    @Override
    public void getHomeListData(String desckey, String page, String pageLenght) {
        TreeMap<String, String> paramsMap = getParamesMap(desckey, page, pageLenght);

        LogUtils.e("json", new Gson().toJson(paramsMap));
        _apiHome.getHomeListData(paramsMap)
                .compose(RxSchedulers.<HomeListData>applySchedulers())
                .compose(mView.<HomeListData>bindToLife())
                .subscribe(new BaseObserver<HomeListData>() {
                    @Override
                    public void onSuccess(HomeListData homeListEntity) throws Exception {
                        mView.loadHomeListData(homeListEntity);
                    }

                    @Override
                    public void onFail(Throwable throwable) throws ClassNotFoundException {
//                        ToastUtils.showLong(throwable.getMessage());
                        mView.loadHomeListData(null);
                    }
                });

        getMessage();
    }

    public void getMessage() {
        TreeMap<String, String> map = getParamesMap();
        _apiHome.getMessages(map)
                .compose(RxSchedulers.<MessageBean>applySchedulers())
                .compose(mView.<MessageBean>bindToLife())
                .subscribe(new BaseObserver<MessageBean>() {
                    @Override
                    public void onSuccess(MessageBean homeListEntity) throws Exception {
                        mView.loanMessages(homeListEntity);
                    }

                    @Override
                    public void onFail(Throwable throwable) throws ClassNotFoundException {
                        ToastUtils.showLong(throwable.getMessage());
                        mView.loanMessages(null);
                    }
                });
    }

    private TreeMap<String, String> getParamesMap() {
        TreeMap<String, String> paramsMap = new TreeMap<String, String>();
        paramsMap.put(Key.SIGN, MapUrlTools.getSign(paramsMap, false));
        return paramsMap;
    }

    @Override
    public void loadMore(String desckey, String page, String pageLenght) {
        TreeMap<String, String> paramsMap = getParamesMap(desckey, page, pageLenght);
        _apiHome.getHomeListData(paramsMap)
                .compose(RxSchedulers.<HomeListData>applySchedulers())
                .compose(mView.<HomeListData>bindToLife())
                .subscribe(new BaseObserver<HomeListData>() {
                    @Override
                    public void onSuccess(HomeListData homeListEntity) throws Exception {
                        mView.loadMoreSuccess(homeListEntity);
                    }

                    @Override
                    public void onFail(Throwable throwable) throws ClassNotFoundException {
                        mView.loadMoreSuccess(null);
                    }
                });

    }

    @Override
    public void refresh(String desckey, String page, String pageLenght) {
        TreeMap<String, String> paramsMap = getParamesMap(desckey, page, pageLenght);
        _apiHome.getHomeListData(paramsMap)
                .compose(RxSchedulers.<HomeListData>applySchedulers())
                .compose(mView.<HomeListData>bindToLife())
                .subscribe(new BaseObserver<HomeListData>() {
                    @Override
                    public void onSuccess(HomeListData homeListEntity) throws Exception {
                        mView.refreshSuccess(homeListEntity);
                    }

                    @Override
                    public void onFail(Throwable throwable) throws ClassNotFoundException {
                        mView.refreshSuccess(null);
                    }
                });
        getMessage();
    }

    @Override
    public void clickItem(String id) {
        TreeMap<String, String> paramsMap = new TreeMap<>();
        paramsMap.put(Key.VERSION, String.valueOf(AppUtils.getAppVersionCode()));
        paramsMap.put(Key.TOKEN, SPUtils.getInstance().getString(Key.TOKEN));
        paramsMap.put(Key.ANDROID_ID, DeviceUtils.getAndroidID());
        paramsMap.put(Key.ADVERTISING_ID, MainActivity.advertising_id);
        paramsMap.put("appid", id);
        paramsMap.put(Key.SIGN, MapUrlTools.getSign(paramsMap));
        _apiHome.clickItemApp(paramsMap)
                .compose(RxSchedulers.<ClickAppListEntity>applySchedulers())
                .compose(mView.<ClickAppListEntity>bindToLife())
                .subscribe(new BaseObserver<ClickAppListEntity>() {
                    @Override
                    public void onSuccess(ClickAppListEntity clickAppListEntity) throws Exception {
                        mView.loadClick(clickAppListEntity);
                    }

                    @Override
                    public void onFail(Throwable throwable) throws ClassNotFoundException {
                        mView.loadClick(null);
                    }
                });

    }

    @Override
    public void order(String desckey, String page, String pageLenght) {
        TreeMap<String, String> paramsMap = getParamesMap(desckey, page, pageLenght);
        _apiHome.getHomeListData(paramsMap)
                .compose(RxSchedulers.<HomeListData>applySchedulers())
                .compose(mView.<HomeListData>bindToLife())
                .subscribe(new BaseObserver<HomeListData>() {
                    @Override
                    public void onSuccess(HomeListData homeListEntity) throws Exception {
                        mView.loadOrder(homeListEntity);
                    }

                    @Override
                    public void onFail(Throwable throwable) throws ClassNotFoundException {
                        mView.loadOrder(null);
                    }
                });
    }

    private TreeMap<String, String> getParamesMap(String desckey, String page, String perpage) {
        TreeMap<String, String> paramsMap = new TreeMap<String, String>();
        paramsMap.put(Key.VERSION, String.valueOf(AppUtils.getAppVersionCode()));
        String token = SPUtils.getInstance().getString(Key.TOKEN);
        if (token.length() != 0) {
            paramsMap.put("token", token);
        }
        if (desckey.length() != 0) {
            paramsMap.put("desckey", desckey);
        }
        if (page.length() != 0) {
            paramsMap.put("page", page);
        }
        if (perpage.length() != 0) {
            paramsMap.put("perpage", perpage);
        }
        /*String urlParamsByMap = MapUrlTools.getUrlParamsByMap(paramsMap);
        String Signs = EncryptUtils.encryptMD5ToString(urlParamsByMap).toLowerCase();
        paramsMap.put(Key.SIGN, Signs);*/
        paramsMap.put(Key.SIGN, MapUrlTools.getSign(paramsMap));

        return paramsMap;
    }

    public void requestAf(String click_url, String package_name) {
        _apiHome.RequestAf(click_url)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(new BaseObserver<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody responseBody) throws Exception {
                        mView.openGoogle(package_name);
                    }

                    @Override
                    public void onFail(Throwable throwable) throws ClassNotFoundException {
                        mView.openGoogle(package_name);
                    }
                });
    }
}
