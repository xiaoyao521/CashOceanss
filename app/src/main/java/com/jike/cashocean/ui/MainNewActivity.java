package com.jike.cashocean.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chaychan.library.BottomBarLayout;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.jike.cashocean.BuildConfig;
import com.jike.cashocean.R;
import com.jike.cashocean.net.RxSchedulers;
import com.jike.cashocean.ui.base.BaseActivity;
import com.jike.cashocean.ui.base.SupportFragment;
import com.jike.cashocean.ui.home.BookkeeperFragment;
import com.jike.cashocean.ui.home.FindFragment;
import com.jike.cashocean.ui.home.HomeFragment;
import com.jike.cashocean.ui.my.MyFragment;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainNewActivity extends BaseActivity {
    @BindView(R.id.bottom_bar)
    BottomBarLayout bottomBar;
    public static String advertising_id = "";
    long mExitTime;//两秒内连续按2次退出
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    private SupportFragment[] mFragments = new SupportFragment[4];

    @Override
    public int getContentLayout() {
        return R.layout.activity_main_new;
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
        mFragments[0] = HomeFragment.newInstance();
        mFragments[1] = BookkeeperFragment.newInstance();
        mFragments[2] = FindFragment.newInstance();
        mFragments[3] = MyFragment.newInstance();
        vpMain.setOffscreenPageLimit(4);
        vpMain.setAdapter(new fragmentAdapter(getSupportFragmentManager()));
        bottomBar.setViewPager(vpMain);
    }


    @Override
    public void initData() {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                AdvertisingIdClient.Info advertisingIdInfo =
                        AdvertisingIdClient.getAdvertisingIdInfo(MainNewActivity.this);
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

    class fragmentAdapter extends FragmentPagerAdapter {


        public fragmentAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }

        @Override
        public int getCount() {
            return mFragments.length;
        }
    }
}