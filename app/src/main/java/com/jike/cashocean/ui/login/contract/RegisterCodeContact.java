package com.jike.cashocean.ui.login.contract;

import com.jike.cashocean.model.RigesterCodeEntity;
import com.jike.cashocean.ui.base.BaseContract;

/**
 * Created by Yey on 2018/5/2.
 */

public interface RegisterCodeContact {

    interface View extends BaseContract.BaseView {

        void loadResult(RigesterCodeEntity rigesterCodeEntity);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getRegisterCode(String phoneNum);

    }
}
