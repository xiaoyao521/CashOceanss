package com.jike.cashocean.ui.login.contract;

import com.jike.cashocean.model.ResetPasswordEntity;
import com.jike.cashocean.ui.base.BaseContract;

/**
 * Created by Yey on 2018/5/5.
 */

public interface FindPasswordRestContact {
    interface View extends BaseContract.BaseView {
        void loadResult(ResetPasswordEntity resetPasswordEntity);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void restPassword(String phoneNum, String key, String newPassword);
    }
}
