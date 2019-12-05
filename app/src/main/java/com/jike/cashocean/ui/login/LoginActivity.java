package com.jike.cashocean.ui.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jike.cashocean.R;
import com.jike.cashocean.model.CheckUserExist;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.net.URL;
import com.jike.cashocean.ui.base.BaseActivity;
import com.jike.cashocean.ui.login.compoment.DaggerLoginComponent;
import com.jike.cashocean.ui.login.contract.LoginContract;
import com.jike.cashocean.ui.login.module.LoginRegisterForgetPasswordModule;
import com.jike.cashocean.ui.login.presenter.LoginPresenter;
import com.jike.cashocean.ui.web.WebActivity;
import com.jike.cashocean.util.DialogTools;
import com.jike.cashocean.util.PhoneReadUtils;
import com.jike.cashocean.util.callback.RegisterCallback;

import butterknife.BindView;


public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    public static final int APP_REQUEST_CODE = 1256;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.btn_to_facebook_register)
    TextView btnToFacebokkRegister;
    @BindView(R.id.btn_find_password)
    TextView btnFindPassword;
    @BindView(R.id.btn_next_step)
    Button btnNextStep;
    @BindView(R.id.tv_other_register)
    TextView tvOtherRegister;
    @BindView(R.id.ibn_back)
    ImageButton ibnBack;

    private String phoneNum;
    //弹窗回调
    RegisterCallback callback = new RegisterCallback() {
        @Override
        public void openRegisterCenter() {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            intent.putExtra(Intent.EXTRA_PHONE_NUMBER, etPhoneNum.getText().toString().trim());
            ActivityUtils.startActivity(intent);
        }

        @Override
        public void openAgreement() {
            //打开协议
            Intent intent = new Intent(LoginActivity.this, WebActivity.class);
//            String url = BaseHttpURL.BASEURL + HttpURL.CHECK_PROTOCL + File.separator + getResources().getString(R.string.private_aggrement);
            String url = URL.BASEURL + URL.CHECK_PROTOCL;
            LogUtils.e(url);
            intent.putExtra(Intent.EXTRA_ORIGINATING_URI, url);
            intent.putExtra(Intent.EXTRA_TITLE, String.valueOf(getResources().getText(R.string.check_contract)));
            startActivity(intent);
        }
    };

    @Override
    public int getContentLayout() {
        return R.layout.activity_login_cashmall;
    }

    @Override
    public void initInjector() {
        DaggerLoginComponent.builder().loginRegisterForgetPasswordModule(new LoginRegisterForgetPasswordModule("登录")).build().inject(this);

    }


    @SuppressLint({"MissingPermission"})
    @Override
    public void bindView(View view, Bundle sacedInstanceState) {
        /*new RxPermissions(this)
                .request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        //imei 需要注册的时候用, 可传可不传递
                        String imei = PhoneUtils.getIMEI();
                        SPUtils.getInstance().put(Key.IMEI, imei);
                        TelephonyManager mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                        String localPhone = mTelephonyMgr.getLine1Number();
                        if (TextUtils.isEmpty(localPhone)) {
                            return;
                        }
                        if (localPhone.startsWith("+63")) {
                            localPhone = localPhone.replace("+63", "").trim();
                        }
                        if (localPhone.startsWith("63")) {
                            localPhone = localPhone.replace("63", "").trim();
                        }
                        if (localPhone.startsWith("09")) {
                            localPhone = localPhone.substring(0, 1);
                        }
                        etPhoneNum.setText(localPhone);
                        SPUtils.getInstance().put(Key.LOCAL_PHONE, localPhone);//本机号码
                    }
                });*/

        btnNextStep.setOnClickListener(v -> {
            phoneNum = etPhoneNum.getText().toString().trim();
            if (TextUtils.isEmpty(phoneNum)) {
                ToastUtils.showLong(getString(R.string.enter_valid_phone_no));
                return;
            }
            if (!phoneNum.startsWith("9")) { //号码必须以8开头
                ToastUtils.showLong(getString(R.string.hint_input_phone_form));//必须以8开头
                return;
            }
            if (phoneNum.length() == 10) {
                showLoadingDialog();
                mPresenter.checkUserExist(phoneNum);
            } else {
                ToastUtils.showLong(getString(R.string.enter_valid_phone_no));
            }
        });
        ibnBack.setOnClickListener(v -> finish());

        btnToFacebokkRegister.setOnClickListener(v -> {
//            DialogTools.dialogRegisterProtocol(this, callbackFacebook);
            DialogTools.dialogRegisterProtocol(this, callback);
        });

        tvOtherRegister.setOnClickListener(v -> {
            DialogTools.dialogRegisterProtocol(this, callback);
        });

        btnFindPassword.setOnClickListener(v -> {
            Intent intentFindPassword = new Intent(LoginActivity.this, FindPasswordActivity.class);
            if (!TextUtils.isEmpty(etPhoneNum.getText().toString().trim())) {
                intentFindPassword.putExtra(Intent.EXTRA_PHONE_NUMBER, etPhoneNum.getText().toString().trim());
            }
            ActivityUtils.startActivity(intentFindPassword);
        });

        etPhoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etPhoneNum.getText().toString().startsWith("9")) {
                } else {
                    etPhoneNum.getText().clear();
                    ToastUtils.showLong(getString(R.string.hint_input_phone_form));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //清空缓存密码
        DialogTools.clearTempPassword();
    }

    @Override
    public void initData() {

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
                    case "0":
                        DialogTools.dialogRegisterProtocol(this, callback);
                        break;
                    case "1":
                        SPUtils.getInstance().put(Key.LOGIN_PHONE, phoneNum);//登录号码
                        Intent intent = new Intent(LoginActivity.this, LoginPasswordActivity.class);
                        intent.putExtra(Intent.EXTRA_PHONE_NUMBER, phoneNum);
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

    @Override
    public boolean needUserLoadSir() {
        return false;
    }
}