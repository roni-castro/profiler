package com.example.roni.profiler.createAccount;

import com.example.roni.profiler.BasePresenter;
import com.example.roni.profiler.BaseView;
import com.example.roni.profiler.login.LoginContract;

import io.reactivex.Completable;

/**
 * Created by roni on 25/01/18.
 */

public interface CreateAccountContract {

    interface View extends BaseView<CreateAccountContract.Presenter>{
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
