package com.jike.cashocean.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jike.cashocean.R;
import com.jike.cashocean.model.MyAppListEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunfusheng on 16/4/20.
 */
public class MyAppListAdapter extends BaseListAdapter<MyAppListEntity.DataBean.DatasBean.ListBean> {

    private boolean isNoData;
    private int mHeight;
    public static final int ONE_SCREEN_COUNT = 8; // 一屏能显示的个数，这个根据屏幕高度和各自的需求定
    public static final int ONE_REQUEST_COUNT = 10; // 一次请求的个数

    public MyAppListAdapter(Context context) {
        super(context);
    }

    public MyAppListAdapter(Context context, List<MyAppListEntity.DataBean.DatasBean.ListBean> list) {
        super(context, list);
    }

    // 设置数据
    public void setData(List<MyAppListEntity.DataBean.DatasBean.ListBean> list) {
        clearAll();
        addALL(list);

        notifyDataSetChanged();
    }

    public void setLoadMore(List<MyAppListEntity.DataBean.DatasBean.ListBean> list) {//设置
        addALL(list);
        notifyDataSetChanged();
    }

    public void setRefresh(List<MyAppListEntity.DataBean.DatasBean.ListBean> list) {
        clearAll();
        addALL(list);
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // 正常数据
        final ViewHolder holder;
        if (convertView != null && convertView instanceof LinearLayout) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.item_my_app_list, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        MyAppListEntity.DataBean.DatasBean.ListBean listBean = getItem(position);
        holder.tvAppName.setText(listBean.getApp_name());
        holder.tvTime.setText(listBean.getCreated_at());
        holder.tvDescCount.setText(listBean.getLoan_max_money());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_onClickListener != null) {
                    _onClickListener.clickItme(listBean.getId());
                }

            }
        });
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tv_app_name)
        TextView tvAppName;
        @BindView(R.id.btn)
        Button btn;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_desc_count)
        TextView tvDescCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    OnItemClickListener _onClickListener;

    public void setOnClickListener(OnItemClickListener onClickListener) {
        _onClickListener = onClickListener;
    }


    public interface OnItemClickListener {
        void clickItme(String id);
    }

}
