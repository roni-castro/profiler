package com.example.roni.profiler.ui.profilePage.edit;

import com.example.roni.profiler.dataModel.database.Profile;
import com.example.roni.profiler.ui.base.BasePresenterContract;
import com.example.roni.profiler.ui.base.BaseView;

/**
 * Created by roni on 10/02/18.
 */

public interface EditProfileContract {

    interface AppView extends BaseView{

        void updateProfileDataConfirmed();

        void setUpProfileData(Profile profile);

        String getInterests();

        String getBio();

        void goBackToProfilePageWithMessage();

        void setUpConfirmationMenuVisibilty(boolean showMenu);
    }

    interface Presenter<V extends EditProfileContract.AppView> extends BasePresenterContract<V>{
        void onConfirmUpdateMenuClick();
        void updateProfileInDB(Profile currentProfile);
        void getUserProfile(String profileID);
    }
}
