package com.jike.cashocean.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.facebook.appevents.AppEventsLogger;
import com.jike.cashocean.Content.KeyValue;
import com.jike.cashocean.R;
import com.jike.cashocean.model.LoginEntity;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.service.GetAppListService;
import com.jike.cashocean.ui.MainActivity;
import com.jike.cashocean.ui.base.BaseActivity;
import com.jike.cashocean.ui.login.compoment.DaggerLoginComponent;
import com.jike.cashocean.ui.login.contract.LoginPasswordContact;
import com.jike.cashocean.ui.login.module.LoginRegisterForgetPasswordModule;
import com.jike.cashocean.ui.login.presenter.LoginPasswordPresenter;
import com.tuo.customview.VerificationCodeView;

import java.util.HashMap;

import butterknife.BindView;

public class LoginPasswordActivity extends BaseActivity<LoginPasswordPresenter> implements LoginPasswordContact.View {
    @BindView(R.id.ibn_back)
    ImageButton ibnBack;
    @BindView(R.id.vcv_verification_code_view)
    VerificationCodeView vcvVerificationCodeView;
    @BindView(R.id.btn_find_password_login)
    TextView btnFindPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    String password;
    private String loginPhone;
    private String phoneFillAtou;
    private HashMap<String, Object> riskMap;

    @Override
    public int getContentLayout() {
        return R.layout.activity_login_password_cashmall;
    }

    @Override
    public void initInjector() {
        DaggerLoginComponent.builder().loginRegisterForgetPasswordModule(new LoginRegisterForgetPasswordModule("登录")).build().inject(this);

    }

    @Override
    public void bindView(View view, Bundle sacedInstanceState) {
        if (getIntent() != null) {
            loginPhone = getIntent().getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        }
    }

    @Override
    public void initData() {
        /**
         * is_root
         * is_adb
         * ip_value
         * device_imei
         * sim_name
         * device_vender
         * system_version
         * system_version_code
         * 判断设备是否 rooted
         * 判断设备 ADB 是否可用
         * 获取 IP 地址
         * 获取 IMEI 码
         * 获取 Sim 卡运营商名称
         * 手机生产厂商
         * 获取设备系统版本号
         * 获取设备系统版本码
         */

        riskMap = new HashMap<>();
        boolean deviceRooted = DeviceUtils.isDeviceRooted();
        riskMap.put("is_root", deviceRooted);
        boolean adbEnabled = DeviceUtils.isAdbEnabled();
        riskMap.put("is_adb", adbEnabled);
        String manufacturer = DeviceUtils.getManufacturer();//设备厂商
        riskMap.put("device_vender", manufacturer);
        String ipAddress = NetworkUtils.getIPAddress(true);
        riskMap.put("ip_value", ipAddress);
        String imei = SPUtils.getInstance().getString(Key.IMEI);
        riskMap.put("device_imei", imei);
        String simOperatorName = PhoneUtils.getSimOperatorName();//sim卡运营商名称
        riskMap.put("sim_name", simOperatorName);
        String sdkVersionName = DeviceUtils.getSDKVersionName();//获取设备系统版本号
        riskMap.put("system_version", sdkVersionName);
        int sdkVersionCode = DeviceUtils.getSDKVersionCode();//获取设备系统版本码
        riskMap.put("system_version_code", sdkVersionCode);

        vcvVerificationCodeView.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                password = vcvVerificationCodeView.getInputContent();
                if (password.length() == 6) {
                    KeyboardUtils.hideSoftInput(LoginPasswordActivity.this);//隐藏软键盘
                    showLoadingDialog();
                    if (!TextUtils.isEmpty(loginPhone)) {
                        mPresenter.goLogin(loginPhone, password);
                    }
                }
            }

            @Override
            public void deleteContent() {
                password = vcvVerificationCodeView.getInputContent();
            }
        });

        ibnBack.setOnClickListener(v -> {
            finish();
        });
        btnFindPassword.setOnClickListener(v -> {
            Intent intent = new Intent(LoginPasswordActivity.this, FindPasswordActivity.class);
            intent.putExtra(Intent.EXTRA_PHONE_NUMBER, loginPhone);
            ActivityUtils.startActivity(intent);
        });

        btnLogin.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(password)) {//密码为空
                if (password.length() == 6) {
                    showLoadingDialog();
                    KeyboardUtils.hideSoftInput(LoginPasswordActivity.this);//隐藏软键盘
                    if (!TextUtils.isEmpty(loginPhone)) {
                        mPresenter.goLogin(loginPhone, password);
                    }
                } else {
                    ToastUtils.showLong(getResources().getText(R.string.password_incomplete));
                }
            } else {
                ToastUtils.showLong(getResources().getText(R.string.password_empty));
            }
        });
    }

//    /**
//     * 这里是登录的地方
//     *
//     * @param loginPhone
//     */
//    private void goLogin(String loginPhone) {
//        String uuid = MyEncryptUtil.getUUID();
//        String encryptKey = MyEncryptUtil.getEncryptKey(uuid);
//        String encryptPhone = MyEncryptUtil.getEncryptData(uuid, loginPhone);
//        String encryptPassword = MyEncryptUtil.getEncryptData(uuid, password);
//        String version = String.valueOf(AppUtils.getAppVersionCode());
//        String fingerprintDeviceN = EncryptUtils.encryptMD5ToString(DeviceUtils.getAndroidID());
//        String fingerprintDevice = MyEncryptUtil.getEncryptData(uuid, fingerprintDeviceN);
//        String riskMapStr = new Gson().toJson(riskMap);
//        String encryptRiskMapStr = MyEncryptUtil.getEncryptData(uuid, riskMapStr);
////        mPresenter.login();//登录
//    }


    @Override
    public void loginSuccess(LoginEntity loginEntity) {
        hideLoadingDialog();
        if (loginEntity != null) {
            if (loginEntity.getRet() == 200) {
                if (loginEntity.getData().getCode() == 100) {
                    SPUtils.getInstance().put(Key.TOKEN,
                            loginEntity.getData().getDatas().getToken());
                    SPUtils.getInstance().put(Key.IS_AUTHENTICAITON,
                            loginEntity.getData().getDatas().getIs_auth());
                    AppEventsLogger.newLogger(this).logEvent(Key.LOGIN);
                    if (!SPUtils.getInstance().getBoolean(KeyValue.IS_UPLOAD_APPLIST)) {
                        Intent intent = new Intent(this, GetAppListService.class);
                        startService(intent);
                    }
                    ActivityUtils.finishToActivity(MainActivity.class, false, true);
                } else {
                    ToastUtils.showLong(loginEntity.getData().getMsg());
                }
            } else {
                ToastUtils.showLong(loginEntity.getMsg());
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
