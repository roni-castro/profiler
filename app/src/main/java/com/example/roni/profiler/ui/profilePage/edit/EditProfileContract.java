package com.example.roni.profiler.ui.profilePage.edit;

import com.example.roni.profiler.ui.base.BasePresenterContract;
import com.example.roni.profiler.ui.base.BaseView;

/**
 * Created by roni on 10/02/18.
 */

public interface EditProfileContract {

    interface AppView extends BaseView{

        void updateProfileBioAndInterest();

        String getInterests();

        String getBio();
    }

    interface Presenter<V extends BaseView> extends BasePresenterContract<V>{
        void onConfirmUpdateMenuClick();
    }
}