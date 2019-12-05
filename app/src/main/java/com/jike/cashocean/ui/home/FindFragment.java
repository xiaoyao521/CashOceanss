package com.jike.cashocean.ui.home;

import android.os.Bundle;
import android.view.View;

import com.jike.cashocean.R;
import com.jike.cashocean.ui.base.BaseFragment;
import com.jike.cashocean.ui.home.presenter.FindPresenter;
import com.trello.rxlifecycle2.LifecycleTransformer;

public class FindFragment extends BaseFragment<FindPresenter> {

    public static FindFragment newInstance() {
        Bundle args = new Bundle();
        FindFragment fragment = new FindFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_find;
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void bindView(View view, Bundle sacedInstanceState) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onReloadClickCashMall(View v) {

    }

    @Override
    public boolean needUserLoadSir() {
        return false;
    }
}
