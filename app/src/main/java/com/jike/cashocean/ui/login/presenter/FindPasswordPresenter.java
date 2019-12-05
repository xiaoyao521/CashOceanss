package com.jike.cashocean.ui.login.presenter;


import com.blankj.utilcode.util.AppUtils;
import com.jike.cashocean.model.CheckUserExist;
import com.jike.cashocean.net.BaseObserver;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.net.RxSchedulers;
import com.jike.cashocean.ui.base.BasePresenter;
import com.jike.cashocean.ui.login.api.ApiLoginCashMall;
import com.jike.cashocean.ui.login.contract.FindPasswordContact;
import com.jike.cashocean.util.MapUrlTools;

import java.util.TreeMap;

import javax.inject.Inject;

/**
 * Created by Yey on 2018/5/5.
 */

public class FindPasswordPresenter extends BasePresenter<FindPasswordContact.View> implements FindPasswordContact.Presenter {

    ApiLoginCashMall apiLogin;

    @Inject
    public FindPasswordPresenter(ApiLoginCashMall apiLogin) {
        this.apiLogin = apiLogin;
    }

    @Override
    public void checkUserExist(String phoneNum) {
        TreeMap<String, String> paramsMap = new TreeMap<>();
        paramsMap.put(Key.VERSION, String.valueOf(AppUtils.getAppVersionCode()));
        paramsMap.put(Key.PHONE, phoneNum);
        paramsMap.put(Key.SIGN, MapUrlTools.getSign(paramsMap));
        apiLogin.checkUserExist(paramsMap)
                .compose(RxSchedulers.<CheckUserExist>applySchedulers())
                .compose(mView.<CheckUserExist>bindToLife())
                .subscribe(new BaseObserver<CheckUserExist>() {
                    @Override
                    public void onSuccess(CheckUserExist checkUserExist) {
                        mView.loadResult(checkUserExist);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        mView.loadResult(null);
                    }
                });
    }
}
