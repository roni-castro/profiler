package com.example.roni.profiler.ui.base;

import android.support.annotation.StringRes;


/**
 * Base interface responsible to act as the parent of all Views(Activity/Fragment) in the MVP
 *
 * Created by roni on 01/02/18.
 */

public interface BaseView {

    void showMessage(@StringRes int stringId);

    void showMessage(String message);

    void onError(@StringRes int resId);

    void onError(String message);

    void showLoading();

    void hideLoading();

    void openActivityOnTokenExpire();

    boolean isNetworkConnected();

}
