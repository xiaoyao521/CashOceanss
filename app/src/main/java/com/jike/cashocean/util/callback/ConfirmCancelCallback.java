package com.jike.cashocean.util.callback;

import android.app.Dialog;

public interface ConfirmCancelCallback {
    void Confirm(Dialog dialog);
    void Cancel(Dialog dialog);
}
