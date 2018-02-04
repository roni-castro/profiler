package com.example.roni.profiler.profilePage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roni.profiler.BaseFragment;
import com.example.roni.profiler.R;
import com.example.roni.profiler.components.DialogUtils;
import com.example.roni.profiler.login.LoginActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfilePageFragment extends BaseFragment implements ProfilePageContract.View{
    private ProfilePageContract.Presenter presenter;
    @BindView(R.id.txt_profile_name) TextView userNameTextView;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true); // Helps the view/Presenter/Service survive orientation change
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile_page, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(presenter == null){
            presenter = new ProfilePagePresenter();
        }
        presenter.subscribe();
    }

    @Override
    public void onDestroy() {
        presenter.unSubscribe();
        super.onDestroy();
    }

    @Override
    public void setPresenter(ProfilePageContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showToast(int stringResId) {
        Toast.makeText(getActivity().getApplicationContext(), stringResId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setName(String name) {
        userNameTextView.setText(name);
    }

    @Override
    public void setEmail(String email) {
        emailTextView.setText(email);
    }

    @Override
    public void setProfilePhotoUrl(String profilePhotoUrl) {
        Picasso.with(getActivity())
                .load(profilePhotoUrl)
                .noFade()
                .into(profilePhotoImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        presenter.onThumbnailLoaded();
                    }

                    @Override
                    public void onError() {
                        showToast(getString(R.string.error_photo_url_request));
                    }
                });
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
    public void setThumbnailLoadingIndicator(boolean show) {

    }

    @Override
    public void setDetailLoadingIndicator(boolean show) {

    }

    @OnClick(R.id.btn_profile_settings)
    public void onSettingsButtonClick(View view) {
        presenter.onAccountSettingsClick();
    }

    @OnClick(R.id.btn_profile_login)
    public void onLogoutButtonCLick(View view){
        presenter.onLogoutClick();
    }
}
