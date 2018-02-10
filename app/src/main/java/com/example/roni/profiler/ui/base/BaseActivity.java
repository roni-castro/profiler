package com.example.roni.profiler.ui.base;

import android.app.ProgressDialog;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.roni.profiler.di.ActivityComponent;
import com.example.roni.profiler.di.ActivityModule;
import com.example.roni.profiler.di.DaggerActivityComponent;
import com.example.roni.profiler.utils.ProgressDialogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by roni on 02/02/18.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView{
    private ProgressDialog progressDialog;
    private Unbinder unbinder;
    private ActivityComponent activityComponent;

    public abstract int getContentView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        unbinder = ButterKnife.bind(this);
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .build();
    }

    protected void setFragment(int containerResId, Fragment fragment, String fragmentTag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerResId, fragment, fragmentTag);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Drawable drawable = item.getIcon();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showMessage(int stringId) {
        showMessage(getString(stringId));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(int resId) {
        onError(getString(resId));
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        if(progressDialog == null) {
            progressDialog = ProgressDialogUtils.createLoadingDialog(this);
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

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}
