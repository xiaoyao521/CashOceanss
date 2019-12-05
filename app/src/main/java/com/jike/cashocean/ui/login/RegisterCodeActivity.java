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
import com.jike.cashocean.model.RigesterCodeEntity;
import com.jike.cashocean.net.RxSchedulers;
import com.jike.cashocean.ui.base.BaseActivity;
import com.jike.cashocean.ui.login.compoment.DaggerLoginComponent;
import com.jike.cashocean.ui.login.contract.RegisterCodeContact;
import com.jike.cashocean.ui.login.module.LoginRegisterForgetPasswordModule;
import com.jike.cashocean.ui.login.presenter.RegisterCodePresenter;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

//import com.oklik.cashmall.utils.SMSContentObserver;


public class RegisterCodeActivity extends BaseActivity<RegisterCodePresenter> implements RegisterCodeContact.View {
    @BindView(R.id.ibn_back)
    ImageButton ibnBack;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.btn_next_step_register_input_code)
    Button btnNextStepRegisterInputCode;
    private String phoneNum;
    boolean isClickGetCode;


    @Override
    public int getContentLayout() {
        return R.layout.activity_register_code_cashmall;
    }

    @Override
    public void initInjector() {
        DaggerLoginComponent.builder().loginRegisterForgetPasswordModule(new LoginRegisterForgetPasswordModule("注册账号")).build().inject(this);

    }

    @Override
    public void bindView(View view, Bundle sacedInstanceState) {
        ibnBack.setOnClickListener(v -> {
            finish();
        });

        btnGetCode.setOnClickListener(v -> {
            if (TextUtils.isEmpty(phoneNum)) {
                ToastUtils.showLong(getResources().getText(R.string.empty_num_phone));
                return;
            }
            if (isClickGetCode) return;
            showLoadingDialog();
            mPresenter.getRegisterCode(phoneNum);
        });

        btnNextStepRegisterInputCode.setOnClickListener(v -> {
            if (TextUtils.isEmpty(phoneNum)) {
                ToastUtils.showLong(getResources().getText(R.string.empty_num_phone));
                return;
            }
            if (TextUtils.isEmpty(etCode.getText().toString().trim())) {
                ToastUtils.showLong(getResources().getText(R.string.empty_code));
                return;
            }
            Intent intent = new Intent(RegisterCodeActivity.this, RegisterPasswordActivity.class);
            intent.putExtra(Intent.EXTRA_PHONE_NUMBER, phoneNum);
            intent.putExtra(Intent.CATEGORY_SAMPLE_CODE, etCode.getText().toString().trim());
            ActivityUtils.startActivity(intent);
        });
    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            phoneNum = getIntent().getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        }
    }

    @Override
    public void loadResult(RigesterCodeEntity rigesterCodeEntity) {
        hideLoadingDialog();
        if (rigesterCodeEntity != null) {
            if (rigesterCodeEntity.getRet() == 200) {
                if (rigesterCodeEntity.getData().getCode() == 100) {
                    startCountDownTime(120);
                } else {
                    ToastUtils.showLong(rigesterCodeEntity.getData().getMsg());
                }
            } else {
                ToastUtils.showLong(rigesterCodeEntity.getMsg());
            }
        }
    }

    /**
     * 开始倒计时
     *
     * @param time
     */
    public void startCountDownTime(final int time) {
        Observable.interval(1, TimeUnit.SECONDS)
                .compose(RxSchedulers.<Long>applySchedulers())
                .compose(this.<Long>bindToLife())
                .subscribe(new Observer<Long>() {
                    private Disposable disposable;

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        if (aLong <= time) {
                            btnGetCode.setText(time - aLong + "s");
                            isClickGetCode = true;
                        } else {
                            btnGetCode.setText(getResources().getText(R.string.kode_verifikasi));
                            isClickGetCode = false;
                            if (!disposable.isDisposed()) {
                                disposable.dispose();
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onReloadClick(View v) {

    }

    @Override
    public boolean needUserLoadSir() {
        return false;
    }
}

