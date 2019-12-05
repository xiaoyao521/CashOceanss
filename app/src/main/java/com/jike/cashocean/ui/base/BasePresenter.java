package com.jike.cashocean.ui.base;

/**
 * Created by Yey on 2018/4/23.
 */
public class BasePresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {

    protected T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if (mView != null) mView = null;
    }
}
