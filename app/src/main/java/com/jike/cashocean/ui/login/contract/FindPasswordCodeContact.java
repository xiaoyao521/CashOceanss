package com.jike.cashocean.ui.login.contract;

import com.jike.cashocean.model.ForgetPWCodeEntity;
import com.jike.cashocean.ui.base.BaseContract;

/**
 * Created by Yey on 2018/5/5.
 */

public interface FindPasswordCodeContact {
    interface View extends BaseContract.BaseView {
        void loadResult(ForgetPWCodeEntity forgetPWCodeEntity);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getForgetCode(String phone);
    }
}
