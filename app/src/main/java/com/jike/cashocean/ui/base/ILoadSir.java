package com.jike.cashocean.ui.base;

import android.view.View;

public interface ILoadSir {
    //点击视图重新加载方法
    void onReloadClick(View v);

//    //返回LoadSir 的Target对象
//    Object getLoadSirTarget();

    //是否需要初始化LoadSir
    boolean needUserLoadSir();
}
