package com.example.roni.profiler.ui.profilePage.edit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.roni.profiler.R;

public class EditProfileActivity extends AppCompatActivity implements EditProfileContract.AppView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
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

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void updateProfileData() {

    }

    @Override
    public String getInterests() {
        return null;
    }

    @Override
    public String getBio() {
        return null;
    }

    @Override
    public void goBackToProfilePageWithMessage() {
        finish();
    }
}
