package com.jike.cashocean.ui.my.contract;

import com.jike.cashocean.model.ClickAppListEntity;
import com.jike.cashocean.model.MyAppListEntity;
import com.jike.cashocean.ui.base.BaseContract;

public interface MyAppListContract {
    interface View extends BaseContract.BaseView {
        void loadAppListData(MyAppListEntity myAppListEntity);

        void loadMoreSuccess(MyAppListEntity myAppListEntity);

        void refreshSuccess(MyAppListEntity myAppListEntity);

        void loadClick(ClickAppListEntity clickAppListEntity);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getMyAppList(String page, String pageLenght);

        void loadMore(String page, String pageLenght);

        void refresh(String page, String pageLenght);

        void clickItem(String id);
    }
}
