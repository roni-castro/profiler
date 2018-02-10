package com.example.roni.profiler.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.roni.profiler.di.ActivityComponent;
import com.example.roni.profiler.utils.ProgressDialogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by roni on 02/02/18.
 */

public abstract class BaseFragment extends Fragment implements BaseView {
    private BaseActivity activity;
    private ProgressDialog progressDialog;
    private Unbinder unbinder;

    public abstract int getFragmentViewResId();
    protected abstract void setUpCreatedView(View view);
    protected abstract void injectViewIntoComponent();
    public abstract void attachViewToPresenter();
    public abstract void detachViewFromPresenter();

    @CallSuper
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(getFragmentViewResId(), container, false);
        unbinder = ButterKnife.bind(this, v);
        injectViewIntoComponent();
        setUpCreatedView(v);
        attachViewToPresenter();
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.activity = (BaseActivity) context;
        }

    }

    @Override
    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        detachViewFromPresenter();
        super.onDestroy();
    }

    public void showToast(int stringResId) {
        Toast.makeText(getActivity().getApplication(), stringResId, Toast.LENGTH_LONG).show();
    }

    public void showToast(String message) {
        Toast.makeText(getActivity().getApplication(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(int stringId) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showLoading() {
        if(progressDialog == null) {
            progressDialog = ProgressDialogUtils.createLoadingDialog(activity);
        } else {
            progressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    public ActivityComponent getActivityComponent() {
        if (activity != null) {
            return activity.getActivityComponent();
        }
        return null;
    }
}