package com.example.roni.profiler.ui.profilePage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roni.profiler.R;
import com.example.roni.profiler.dataModel.auth.User;
import com.example.roni.profiler.dataModel.database.Profile;
import com.example.roni.profiler.ui.base.BaseFragment;
import com.example.roni.profiler.ui.login.LoginActivity;
import com.example.roni.profiler.ui.profilePage.edit.EditProfileActivity;
import com.example.roni.profiler.utils.DialogUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ProfilePageFragment extends BaseFragment implements ProfilePageContract.AppView {
    @Inject
    ProfilePageContract.Presenter<ProfilePageContract.AppView> presenter;

    @BindView(R.id.txt_profile_name) TextView nameTextView;
    @BindView(R.id.txt_profile_email) TextView emailTextView;
    @BindView(R.id.txt_profile_interests_description) TextView interestsTextView;
    @BindView(R.id.txt_profile_about_me_description) TextView aboutMeTextView;
    @BindView(R.id.img_profile_photo) ImageView profilePhotoImageView;

    public ProfilePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProfilePageFragment.
     */
    public static ProfilePageFragment newInstance() {
        ProfilePageFragment fragment = new ProfilePageFragment();
        return fragment;
    }

    @Override
    public int getFragmentViewResId() {
        return R.layout.fragment_profile_page;
    }

    @Override
    protected void setUpCreatedView(View view) {

    }

    @Override
    protected void injectViewIntoComponent() {
        getActivityComponent().inject(this);
    }

    @Override
    public void attachViewToPresenter() {
        presenter.onAttach(this);
        presenter.loadUserProfileData();
    }

    @Override
    public void detachViewFromPresenter() {
        presenter.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true); // Helps the view/Presenter/Service survive orientation change
    }

    @Override
    public void showMessage(int stringResId) {
        Toast.makeText(getActivity().getApplicationContext(), stringResId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
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
    public void setName(String name) {
        nameTextView.setText(name);
    }

    @Override
    public void setEmail(String email) {
        emailTextView.setText(email);
    }

    @Override
    public void setProfilePhotoUrl(String profilePhotoUrl) {
        if(profilePhotoUrl != null && profilePhotoUrl.length() > 0) {
            Picasso.with(getActivity())
                    .load(profilePhotoUrl)
                    .noFade()
                    .into(profilePhotoImageView, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            presenter.onThumbnailLoaded();
                        }

                        @Override
                        public void onError() {
                            showMessage(getString(R.string.error_photo_url_request));
                            setDefaultProfilePhoto();
                        }
                    });
        } else{
            setDefaultProfilePhoto();
        }
    }

    @Override
    public void setDefaultProfilePhoto() {
        profilePhotoImageView.setImageResource(R.drawable.ic_person_black_36px);
    }

    @Override
    public void setAboutMeDescription(String description) {
        aboutMeTextView.setText(description);
    }

    @Override
    public void setInterests(String interests) {
        interestsTextView.setText(interests);
    }

    @Override
    public void showLogoutDialog() {
        DialogUtils.showYesNoDialog(getActivity(), "", getString(R.string.msg_confirmation_logout), new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.onLogoutConfirmed();
            }
        });
    }

    @Override
    public void goToLoginActivity() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void goToEditProfileActivity() {
        Intent intent = new Intent(getActivity(), EditProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void openPhotoGallery() {
        //Logic to be implemented using a lib
    }

    @Override
    public void setThumbnailLoadingIndicator(boolean show) {

    }

    @Override
    public void setDetailLoadingIndicator(boolean show) {

    }

    @Override
    public void setUpProfileFields(Profile profile) {
        nameTextView.setText(profile.getName());
        emailTextView.setText(profile.getEmail());
        aboutMeTextView.setText(profile.getBio());
        interestsTextView.setText(profile.getInterests());
        setProfilePhotoUrl(profile.getPhotoURL());
    }

    @OnClick(R.id.img_profile_photo)
    @Override
    public void onThumbnailClick(View view) {
        presenter.onThumbnailClick();
    }

    @OnClick(R.id.btn_profile_settings)
    @Override
    public void onSettingsButtonClick(View view) {
        presenter.onAccountSettingsClick();
    }

    @OnClick(R.id.btn_profile_login)
    @Override
    public void onLogoutButtonClick(View view){
        presenter.onLogoutClick();
    }

    @OnClick(R.id.fab)
    @Override
    public void onFabClick(View view){
        presenter.onEditProfileClick();
    }
}
