package com.jike.cashocean.ui.my;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jike.cashocean.R;
import com.jike.cashocean.adapter.MyAppListAdapter;
import com.jike.cashocean.model.ClickAppListEntity;
import com.jike.cashocean.model.MyAppListEntity;
import com.jike.cashocean.model.ServerDataCode;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.net.exception.SERVER_CODE;
import com.jike.cashocean.ui.authentication.AuthenticationActivity;
import com.jike.cashocean.ui.base.BaseActivity;
import com.jike.cashocean.ui.base.IStateView;
import com.jike.cashocean.ui.my.compoment.DaggerMyComponent;
import com.jike.cashocean.ui.my.contract.MyAppListContract;
import com.jike.cashocean.ui.my.module.MyModule;
import com.jike.cashocean.ui.my.presenter.MyAppListPresenter;
import com.jike.cashocean.view.SmoothListView.SmoothListView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Yey on 2017/12/30.
 */

public class MyAppListActivity extends BaseActivity<MyAppListPresenter> implements MyAppListContract.View, IStateView, SmoothListView.ISmoothListViewListener {
    @BindView(R.id.ibn_back)
    ImageButton ibnBack;
    @BindView(R.id.rv_app_list)
    SmoothListView rvApplist;
    @BindView(R.id.container_empty)
    LinearLayout containerEmpty;
    @BindView(R.id.btn_apply)
    Button btnApply;
    String pageLenght = "10";
    int page = 1;
    private MyAppListAdapter myAppListAdapter;

    @Override
    public int getContentLayout() {
        return R.layout.activity_my_app_list;
    }


    @Override
    public void initInjector() {
        DaggerMyComponent.builder().myModule(new MyModule("我的APP列表", this)).build().inject(this);
    }


    @Override
    public void bindView(View view, Bundle sacedInstanceState) {
        rvApplist.setRefreshEnable(true);
        rvApplist.setLoadMoreEnable(true);
        rvApplist.setSmoothListViewListener(this);
        ibnBack.setOnClickListener(v -> finish());
        btnApply.setOnClickListener(v -> finish());
    }

    @Override
    public void initData() {
        showLoading();
        mPresenter.getMyAppList(String.valueOf(1), pageLenght);
        myAppListAdapter = new MyAppListAdapter(this);
        myAppListAdapter.setOnClickListener(new MyAppListAdapter.OnItemClickListener() {
            @Override
            public void clickItme(String id) {
                showLoadingDialog();
                mPresenter.clickItem(id);
            }
        });
        rvApplist.setAdapter(myAppListAdapter);
    }


    @Override
    public void onReloadClick(View v) {
        showLoading();
        mPresenter.getMyAppList(String.valueOf(1), pageLenght);
    }

    @Override
    public boolean needUserLoadSir() {
        return true;
    }

    @Override
    public void loadAppListData(MyAppListEntity myAppListEntity) {
        if (myAppListEntity != null) {
            if (myAppListEntity.getRet() == 200) {
                if (myAppListEntity.getData().getCode() == 100) {
                    showSuccess();
                    List<MyAppListEntity.DataBean.DatasBean.ListBean> list =
                            myAppListEntity.getData().getDatas().getList();
                    if (list.size() == 0) {
                        containerEmpty.setVisibility(View.VISIBLE);
                        rvApplist.setVisibility(View.INVISIBLE);
                    } else {
                        containerEmpty.setVisibility(View.INVISIBLE);
                        rvApplist.setVisibility(View.VISIBLE);
                        myAppListAdapter.setData(list);
                    }
                } else {
                    ToastUtils.showLong(myAppListEntity.getData().getMsg());
                    showFaild();
                }
            } else {
                ToastUtils.showLong(myAppListEntity.getMsg());
                showFaild();
            }
        } else {
            showFaild();
        }
    }

    @Override
    public void loadMoreSuccess(MyAppListEntity myAppListEntity) {
        rvApplist.stopLoadMore();
        if (myAppListEntity != null) {
            if (myAppListEntity.getRet() == 200) {
                if (myAppListEntity.getData().getCode() == 100) {
                    List<MyAppListEntity.DataBean.DatasBean.ListBean> list =
                            myAppListEntity.getData().getDatas().getList();
                    myAppListAdapter.setLoadMore(list);
                    if (list.size() == 0) {
                        rvApplist.setLoadMoreEnable(false);
                    }
                } else {
                    ToastUtils.showLong(myAppListEntity.getData().getMsg());
                    showFaild();
                }
            } else {
                ToastUtils.showLong(myAppListEntity.getMsg());
                showFaild();
            }
        }
    }

    @Override
    public void refreshSuccess(MyAppListEntity myAppListEntity) {
        rvApplist.stopRefresh();
        if (myAppListEntity != null) {
            if (myAppListEntity.getRet() == 200) {
                if (myAppListEntity.getData().getCode() == 100) {
                    List<MyAppListEntity.DataBean.DatasBean.ListBean> list =
                            myAppListEntity.getData().getDatas().getList();
                    myAppListAdapter.setRefresh(list);
                    rvApplist.setLoadMoreEnable(true);
                } else {
                    ToastUtils.showLong(myAppListEntity.getData().getMsg());
                    showFaild();
                }
            } else {
                ToastUtils.showLong(myAppListEntity.getMsg());
                showFaild();
            }
        }
    }

    @Override
    public void loadClick(ClickAppListEntity clickAppListEntity) {
        hideLoadingDialog();
        if (clickAppListEntity != null) {
            if (clickAppListEntity.getRet() == 200) {
                if (clickAppListEntity.getData().getCode() == 100) {
                    String isAuth = SPUtils.getInstance().getString(Key.IS_AUTHENTICAITON, "0");
                    if ("0".equals(isAuth)) {
                        ActivityUtils.startActivity(AuthenticationActivity.class);
                    }
                    if ("1".equals(isAuth)) {
                        String click_url = clickAppListEntity.getData().getDatas().getClick_url();
                        Uri uri = Uri.parse(click_url);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        this.startActivity(intent);
                    }
                } else {
                    ToastUtils.showLong(clickAppListEntity.getData().getMsg());
                    showFaild();
                }
            } else {
                ToastUtils.showLong(clickAppListEntity.getMsg());
            }
        }
    }

    @Override
    public void showLoading() {
        loadService.showWithConvertor(new ServerDataCode() {
            @Override
            public int getServerDataCode() {
                return SERVER_CODE.DATA_LOADING;
            }
        });
    }

    @Override
    public void showSuccess() {
        loadService.showWithConvertor(new ServerDataCode() {
            @Override
            public int getServerDataCode() {
                return SERVER_CODE.SUCCESS;
            }
        });
    }

    @Override
    public void showFaild() {
        loadService.showWithConvertor(new ServerDataCode() {
            @Override
            public int getServerDataCode() {
                return SERVER_CODE.DATA_ERROR;
            }
        });
    }

    @Override
    public void showAgain() {
        loadService.showWithConvertor(new ServerDataCode() {
            @Override
            public int getServerDataCode() {
                return SERVER_CODE.DATA_AGAIN_LOADING;
            }
        });
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh(String.valueOf(1), pageLenght);
    }

    @Override
    public void onLoadMore() {
        page++;
        mPresenter.loadMore(String.valueOf(page), pageLenght);
    }

}
