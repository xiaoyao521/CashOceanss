package com.jike.cashocean.ui.web;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jike.cashocean.R;
import com.jike.cashocean.model.ServerDataCode;
import com.jike.cashocean.net.exception.SERVER_CODE;
import com.jike.cashocean.ui.base.BaseActivity;

import butterknife.BindView;
import im.delight.android.webview.AdvancedWebView;


/**
 * Created by Yey on 2017/12/30.
 */

public class WebActivity extends BaseActivity {
    @BindView(R.id.ibn_back)
    ImageButton ibnBack;
    @BindView(R.id.container_loadsir)
    AdvancedWebView awvAboutUs;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private String url;


    @Override
    public int getContentLayout() {
        return R.layout.activity_about_us;
    }


    @Override
    public void initInjector() {

    }


    @Override
    public void bindView(View view, Bundle sacedInstanceState) {
        awvAboutUs.setListener(this, new AdvancedWebView.Listener() {
            @Override
            public void onPageStarted(String url, Bitmap favicon) {
                loadService.showWithConvertor(new ServerDataCode() {
                    @Override
                    public int getServerDataCode() {
                        return SERVER_CODE.DATA_LOADING;
                    }
                });
            }

            @Override
            public void onPageFinished(String url) {
                loadService.showWithConvertor(new ServerDataCode() {
                    @Override
                    public int getServerDataCode() {
                        return SERVER_CODE.SUCCESS;
                    }
                });
            }

            @Override
            public void onPageError(int errorCode, String description, String failingUrl) {
                loadService.showWithConvertor(new ServerDataCode() {
                    @Override
                    public int getServerDataCode() {
                        return SERVER_CODE.DATA_AGAIN_LOADING;
                    }
                });
            }


            @Override
            public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

            }

            @Override
            public void onExternalPageRequest(String url) {

            }
        });

        ibnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            url = getIntent().getStringExtra(Intent.EXTRA_ORIGINATING_URI);
            String title = getIntent().getStringExtra(Intent.EXTRA_TITLE);
            tvTitle.setText(title);
            awvAboutUs.loadUrl(url, true);
        }
    }

//    @Override
//    public void onRetry() {
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        awvAboutUs.onActivityResult(requestCode, resultCode, intent);
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        awvAboutUs.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        awvAboutUs.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        awvAboutUs.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    public void onBackPressedSupport() {
        if (!awvAboutUs.onBackPressed()) {
            return;
        }
        super.onBackPressedSupport();
    }

    @Override
    public void onReloadClick(View v) {
        awvAboutUs.reload();
    }


    @Override
    public boolean needUserLoadSir() {
        return true;
    }
}
