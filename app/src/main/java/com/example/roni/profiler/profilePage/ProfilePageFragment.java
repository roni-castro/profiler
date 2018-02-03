package com.example.roni.profiler.profilePage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roni.profiler.BaseFragment;
import com.example.roni.profiler.R;

public class ProfilePageFragment extends BaseFragment implements ProfilePageContract.View{
    private ProfilePageContract.Presenter presenter;

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
    public void setName(String name) {

    }

    @Override
    public void setEmail(String email) {

    }

    @Override
    public void setProfilePhotoUrl(String profilePhotoUrl) {

    }

    @Override
    public void setDefaultProfilePhoto() {

    }

    @Override
    public void setAboutMeDescription(String description) {

    }

    @Override
    public void setInterests(String interests) {

    }

    @Override
    public void showLogoutDialog() {

    }

    @Override
    public void goToLoginActivity() {

    }

    @Override
    public void setThumbnailLoadingIndicator(boolean show) {

    }

    @Override
    public void setDetailLoadingIndicator(boolean show) {

    }
}
