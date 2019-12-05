package com.jike.cashocean.ui.my.contract;

import com.jike.cashocean.model.IdentityInfoEntity;
import com.jike.cashocean.ui.base.BaseContract;

public interface MyContract {
    interface View extends BaseContract.BaseView {
        void loadIdentityInfo(IdentityInfoEntity identityInfoEntity);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void getIdentityInfo();
    }
}
