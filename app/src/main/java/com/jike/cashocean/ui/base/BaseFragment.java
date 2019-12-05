package com.jike.cashocean.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jike.cashocean.R;
import com.jike.cashocean.model.ServerDataCode;
import com.jike.cashocean.net.exception.SERVER_CODE;
import com.jike.cashocean.util.DialogTools;
import com.jike.cashocean.view.loadsircallback.EmptyCallback;
import com.jike.cashocean.view.loadsircallback.ErrorCallback;
import com.jike.cashocean.view.loadsircallback.LoadingCallback;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.core.Convertor;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

//import com.classic.common.MultipleStatusView;

/**
 * desc:
 * author: Will .
 * date: 2017/9/2 .
 */
public abstract class BaseFragment<T1 extends BaseContract.BasePresenter> extends SupportFragment implements IBase, BaseContract.BaseView, ILoadSirFragment {
    protected Context mContext;
    protected Dialog mLoadingDialog = null;
    AnimationDrawable animationDrawable;
    Unbinder unbinder;
    @Nullable
    @Inject
    protected T1 mPresenter;

    protected LoadService loadService;

    static Convertor convertor = new Convertor<ServerDataCode>() {
        @Override
        public Class<? extends Callback> map(ServerDataCode dataCode) {
            Class<? extends Callback> resultCode = SuccessCallback.class;
            switch (dataCode.getServerDataCode()) {
                case SERVER_CODE.SUCCESS://成功
                    resultCode = SuccessCallback.class;
                    break;
                case SERVER_CODE.DATA_LOADING://加载中
                    resultCode = LoadingCallback.class;
                    break;
                case SERVER_CODE.DATA_AGAIN_LOADING://再次加载
                    resultCode = EmptyCallback.class;
                    break;
                case SERVER_CODE.DATA_ERROR://再次加载
                    resultCode = ErrorCallback.class;
                    break;
                default:
                    resultCode = ErrorCallback.class;
                    break;
            }
            return resultCode;
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getContentLayout(), container, false);
        mContext = rootView.getContext();
        unbinder = ButterKnife.bind(this, rootView);
        mLoadingDialog = DialogTools.dialogWait(mContext, R.layout.layout_waiting_animation);
        animationDrawable = (AnimationDrawable) mLoadingDialog.findViewById(R.id.img_waiting_animation).getBackground();
        initInjector();
        if (needUserLoadSir()) {
            View mRootView = initLoadSir(rootView);
            return mRootView;
        }

        return rootView;
    }

    protected View initLoadSir(View rootView) {
        loadService = LoadSir.getDefault().register(rootView, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                onReloadClickCashMall(v);
            }
        }, convertor);
        return loadService.getLoadLayout();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view, savedInstanceState);
        //dagger 2  注入之后才会有mPresenter
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initData();
    }

    protected void showLoadingDialog() {
        if (mLoadingDialog != null && !mLoadingDialog.isShowing()) {
            animationDrawable.start();
            mLoadingDialog.show();
        }
    }

    protected void hideLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
            animationDrawable.stop();
        }
    }


    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
