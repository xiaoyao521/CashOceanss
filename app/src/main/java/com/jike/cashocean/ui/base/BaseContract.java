package com.jike.cashocean.ui.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by Yey on 2018/4/23.
 */

public class BaseContract {

    public interface BaseView {
//        void showLoading();
//
//        void showSuccess();
//
//        void showFaild();
//
//        void showNoNet();

        <T> LifecycleTransformer<T> bindToLife();
    }

    public interface BasePresenter<T extends BaseView> {
        void attachView(T View);

        void detachView();
    }
}
