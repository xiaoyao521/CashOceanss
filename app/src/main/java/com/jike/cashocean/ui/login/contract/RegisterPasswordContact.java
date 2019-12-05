package com.jike.cashocean.ui.login.contract;

import com.jike.cashocean.model.RegisterUserEntity;
import com.jike.cashocean.ui.base.BaseContract;

import java.util.Map;

/**
 * Created by Yey on 2018/5/5.
 */

public interface RegisterPasswordContact {
    interface View extends BaseContract.BaseView {
        void loadResult(RegisterUserEntity registerUserEntity);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void register(Map<String, String> map);
    }
}
