package com.jike.cashocean.ui.home.presenter;

import android.content.Context;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.jike.cashocean.model.ClickAppListEntity;
import com.jike.cashocean.model.HomeListData;
import com.jike.cashocean.model.MessageBean;
import com.jike.cashocean.net.BaseObserver;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.net.RxSchedulers;
import com.jike.cashocean.ui.MainActivity;
import com.jike.cashocean.ui.base.BasePresenter;
import com.jike.cashocean.ui.home.FindFragment;
import com.jike.cashocean.ui.home.HomeFragment;
import com.jike.cashocean.ui.home.api.ApiHome;
import com.jike.cashocean.ui.home.contract.HomeContract;
import com.jike.cashocean.util.MapUrlTools;

import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;

/**
 * Created by Yey on 2018/5/5.
 */

public class FindPresenter extends BasePresenter<FindFragment> {
    private ApiHome _apiHome;
    private Context _context;

    @Inject
    public FindPresenter(ApiHome apiHome, Context context) {
        this._apiHome = apiHome;
        this._context = context;
    }
}

