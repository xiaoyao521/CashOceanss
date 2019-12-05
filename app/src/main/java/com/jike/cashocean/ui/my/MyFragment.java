package com.jike.cashocean.ui.my;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jike.cashocean.R;
import com.jike.cashocean.component.GlideApp;
import com.jike.cashocean.model.IdentityInfoEntity;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.ui.base.BaseFragment;
import com.jike.cashocean.ui.login.LoginActivity;
import com.jike.cashocean.ui.my.compoment.DaggerMyComponent;
import com.jike.cashocean.ui.my.contract.MyContract;
import com.jike.cashocean.ui.my.module.MyModule;
import com.jike.cashocean.ui.my.presenter.MyPresenter;
import com.jike.cashocean.util.DialogTools;
import com.jike.cashocean.util.callback.ConfirmCancelCallback;

import butterknife.BindView;


/**
 * @author JingYeoh
 * <a href="mailto:yangjing9611@foxmail.com">Email me</a>
 * <a href="https://github.com/justkiddingbaby">Github</a>
 * <a href="http://blog.justkiddingbaby.com">Blog</a>
 * @since Nov 23,2017
 */
public class MyFragment extends BaseFragment<MyPresenter> implements MyContract.View {

    private static final String BUNDLE_KEY = "my/fragment";
    @BindView(R.id.im_bg_head)
    ImageView imBgHead;
    @BindView(R.id.container_my_app_list)
    FrameLayout containerMyAppList;
    @BindView(R.id.container_about_us)
    FrameLayout containerAboutUs;
    @BindView(R.id.container_exit_login)
    FrameLayout containerExitLogin;
    @BindView(R.id.im_login)
    ImageView imLogin;
    @BindView(R.id.tv_hint_login)
    TextView tvHintLogin;
    @BindView(R.id.container_login)
    LinearLayout containerLogin;


    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void initInjector() {
//        DaggerAuthenticationInfoComponent.builder().saveAuthenticationInfoModule(new SaveAuthenticationInfoModule("我的界面", getContext())).build().inject(this);
        DaggerMyComponent.builder().myModule(new MyModule("我的界面", mContext)).build().inject(this);
    }



    @Override
    public void bindView(View view, Bundle sacedInstanceState) {
        containerAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(AboutUsActivityCashMall.class);
            }
        });
        containerMyAppList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = SPUtils.getInstance().getString(Key.TOKEN);
                if (TextUtils.isEmpty(token)) {
                    ActivityUtils.startActivity(LoginActivity.class);
                } else {
                    ActivityUtils.startActivity(MyAppListActivity.class);
                }
            }
        });
        containerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = SPUtils.getInstance().getString(Key.TOKEN);
                if (TextUtils.isEmpty(token)) {
                    ActivityUtils.startActivity(LoginActivity.class);
                } else {
                    ToastUtils.showLong(getResources().getString(R.string.hint_to_login));
                }
            }
        });

        containerExitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = SPUtils.getInstance().getString(Key.TOKEN);
                if (!TextUtils.isEmpty(token)) {
                    Dialog dialogLoginOut = DialogTools.dialogCommonConfirmCancel(getActivity(), getString(R.string.confirm_exit_login), new ConfirmCancelCallback() {
                        @Override
                        public void Confirm(Dialog dialog) {
                            tvHintLogin.setText(getString(R.string.please_login));
                            GlideApp.with(getActivity()).load(R.mipmap.exit_login).into(imLogin);
                            SPUtils.getInstance().put(Key.TOKEN, "");
                            dialog.dismiss();
                        }

                        @Override
                        public void Cancel(Dialog dialog) {
                            dialog.dismiss();
                        }
                    });
                    dialogLoginOut.show();
                } else {
                    ToastUtils.showLong(getResources().getString(R.string.hint_to_login));
                }
            }
        });


    }

    @Override
    public void initData() {

    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        showLoadingDialog();
        mPresenter.getIdentityInfo();
    }


    @Override
    public void onReloadClickCashMall(View v) {

    }

    @Override
    public boolean needUserLoadSir() {
        return false;
    }

    @Override
    public void loadIdentityInfo(IdentityInfoEntity identityInfoEntity) {
        hideLoadingDialog();
        if (identityInfoEntity != null) {
            if (identityInfoEntity.getRet() == 200) {
                SPUtils.getInstance().put(Key.IS_AUTHENTICAITON, identityInfoEntity.getData().getIs_auth());
                tvHintLogin.setText(identityInfoEntity.getData().getName());
                GlideApp.with(this).load(R.mipmap.is_login).into(imLogin);
            } else {
                tvHintLogin.setText(getString(R.string.please_login));
                GlideApp.with(this).load(R.mipmap.exit_login).into(imLogin);
                SPUtils.getInstance().put(Key.TOKEN, "");
            }
        }
    }
}
