package com.jike.cashocean.view.loadsircallback;

import android.content.Context;
import android.view.View;

import com.jike.cashocean.R;
import com.kingja.loadsir.callback.Callback;


public class LottieLoadingCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_lottie_loading;
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return true;
    }
}
