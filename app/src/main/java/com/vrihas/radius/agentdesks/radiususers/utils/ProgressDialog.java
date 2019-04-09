package com.vrihas.radius.agentdesks.radiususers.utils;

import android.content.Context;

import com.vrihas.radius.agentdesks.radiususers.R;

public class ProgressDialog {

    public android.app.ProgressDialog dialog;

    public void showDialog(String message, Context activity) {
        if (dialog == null) {
            dialog = new android.app.ProgressDialog(activity);
        }
        dialog.setProgressStyle(R.style.ProgressDialog);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void hideDialog() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

}
