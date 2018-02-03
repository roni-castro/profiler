package com.example.roni.profiler.components;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.example.roni.profiler.R;

/**
 * Created by roni on 02/02/18.
 */
public class DialogUtils {
    public static void showOkDialog(Context context,
                                    String title,
                                    String message,
                                    DialogInterface.OnClickListener okListener) {
        AlertDialog.Builder builder = creteGenericDialogBuilder(context, title, message);
        builder.setPositiveButton(android.R.string.ok, okListener);
        builder.show();
    }

    public static void showYesNoDialog(Context context,
                                               String title,
                                               String message,
                                               DialogInterface.OnClickListener positiveListener){
        AlertDialog.Builder builder = creteGenericDialogBuilder(context, title, message);
        builder.setNegativeButton(context.getString(R.string.NO), null);
        builder.setPositiveButton(context.getString(R.string.YES), positiveListener);
        builder.show();
    }

    private static AlertDialog.Builder creteGenericDialogBuilder(Context context, String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        return builder;
    }
}


