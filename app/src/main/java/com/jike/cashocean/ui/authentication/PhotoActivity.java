package com.jike.cashocean.ui.authentication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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
import com.otaliastudios.cameraview.Facing;

import java.io.File;

import butterknife.BindView;


/**
 * Created by Yey on 2018/1/4.
 */

public class PhotoActivity extends BaseActivity {

    @BindView(R.id.ibtn_finish_screen_identify)
    ImageButton ibtnFinishScreenIdentify;
    @BindView(R.id.ibtn_screen_identify_taken)
    ImageButton ibtnScreenIdentifyTaken;
    @BindView(R.id.ibtn_screen_identify_confirm)
    ImageButton ibtnScreenIdentifyConfirm;
    @BindView(R.id.ibtn_screen_identify_cancel)
    ImageButton ibtnScreenIdentifyCancel;
    @BindView(R.id.camera_view)
    CameraView mCameraView;
    @BindView(R.id.container_confirm_cancel)
    LinearLayout containerConfirmCancel;



    private String idFile;
    private boolean isSavePicture;



    @Override
    public int getContentLayout() {
        return R.layout.activity_photo_identify;
    }

    @Override
    public void initInjector(){

    }

    @Override
    public void bindView(View view, Bundle sacedInstanceState) {
        ibtnFinishScreenIdentify.setOnClickListener(v -> finish());
        ibtnScreenIdentifyTaken.setOnClickListener(v -> {
            ibtnScreenIdentifyTaken.setVisibility(View.GONE);
            containerConfirmCancel.setVisibility(View.VISIBLE);
            mCameraView.captureSnapshot();
        });
        ibtnScreenIdentifyConfirm.setOnClickListener(v -> {
            if (ibtnScreenIdentifyConfirm.getVisibility() == View.VISIBLE) {
                if (isSavePicture) {
                    Intent data = new Intent();
                    data.putExtra("path", idFile);
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });

        ibtnScreenIdentifyCancel.setOnClickListener(v -> {
            if (ibtnScreenIdentifyCancel.getVisibility() == View.VISIBLE) {
                mCameraView.start();
                ibtnScreenIdentifyTaken.setVisibility(View.VISIBLE);
                containerConfirmCancel.setVisibility(View.GONE);
            }
        });
        mCameraView.setFacing(Facing.BACK);
        mCameraView.setLifecycleOwner(this);
        mCameraView.addCameraListener(new CameraListener() {



            public void onCameraOpened(CameraOptions options) {

            }

            public void onPictureTaken(byte[] jpeg) {
                //压缩的时候,外面可以出现一个loding
                mCameraView.stop();
//                showLoadingDialog();
                CameraUtils.decodeBitmap(jpeg, 1000, 1000, new CameraUtils.BitmapCallback() {
                    @Override
                    public void onBitmapReady(Bitmap bitmap) {
                        idFile = getCacheDir().getAbsolutePath() + File.separator + Key.TEMP_PHOTO_PATH;
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        if (width < height) {
                            bitmap = ImageUtils.rotate(bitmap, 270, 0, 0);
                        }
                        isSavePicture = ImageUtils.save(bitmap, idFile, Bitmap.CompressFormat.JPEG);
                        if (BuildConfig.DEBUG) {
                            LogUtils.e("PhotoActivity 相片 " + isSavePicture);
                            LogUtils.e("PhotoActivity 相片  " + String.valueOf(FileUtils.getFileSize(idFile)));
                            LogUtils.e("PhotoActivity 相片 宽 " + String.valueOf(bitmap.getWidth()));
                            LogUtils.e("PhotoActivity 相片 高 " + String.valueOf(bitmap.getHeight()));
                        }
//                        hideLoadingDialog();
                    }
                });
            }

            @Override
            public void onVideoTaken(File video) {

            }
        });
    }

    @Override
    public void initData() {
//        Intent intent = getIntent();
//        if (intent != null) {
//            reqWidth = (int) intent.getIntExtra("reqWidth", 0);
//            reqHeight = (int) intent.getIntExtra("reqHeight", 0);
//        }
    }

//    @Override
//    public void onRetry() {
//
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        try {
//            mCameraView.start();
//        } catch (Throwable throwable) {
//
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        try {
//            mCameraView.stop();
//        } catch (Throwable throwable) {
//        }
//    }

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