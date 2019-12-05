package com.jike.cashocean.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.SPUtils;
import com.jike.cashocean.R;
import com.jike.cashocean.component.GlideApp;
import com.jike.cashocean.model.HomeListData;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class HomeAppListAdapter extends BaseListAdapter<HomeListData.DataBean.DatasBean.AppListBean> {

    /*判断app是否安装
     * https://blog.csdn.net/divaid/article/details/83054588
     * */
    private boolean isNoData;
    private int mHeight;
    private Context context;
    public static final int ONE_SCREEN_COUNT = HomeFragment.fullItemCount; //
    // 一屏能显示的个数，这个根据屏幕高度和各自的需求定
    public static final int ONE_REQUEST_COUNT = 10; // 一次请求的个数

    public HomeAppListAdapter(Context context) {
        super(context);
        this.context = context;
    }

    public HomeAppListAdapter(Context context,
                              List<HomeListData.DataBean.DatasBean.AppListBean> list) {
        super(context, list);
        this.context = context;
    }

    // 设置数据
    public void setData(List<HomeListData.DataBean.DatasBean.AppListBean> list) {
        clearAll();
        addALL(list);
        if (list.size() < ONE_SCREEN_COUNT) {
            addALL(createEmptyList(ONE_SCREEN_COUNT - list.size()));
        }
        this.isNoData = false;
        notifyDataSetChanged();
    }

    public void setLoadMore(List<HomeListData.DataBean.DatasBean.AppListBean> list) {//设置
        addALL(list);
        this.isNoData = false;
        notifyDataSetChanged();
    }

    public void setRefresh(List<HomeListData.DataBean.DatasBean.AppListBean> list) {
        clearAll();
        addALL(list);
        if (list.size() < ONE_SCREEN_COUNT) {
            addALL(createEmptyList(ONE_SCREEN_COUNT - list.size()));
        }
        this.isNoData = false;
        notifyDataSetChanged();
    }

    public void setNoData(int mHeight, boolean isNoData) {
        clearAll();
        add(new HomeListData.DataBean.DatasBean.AppListBean(true));//不加上一个元素, notifydatasetchage
        // 是更新不了的
        this.mHeight = mHeight;
        this.isNoData = isNoData;
        notifyDataSetChanged();
    }


    // 创建不满一屏的空数据
    public List<HomeListData.DataBean.DatasBean.AppListBean> createEmptyList(int size) {
        List<HomeListData.DataBean.DatasBean.AppListBean> emptyList = new ArrayList<>();
        if (size <= 0) return emptyList;
        for (int i = 0; i < size; i++) {
            emptyList.add(new HomeListData.DataBean.DatasBean.AppListBean(true));
        }
        return emptyList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 暂无数据
        if (isNoData) {
            convertView = mInflater.inflate(R.layout.item_no_data_layout, null);
            AbsListView.LayoutParams params =
                    new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight);
            RelativeLayout rootView = convertView.findViewById(R.id.rl_root_view);
            rootView.setLayoutParams(params);
            return convertView;
        }

        // 正常数据
        final ViewHolder holder;
        if (convertView != null && convertView instanceof LinearLayout) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.item_loans_app_new, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        HomeListData.DataBean.DatasBean.AppListBean listBean = getItem(position);
        if (listBean.isShow()) {
            convertView.setVisibility(View.INVISIBLE);
        } else {
            convertView.setVisibility(View.VISIBLE);
            GlideApp.with(mContext)
                    .load(listBean.getLogo_img())
                    .placeholder(R.mipmap.placeholed)
                    .fitCenter()
                    .into(holder.appLogo);
            holder.tvAppName.setText(listBean.getApp_name());
            if (listBean.getStars() == 0) {
                holder.ratingbar.setVisibility(View.GONE);
                holder.tvScore.setText("");
            } else {
                holder.tvScore.setText(String.valueOf(listBean.getStars()));
                holder.ratingbar.setVisibility(View.VISIBLE);
            }
            holder.ratingbar.setRating(listBean.getStars());
            holder.llroot_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String isAuth = SPUtils.getInstance().getString(Key.IS_AUTHENTICAITON, "0");
                    SPUtils.getInstance().put(listBean.getApp_name(), true);
                    _onClickListener.clickItme(listBean.getId());
                }
            });
            holder.tvLimetRangeCount.setText(listBean.getLoan_min_money() + "-" + listBean.getLoan_max_money());
            holder.tvFreeRateCount.setText("≥" + listBean.getLoan_min_rate());
            holder.tvDescBottom.setText(listBean.getDesc_content());

            if (listBean.getIs_hot() == 1) {
                holder.ivHot.setVisibility(View.VISIBLE);
            } else {
                holder.ivHot.setVisibility(View.GONE);
            }

            if (SPUtils.getInstance().getBoolean(listBean.getApp_name(), false)) {
                holder.iv.setVisibility(View.VISIBLE);
                holder.btn.setBackgroundResource(R.drawable.shape_white_btn_bg);
                holder.btn.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            } else {
                holder.iv.setVisibility(View.GONE);
                holder.btn.setBackgroundResource(R.drawable.shape_green_btn_bg);
                holder.btn.setTextColor(ContextCompat.getColor(context, R.color.white));
            }
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.ll_root_view)
        RelativeLayout llroot_view;
        @BindView(R.id.app_logo)
        ImageView appLogo;
        @BindView(R.id.tv_app_name)
        TextView tvAppName;
        @BindView(R.id.tv_score)
        TextView tvScore;
        @BindView(R.id.ratingbar)
        MaterialRatingBar ratingbar;
        @BindView(R.id.btn)
        Button btn;
        @BindView(R.id.tv_limet_range)
        TextView tvLimetRange;
        @BindView(R.id.tv_limet_range_count)
        TextView tvLimetRangeCount;
        @BindView(R.id.tv_free_rate)
        TextView tvFreeRate;
        @BindView(R.id.tv_free_rate_count)
        TextView tvFreeRateCount;
        @BindView(R.id.tv_desc_bottom)
        TextView tvDescBottom;
        @BindView(R.id.iv_hot)
        ImageView ivHot;
        @BindView(R.id.iv)
        ImageView iv;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    MyAppListAdapter.OnItemClickListener _onClickListener;

    public void setOnClickListener(MyAppListAdapter.OnItemClickListener onClickListener) {
        _onClickListener = onClickListener;
    }


    public interface OnItemClickListener {
        void clickItme(String id);
    }

}
