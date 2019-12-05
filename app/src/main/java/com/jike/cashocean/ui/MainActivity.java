package com.jike.cashocean.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chaychan.library.BottomBarItem;
import com.chaychan.library.BottomBarLayout;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.jike.cashocean.BuildConfig;
import com.jike.cashocean.R;
import com.jike.cashocean.net.RxSchedulers;
import com.jike.cashocean.ui.base.BaseActivity;
import com.jike.cashocean.ui.base.SupportFragment;
import com.jike.cashocean.ui.home.HomeFragment;
import com.jike.cashocean.ui.my.MyFragment;
import com.jike.cashocean.util.PhoneReadUtils;

import java.util.Locale;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity {
    @BindView(R.id.bottom_bar)
    BottomBarLayout bottomBar;
    public static String advertising_id = "";
    long mExitTime;//两秒内连续按2次退出
    private SupportFragment[] mFragments = new SupportFragment[2];

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }


    @Override
    public void initInjector() {

    }

    /*@Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Configuration configuration = getResources().getConfiguration();
        //17以上利用 setLocale（）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(new Locale("tl","PH"));
        } else {
            configuration.locale = new Locale("tl","PH");
        }
        getResources().updateConfiguration(configuration, displayMetrics);
    }*/

    @Override
    public void bindView(View view, Bundle sacedInstanceState) {
        HomeFragment homeFragment = findFragment(HomeFragment.class);
        if (homeFragment == null) {
            mFragments[0] = HomeFragment.newInstance();
            mFragments[1] = MyFragment.newInstance();
            getSupportDelegate().loadMultipleRootFragment(R.id.fl_container, 0, mFragments[0],
                    mFragments[1]);
        } else {
            mFragments[0] = homeFragment;
            mFragments[1] = findFragment(MyFragment.class);
        }

        bottomBar.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(BottomBarItem bottomBarItem, int i, int i1p) {
                SupportFragment currentFragment = mFragments[i1p];
                if (currentFragment instanceof HomeFragment) {
                    getSupportDelegate().showHideFragment(mFragments[0], mFragments[1]);
                } else if (currentFragment instanceof MyFragment) {
                    getSupportDelegate().showHideFragment(mFragments[1], mFragments[0]);
                }
            }
        });
    }


    @Override
    public void initData() {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                AdvertisingIdClient.Info advertisingIdInfo =
                        AdvertisingIdClient.getAdvertisingIdInfo(MainActivity.this);
                String adId = advertisingIdInfo.getId();
                e.onNext(adId);
                e.onComplete();
            }
        })
                .compose(RxSchedulers.<String>applySchedulers())
                .compose(this.<String>bindToLife())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        advertising_id = s;
                        if (BuildConfig.DEBUG) {
                            LogUtils.e("查看手机ID", advertising_id, DeviceUtils.getAndroidID());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        advertising_id = "123456";//格式就是123456告诉后台google Id 获取失败
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    @Override
    public void onBackPressedSupport() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtils.showLong(getResources().getText(R.string.quit_hint));
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public void onReloadClick(View v) {

    }

    @Override
    public boolean needUserLoadSir() {
        return false;
    }
}