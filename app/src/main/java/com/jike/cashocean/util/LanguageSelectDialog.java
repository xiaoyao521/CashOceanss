package com.jike.cashocean.util;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jike.cashocean.Content.KeyValue;
import com.jike.cashocean.R;
import com.jike.cashocean.net.Key;
import com.jike.cashocean.util.languageutil.LanguageConstants;

//语言切换dialog
public class LanguageSelectDialog extends BaseDialogFragment {
    public interface OnClickLanguage {
        void onEglish();

        void onTagalog();
    }

    private OnClickLanguage onClickLanguage;
    private ImageView ivCancle;
    private RadioGroup rgLanguage;
    private RadioButton rgLanguageEnglish;
    private RadioButton rgLanguageTagalog;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.StyleDialogPop);
        dialog.setContentView(R.layout.dialog_language_select);
        WindowManager.LayoutParams lay = dialog.getWindow().getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager =
                (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        lay.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lay.width = (int) (dm.widthPixels * 0.85);
//        Rect rect = new Rect();
//        View view = popDialog.getWindow().getDecorView();
// decorView是window中的最顶层view，可以从window中获取到decorView
//        view.getWindowVisibleDisplayFrame(rect);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        return dialog;
    }

    public void setOnClickLanguage(OnClickLanguage onClickLanguage) {
        this.onClickLanguage = onClickLanguage;
    }

    @Override
    public void onResume() {
        super.onResume();
        ivCancle = (ImageView) getDialog().findViewById(R.id.iv_cancle);
        rgLanguage = (RadioGroup) getDialog().findViewById(R.id.rg_language);
        rgLanguageEnglish = (RadioButton) getDialog().findViewById(R.id.rb_language_english);
        rgLanguageTagalog = (RadioButton) getDialog().findViewById(R.id.rb_language_tagalog);
        String languageKey = SPUtils.getInstance().getString(Key.LANGUAGE_KEY,
                "");
        if (languageKey.equals(LanguageConstants.TAGALOG)) {
            rgLanguageTagalog.setChecked(true);
        } else {
            rgLanguageEnglish.setChecked(true);
        }
        ivCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        rgLanguage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_language_english) {
                    if (onClickLanguage != null) {
                        onClickLanguage.onEglish();
                    } else {
                        ToastUtils.showLong("OnClickLanguage 不能为null");
                    }
                }
                if (checkedId == R.id.rb_language_tagalog) {
                    if (onClickLanguage != null) {
                        onClickLanguage.onTagalog();
                    } else {
                        ToastUtils.showLong("OnClickLanguage 不能为null");
                    }
                }
            }
        });
    }
}
