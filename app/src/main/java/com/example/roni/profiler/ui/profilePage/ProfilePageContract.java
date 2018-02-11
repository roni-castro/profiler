package com.example.roni.profiler.ui.profilePage;

import android.view.View;

import com.example.roni.profiler.R;
import com.example.roni.profiler.dataModel.auth.User;
import com.example.roni.profiler.dataModel.database.Profile;
import com.example.roni.profiler.ui.base.BasePresenterContract;
import com.example.roni.profiler.ui.base.BaseView;

import butterknife.OnClick;

/**
 * Created by roni on 25/01/18.
 */

public interface ProfilePageContract {
    // Override the BaseView setPresenter to the Presenter of this contract and add some
    // specific methods to be used on the profile page view
    interface AppView extends BaseView {
        void setName(String name);
        void setEmail(String email);
        void setProfilePhotoUrl(String profilePhotoUrl);
        void setDefaultProfilePhoto();
        void setAboutMeDescription(String description);
        void setInterests(String interests);

        User loadUserDataFromCache();

        void showLogoutDialog();
        void goToLoginActivity();
        void goToEditProfileActivity();
        void openPhotoGallery();
        void setThumbnailLoadingIndicator(boolean show);
        void setDetailLoadingIndicator(boolean show);

        void onThumbnailClick(View view);
        void onSettingsButtonClick(View view);
        void onLogoutButtonClick(View view);
        void onFabClick(View view);

        void setUpProfileFields(Profile profile);
    }

    interface Presenter<V extends ProfilePageContract.AppView> extends BasePresenterContract<V> {
        void loadUserProfileData();
        void onThumbnailClick();
        void onEditProfileClick();
        void onLogoutClick();
        void onLogoutConfirmed();
        void onAccountSettingsClick();
        void onThumbnailLoaded();
    }
}
