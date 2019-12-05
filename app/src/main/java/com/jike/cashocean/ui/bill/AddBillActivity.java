package com.jike.cashocean.ui.bill;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jike.cashocean.R;
import com.jike.cashocean.ui.base.BaseActivity;
import com.jike.cashocean.ui.bill.preseneter.AddBillPresenter;
import com.jike.cashocean.util.search.PopupRvClickListener;
import com.jike.cashocean.util.search.SearchResultPopuWindow;
import com.jike.cashocean.util.search.SearchResultRvAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;

public class AddBillActivity extends BaseActivity<AddBillPresenter> {


    @BindView(R.id.ibn_back)
    ImageButton ibnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_app_name)
    TextView tvAppName;
    @BindView(R.id.tv_fill_money)
    TextView tvFillMoney;
    @BindView(R.id.tv_select_time)
    TextView tvSelectTime;
    @BindView(R.id.iv_query)
    ImageView ivQuery;
    @BindView(R.id.tv_query)
    TextView tvQuery;

    @Override
    public int getContentLayout() {
        return R.layout.activity_add_bill;
    }

    @Override
    public void initInjector() {

    }

    private CharSequence cc;

    private void showChooseData() {
        KeyboardUtils.hideSoftInput(this);//隐藏软键盘
        Calendar selecetDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.set(1965, 0, 1);
        endDate.set(2003, 11, 31);
        TimePickerBuilder timePickerBuilder = new TimePickerBuilder(this,
                new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
                        String format = simpleDateFormat.format(date);
                        tvSelectTime.setText(format);
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setCancelText(getString(R.string.cancel))
                .setSubmitText(getString(R.string.pickerview_submit))
                .setOutSideCancelable(false)
                .setRangDate(startDate, endDate);
        timePickerBuilder.build().show();
    }

    @Override
    public void bindView(View view, Bundle sacedInstanceState) {
        ibnBack.setOnClickListener(view1 -> finish());
        tvTitle.setText(getString(R.string.add_bill));
        tvTitleRight.setVisibility(View.GONE);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                cc = charSequence;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ToastUtils.showShort(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (cc.length() > 0) {
                    ivQuery.setVisibility(View.GONE);
                    tvQuery.setVisibility(View.GONE);
                    searchStart(cc.toString());
                } else {
                    ivQuery.setVisibility(View.VISIBLE);
                    tvQuery.setVisibility(View.VISIBLE);
                }


            }
        });
        tvSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChooseData();
            }
        });
    }

    @Override
    public void initData() {
        searchResultList = new ArrayList<>();
        allList = new ArrayList();
        allList.add("Pesopop");
        allList.add("Cash2U");
        allList.add("Pautang Online");
        allList.add("Juanhand");
        allList.add("Jetpeso");
        allList.add("BeeLoan");
        allList.add("Pesoloan");
        allList.add("Pesogogo");
    }

    @Override
    public void onReloadClick(View v) {

    }

    @Override
    public boolean needUserLoadSir() {
        return false;
    }

    /**
     * 搜索runnable,500ms不输入新字符，触发搜索，可自己修改
     */
    class SearchRunnable implements Runnable {
        String keyWord;
        Handler handler = new Handler();

        public void pushKeyWord(String keyWord) {
            this.keyWord = keyWord;
            handler.removeCallbacks(this);
            handler.postDelayed(this, 500);
        }

        @Override
        public void run() {
            //此处发起Http请求
            getCompileList(searchKeyWord, allList);
            wordSearching = searchKeyWord;
        }

    }

    private List<String> searchResultList;
    private List<String> allList;

    SearchRunnable searchRunnable = new SearchRunnable();
    String searchKeyWord;//用于在500ms内缓存用户输入的关键字
    String wordSearching;//正在搜索的关键字
    private SearchResultPopuWindow popuWindow;

    /**
     * 开始执行搜索，如果多次调用接口操作，需要手动结束上次请求，可在此方法内执行
     *
     * @param keyword
     */
    public void searchStart(final String keyword) {
        Log.i("MartinFilter", "getAllSearchTextFilter keyword=>" + keyword);
        searchKeyWord = keyword;
        searchRunnable.pushKeyWord(keyword);
    }

    /**
     * 模拟模糊查询
     * <p>
     * 高级的：
     * https://www.jianshu.com/p/f4bc6655ec18
     */
    private void getCompileList(String keyWord, List<String> list) {
        searchResultList.clear();
        Pattern pattern = Pattern.compile(keyWord);
        for (int i = 0; i < list.size(); i++) {
            Matcher matcher = pattern.matcher(list.get(i));
            if (matcher.find()) {
                searchResultList.add(list.get(i));
            }
        }
        //adapter在最外层new出来，由builder传入window，方便点击事件的监听
        SearchResultRvAdapter adapter = new SearchResultRvAdapter(this, searchResultList);
        //new window
        popuWindow = new SearchResultPopuWindow.Builder(this)
                .setSearchResultRvAdapter(adapter)
                .build();
        //show方法传入editText，用来完成展示位置，和宽度计算
        popuWindow.show(etSearch);
        //recycleView各个部分的点击监听
        adapter.setPopupRvClickListener(new PopupRvClickListener() {
            @Override
            public void onItemChildClick(int id, int position, String content) {
                if (id == R.id.item_search_result_icon_left) {
                    Toast.makeText(AddBillActivity.this, "点击左侧icon" + position + content,
                            Toast.LENGTH_SHORT).show();
                } else if (id == R.id.item_search_result_icon_right) {
                    Toast.makeText(AddBillActivity.this, "点击右侧icon" + position + content,
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddBillActivity.this, "点击item" + position + content,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
