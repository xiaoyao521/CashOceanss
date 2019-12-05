package com.jike.cashocean.ui.login;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jike.cashocean.R;
import com.jike.cashocean.model.CheckUserExist;
import com.jike.cashocean.ui.base.BaseActivity;
import com.jike.cashocean.ui.login.compoment.DaggerLoginComponent;
import com.jike.cashocean.ui.login.contract.FindPasswordContact;
import com.jike.cashocean.ui.login.module.LoginRegisterForgetPasswordModule;
import com.jike.cashocean.ui.login.presenter.FindPasswordPresenter;

import butterknife.BindView;

public class FindPasswordActivity extends BaseActivity<FindPasswordPresenter> implements FindPasswordContact.View {

    @BindView(R.id.ibn_back)
    ImageButton ibnBack;
    @BindView(R.id.et_phone_forget_password)
    EditText etPhoneForgetPassword;
    @BindView(R.id.btn_next_forget_password)
    Button btnNextForgetPassword;
    private String mPhoneNum;


    @Override
    public int getContentLayout() {
        return R.layout.activity_find_password_phone;
    }

    @Override
    public void initInjector() {
        DaggerLoginComponent.builder().loginRegisterForgetPasswordModule(new LoginRegisterForgetPasswordModule("找回密码")).build().inject(this);
    }

    @Override
    public void bindView(View view, Bundle sacedInstanceState) {
        ibnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnNextForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mPhoneNum = etPhoneForgetPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(mPhoneNum)) {
                    showLoadingDialog();
                    mPresenter.checkUserExist(mPhoneNum);
                } else {
                    ToastUtils.showLong(R.string.empty_num_phone);
                }
            }
        });

    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            mPhoneNum = getIntent().getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            etPhoneForgetPassword.setText(mPhoneNum);
        }
    }

    @Override
    public void loadResult(CheckUserExist checkUserExist) {
        hideLoadingDialog();
        if (checkUserExist == null) {
            return;
        }
        if (checkUserExist.getRet() == 200) {
            if (checkUserExist.getData().getCode() == 100) {
                switch (checkUserExist.getData().getDatas().getIs_register()) {
                    case "1":
                        String mPhoneNum = etPhoneForgetPassword.getText().toString().trim();
                        Intent intent = new Intent(FindPasswordActivity.this, FindPasswordCodeActivity.class);
                        intent.putExtra(Intent.EXTRA_PHONE_NUMBER, mPhoneNum);
                        ActivityUtils.startActivity(intent);
                        break;
                    default:
                        ToastUtils.showLong(checkUserExist.getData().getMsg());
                        break;
                }
            } else {
                ToastUtils.showLong(checkUserExist.getData().getMsg());
            }
        } else {
            ToastUtils.showLong(checkUserExist.getMsg());
        }
    }

    @Override
    public void onReloadClick(View v) {

    }

//    @Override
//    public Object getLoadSirTarget() {
//        return null;
//    }

    @Override
    public boolean needUserLoadSir() {
        return false;
    }
}
