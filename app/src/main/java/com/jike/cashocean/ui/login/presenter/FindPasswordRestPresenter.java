package com.jike.cashocean.ui.login.presenter;

import com.blankj.utilcode.util.AppUtils;
import com.jike.cashocean.model.ResetPasswordEntity;
import com.jike.cashocean.net.BaseObserver;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.net.RxSchedulers;
import com.jike.cashocean.ui.base.BasePresenter;
import com.jike.cashocean.ui.login.api.ApiLoginCashMall;
import com.jike.cashocean.ui.login.contract.FindPasswordRestContact;
import com.jike.cashocean.util.MapUrlTools;

import java.util.TreeMap;

import javax.inject.Inject;

/**
 * Created by Yey on 2018/5/5.
 */

public class FindPasswordRestPresenter extends BasePresenter<FindPasswordRestContact.View> implements FindPasswordRestContact.Presenter {

    ApiLoginCashMall apiLogin;

    @Inject
    public FindPasswordRestPresenter(ApiLoginCashMall apiLogin) {
        this.apiLogin = apiLogin;
    }

    @Override
    public void restPassword(String phoneNum, String code, String newPassword) {
        TreeMap<String, String> paramsMap = new TreeMap<>();
        paramsMap.put(Key.VERSION, String.valueOf(AppUtils.getAppVersionCode()));
        paramsMap.put(Key.PHONE, phoneNum);
        paramsMap.put(Key.AUTHETICATION_CODE, code);
        paramsMap.put(Key.PASSWORD, newPassword);
        paramsMap.put(Key.REGISTER_TYPE, "1");
        paramsMap.put(Key.SIGN, MapUrlTools.getSign(paramsMap));
        apiLogin.resetPassword(paramsMap)
                .compose(RxSchedulers.<ResetPasswordEntity>applySchedulers())
                .compose(mView.<ResetPasswordEntity>bindToLife())
                .subscribe(new BaseObserver<ResetPasswordEntity>() {
                    @Override
                    public void onSuccess(ResetPasswordEntity resetPasswordEntity) {
                        mView.loadResult(resetPasswordEntity);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        mView.loadResult(null);
                    }
                });

    }
}
