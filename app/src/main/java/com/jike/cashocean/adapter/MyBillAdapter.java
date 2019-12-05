package com.jike.cashocean.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.jike.cashocean.R;
import com.jike.cashocean.model.BillBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyBillAdapter extends BaseListAdapter<BillBean> {

    Context context;


    public MyBillAdapter(Context context, List<BillBean> list) {
        super(context, list);
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final BillHolder holder;
        if (view != null) {
            holder = (BillHolder) view.getTag();
        } else {
            view = mInflater.inflate(R.layout.item_bookkeeper_list, viewGroup, false);
            holder = new BillHolder(view);
            view.setTag(holder);
        }
        BillBean billBean = getItem(i);
        holder.tvMoney.setText("₱" + 123456);
        holder.tvDayStatus.setText("逾期12天");
        holder.tvAppName.setText("Ocean");
        return view;
    }

    class BillHolder {
        @BindView(R.id.app_logo)
        ImageView appLogo;
        @BindView(R.id.card_view)
        CardView cardView;
        @BindView(R.id.tv_day_status)
        TextView tvDayStatus;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_app_name)
        TextView tvAppName;

        public BillHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
