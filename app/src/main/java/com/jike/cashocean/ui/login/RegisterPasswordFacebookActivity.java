package com.jike.cashocean.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jaeger.library.StatusBarUtil;
import com.jike.cashocean.R;
import com.jike.cashocean.model.RegisterUserEntity;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.ui.MainActivity;
import com.jike.cashocean.ui.base.BaseActivity;
import com.jike.cashocean.ui.login.compoment.DaggerLoginComponent;
import com.jike.cashocean.ui.login.contract.RegisterPasswordContact;
import com.jike.cashocean.ui.login.module.LoginRegisterForgetPasswordModule;
import com.jike.cashocean.ui.login.presenter.RegisterPasswordPresenter;
import com.jike.cashocean.util.MapUrlTools;
import com.tuo.customview.VerificationCodeView;

import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;


public class RegisterPasswordFacebookActivity extends BaseActivity<RegisterPasswordPresenter> implements RegisterPasswordContact.View {
    private static final int RC_SIGN_IN = 352;
    @BindView(R.id.ibn_back)
    ImageButton ibnBack;
    @BindView(R.id.vcv_register_password)
    VerificationCodeView vcvRegisterPassword;
    @BindView(R.id.btn_next_step_password_register)
    Button btnNextStepPasswordRegister;
    private String password;
    private String strPhoneNumber;
    private String accountKitErrorStr;
    private String currentAccessTokenToken;


    @Override
    public int getContentLayout() {
        return R.layout.activity_register_password;
    }

    @Override
    public void initInjector() {
        DaggerLoginComponent.builder().loginRegisterForgetPasswordModule(new LoginRegisterForgetPasswordModule("注册账号")).build().inject(this);
    }

    @Override
    public void bindView(View view, Bundle sacedInstanceState) {
        StatusBarUtil.setTranslucent(this, 30);//设置状态栏透明度
        vcvRegisterPassword.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                password = vcvRegisterPassword.getInputContent();
            }

            @Override
            public void deleteContent() {
                password = vcvRegisterPassword.getInputContent();
            }
        });
        ibnBack.setOnClickListener(v -> finish());
        btnNextStepPasswordRegister.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(accountKitErrorStr) || TextUtils.isEmpty(strPhoneNumber)) {
                //AccountKit有可能报错导致无法获取手机号码
                ToastUtils.showLong(accountKitErrorStr);
                return;
            }
            if (strPhoneNumber.startsWith("9") && strPhoneNumber.length() ==10) {
                if (!TextUtils.isEmpty(password)) {
                    if (password.length() == 6) {
                        showLoadingDialog();
                        Map paramsMap = getParamsMap();
                        mPresenter.register(paramsMap);
                    } else {
                        ToastUtils.showLong(getResources().getText(R.string.password_incomplete));
                    }
                } else {
                    ToastUtils.showLong(getResources().getText(R.string.password_incomplete));
                }
            } else {
                ToastUtils.showLong(R.string.service_only_suport_ph);
            }
        });
    }

    private Map getParamsMap() {
        TreeMap<String, String> paramsMap = new TreeMap<>();
        paramsMap.put(Key.VERSION, String.valueOf(AppUtils.getAppVersionCode()));
        paramsMap.put(Key.PHONE, strPhoneNumber);
        paramsMap.put(Key.AUTHETICATION_CODE, currentAccessTokenToken);
        paramsMap.put(Key.REGISTER_TYPE, "2");//类型1 短信 2facebook
        paramsMap.put(Key.PASSWORD, password);
        paramsMap.put(Key.ANDROID_ID, DeviceUtils.getAndroidID());
        paramsMap.put(Key.ADVERTISING_ID, MainActivity.advertising_id);
        String urlParamsByMap = MapUrlTools.getUrlParamsByMap(paramsMap);
        String signs = EncryptUtils.encryptMD5ToString(urlParamsByMap).toLowerCase();
        paramsMap.put(Key.SIGN, signs);
        return paramsMap;
    }

    @Override
    public void initData() {

    }

    @Override
    public void loadResult(RegisterUserEntity registerUser) {
        hideLoadingDialog();
        if (registerUser.getRet() == 200) {
            if (registerUser.getData().getCode() == 100) {
                SPUtils.getInstance().put(Key.TOKEN, registerUser.getData().getDatas().getToken());
                ActivityUtils.finishToActivity(MainActivity.class, false, true);
            } else {
                ToastUtils.showLong(registerUser.getData().getMsg());
            }
        } else {
            ToastUtils.showLong(registerUser.getMsg());
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