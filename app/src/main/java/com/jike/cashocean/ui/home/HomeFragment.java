package com.jike.cashocean.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.jike.cashocean.Content.Code;
import com.jike.cashocean.R;
import com.jike.cashocean.adapter.HomeAppListAdapter;
import com.jike.cashocean.adapter.MyAppListAdapter;
import com.jike.cashocean.model.ClickAppListEntity;
import com.jike.cashocean.model.FilterData;
import com.jike.cashocean.model.FilterEntity;
import com.jike.cashocean.model.HomeBannerEntity;
import com.jike.cashocean.model.HomeListData;
import com.jike.cashocean.model.MessageBean;
import com.jike.cashocean.model.ServerDataCode;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.net.exception.SERVER_CODE;
import com.jike.cashocean.ui.MainActivity;
import com.jike.cashocean.ui.authentication.AuthenticationActivity;
import com.jike.cashocean.ui.base.BaseFragment;
import com.jike.cashocean.ui.base.IStateView;
import com.jike.cashocean.ui.home.compoment.DaggerHomeComponent;
import com.jike.cashocean.ui.home.contract.HomeContract;
import com.jike.cashocean.ui.home.module.HomeModule;
import com.jike.cashocean.ui.home.presenter.HomePresenter;
import com.jike.cashocean.ui.login.LoginActivity;
import com.jike.cashocean.util.DensityUtil;
import com.jike.cashocean.util.LanguageSelectDialog;
import com.jike.cashocean.util.languageutil.LanguageConstants;
import com.jike.cashocean.view.FilterView;
import com.jike.cashocean.view.SmoothListView.SmoothListView;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

import static com.jike.cashocean.Content.KeyValue.HOME_CLICK_URL;
import static com.jike.cashocean.Content.KeyValue.HOME_PACKAGE_NAME;

//extends BaseFragment<LoansRepaymentPresenter> implements LoansRepaymentContract.View, IStateView
public class HomeFragment extends BaseFragment<HomePresenter> implements SmoothListView.ISmoothListViewListener, HomeContract.View, IStateView {
    @BindView(R.id.listView)
    SmoothListView smoothListView;
    @BindView(R.id.real_filterView)
    FilterView realFilterView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_language)
    TextView tv_language;
    @BindView(R.id.fragment_home)
    RelativeLayout fragmentHome;

    private Context mContext;
    private Activity mActivity;
    private int mScreenHeight; // 屏幕高度

    private List<HomeListData.DataBean.DatasBean.BannerListBean> bannerList = new ArrayList<>();
    // 广告数据

    //    private HeaderBannerView headerBannerView; // 广告视图
//    private HeaderDividerView headerDividerView; // 分割线占位图
//    private HeaderFilterView headerFilterView; // 分类筛选视图

    private FilterData filterData; // 筛选数据
    private HomeAppListAdapter mHomeListAdapter;
    private int titleViewHeight = 0; // 标题栏的高度

    private View itemHeaderBannerView; // 从ListView获取的广告子View
    private int bannerViewHeight; // 广告视图的高度

    private int bannerViewTopMargin; // 广告视图距离顶部的距离

    private View itemHeaderFilterView; // 从ListView获取的筛选子View
    private int filterViewPosition; // 筛选视图的位置
    private int filterViewTopMargin; // 筛选视图距离顶部的距离
    private boolean isScrollIdle = true; // ListView是否在滑动
    private boolean isStickyTop = false; // 是否吸附在顶部
    private boolean isSmooth = false; // 没有吸附的前提下，是否在滑动
    private int filterPosition = -1; // 点击FilterView的位置：分类(0)、排序(1)、筛选(2)
    private boolean canScroll = true;
    private BGABanner bgaBanner;
    private FilterView fileterView;
    private int page = 1;//分页数listView
    private int pageLength = 10;//分页数量
    String token = "";//token值
    String sort = "";//排序方式
    private List<HomeListData.DataBean.DatasBean.BannerListBean> banner_list; //banner 图
    public static int fullItemCount;
    TextView tvsort;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initInjector() {
        DaggerHomeComponent.builder().homeModule(new HomeModule("首页", getActivity())).build().inject(this);
    }

    @Override
    public void bindView(View view, Bundle sacedInstanceState) {

    }

    @Override
    public void initData() {
        mContext = getContext();
        mActivity = getActivity();
        mScreenHeight = DensityUtil.getWindowHeight(mActivity);
        fullItemCount =
                (mScreenHeight - DensityUtil.dip2px(mContext, 128)) / DensityUtil.dip2px(mContext
                        , 197) + 1;
        // 筛选数据
        filterData = new FilterData();
        List<FilterEntity> list = new ArrayList<>();
        list.add(new FilterEntity(getResources().getString(R.string.comprehensive_sort), "1"));
        list.add(new FilterEntity(getResources().getString(R.string.sort_high_to_low), "2"));
        list.add(new FilterEntity(getResources().getString(R.string.sort_low_to_high), "3"));
        filterData.setSorts(list);
        token = SPUtils.getInstance().getString(Key.TOKEN);
        initView(); // 顺序不能写反
        initListener();
        mPresenter.getHomeListData(sort, String.valueOf(page), String.valueOf(pageLength));
        showLoading();
    }

    private LinearLayout linearLayout;
    MarqueeView marqueeTextView;

    private void initView() {
        linearLayout =
                (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.item_banner,
                        smoothListView, false);
        bgaBanner = (BGABanner) linearLayout.findViewById(R.id.banner_content);
        marqueeTextView = linearLayout.findViewById(R.id.tv_sign);
        bgaBanner.setAutoPlayAble(false);
        smoothListView.addHeaderView(linearLayout);
        //假filter
        fileterView =
                (FilterView) LayoutInflater.from(getActivity()).inflate(R.layout.header_filter_layout, smoothListView, false);
        tvsort = fileterView.findViewById(R.id.tv_sort_title);
        fileterView.setFilterData(mActivity, filterData);
        smoothListView.addHeaderView(fileterView);
        // 设置真FilterView数据
        realFilterView.setFilterData(mActivity, filterData);
        realFilterView.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mHomeListAdapter != null) {
            mHomeListAdapter.notifyDataSetChanged();
        }
    }

    private void initListener() {
        // 设置ListView数据
        mHomeListAdapter = new HomeAppListAdapter(mActivity);
        mHomeListAdapter.setOnClickListener(new MyAppListAdapter.OnItemClickListener() {
            @Override
            public void clickItme(String id) {
                if (!TextUtils.isEmpty(SPUtils.getInstance().getString(Key.TOKEN))) {
                    showLoadingDialog();
                    mPresenter.clickItem(id);
                } else {
                    ActivityUtils.startActivity(LoginActivity.class);
                }
            }
        });
        tv_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LanguageSelectDialog languageSelectDialog = new LanguageSelectDialog();
                languageSelectDialog.show(getFragmentManager(), "language_select_dialog");
                languageSelectDialog.setOnClickLanguage(new LanguageSelectDialog.OnClickLanguage() {
                    @Override
                    public void onEglish() {
                        SPUtils.getInstance().put(Key.LANGUAGE_KEY, "");
                        languageSelectDialog.dismiss();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent
                                .FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }

                    @Override
                    public void onTagalog() {
                        SPUtils.getInstance().put(Key.LANGUAGE_KEY, LanguageConstants.TAGALOG);
                        languageSelectDialog.dismiss();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent
                                .FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
//                LogUtils.e("ererere",PhoneReadUtils.getAppList());
            }
        });
        smoothListView.setAdapter(mHomeListAdapter);
        filterViewPosition = smoothListView.getHeaderViewsCount() - 1;

        // (假的ListView头部展示的)筛选视图点击, 点击这个假的视图, 会触发ListView向上滚动
        fileterView.setOnFilterClickListener(new FilterView.OnFilterClickListener() {
            @Override
            public void onFilterClick(int position) {
                filterPosition = position;
                isSmooth = true;
                smoothListView.smoothScrollToPositionFromTop(filterViewPosition, 0);
            }
        });

        // (真正的)筛选视图点击
        realFilterView.setOnFilterClickListener(new FilterView.OnFilterClickListener() {
            @Override
            public void onFilterClick(int position) {
                filterPosition = position;
                realFilterView.show(position);
                fileterView.showAnother(position);
                smoothListView.smoothScrollToPositionFromTop(filterViewPosition, 0);
            }
        });
        realFilterView.setOnSortClickListener(new FilterView.OnSortClickListener() {
            @Override
            public void onItemSortClick(int options) {
                LogUtils.e("看看选项", options);
//                排序,all是默认排序,money_rises金额升序,money_down金额降序,easy易通过,low_interest低利息,
//                fast_lending快速放款
                tvsort.setText(filterData.getSorts().get(options).getKey());
                if (options == 2) {
                    showLoadingDialog();
                    sort = "money_rises";
                    mPresenter.order(sort, "1", String.valueOf(pageLength));
                }
                if (options == 1) {
                    showLoadingDialog();
                    sort = "money_down";
                    mPresenter.order(sort, "1", String.valueOf(pageLength));
                }
                if (options == 0) {
                    showLoadingDialog();
                    sort = "all";
                    mPresenter.order(sort, "1", String.valueOf(pageLength));
                }
            }
        });
        realFilterView.setOnPassClickListener(new FilterView.OnPassClickListener() {
            @Override
            public void onItemPassClick() {
//                ToastUtils.showLong("容易通过");
                showLoadingDialog();
                sort = "easy";
                mPresenter.order(sort, "1", String.valueOf(pageLength));
            }
        });
        realFilterView.setOnFreeClickListener(new FilterView.OnFreeClickListener() {
            @Override
            public void onItemFreeClick() {
//                ToastUtils.showLong("低利率");
                showLoadingDialog();
                sort = "low_interest";
                mPresenter.order(sort, "1", String.valueOf(pageLength));
            }
        });

        smoothListView.setRefreshEnable(true);
        smoothListView.setLoadMoreEnable(true);
        smoothListView.setSmoothListViewListener(this);
        smoothListView.setOnScrollListener(new SmoothListView.OnSmoothScrollListener() {
            @Override
            public void onSmoothScrolling(View view) {
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                isScrollIdle = (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                if (isScrollIdle && bannerViewTopMargin < 0) return;
                // 获取广告头部View、自身的高度、距离顶部的高度
                if (itemHeaderBannerView == null) {
                    itemHeaderBannerView = smoothListView.getChildAt(1);
                }
                if (itemHeaderBannerView != null) {
                    bannerViewTopMargin = DensityUtil.px2dip(mContext,
                            itemHeaderBannerView.getTop());
                    bannerViewHeight = DensityUtil.px2dip(mContext,
                            itemHeaderBannerView.getHeight());
                }

                // 获取筛选View、距离顶部的高度
                if (itemHeaderFilterView == null) {
                    itemHeaderFilterView =
                            smoothListView.getChildAt(filterViewPosition - firstVisibleItem);
                }
                if (itemHeaderFilterView != null) {
                    filterViewTopMargin = DensityUtil.px2dip(mContext,
                            itemHeaderFilterView.getTop());
                }

                // 处理筛选是否吸附在顶部
                if (filterViewTopMargin <= titleViewHeight || firstVisibleItem > filterViewPosition) {
                    isStickyTop = true; // 吸附在顶部
                    realFilterView.setVisibility(View.VISIBLE);
                    fileterView.setVisibility(View.GONE);
                } else {
                    isStickyTop = false; // 没有吸附在顶部
                    realFilterView.setVisibility(View.GONE);
                    fileterView.setVisibility(View.VISIBLE);
                }
                if (isSmooth && isStickyTop) {
                    isSmooth = false;
                    realFilterView.show(filterPosition);
                }

            }
        });
        //Banner 图
        bgaBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model,
                                       int position) {
                Glide.with(getActivity())
                        .load(model)
                        .placeholder(R.mipmap.placeholed)
                        .error(R.mipmap.placeholed)
                        .centerCrop()
                        .dontAnimate()
                        .into(itemView);
            }
        });
        bgaBanner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, String model,
                                          int position) {
//                Toast.makeText(banner.getContext(), "点击了" + position, Toast.LENGTH_SHORT).show();
                if (!TextUtils.isEmpty(SPUtils.getInstance().getString(Key.TOKEN))) {
                    HomeListData.DataBean.DatasBean.BannerListBean bannerListBean =
                            banner_list.get(position);
                    String banner_type = bannerListBean.getBanner_type();
                    String banner_value = bannerListBean.getBanner_value();
                    if (banner_type.equals("1")) {//APP ID
                        showLoadingDialog();
                        mPresenter.clickItem(banner_value);
                    }
                    if (banner_type.equals("2")) {
                        Uri uri = Uri.parse(banner_value);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        mActivity.startActivity(intent);
                    }
                } else {
                    ActivityUtils.startActivity(LoginActivity.class);
                }

            }
        });
    }

    @Override
    public void onRefresh() {
        page = 1;//刷新之后page置为0
        mPresenter.refresh(sort, "1", String.valueOf(pageLength));//下拉刷新 page 不传值
    }

    @Override
    public void onLoadMore() {
        page++;
        mPresenter.loadMore(sort, String.valueOf(page), String.valueOf(pageLength));//下拉刷新 page 不传值
    }


    @Override
    public void onReloadClickCashMall(View v) {
        showLoading();
        mPresenter.getHomeListData(sort, "1", String.valueOf(pageLength));//下拉刷新 page 不传值
    }

    @Override
    public boolean needUserLoadSir() {
        return true;
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
    public void loadBannerUrl(HomeBannerEntity homeBannerEntity) {

    }

    @Override
    public void loadHomeListData(HomeListData homeListEntity) {
        if (homeListEntity != null) {
            if (homeListEntity.getRet() == 200) {
                if (homeListEntity.getData().getCode() == 100) {
                    showSuccess();
                    banner_list = homeListEntity.getData().getDatas().getBanner_list();
                    ArrayList<String> imageUrls = new ArrayList<>();//这是banner 图的url数据
                    ArrayList<String> tipDescs = new ArrayList<>();
                    for (int i = 0; i < banner_list.size(); i++) {
                        imageUrls.add(banner_list.get(i).getBanner_image());
                        tipDescs.add("");
                    }
                    if (imageUrls.size() == 0) {
                        bgaBanner.setVisibility(View.GONE);
                        fileterView.setVisibility(View.GONE);
                        realFilterView.setVisibility(View.VISIBLE);
                    } else {
                        bgaBanner.setVisibility(View.VISIBLE);
                        fileterView.setVisibility(View.VISIBLE);
                    }
                    bgaBanner.setData(imageUrls, tipDescs);
                    List<HomeListData.DataBean.DatasBean.AppListBean> app_list =
                            homeListEntity.getData().getDatas().getApp_list();
                    if (app_list.size() != 0) {
                        mHomeListAdapter.setData(app_list);
                        if (app_list.size() > fullItemCount) {
                            smoothListView.setLoadMoreEnable(true);
                        }
                    } else {
                        smoothListView.setLoadMoreEnable(false);
                    }
                } else {
                    ToastUtils.showLong(homeListEntity.getData().getMsg());
                    showAgain();
                }
            } else {
                ToastUtils.showLong(homeListEntity.getMsg());
                showAgain();
            }
        } else {
            showAgain();
        }
    }

    @Override
    public void loadMoreSuccess(HomeListData homeListEntity) {
        smoothListView.stopLoadMore();
        if (homeListEntity != null) {
            if (homeListEntity.getRet() == 200) {
                if (homeListEntity.getData().getCode() == 100) {
                    banner_list = homeListEntity.getData().getDatas().getBanner_list();
                    ArrayList<String> imageUrls = new ArrayList<>();//这是banner 图的url数据
                    ArrayList<String> tipDescs = new ArrayList<>();
                    for (int i = 0; i < banner_list.size(); i++) {
                        imageUrls.add(banner_list.get(i).getBanner_image());
                        tipDescs.add("");
                    }
                    if (imageUrls.size() == 0) {
                        bgaBanner.setVisibility(View.GONE);
//                        linearLayout.setVisibility(View.GONE);
                        fileterView.setVisibility(View.GONE);
                        realFilterView.setVisibility(View.VISIBLE);
                    } else {
                        bgaBanner.setVisibility(View.VISIBLE);
//                        linearLayout.setVisibility(View.VISIBLE);
                        fileterView.setVisibility(View.VISIBLE);
                    }
                    bgaBanner.setData(imageUrls, tipDescs);
                    List<HomeListData.DataBean.DatasBean.AppListBean> app_list =
                            homeListEntity.getData().getDatas().getApp_list();
                    if (app_list.size() != 0) {
                        mHomeListAdapter.setLoadMore(app_list);
                        smoothListView.setLoadMoreEnable(true);
                    } else {
                        smoothListView.setLoadMoreEnable(false);
                    }
                } else {
                    ToastUtils.showLong(homeListEntity.getData().getMsg());
                }
            } else {
                ToastUtils.showLong(homeListEntity.getMsg());
            }
        }
    }

    @Override
    public void refreshSuccess(HomeListData homeListEntity) {
        smoothListView.stopRefresh();
        if (homeListEntity != null) {
            showSuccess();
            if (homeListEntity.getRet() == 200) {
                if (homeListEntity.getData().getCode() == 100) {
                    banner_list = homeListEntity.getData().getDatas().getBanner_list();
                    ArrayList<String> imageUrls = new ArrayList<>();//这是banner 图的url数据
                    ArrayList<String> tipDescs = new ArrayList<>();
                    for (int i = 0; i < banner_list.size(); i++) {
                        imageUrls.add(banner_list.get(i).getBanner_image());
                        tipDescs.add("");
                    }
                    if (imageUrls.size() == 0) {
                        bgaBanner.setVisibility(View.GONE);
                        fileterView.setVisibility(View.GONE);
                        realFilterView.setVisibility(View.VISIBLE);
                    } else {
                        bgaBanner.setVisibility(View.VISIBLE);
                        fileterView.setVisibility(View.VISIBLE);
                    }
                    bgaBanner.setData(imageUrls, tipDescs);
                    List<HomeListData.DataBean.DatasBean.AppListBean> app_list =
                            homeListEntity.getData().getDatas().getApp_list();
                    if (app_list.size() != 0) {
                        mHomeListAdapter.setRefresh(app_list);
                        if (app_list.size() > fullItemCount) {
                            smoothListView.setLoadMoreEnable(true);
                        }
                    } else {
                        smoothListView.setLoadMoreEnable(false);
                    }
                } else {
                    ToastUtils.showLong(homeListEntity.getData().getMsg());
                }
            } else {
                ToastUtils.showLong(homeListEntity.getMsg());
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
//                        ActivityUtils.startActivity(AuthenticationActivity.class);
                        Intent intent = new Intent(getActivity(), AuthenticationActivity.class);
                        intent.putExtra(HOME_CLICK_URL,
                                clickAppListEntity.getData().getDatas().getClick_url());
                        intent.putExtra(HOME_PACKAGE_NAME,
                                clickAppListEntity.getData().getDatas().getPackage_name());
                        this.startActivityForResult(intent, Code.APPLY_APP_CLICK);
                    } else if ("1".equals(isAuth)) {

                        int jump_type = clickAppListEntity.getData().getDatas().getJump_type();
                        if (jump_type == 1) {
                            //继续请求下一个接口
                            //请求服务器返回的链接,成功后打开Google play
                            showLoadingDialog();
                            mPresenter.requestAf(clickAppListEntity.getData().getDatas().getClick_url(),
                                    clickAppListEntity.getData().getDatas().getPackage_name());
                        } else if (jump_type == 2) {
                            //关闭loading显示
                            Intent intent = new Intent();
                            intent.setData(Uri.parse(clickAppListEntity.getData().getDatas().getClick_url()));
                            intent.setAction(Intent.ACTION_VIEW);
                            startActivity(intent);
                        }
                    }
                } else {
                    ToastUtils.showLong(clickAppListEntity.getData().getMsg());
                }
            } else {
                ToastUtils.showLong(clickAppListEntity.getMsg());
            }
        }
    }

    @Override
    public void openGoogle(String packageName) {
        hideLoadingDialog();
        try {
            Uri uri = Uri.parse("market://details?id=" + packageName);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.android.vending");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Throwable t) {
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + packageName);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }

    @Override
    public void loanMessages(MessageBean messageBean) {
        if (messageBean != null) {
            if (messageBean.getRet() == 200) {
                List<MessageBean.DataBeanX.DatasBean.DataBean.AdWheelListBean> ad_wheel_list =
                        messageBean.getData().getDatas().getData().getAd_wheel_list();
                List<String> messages = new ArrayList<>();
                /*messages.add("1. 大家好，我是孙福生。");
                messages.add("2. 欢迎大家关注我哦！");
                messages.add("3. GitHub帐号：sunfusheng");
                messages.add("4. 新浪微博：孙福生微博");
                messages.add("5. 个人博客：sunfusheng.com");
                messages.add("6. 微信公众号：孙福生");
                marqueeTextView.startWithList(messages);*/
                for (int i = 0; i < ad_wheel_list.size(); i++) {
                    messages.add(ad_wheel_list.get(i).getMsg());
                }
                marqueeTextView.startWithList(messages);
            }
        }
    }

    @Override
    public void loadOrder(HomeListData homeListData) {
        hideLoadingDialog();
        if (homeListData != null) {
            if (homeListData.getRet() == 200) {
                if (homeListData.getData().getCode() == 100) {
                    List<HomeListData.DataBean.DatasBean.AppListBean> app_list =
                            homeListData.getData().getDatas().getApp_list();

                    if (app_list.size() == 0) {
                        int height = mScreenHeight - DensityUtil.dip2px(mContext, 128); // 95 =
                        // 标题栏高度 ＋ FilterView的高度
                        mHomeListAdapter.setNoData(height, true);//这里是当数据未空的时候, 创建一个视图塞进ListView中
//                        smoothListView.smoothScrollToPositionFromTop(filterViewPosition, 0);
                        smoothListView.setLoadMoreEnable(false);
                    } else {
                        mHomeListAdapter.setData(app_list);
                        if (app_list.size() > fullItemCount) {
                            smoothListView.setLoadMoreEnable(true);
                        }
                    }
                } else {
                    ToastUtils.showLong(homeListData.getData().getMsg());
                }
            } else {
                ToastUtils.showLong(homeListData.getMsg());
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == Code.APPLY_APP_CLICK) {
            String click_url = data.getStringExtra(HOME_CLICK_URL);
            String package_name = data.getStringExtra(HOME_PACKAGE_NAME);
            showLoadingDialog();
            mPresenter.requestAf(click_url,
                    package_name);
        }
    }
}


