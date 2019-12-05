package com.jike.cashocean.ui.authentication;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.contrarywind.interfaces.IPickerViewData;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.jakewharton.rxbinding2.view.RxView;
import com.jike.cashocean.Content.KeyValue;
import com.jike.cashocean.R;
import com.jike.cashocean.model.IdTypeEntity;
import com.jike.cashocean.model.SaveIdentityInfoEntity;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.net.RxSchedulers;
import com.jike.cashocean.ui.authentication.compoment.DaggerAuthenticationInfoComponent;
import com.jike.cashocean.ui.authentication.contract.AuthenticationContract;
import com.jike.cashocean.ui.authentication.module.SaveAuthenticationInfoModule;
import com.jike.cashocean.ui.authentication.presenter.AuthenticationPresenter;
import com.jike.cashocean.ui.base.BaseActivity;
import com.jike.cashocean.util.BitmapUtil;
import com.jike.cashocean.util.DialogTools;
import com.jike.cashocean.util.MapUrlTools;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xw.repo.XEditText;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AuthenticationActivity extends BaseActivity<AuthenticationPresenter> implements AuthenticationContract.View {

    private static final int CODE_FACE = 147;
    private static final int CODE_ID_PHOTO = 258;
    @BindView(R.id.ibn_back)
    ImageButton ibnBack;
    @BindView(R.id.btn_submit_identity)
    Button btnSubmitIdentity;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_identity_number)
    XEditText etIdentityNumber;
    @BindView(R.id.im_identity_id_car_photo)
    ImageView imIdentityIDCarPhoto;
    @BindView(R.id.im_face_photo)
    ImageView imFacePhoto;
    @BindView(R.id.line_card_type)
    LinearLayout line_id_type;
    @BindView(R.id.tv_card_type)
    TextView tvCardType;
    private String facePicturePath;
    private String facePicturePathShow;
    private String idCardPath;
    private String idCardPathShow;
    private String advertising_id;
    private String _idName;
    private String _faceName;


    @Override
    public int getContentLayout() {
        return R.layout.activity_authentication;
    }

    @Override
    public void initInjector() {
        DaggerAuthenticationInfoComponent.builder().saveAuthenticationInfoModule(new SaveAuthenticationInfoModule("认证接口", this)).build().inject(this);
    }

    String home_url;
    String home_packageName;

    @Override
    public void bindView(View view, Bundle sacedInstanceState) {
        if (getIntent() != null) {
            home_url = getIntent().getStringExtra(KeyValue.HOME_CLICK_URL);
            home_packageName = getIntent().getStringExtra(KeyValue.HOME_PACKAGE_NAME);
        }
        ibnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RxView.clicks(imIdentityIDCarPhoto)
                .compose(this.bindToLife())
                .compose(new RxPermissions(this).ensure(Manifest.permission.CAMERA))
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        startActivityForResult(new Intent(AuthenticationActivity.this,
                                PhotoActivity.class), CODE_ID_PHOTO);
                    } else {
                        DialogTools.dialogPermission(this,
                                getString(R.string.open_camera_permission));
                    }
                });
        RxView.clicks(imFacePhoto)
                .compose(this.bindToLife())
                .compose(new RxPermissions(this).ensure(Manifest.permission.CAMERA))
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        startActivityForResult(new Intent(AuthenticationActivity.this,
                                PhotoFaceActivity.class), CODE_FACE);
                    } else {
                        DialogTools.dialogPermission(this,
                                getString(R.string.open_camera_permission));
                    }
                });

        RxView.clicks(btnSubmitIdentity)
                .compose(this.bindToLife())
                .throttleFirst(3, TimeUnit.SECONDS)
                .subscribe(o -> {
                    if (judgeIdentityInfo(true)) {
                        showLoadingDialog();
                        Map<String, String> parameMap = getParameMap();

                        mPresenter.saveInfo(parameMap);
                    }
                });
        tvCardType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idType != null) {
                    getCustomOptionPicker(idType.getData().getDatas().getList(),
                            R.layout.layout_pickerview_custom_options, getString(R.string.id_type),
                            tvCardType).show();
                } else {
                    mPresenter.getIdType(true);
                }
            }
        });
    }

    private OptionsPickerView pvCustomOptions;

    public <T extends IPickerViewData> OptionsPickerView getCustomOptionPicker(final List<T> resouce, int layout, final String title, final TextView textView) {
        KeyboardUtils.hideSoftInput(this);//影藏软键盘
        pvCustomOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                String idType = resouce.get(options1).getPickerViewText();
                textView.setText(idType);
                //修改字数限制
                // 证件类型对应的ID位数限制。
                //SSS为10位，TIN为12位。
                //为了避免混乱，证件类型每次重新选择后，输入框清空
                changeEditedShow(idType);

            }
        }).setLayoutRes(layout, new CustomListener() {
            @Override
            public void customLayout(View v) {
                ((TextView) v.findViewById(R.id.tv_title_desc)).setText(title);
                v.findViewById(R.id.btn_confirm_pickerview)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.returnData();
                                pvCustomOptions.dismiss();
                            }
                        });
                v.findViewById(R.id.btn_cancel_pickerview)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.dismiss();
                            }
                        });
            }
        })
                .build();
        pvCustomOptions.setPicker(resouce);
        return pvCustomOptions;
    }

    public void changeEditedShow(String idType) {
        if (idType.equals("Driver's License")) {
            //驾照
            etIdentityNumber.setInputType(InputType.TYPE_CLASS_TEXT);
            etIdentityNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
            etIdentityNumber.setHint("*00-00-000000");
            etIdentityNumber.setPattern(new int[]{3, 2, 6});
        } else if (idType.equals("Passport")) {
            //护照
            etIdentityNumber.setInputType(InputType.TYPE_CLASS_TEXT);
            etIdentityNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});
            etIdentityNumber.setHint(R.string.please_input);
            etIdentityNumber.setPattern(new int[]{9});
        } else if (idType.equals("SSS ID")) {
            //社会安全系统卡
            etIdentityNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
            etIdentityNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
            etIdentityNumber.setHint("00-0000000-0");
            etIdentityNumber.setPattern(new int[]{2, 7, 1});
        } else if (idType.equals("UMID")) {
            etIdentityNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
            etIdentityNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});
            etIdentityNumber.setHint("0000-0000000-0");
            etIdentityNumber.setPattern(new int[]{4, 7, 1});
        } else if (idType.equals("PRC ID")) {
            etIdentityNumber.setInputType(InputType.TYPE_CLASS_TEXT);
            etIdentityNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(7)});
            etIdentityNumber.setHint("0000000");
            etIdentityNumber.setPattern(new int[]{7});
        }
        etIdentityNumber.setText("");
    }

    @Override
    public void initData() {
        initPicturePath();
        getGoogleID();
        showLoadingDialog();
        mPresenter.getIdType(false);
    }


    private String getPhotoName() {
        String nameStr =
                String.valueOf(System.currentTimeMillis()) + SPUtils.getInstance().getString(Key.TOKEN)+".png";
        return EncryptUtils.encryptMD5ToString(nameStr);
    }

    /**
     * 获取Google ID
     */
    private void getGoogleID() {
        showLoadingDialog();
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                AdvertisingIdClient.Info advertisingIdInfo =
                        AdvertisingIdClient.getAdvertisingIdInfo(AuthenticationActivity.this);
                String adId = advertisingIdInfo.getId();
                e.onNext(adId);
                e.onComplete();
            }
        })
                .compose(RxSchedulers.<String>applySchedulers())
                .compose(this.<String>bindToLife())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        advertising_id = s;
                    }

                    @Override
                    public void onError(Throwable e) {
                        advertising_id = "123456";//格式就是123456告诉后台google Id 获取失败
                        hideLoadingDialog();
                    }

                    @Override
                    public void onComplete() {
                        hideLoadingDialog();
                    }
                });
    }

    //初始化图片路径
    private void initPicturePath() {
        facePicturePath =
                getCacheDir().getAbsolutePath() + File.separator + Key.PATH_FACE_PHOTO_PICTURE;
        facePicturePathShow =
                getCacheDir().getAbsolutePath() + File.separator + Key.PATH_FACE_PHOTO_PICTURE_SHOW;
        idCardPath = getCacheDir().getAbsolutePath() + File.separator + Key.PATH_ID_CARD_PICTURE;
        idCardPathShow =
                getCacheDir().getAbsolutePath() + File.separator + Key.PATH_ID_CARD_PICTURE_SHOW;
    }

    public Map<String, String> getParameMap() {
        TreeMap<String, String> paramsMap = new TreeMap<>();
        paramsMap.put(Key.VERSION, String.valueOf(AppUtils.getAppVersionCode()));
        paramsMap.put(Key.TOKEN, SPUtils.getInstance().getString(Key.TOKEN));
        paramsMap.put(Key.IDENTITY_NAME, etName.getText().toString().trim());
        paramsMap.put(Key.ID_TYPE, tvCardType.getText().toString().trim());
        paramsMap.put(Key.ID_NUM, etIdentityNumber.getText().toString().trim());
        paramsMap.put(Key.ID_IMAGE, _idName);
        paramsMap.put(Key.FACE_IMAGE, _faceName);
        paramsMap.put(Key.ADVERTISING_ID, advertising_id);
        paramsMap.put(Key.ANDROID_ID, DeviceUtils.getAndroidID());
        paramsMap.put(Key.SIGN, MapUrlTools.getSign(paramsMap));
        return paramsMap;
    }

    //判断信息是否填写完整
    public boolean judgeIdentityInfo(boolean showHint) {
        //判断姓名是否填写
        String nameStr = etName.getText().toString().trim();
        if (TextUtils.isEmpty(nameStr)) {
            if (showHint) {
                ToastUtils.showLong(getString(R.string.please_input_name));
            }
            return false;
        }
        if (TextUtils.isEmpty(tvCardType.getText().toString())) {
            if (showHint) {
                ToastUtils.showLong(getString(R.string.select_id_type));
            }
            return false;
        }
        //判断身份证号码
        String cardNum = etIdentityNumber.getText().toString().trim();
        if (TextUtils.isEmpty(cardNum)) {
            if (showHint) {
                ToastUtils.showLong(getString(R.string.please_input_id_num));
            }
            return false;
        }
        //判断证件照片
        if (TextUtils.isEmpty(_idName)) {
            if (showHint) {
                ToastUtils.showLong(getString(R.string.please_id_photo));
            }
            return false;
        }
        //判断人脸照片
        if (TextUtils.isEmpty(_faceName)) {
            if (showHint) {
                ToastUtils.showLong(getString(R.string.please_face_photo));
            }
            return false;
        }
        return true;
    }


    @Override
    public void onReloadClick(View v) {

    }


    @Override
    public boolean needUserLoadSir() {
        return false;
    }

    @Override
    public void loadSaveInfoSuccess(SaveIdentityInfoEntity saveIdentityInfoEntity) {
        hideLoadingDialog();
        if (saveIdentityInfoEntity != null) {
            if (saveIdentityInfoEntity.getRet() == 200) {
                if (saveIdentityInfoEntity.getData().getCode() == 100) {
                    SPUtils.getInstance().put(Key.IS_AUTHENTICAITON,
                            saveIdentityInfoEntity.getData().getDatas().getIs_auth());
                    Intent intent = new Intent();
                    intent.putExtra(KeyValue.HOME_CLICK_URL, home_url);
                    intent.putExtra(KeyValue.HOME_PACKAGE_NAME, home_packageName);
                    setResult(RESULT_OK, intent);
                    AppEventsLogger.newLogger(this).logEvent(Key.SUBMIT_INFO);
                    finish();
                } else {
                    ToastUtils.showLong(saveIdentityInfoEntity.getData().getMsg());
                }
            } else {
                ToastUtils.showLong(saveIdentityInfoEntity.getMsg());
            }
        }
    }

    /**
     * 图片处理
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //处理自拍照片
        if (requestCode == CODE_FACE && resultCode == RESULT_OK) {
            String path = data.getStringExtra("path");
            FileUtils.copyFile(path, facePicturePath);
            String faceName = getPhotoName();
            showLoadingDialog();
            mPresenter.pushPhoto(faceName, path, false);
        }
        //证件照
        if (requestCode == CODE_ID_PHOTO && resultCode == RESULT_OK) {
            String path = data.getStringExtra("path");
            FileUtils.copyFile(path, idCardPath);
            String idName = getPhotoName();
            showLoadingDialog();
            mPresenter.pushPhoto(idName, path, true);
        }
    }

    @Override
    public void loadSuccessFacePhoto(String faceName) {
        hideLoadingDialog();
        if (faceName != null) {
            _faceName = faceName;
            Bitmap bitmapFaceShow = BitmapUtil.decodeSampledBitmapFromFile(facePicturePath,
                    imFacePhoto.getWidth(), imFacePhoto.getHeight());
            ImageUtils.save(bitmapFaceShow, facePicturePathShow, Bitmap.CompressFormat.JPEG);
            imFacePhoto.setImageBitmap(bitmapFaceShow);
        }
    }

    @Override
    public void loadScuccessIDPhoto(String idName) {
        hideLoadingDialog();
        if (idName != null) {
            _idName = idName;
            Bitmap idPhotoBitmapShow = BitmapUtil.decodeSampledBitmapFromFile(idCardPath,
                    imIdentityIDCarPhoto.getWidth(), imIdentityIDCarPhoto.getHeight());
            ImageUtils.save(idPhotoBitmapShow, idCardPathShow, Bitmap.CompressFormat.JPEG);
            imIdentityIDCarPhoto.setImageBitmap(idPhotoBitmapShow);
        }
    }

    IdTypeEntity idType;

    @Override
    public void loadType(IdTypeEntity idTypeEntity, boolean isFirstFailed) {
        if (idTypeEntity == null) {

        } else {
            idType = idTypeEntity;
            if (isFirstFailed) {
                getCustomOptionPicker(idType.getData().getDatas().getList(),
                        R.layout.layout_pickerview_custom_options, getString(R.string.id_type),
                        tvCardType).show();
            } else {
                tvCardType.setText(idType.getData().getDatas().getList().get(0).getType_name());
                changeEditedShow(tvCardType.getText().toString());
            }
        }
    }
}
