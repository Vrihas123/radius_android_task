package com.vrihas.radius.agentdesks.radiususers.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialog;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;

public class DialogFactory {

    private DialogFactory() {

    }

    public static final int CONNECTION_PROBLEM_DIALOG = 1;

    public static void showDialog(int id, final Context context, DialogInterface.OnClickListener clickListenerPositive, DialogInterface.OnClickListener clickListenerNegative, boolean isCancellable, Object... d) {
        switch (id) {
            case CONNECTION_PROBLEM_DIALOG:
                getDialog(context, clickListenerPositive, clickListenerNegative, isCancellable, d);
                break;
            default:
                break;
        }
    }

    private static void getDialog(Context context, DialogInterface.OnClickListener clickListenerPositive, DialogInterface.OnClickListener clickListenerNegative, boolean isCancellable, Object... d) {
        final AlertDialog alertDialog = new AlertDialog.Builder(
                context).create();
        alertDialog.setTitle(d[0].toString());
        alertDialog.setMessage(d[1].toString());
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, d[2].toString(), clickListenerPositive);
        if (d.length > 3){
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, d[3].toString(), clickListenerNegative);
        }
        if (isCancellable){
            alertDialog.setCancelable(true);
        }else {
            alertDialog.setCancelable(false);
        }
        alertDialog.show();
    }

}
