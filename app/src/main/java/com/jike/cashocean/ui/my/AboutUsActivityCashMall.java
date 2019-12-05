package com.jike.cashocean.ui.my;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.jike.cashocean.R;
import com.jike.cashocean.ui.base.BaseActivity;

import butterknife.BindView;

public class AboutUsActivityCashMall extends BaseActivity {


    @BindView(R.id.ibn_back)
    ImageButton ibnBack;

    @Override
    public int getContentLayout() {
        return R.layout.activity_about_us_cashmall;
    }


    @Override
    public void initInjector() {

    }

    @Override
    public void bindView(View view, Bundle sacedInstanceState) {
        ibnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
