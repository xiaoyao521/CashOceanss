package com.jike.cashocean.ui.authentication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.LogUtils;
import com.jike.cashocean.BuildConfig;
import com.jike.cashocean.R;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.ui.base.BaseActivity;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraOptions;
import com.otaliastudios.cameraview.CameraUtils;
import com.otaliastudios.cameraview.CameraView;

import java.io.File;

import butterknife.BindView;

/**
 * Created by Yey on 2018/1/4.
 */

public class PhotoFaceActivity extends BaseActivity {
    @BindView(R.id.face_camera_view)
    CameraView mCameraView;
    @BindView(R.id.iv_face_bg)
    ImageView ivFaceBG;
    @BindView(R.id.ibtn_finish)
    ImageButton ibtnClose;
    @BindView(R.id.ibtn_taken)
    ImageView ibtnTaken;
    @BindView(R.id.ibtn_confirm)
    ImageButton ibtnConfirm;
    @BindView(R.id.ibtn_cancel)
    ImageButton ibtnCancel;

    @BindView(R.id.container_confirm_cancel)
    LinearLayout containerConfirmCancel;

    private String identifyPictureFile; //这个是拍到身份证照片的路径
    private boolean isSavePicture;//是保存拍摄的图片

    @Override
    public int getContentLayout() {
        return R.layout.activity_authenticate_face;
    }

    @Override
    public void initInjector(){

    }

    @Override
    public void bindView(View view, Bundle sacedInstanceState) {
        mCameraView.setLifecycleOwner(this);
        mCameraView.setCameraListener(new CameraListener() {
            @Override
            public void onCameraOpened(CameraOptions options) {
                super.onCameraOpened(options);
            }

            @Override
            public void onPictureTaken(byte[] jpeg) {
                CameraUtils.decodeBitmap(jpeg, 800, 800, new CameraUtils.BitmapCallback() {
                    @Override
                    public void onBitmapReady(Bitmap bitmap) {
                        identifyPictureFile = getCacheDir() + File.separator + Key.TEMP_PHOTO_PATH;
                        int w = bitmap.getWidth();
                        int h = bitmap.getHeight();
                        Matrix matrix = new Matrix();
                        matrix.postScale(-1, 1); // 镜像水平翻转
                        Bitmap convertBmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
                        isSavePicture = ImageUtils.save(convertBmp, new File(identifyPictureFile), Bitmap.CompressFormat.JPEG);
                        if (BuildConfig.DEBUG) {
                            LogUtils.e("身份认证Face  " + isSavePicture);
                            LogUtils.e("身份认证Face  " + String.valueOf(FileUtils.getFileSize(identifyPictureFile)));
                        }
                    }
                });
                ivFaceBG.setVisibility(View.GONE);
                mCameraView.stop();
            }
        });
    }

    @Override
    public void initData() {
        ibtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ibtnTaken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ibtnTaken.setVisibility(View.GONE);
                containerConfirmCancel.setVisibility(View.VISIBLE);
                mCameraView.captureSnapshot();
            }
        });


        ibtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ibtnConfirm.getVisibility() == View.VISIBLE) {
                    if (isSavePicture) {
                        Intent data = new Intent();
                        data.putExtra("path", identifyPictureFile);
                        setResult(RESULT_OK, data);
                        finish();
                    }
                }
            }
        });
        ibtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ibtnCancel.getVisibility() == View.VISIBLE) {
                    mCameraView.start();
                    ivFaceBG.setVisibility(View.VISIBLE);
                    ibtnTaken.setVisibility(View.VISIBLE);
                    containerConfirmCancel.setVisibility(View.GONE);
                }
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
