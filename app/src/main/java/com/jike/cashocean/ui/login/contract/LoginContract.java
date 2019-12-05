package com.jike.cashocean.ui.login.contract;

import com.jike.cashocean.model.CheckUserExist;
import com.jike.cashocean.ui.base.BaseContract;

/**
 * Created by Yey on 2018/4/26.
 */

public interface LoginContract {
    interface View extends BaseContract.BaseView {
        void loadResult(CheckUserExist checkUserExist);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void checkUserExist(String phoneNum);
    }
}
