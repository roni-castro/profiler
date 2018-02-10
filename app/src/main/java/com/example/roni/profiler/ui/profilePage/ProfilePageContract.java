package com.example.roni.profiler.ui.profilePage;

import com.example.roni.profiler.ui.base.BasePresenterContract;
import com.example.roni.profiler.ui.base.BaseView;

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

        void showLogoutDialog();
        void goToLoginActivity();
        void setThumbnailLoadingIndicator(boolean show);
        void setDetailLoadingIndicator(boolean show);
    }

    interface Presenter<V extends ProfilePageContract.AppView> extends BasePresenterContract<V> {
        void onThumbnailClick();
        void onEditProfileClick();
        void onLogoutClick();
        void onLogoutConfirmed();
        void onAccountSettingsClick();
        void onThumbnailLoaded();
    }
}
