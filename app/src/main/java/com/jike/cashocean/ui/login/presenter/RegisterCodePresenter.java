package com.jike.cashocean.ui.login.presenter;

import com.blankj.utilcode.util.AppUtils;
import com.jike.cashocean.model.RigesterCodeEntity;
import com.jike.cashocean.net.BaseObserver;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.net.RxSchedulers;
import com.jike.cashocean.ui.base.BasePresenter;
import com.jike.cashocean.ui.login.api.ApiLoginCashMall;
import com.jike.cashocean.ui.login.contract.RegisterCodeContact;
import com.jike.cashocean.util.MapUrlTools;

import java.util.TreeMap;

import javax.inject.Inject;

/**
 * Created by Yey on 2018/5/2.
 */

public class RegisterCodePresenter extends BasePresenter<RegisterCodeContact.View> implements RegisterCodeContact.Presenter {

    ApiLoginCashMall apiLogin;

    @Inject
    public RegisterCodePresenter(ApiLoginCashMall apiLogin) {
        this.apiLogin = apiLogin;
    }

    @Override
    public void getRegisterCode(String phoneNum) {
        TreeMap<String, String> paramsMap = new TreeMap<>();
        paramsMap.put(Key.VERSION, String.valueOf(AppUtils.getAppVersionCode()));
        paramsMap.put(Key.PHONE, phoneNum);
        paramsMap.put(Key.TYPE, "1");
        paramsMap.put(Key.SIGN, MapUrlTools.getSign(paramsMap));
        apiLogin.registerSmsCode(paramsMap)
                .compose(RxSchedulers.<RigesterCodeEntity>applySchedulers())
                .compose(mView.<RigesterCodeEntity>bindToLife())
                .subscribe(new BaseObserver<RigesterCodeEntity>() {
                    @Override
                    public void onSuccess(RigesterCodeEntity getCode) {
                        mView.loadResult(getCode);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        mView.loadResult(null);
                    }
                });
    }


}
