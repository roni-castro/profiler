package com.example.roni.profiler.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.roni.profiler.R;

/**
 * Created by roni on 09/02/18.
 */

public class ProgressDialogUtils {

    public static Dialog createLoadingDialog(Context context) {
        LayoutInflater inflator = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflator.inflate(R.layout.progress_dialog, null);
        final TextView tv = view.findViewById(R.id.progress_title);
            tv.setText(context.getString(R.string.please_wait));

        Dialog dialog = new Dialog(context);
        dialog.setContentView(view);
        if(dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        }
        dialog.setCancelable(false);
        dialog.show();
        return dialog;
    }
}
