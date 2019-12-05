package com.jike.cashocean.ui.base;

import android.view.View;

public interface ILoadSirFragment {
    //点击视图重新加载方法
    void onReloadClickCashMall(View v);


    //是否需要初始化LoadSir
    boolean needUserLoadSir();
}
