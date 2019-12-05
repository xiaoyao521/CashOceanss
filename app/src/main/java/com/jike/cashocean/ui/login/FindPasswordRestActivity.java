package com.jike.cashocean.ui.login;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jike.cashocean.R;
import com.jike.cashocean.model.ResetPasswordEntity;
import com.jike.cashocean.ui.MainActivity;
import com.jike.cashocean.ui.base.BaseActivity;
import com.jike.cashocean.ui.login.compoment.DaggerLoginComponent;
import com.jike.cashocean.ui.login.contract.FindPasswordRestContact;
import com.jike.cashocean.ui.login.module.LoginRegisterForgetPasswordModule;
import com.jike.cashocean.ui.login.presenter.FindPasswordRestPresenter;
import com.tuo.customview.VerificationCodeView;

import butterknife.BindView;

public class FindPasswordRestActivity extends BaseActivity<FindPasswordRestPresenter> implements FindPasswordRestContact.View {

    @BindView(R.id.ibn_back)
    ImageButton ibnBack;
    @BindView(R.id.vcv_find_password_reset)
    VerificationCodeView vcvRegisterPasswordReset;
    @BindView(R.id.btn_next_step_reset)
    Button btnNextStepReset;
    private String mPhoneNum;
    private String mCode;
    private String password;

    @Override
    public int getContentLayout() {
        return R.layout.activity_rest_password;
    }

    @Override
    public void initInjector() {
        DaggerLoginComponent.builder().loginRegisterForgetPasswordModule(new LoginRegisterForgetPasswordModule("找回密码")).build().inject(this);

    }

    @Override
    public void bindView(View view, Bundle sacedInstanceState) {
        //        setStatusBarTranslucent(20);
        vcvRegisterPasswordReset.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                password = vcvRegisterPasswordReset.getInputContent();
            }

            @Override
            public void deleteContent() {
                password = vcvRegisterPasswordReset.getInputContent();
            }
        });

        ibnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnNextStepReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(password)) {//密码为空
                    if (password.length() == 6) {
                        showLoadingDialog();
                        mPresenter.restPassword(mPhoneNum, mCode, password);
                    } else {//密码不完整
                        ToastUtils.showLong(getResources().getText(R.string.password_incomplete));
                    }
                } else {
                    ToastUtils.showLong(getResources().getText(R.string.password_empty));
                }
            }
        });

    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            mPhoneNum = getIntent().getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            mCode = getIntent().getStringExtra(Intent.CATEGORY_SAMPLE_CODE);
        }
    }

    @Override
    public void loadResult(ResetPasswordEntity resetPasswordEntity) {
        hideLoadingDialog();
        if (resetPasswordEntity != null) {
            if (resetPasswordEntity.getRet() == 200) {
                if (resetPasswordEntity.getData().getCode() == 100) {
                    ActivityUtils.finishToActivity(MainActivity.class, false, true);
                } else {
                    ToastUtils.showLong(resetPasswordEntity.getData().getMsg());
                }
            } else {
                ToastUtils.showLong(resetPasswordEntity.getMsg());
            }
        }


//        if (resetPassword != null) {
//            if (resetPassword.getCode().equals("200")) {
//                ActivityUtils.finishToActivity(LoginActivity.class, false, true);
//            } else {
//                ToastUtils.showLong(resetPassword.getMsg());
//            }
//        }
    }

    @Override
    public void onReloadClick(View v) {

    }

    @Override
    public boolean needUserLoadSir() {
        return false;
    }
}
