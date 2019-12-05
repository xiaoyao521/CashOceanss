package com.jike.cashocean.ui.my.presenter;

import android.content.Context;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.SPUtils;
import com.jike.cashocean.model.ClickAppListEntity;
import com.jike.cashocean.model.MyAppListEntity;
import com.jike.cashocean.net.BaseObserver;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.net.RxSchedulers;
import com.jike.cashocean.ui.MainActivity;
import com.jike.cashocean.ui.base.BasePresenter;
import com.jike.cashocean.ui.my.api.ApiMy;
import com.jike.cashocean.ui.my.contract.MyAppListContract;
import com.jike.cashocean.util.MapUrlTools;

import java.util.TreeMap;

import javax.inject.Inject;

/**
 * Created by Yey on 2018/5/5.
 */

public class MyAppListPresenter extends BasePresenter<MyAppListContract.View> implements MyAppListContract.Presenter {

    private ApiMy _apiMy;
    private Context _context;

    @Inject
    public MyAppListPresenter(ApiMy _apiMy, Context context) {
        this._apiMy = _apiMy;
        this._context = context;
    }

    @Override
    public void getMyAppList(String page, String pageLenght) {
        _apiMy.getMyAppList(getParamesTreeMap(page, pageLenght))
                .compose(RxSchedulers.<MyAppListEntity>applySchedulers())
                .compose(mView.<MyAppListEntity>bindToLife())
                .subscribe(new BaseObserver<MyAppListEntity>() {
                    @Override
                    public void onSuccess(MyAppListEntity myAppListEntity) throws Exception {
                        mView.loadAppListData(myAppListEntity);
                    }

                    @Override
                    public void onFail(Throwable throwable) throws ClassNotFoundException {
                        mView.loadAppListData(null);
                    }
                });
    }


    @Override
    public void loadMore(String page, String pageLenght) {
        _apiMy.getMyAppList(getParamesTreeMap(page, pageLenght))
                .compose(RxSchedulers.<MyAppListEntity>applySchedulers())
                .compose(mView.<MyAppListEntity>bindToLife())
                .subscribe(new BaseObserver<MyAppListEntity>() {
                    @Override
                    public void onSuccess(MyAppListEntity myAppListEntity) throws Exception {
                        mView.loadMoreSuccess(myAppListEntity);
                    }

                    @Override
                    public void onFail(Throwable throwable) throws ClassNotFoundException {
                        mView.loadMoreSuccess(null);
                    }
                });
    }

    @Override
    public void refresh(String page, String pageLenght) {
        _apiMy.getMyAppList(getParamesTreeMap(page, pageLenght))
                .compose(RxSchedulers.<MyAppListEntity>applySchedulers())
                .compose(mView.<MyAppListEntity>bindToLife())
                .subscribe(new BaseObserver<MyAppListEntity>() {
                    @Override
                    public void onSuccess(MyAppListEntity myAppListEntity) throws Exception {
                        mView.refreshSuccess(myAppListEntity);
                    }

                    @Override
                    public void onFail(Throwable throwable) throws ClassNotFoundException {
                        mView.refreshSuccess(null);
                    }
                });
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
        _apiMy.clickItemApp(paramsMap)
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

    private TreeMap<String, String> getParamesTreeMap(String page, String pageLenght) {
        TreeMap<String, String> paramsMap = new TreeMap<>();
        paramsMap.put(Key.VERSION, String.valueOf(AppUtils.getAppVersionCode()));
        paramsMap.put(Key.TOKEN, SPUtils.getInstance().getString(Key.TOKEN));
        paramsMap.put("page", page);
        paramsMap.put("perpage", pageLenght);
        paramsMap.put(Key.SIGN, MapUrlTools.getSign(paramsMap));
        return paramsMap;
    }
}
