package com.jike.cashocean.util;

import androidx.fragment.app.DialogFragment;

public class BaseDialogFragment extends DialogFragment {
    public interface OnClickDialogFragment {
        void onClikConfirm(String comments);

        void onClikCancle();
    }

    OnClickDialogFragment onClickDialogFragment;

    public void setOnClickDialogFragment(OnClickDialogFragment onClickDialogFragment) {
        this.onClickDialogFragment = onClickDialogFragment;
    }
}
