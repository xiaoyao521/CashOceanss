package com.jike.cashocean.util.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jike.cashocean.R;

import java.util.List;


public class SearchResultRvAdapter extends RecyclerView.Adapter<SearchResultRvAdapter.ViewHolder> {

    Context mContext;
    List<String> list;
    PopupRvClickListener popupRvClickListener;

    public SearchResultRvAdapter(Context context, List<String> data) {
        mContext = context;
        list = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_search_result_layout,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.content.setText(list.get(position));
        holder.parentLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupRvClickListener.onItemChildClick(holder.parentLl.getId(), position,
                        list.get(position));
            }
        });
        holder.iconLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupRvClickListener.onItemChildClick(holder.iconLeft.getId(), position,
                        list.get(position));
            }
        });
        holder.iconRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupRvClickListener.onItemChildClick(holder.iconRight.getId(), position,
                        list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView content;
        private ImageView iconLeft;
        private ImageView iconRight;
        private LinearLayout parentLl;

        public ViewHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.item_search_result_content);
            iconLeft = itemView.findViewById(R.id.item_search_result_icon_left);
            iconRight = itemView.findViewById(R.id.item_search_result_icon_right);
            parentLl = itemView.findViewById(R.id.item_search_result_parent_ll);
        }
    }

    public PopupRvClickListener getPopupRvClickListener() {
        return popupRvClickListener;
    }

    public void setPopupRvClickListener(PopupRvClickListener popupRvClickListener) {
        this.popupRvClickListener = popupRvClickListener;
    }
}
