package com.jike.cashocean.ui.login.contract;


import com.jike.cashocean.model.RegisterUser;
import com.jike.cashocean.ui.base.BaseContract;

public interface BindEmailContract {
    interface View extends BaseContract.BaseView {
        void loadData(RegisterUser registerUser);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void register(String phoneNum,
                      String password,
                      String rsakey,
                      String code,
                      String version,
                      String type,
                      String smsCodeInpuType,
                      String emai,
                      String fingerprintDevice,
                      String packageName,
                      String storename
        );
    }
}
