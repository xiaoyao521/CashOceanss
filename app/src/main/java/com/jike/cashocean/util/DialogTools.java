package com.jike.cashocean.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import androidx.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.jike.cashocean.R;
import com.jike.cashocean.util.callback.ConfirmCancelCallback;
import com.jike.cashocean.util.callback.OnClick;
import com.jike.cashocean.util.callback.RegisterCallback;


public class DialogTools {


    /**
     * 引导用户去到系统设置页面打开权限Dialog
     *
     * @param mContext
     * @param str
     */
    public static void dialogPermission(Context mContext, String str) {
        Dialog hintDialog = getFixSizeDialog(mContext, R.layout.dialog_setting_permission_cancle_confrim);
        hintDialog.findViewById(R.id.btn_confirm_1).setOnClickListener(v -> {
            hintDialog.dismiss();
            Intent localIntent = new Intent();
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", mContext.getPackageName(), null));
            mContext.startActivity(localIntent);
        });
        hintDialog.findViewById(R.id.btn_cancel_1).setOnClickListener(v -> hintDialog.dismiss());
        ((TextView) hintDialog.findViewById(R.id.tv_desc_1)).setText(str);
        hintDialog.show();
    }

    /**
     * 等待网络请求弹窗
     */
    public static Dialog dialogWait(Context mContext, int viewId) {
        Dialog fixSizeDialog = getFixSizeDialog(mContext, viewId);
        return fixSizeDialog;
    }

    /**
     * @param mContext   上下文
     * @param resourceId 布局文件
     * @return
     */
    @NonNull
    public static Dialog getFixSizeDialog(Context mContext, int resourceId) {
        Dialog popDialog = new Dialog(mContext, R.style.StyleDialogPop);
        popDialog.setContentView(resourceId);
        WindowManager.LayoutParams lay = popDialog.getWindow().getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        lay.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lay.width = (int) (dm.widthPixels * 0.85);
//        Rect rect = new Rect();
//        View view = popDialog.getWindow().getDecorView();//decorView是window中的最顶层view，可以从window中获取到decorView
//        view.getWindowVisibleDisplayFrame(rect);
        popDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        return popDialog;
    }

    /**
     * @param mContext
     * @param strContent
     * @param confirmCancelCallback
     */
    public static Dialog dialogCommonConfirmCancel(Context mContext, String strContent, ConfirmCancelCallback confirmCancelCallback) {
        return dialogCommonConfirmCancel(mContext, strContent, "", "", confirmCancelCallback);
    }

    public static Dialog dialogCommonConfirmCancel(Context mContext, String strContent, String cancelStr, String confirmStr, ConfirmCancelCallback confirmCancelCallback) {
        Dialog popDialog = getFixSizeDialog(mContext, R.layout.dialog_hint_comment);
        ((TextView) popDialog.findViewById(R.id.tv_content_desc)).setText(strContent);
        TextView tvCancle = (TextView) popDialog.findViewById(R.id.tv_cancle);
        if (!TextUtils.isEmpty(cancelStr)) {
            tvCancle.setText(cancelStr);
        }
        tvCancle.setOnClickListener(v -> {
            confirmCancelCallback.Cancel(popDialog);
        });
        TextView tvConfirm = (TextView) popDialog.findViewById(R.id.tv_confirm);
        if (!TextUtils.isEmpty(confirmStr)) {
            tvConfirm.setText(confirmStr);
        }
        tvConfirm.setOnClickListener(v -> {
            confirmCancelCallback.Confirm(popDialog);
        });
        return popDialog;
    }


    /**
     * 通用弹窗, 只有一个按钮,并且弹窗无法手动取消
     */
    public static Dialog dialogCommonConfirm(Context mContext, String strContent, OnClick onClick) {
        return dialogCommonConfirm(mContext, strContent, "", onClick);
    }

    public static Dialog dialogCommonConfirm(Context mContext, String strContent, String strconfirm, OnClick onClick) {
        Dialog hintDialog = getFixSizeDialog(mContext, R.layout.dialog_setting_permission_confirm);
        TextView tvConfirm = (TextView) hintDialog.findViewById(R.id.btn_confirm_1);
        if (!TextUtils.isEmpty(strconfirm)) {
            tvConfirm.setText(strconfirm);
        }
        TextView tvContentDesc = (TextView) hintDialog.findViewById(R.id.tv_desc_1);
        tvContentDesc.setText(strContent);
        tvConfirm.setOnClickListener(v -> {
            onClick.onClick();
            hintDialog.dismiss();
        });
        return hintDialog;
    }

    private static String tempPasswordOffline = ""; //临时预存用户密码

    /**
     * 提供方法清空缓存密码
     */
    public static void clearTempPassword() {
        tempPasswordOffline = "";
    }

    /**
     * 注册页面协议Dialog
     *
     * @param mContext
     */
    public static void dialogRegisterProtocol(Activity mContext, RegisterCallback callback) {
        Dialog hintDialog = getFixSizeDialog(mContext, R.layout.dialog_protocol_agree);
        hintDialog.findViewById(R.id.tv_protocol_disagree).setOnClickListener(v -> hintDialog.dismiss());
        hintDialog.findViewById(R.id.tv_protocol_agree).setOnClickListener(v -> {
            hintDialog.dismiss();
            callback.openRegisterCenter();
        });

        String protocol = String.valueOf(mContext.getResources().getText(R.string.protocol_agree_content));
        String protocolName = String.valueOf(mContext.getResources().getText(R.string.regist_protocol_name));
        SpannableStringBuilder contentSpannableBuilder = new SpannableStringBuilder(protocol);
        contentSpannableBuilder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                hintDialog.dismiss();
                callback.openAgreement();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(true);
                ColorStateList colorStateList = mContext.getResources().getColorStateList(R.color.cash_mall_text_color_light);
                ds.setColor(colorStateList.getDefaultColor());
                ds.setFakeBoldText(true);
            }
        }, protocol.indexOf(protocolName), protocol.indexOf(protocolName) + protocolName.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ((TextView) hintDialog.findViewById(R.id.tv_protocol_content)).setMovementMethod(LinkMovementMethod.getInstance());
        ((TextView) hintDialog.findViewById(R.id.tv_protocol_content)).setText(contentSpannableBuilder);
        hintDialog.setOnKeyListener(null);
        hintDialog.show();
    }


}