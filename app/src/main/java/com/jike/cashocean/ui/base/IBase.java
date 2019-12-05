package com.jike.cashocean.ui.base;

import android.os.Bundle;
import android.view.View;


/**
 * Created by Yey on 2018/4/23.
 */

public interface IBase {

//    View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    int getContentLayout();

    void initInjector();

    void bindView(View view, Bundle sacedInstanceState);

    void initData();

}
