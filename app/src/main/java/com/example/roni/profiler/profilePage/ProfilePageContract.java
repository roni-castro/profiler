package com.example.roni.profiler.profilePage;

import com.example.roni.profiler.BasePresenter;
import com.example.roni.profiler.BaseView;

/**
 * Created by roni on 25/01/18.
 */

public interface ProfilePageContract {
    // Override the BaseView setPresenter to the Presenter of this contract and add some
    // specific methods to be used on the profile page view
    interface View extends BaseView<Presenter> {
        void setPresenter(ProfilePageContract.Presenter presenter);
        void setName(String name);
        void setEmail(String email);
        void setProfilePhotoUrl(String profilePhotoUrl);
        void setDefaultProfilePhoto();
        void setAboutMeDescription(String description);
        void setInterests(String interests);

        void showLogoutDialog();
        void goToLoginActivity();
        void setThumbnailLoadingIndicator(boolean show);
        void setDetailLoadingIndicator(boolean show);
    }

    interface Presenter extends BasePresenter{
        void onThumbnailClick();
        void onEditProfileClick();
        void onLogoutClick();
        void onLogoutConfirmed();
        void onAccountSettingsClick();
        void onThumbnailLoaded();
    }
}
