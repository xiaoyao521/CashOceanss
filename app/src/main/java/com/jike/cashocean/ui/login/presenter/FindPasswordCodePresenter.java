package com.jike.cashocean.ui.login.presenter;

import com.blankj.utilcode.util.AppUtils;
import com.jike.cashocean.model.ForgetPWCodeEntity;
import com.jike.cashocean.net.BaseObserver;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.net.RxSchedulers;
import com.jike.cashocean.ui.base.BasePresenter;
import com.jike.cashocean.ui.login.api.ApiLoginCashMall;
import com.jike.cashocean.ui.login.contract.FindPasswordCodeContact;
import com.jike.cashocean.util.MapUrlTools;

import java.util.TreeMap;

import javax.inject.Inject;

/**
 * Created by Yey on 2018/5/5.
 */

public class FindPasswordCodePresenter extends BasePresenter<FindPasswordCodeContact.View> implements FindPasswordCodeContact.Presenter {

    ApiLoginCashMall apiLogin;

    @Inject
    public FindPasswordCodePresenter(ApiLoginCashMall apiLogin) {
        this.apiLogin = apiLogin;
    }

    @Override
    public void getForgetCode(String phone) {
        TreeMap<String, String> paramsMap = new TreeMap<>();
        paramsMap.put(Key.VERSION, String.valueOf(AppUtils.getAppVersionCode()));
        paramsMap.put(Key.PHONE, phone);
        paramsMap.put("type", "1");
        paramsMap.put(Key.SIGN, MapUrlTools.getSign(paramsMap));
        apiLogin.getForgetSMSCode(paramsMap)
                .compose(RxSchedulers.<ForgetPWCodeEntity>applySchedulers())
                .compose(mView.<ForgetPWCodeEntity>bindToLife())
                .subscribe(new BaseObserver<ForgetPWCodeEntity>() {
                    @Override
                    public void onSuccess(ForgetPWCodeEntity forgetPWCodeEntity) {
                        mView.loadResult(forgetPWCodeEntity);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        mView.loadResult(null);
                    }
                });

    }
}
