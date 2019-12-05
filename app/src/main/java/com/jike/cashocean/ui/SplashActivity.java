package com.jike.cashocean.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.facebook.appevents.AppEventsLogger;
import com.jike.cashocean.R;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.net.RxSchedulers;
import com.jike.cashocean.ui.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by Yey on 2018/1/21.
 */

public class SplashActivity extends BaseActivity {
    private static final int GUIDE_WHAT = 1039;
    @BindView(R.id.tv_timer)
    TextView tvTimer;

    @Override
    public int getContentLayout() {
        return R.layout.activity_splash_cash;
    }

    @Override
    public void initInjector() {

    }

//    boolean isManualSkip;

    @SuppressLint({"CheckResult", "MissingPermission"})
    @Override
    public void bindView(View view, Bundle sacedInstanceState) {
        AppEventsLogger.newLogger(this).logEvent(Key.START_APP);
        tvTimer.setText(getString(R.string.skip) + " " + 3);
        tvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(MainNewActivity.class);
                finish();
            }
        });

        Observable.interval(1, 2, TimeUnit.SECONDS)
                .compose(RxSchedulers.applySchedulers())
                .compose(bindToLife())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        if (aLong == 2) {
//                            if (isManualSkip) {
//                                return;
//                            }
                            ActivityUtils.startActivity(MainNewActivity.class);
                            finish();
                        } else {
                            long timer = 2 - aLong;
                            if (timer >= 0) {
                                tvTimer.setText(getString(R.string.skip) + " " + timer);
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onReloadClick(View v) {

    }


    @Override
    public boolean needUserLoadSir() {
        return false;
    }

}
