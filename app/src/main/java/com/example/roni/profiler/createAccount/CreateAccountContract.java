package com.example.roni.profiler.createAccount;

import com.example.roni.profiler.BasePresenter;
import com.example.roni.profiler.BaseView;

/**
 * Created by roni on 25/01/18.
 */

public interface CreateAccountContract {

    interface AppView extends BaseView<CreateAccountContract.Presenter>{
        String getName();
        String getEmail();
        String getPassword();
        String getConfirmationPassword();
        void goToProfilePageActivity();
    }

    interface Presenter extends BasePresenter{
        void onCreateAccount();
        void onAccountCreatedSuccessfully();
    }
}
