package com.jike.cashocean.ui.login.contract;

import com.jike.cashocean.model.LoginEntity;
import com.jike.cashocean.ui.base.BaseContract;

/**
 * Created by Yey on 2018/5/4.
 */

public interface LoginPasswordContact {
    interface View extends BaseContract.BaseView {
        void loginSuccess(LoginEntity login);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void goLogin(String phone, String password);
    }
}

