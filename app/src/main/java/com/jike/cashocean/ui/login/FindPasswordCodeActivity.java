package com.jike.cashocean.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jike.cashocean.R;
import com.jike.cashocean.model.ForgetPWCodeEntity;
import com.jike.cashocean.net.RxSchedulers;
import com.jike.cashocean.ui.base.BaseActivity;
import com.jike.cashocean.ui.login.compoment.DaggerLoginComponent;
import com.jike.cashocean.ui.login.contract.FindPasswordCodeContact;
import com.jike.cashocean.ui.login.module.LoginRegisterForgetPasswordModule;
import com.jike.cashocean.ui.login.presenter.FindPasswordCodePresenter;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

//import com.oklik.cashmall.utils.SMSContentObserver;


public class FindPasswordCodeActivity extends BaseActivity<FindPasswordCodePresenter> implements FindPasswordCodeContact.View {

    @BindView(R.id.ibn_back)
    ImageButton ibnBack;
    @BindView(R.id.btn_get_code)
    TextView btnFindGetCode;
    @BindView(R.id.et_input_code)
    EditText etInputCode;
    @BindView(R.id.btn_next_code)
    Button btnNextCode;
    boolean isClickGetCode;
    private String mPhoneNum;

    @Override
    public int getContentLayout() {
        return R.layout.activity_forget_code;
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
        btnFindGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mPhoneNum)) {
                    ToastUtils.showLong(getResources().getText(R.string.empty_num_phone));
                    return;
                }
                if (isClickGetCode) return;
                showLoadingDialog();
                mPresenter.getForgetCode(mPhoneNum);
            }
        });
        btnNextCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codeSms = etInputCode.getText().toString().trim();
                if (!TextUtils.isEmpty(codeSms)) {
                    Intent intent = new Intent(FindPasswordCodeActivity.this, FindPasswordRestActivity.class);
                    intent.putExtra(Intent.EXTRA_PHONE_NUMBER, mPhoneNum);
                    intent.putExtra(Intent.CATEGORY_SAMPLE_CODE, codeSms);
                    startActivity(intent);
                } else {
                    ToastUtils.showLong(getString(R.string.please_input_code));
                }

            }
        });
    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            mPhoneNum = getIntent().getStringExtra(Intent.EXTRA_PHONE_NUMBER);
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
                            btnFindGetCode.setText(time - aLong + "s");
                            isClickGetCode = true;
                        } else {
                            btnFindGetCode.setText(getResources().getText(R.string.kode_verifikasi));
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
    public void loadResult(ForgetPWCodeEntity forgetPWCodeEntity) {
        hideLoadingDialog();
        if (forgetPWCodeEntity != null) {
            if (forgetPWCodeEntity.getRet() == 200) {
                if (forgetPWCodeEntity.getData().getCode() == 100) {
                    startCountDownTime(120);
                } else {
                    ToastUtils.showLong(forgetPWCodeEntity.getData().getMsg());
                }
            } else {
                ToastUtils.showLong(forgetPWCodeEntity.getMsg());
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