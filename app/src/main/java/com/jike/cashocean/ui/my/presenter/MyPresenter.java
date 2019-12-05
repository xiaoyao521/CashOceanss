package com.jike.cashocean.ui.my.presenter;

import android.content.Context;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.jike.cashocean.model.IdentityInfoEntity;
import com.jike.cashocean.net.BaseObserver;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.net.RxSchedulers;
import com.jike.cashocean.ui.base.BasePresenter;
import com.jike.cashocean.ui.my.api.ApiMy;
import com.jike.cashocean.ui.my.contract.MyContract;
import com.jike.cashocean.util.MapUrlTools;

import java.util.TreeMap;

import javax.inject.Inject;

/**
 * Created by Yey on 2018/5/5.
 */

public class MyPresenter extends BasePresenter<MyContract.View> implements MyContract.Presenter {

    private ApiMy _apiMy;
    private Context _context;

    @Inject
    public MyPresenter(ApiMy _apiMy, Context context) {
        this._apiMy = _apiMy;
        this._context = context;
    }


    @Override
    public void getIdentityInfo() {
        TreeMap<String, String> paramsMap = new TreeMap<>();
        paramsMap.put(Key.VERSION, String.valueOf(AppUtils.getAppVersionCode()));
        paramsMap.put(Key.TOKEN, SPUtils.getInstance().getString(Key.TOKEN));
        paramsMap.put(Key.SIGN, MapUrlTools.getSign(paramsMap));
        _apiMy.getIdentityInfo(paramsMap)
                .compose(RxSchedulers.<IdentityInfoEntity>applySchedulers())
                .compose(mView.<IdentityInfoEntity>bindToLife())
                .subscribe(new BaseObserver<IdentityInfoEntity>() {
                    @Override
                    public void onSuccess(IdentityInfoEntity identityInfoEntity) {
                        mView.loadIdentityInfo(identityInfoEntity);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        mView.loadIdentityInfo(null);
                    }
                });
    }
}
