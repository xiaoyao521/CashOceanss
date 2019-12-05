package com.jike.cashocean.ui.login.presenter;

import com.blankj.utilcode.util.AppUtils;
import com.jike.cashocean.model.LoginEntity;
import com.jike.cashocean.net.BaseObserver;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.net.RxSchedulers;
import com.jike.cashocean.ui.base.BasePresenter;
import com.jike.cashocean.ui.login.api.ApiLoginCashMall;
import com.jike.cashocean.ui.login.contract.LoginPasswordContact;
import com.jike.cashocean.util.MapUrlTools;

import java.util.TreeMap;

import javax.inject.Inject;

/**
 * Created by Yey on 2018/5/4.
 */

public class LoginPasswordPresenter extends BasePresenter<LoginPasswordContact.View> implements LoginPasswordContact.Presenter {

    ApiLoginCashMall apiLoginCashMall;

    @Inject
    public LoginPasswordPresenter(ApiLoginCashMall apiLogin) {
        this.apiLoginCashMall = apiLogin;
    }

    @Override
    public void goLogin(String phone, String password) {
        TreeMap<String, String> paramsMap = new TreeMap<>();
        paramsMap.put(Key.VERSION, String.valueOf(AppUtils.getAppVersionCode()));
        paramsMap.put(Key.PHONE, phone);
        paramsMap.put(Key.PASSWORD, password);
        paramsMap.put(Key.SIGN, MapUrlTools.getSign(paramsMap));
        apiLoginCashMall.loginUser(paramsMap)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.<LoginEntity>bindToLife())
                .subscribe(new BaseObserver<LoginEntity>() {
                    @Override
                    public void onSuccess(LoginEntity login) {
                        mView.loginSuccess(login);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        mView.loginSuccess(null);
                    }
                });
    }
}
