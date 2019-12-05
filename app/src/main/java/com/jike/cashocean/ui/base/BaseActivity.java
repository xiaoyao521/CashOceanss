package com.jike.cashocean.ui.base;

import android.app.Dialog;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.SPUtils;
import com.jaeger.library.StatusBarUtil;
import com.jike.cashocean.R;
import com.jike.cashocean.model.ServerDataCode;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.net.exception.SERVER_CODE;
import com.jike.cashocean.util.DialogTools;
import com.jike.cashocean.view.loadsircallback.EmptyCallback;
import com.jike.cashocean.view.loadsircallback.ErrorCallback;
import com.jike.cashocean.view.loadsircallback.LoadingCallback;
import com.jike.cashocean.view.loadsircallback.LottieLoadingCallback;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.core.Convertor;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

//import com.classic.common.MultipleStatusView;


/**
 * Created by Yey on 2018/4/23.
 */

public abstract class BaseActivity<T extends BaseContract.BasePresenter> extends SupportActivity implements BaseContract.BaseView, ILoadSir, BGASwipeBackHelper.Delegate, IBase, LifecycleOwner {

    private Unbinder unbinder;
    @Nullable
    @Inject
    protected T mPresenter;
    private Dialog mLoadingDialog = null;
    protected BGASwipeBackHelper mSwipeBackHelper;
    AnimationDrawable animationDrawable;
    protected LoadService loadService;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(getContentLayout(), null);
        setContentView(view);
        mLoadingDialog = DialogTools.dialogWait(this, R.layout.layout_waiting_animation);
        animationDrawable =
                (AnimationDrawable) mLoadingDialog.findViewById(R.id.img_waiting_animation).getBackground();
        unbinder = ButterKnife.bind(this, view);
        initInjector();
        bindView(view, savedInstanceState);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        if (needUserLoadSir() && view.findViewById(R.id.container_loadsir) != null) {
            initLoadSir(view.findViewById(R.id.container_loadsir));
        }
        initData();
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.main_color), 0);
    }

    protected void initLoadSir(Object object) {
        loadService = LoadSir.getDefault().register(object, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                //通过V的类型判断是哪种类型的报错，再看需不需要回调
                onReloadClick(v);
            }
        }, new Convertor<ServerDataCode>() {
            @Override
            public Class<? extends Callback> map(ServerDataCode dataCode) {
                Class<? extends Callback> resultCode = LottieLoadingCallback.class;
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
        });
    }


    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);

        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。

        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
        // 设置底部导航条是否悬浮在内容上，默认值为 false
        mSwipeBackHelper.setIsNavigationBarOverlap(false);
    }


    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     *
     * @return
     */
    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    /**
     * 正在滑动返回
     *
     * @param slideOffset 从 0 到 1
     */
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {
        if (slideOffset < 0.03) {
            KeyboardUtils.hideSoftInput(this);
        }
    }

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    @Override
    public void onSwipeBackLayoutCancel() {

    }

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        // 正在滑动返回的时候取消返回按钮事件
        if (mSwipeBackHelper.isSliding()) {
            return;
        }
        mSwipeBackHelper.backward();
    }


    /**
     * 设置状态栏颜色
     *
     * @param color
     * @param statusBarAlpha 透明度
     */
    public void setStatusBarColor(int color, @IntRange(from = 0, to = 255) int statusBarAlpha) {
        StatusBarUtil.setColorForSwipeBack(this, getResources().getColor(color), statusBarAlpha);
    }

    public void setStatusBarTranslucent(@IntRange(from = 0, to = 255) int statusBarAlpha) {
        StatusBarUtil.setTranslucent(this, statusBarAlpha);
    }


    protected void showLoadingDialog() {
        if (mLoadingDialog != null && !mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
            animationDrawable.start();
        }
    }

    protected void hideLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
            animationDrawable.stop();
        }
    }

//    @Override
//    public void showLoading() {
//
//    }
//
//    @Override
//    public void showSuccess() {
//
//
//    }
//
//    @Override
//    public void showFaild() {
//
//    }
//
//    @Override
//    public void showNoNet() {
//
//    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        Locale locale;
        if (TextUtils.isEmpty(SPUtils.getInstance().getString(Key.LANGUAGE_KEY))) {
            //如果没有指定语言使用系统首选语言
            locale = Locale.ENGLISH;
        } else {//指定了语言使用指定语言，没有则使用首选语言
            locale = new Locale("tl", "PH");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale);
        } else {
            config.locale = locale;
        }
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;

    }
}
