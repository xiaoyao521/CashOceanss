package com.jike.cashocean.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jike.cashocean.R;
import com.jike.cashocean.model.CheckUserExist;
import com.jike.cashocean.ui.base.BaseActivity;
import com.jike.cashocean.ui.login.compoment.DaggerLoginComponent;
import com.jike.cashocean.ui.login.contract.RegisterContact;
import com.jike.cashocean.ui.login.module.LoginRegisterForgetPasswordModule;
import com.jike.cashocean.ui.login.presenter.RegisterPresenter;

import butterknife.BindView;


public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContact.View {

    @BindView(R.id.ibn_back)
    ImageButton ibnBack;
    @BindView(R.id.et_register_phone_num)
    EditText etRegisterPhoneNum;
    @BindView(R.id.btn_login_register_phone)
    TextView btnLoginRegisterPhone;
    @BindView(R.id.btn_next_register_user)
    Button btnNextRegisterUser;
    boolean isSelectProtocol = true;
    private String phoneNum;

    @Override
    public int getContentLayout() {
        return R.layout.activity_registrer_phone_cashmall;
    }

    @Override
    public void initInjector(){
        DaggerLoginComponent.builder().loginRegisterForgetPasswordModule(new LoginRegisterForgetPasswordModule("注册账号")).build().inject(this);

    }

    @Override
    public void bindView(View view, Bundle sacedInstanceState) {
        if (getIntent() != null) {
            phoneNum = getIntent().getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        }
        etRegisterPhoneNum.setText(phoneNum);

        ibnBack.setOnClickListener(v -> finish());

        btnLoginRegisterPhone.setOnClickListener(v -> ActivityUtils.finishToActivity(LoginActivity.class, false, true));

        btnNextRegisterUser.setOnClickListener(v -> {
            phoneNum = etRegisterPhoneNum.getText().toString().trim();
            if (TextUtils.isEmpty(phoneNum)) {
                ToastUtils.showLong(R.string.enter_valid_phone_no);
                return;
            }
            if (!phoneNum.startsWith("9")) {
                ToastUtils.showLong(getString(R.string.hint_input_phone_form));
                return;
            }
            if (phoneNum.length()== 10) {
                showLoadingDialog();
                mPresenter.checkUserExist(phoneNum);
            } else {
                ToastUtils.showLong(getString(R.string.enter_valid_phone_no));
            }
        });
    }

    @Override
    public void initData() {
        /*new RxPermissions(this).request(Manifest.permission.READ_PHONE_STATE)
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
                        if (localPhone.startsWith("+62")) {
                            localPhone = localPhone.replace("+62", "").trim();
                        }
                        if (localPhone.startsWith("62")) {
                            localPhone = localPhone.replace("62", "").trim();
                        }
                        if (localPhone.startsWith("0")) {
                            localPhone = localPhone.substring(0, 1);
                            localPhone = localPhone.substring(0, 1);
                        }
                        etRegisterPhoneNum.setText(localPhone);
                        SPUtils.getInstance().put(Key.LOCAL_PHONE, localPhone);//本机号码
                    }
                });*/
    }

    @Override
    public void loadResult(CheckUserExist checkUserExist) {
        hideLoadingDialog();
        if (checkUserExist != null) {
            if (checkUserExist.getRet() == 200) {
                if (checkUserExist.getData().getCode() == 100) {
                    switch (checkUserExist.getData().getDatas().getIs_register()) {
                        case "0":
                            Intent intent = new Intent(RegisterActivity.this, RegisterCodeActivity.class);
                            intent.putExtra(Intent.EXTRA_PHONE_NUMBER, phoneNum);
                            ActivityUtils.startActivity(intent);
                            break;
                        case "1":
                            ToastUtils.showLong(checkUserExist.getData().getMsg());//提示已经注册, 去到登录页面
                            finish();
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
    }

    @Override
    public void onReloadClick(View v) {

    }

    @Override
    public boolean needUserLoadSir() {
        return false;
    }
}
