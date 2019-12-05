package com.jike.cashocean.ui.home.presenter;

import android.content.Context;

import com.jike.cashocean.ui.base.BasePresenter;
import com.jike.cashocean.ui.home.BookkeeperFragment;
import com.jike.cashocean.ui.home.api.ApiHome;

import javax.inject.Inject;

/**
 * Created by Yey on 2018/5/5.
 */

public class BookkeepPresenter extends BasePresenter<BookkeeperFragment> {
    private ApiHome _apiHome;
    private Context _context;

    @Inject
    public BookkeepPresenter(ApiHome apiHome, Context context) {
        this._apiHome = apiHome;
        this._context = context;
    }

}
