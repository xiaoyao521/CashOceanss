package com.jike.cashocean.ui.login.presenter;

import com.jike.cashocean.model.RegisterUserEntity;
import com.jike.cashocean.net.BaseObserver;
import com.jike.cashocean.net.RxSchedulers;
import com.jike.cashocean.ui.base.BasePresenter;
import com.jike.cashocean.ui.login.api.ApiLoginCashMall;
import com.jike.cashocean.ui.login.contract.RegisterPasswordContact;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Yey on 2018/5/5.
 */

public class RegisterPasswordPresenter extends BasePresenter<RegisterPasswordContact.View> implements RegisterPasswordContact.Presenter {

    ApiLoginCashMall apiLogin;

    @Inject
    public RegisterPasswordPresenter(ApiLoginCashMall apiLogin) {
        this.apiLogin = apiLogin;
    }

    @Override
    public void register(Map<String, String> map) {
        apiLogin.registerUser(map)
                .compose(RxSchedulers.<RegisterUserEntity>applySchedulers())
                .compose(mView.<RegisterUserEntity>bindToLife())
                .subscribe(new BaseObserver<RegisterUserEntity>() {
                    @Override
                    public void onSuccess(RegisterUserEntity registerUserEntity) {
                        mView.loadResult(registerUserEntity);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        mView.loadResult(null);
                    }
                });
    }
}
