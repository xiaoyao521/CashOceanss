package com.jike.cashocean.ui.authentication.contract;

import com.jike.cashocean.model.IdTypeEntity;
import com.jike.cashocean.model.SaveIdentityInfoEntity;
import com.jike.cashocean.ui.base.BaseContract;

import java.util.Map;

public interface AuthenticationContract {
    interface View extends BaseContract.BaseView {
        void loadSaveInfoSuccess(SaveIdentityInfoEntity saveIdentityInfoEntity);

        void loadSuccessFacePhoto(String faceName);

        void loadScuccessIDPhoto(String idName);

        void loadType(IdTypeEntity idTypeEntity, boolean isFirstFailed);


    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void saveInfo(Map<String, String> mapInfo);

        void getIdType(boolean isFirstFailed);
    }
}
