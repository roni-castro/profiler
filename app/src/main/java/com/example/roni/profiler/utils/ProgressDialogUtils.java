package com.example.roni.profiler.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.example.roni.profiler.R;

/**
 * Created by roni on 09/02/18.
 */

public class ProgressDialogUtils {

    public static ProgressDialog createLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
}
