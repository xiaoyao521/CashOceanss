package com.jike.cashocean.ui.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.jike.cashocean.R;
import com.jike.cashocean.adapter.MyAppListAdapter;
import com.jike.cashocean.adapter.MyBillAdapter;
import com.jike.cashocean.model.BillBean;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.ui.base.BaseFragment;
import com.jike.cashocean.ui.bill.AddBillActivity;
import com.jike.cashocean.ui.home.presenter.BookkeepPresenter;
import com.jike.cashocean.ui.login.LoginActivity;
import com.jike.cashocean.view.SmoothListView.SmoothListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BookkeeperFragment extends BaseFragment<BookkeepPresenter> {

    @BindView(R.id.ibn_back)
    ImageButton ibnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.listView)
    SmoothListView listView;

    public static BookkeeperFragment newInstance() {
        Bundle args = new Bundle();
        BookkeeperFragment fragment = new BookkeeperFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_bookkeep_new;
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void bindView(View view, Bundle sacedInstanceState) {
        ibnBack.setVisibility(View.GONE);
        tvTitleRight.setOnClickListener(view1 -> {
            if (!TextUtils.isEmpty(SPUtils.getInstance().getString(Key.TOKEN))) {
                ActivityUtils.startActivity(AddBillActivity.class);
            } else {
                ActivityUtils.startActivity(LoginActivity.class);
            }
        });
        tvTitle.setText(getString(R.string.title_bookkeep));
        tvTitleRight.setText(getString(R.string.bookkeep_add_bill));
        LinearLayout inflate =
                (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.bookkeep_item,
                        listView, false);
        listView.addHeaderView(inflate);
        List<BillBean> fd = new ArrayList<>();
        fd.add(new BillBean());
        fd.add(new BillBean());
        fd.add(new BillBean());
        fd.add(new BillBean());
        fd.add(new BillBean());
        fd.add(new BillBean());
        fd.add(new BillBean());
        listView.setAdapter(new MyBillAdapter(getActivity(), fd));
    }

    @Override
    public void initData() {

    }

    @Override
    public void onReloadClickCashMall(View v) {

    }

    @Override
    public boolean needUserLoadSir() {
        return false;
    }
}
