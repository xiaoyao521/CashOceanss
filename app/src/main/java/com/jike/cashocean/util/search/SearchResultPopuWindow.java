package com.jike.cashocean.util.search;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jike.cashocean.R;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPopuWindow {
    //    private final Dialog mDialog;
    private final View contentView;
    private Context mContext;
    private Builder mBuilder;

    private final PopupWindow window;
    private TextView parentView;
    private List<TextView> viewList;
    private final RecyclerView recyclerView;

    private SearchResultPopuWindow(final Builder builder) {
        this.mBuilder = builder;
        mContext = builder.mContext;
        contentView = LayoutInflater.from(mContext).inflate(R.layout.search_result_popup_layout, null);

        recyclerView = contentView.findViewById(R.id.search_result_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        recyclerView.setAdapter(builder.getSearchResultRvAdapter());
        //分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));

        window = new PopupWindow(contentView, builder.width, builder.height, false);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
    }

    public boolean isShowing() {
        return window.isShowing();
    }

    public void show(final View parent) {
        int[] location = new int[2];
        parent.getLocationOnScreen(location);
        parentView = (TextView) parent;
        //根据parentView的宽重新赋值window的宽，同时确定window的位置
        window.setWidth(parent.getWidth());
        window.showAtLocation(parent, Gravity.NO_GRAVITY, location[0], location[1] + parent.getHeight());
    }

    public void dismiss() {
        window.dismiss();
    }

    public static class Builder {
        private Context mContext;
        private SearchResultRvAdapter searchResultRvAdapter;
        private int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        private int width = ViewGroup.LayoutParams.WRAP_CONTENT;

        public int getHeight() {
            return height;
        }

        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        public int getWidth() {
            return width;
        }

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        public SearchResultRvAdapter getSearchResultRvAdapter() {
            return searchResultRvAdapter;
        }

        public Builder setSearchResultRvAdapter(SearchResultRvAdapter searchResultRvAdapter) {
            this.searchResultRvAdapter = searchResultRvAdapter;
            return this;
        }

        public Builder(Context context) {
            mContext = context;
        }

        public SearchResultPopuWindow build() {

            return new SearchResultPopuWindow(this);
        }
    }

    public PopupWindow getWindow() {
        return window;
    }

}
