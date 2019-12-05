package com.jike.cashocean.ui.home.contract;

import com.jike.cashocean.model.ClickAppListEntity;
import com.jike.cashocean.model.HomeBannerEntity;
import com.jike.cashocean.model.HomeListData;
import com.jike.cashocean.model.MessageBean;
import com.jike.cashocean.ui.base.BaseContract;
import com.jike.cashocean.ui.home.HomeFragment;

import java.util.Map;

public interface HomeContract {
    interface View extends BaseContract.BaseView {
        void loadBannerUrl(HomeBannerEntity homeBannerEntity);

        void loadHomeListData(HomeListData homeListEntity);

        void loadMoreSuccess(HomeListData homeListData);

        void refreshSuccess(HomeListData homeListData);

        void loadClick(ClickAppListEntity clickAppListEntity);

        void loadOrder(HomeListData homeListData);

        void openGoogle(String packageName);

        void loanMessages(MessageBean messageBean);
    }

    interface Presenter extends BaseContract.BasePresenter<HomeFragment> {

        void getBannerUrl(Map<String, String> map);

        void getMessages();

        void getHomeListData(String desckey, String page, String perpage);

        void loadMore(String desckey, String page, String pageLenght);

        void refresh(String desckey, String page, String pageLenght);

        void clickItem(String id);

        void order(String desckey, String page, String pageLenght);
    }
}
