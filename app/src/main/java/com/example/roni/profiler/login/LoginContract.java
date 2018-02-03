package com.example.roni.profiler.login;

import com.example.roni.profiler.BasePresenter;
import com.example.roni.profiler.BaseView;

/**
 * Created by roni on 25/01/18.
 */

public interface LoginContract {
    interface View extends BaseView<LoginContract.Presenter>{
        String getEmail();
        String getPassword();

        void goToCreateAccountActivity();
        void goToProfilePageActivity();
    }

    interface Presenter extends BasePresenter{
        void onLoginClick();
        void onLoginSuccess();
        void onForgotPasswordClick();
        void onRegisterClick();
    }

}
