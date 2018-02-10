package com.example.roni.profiler.ui.login;

import com.example.roni.profiler.ui.base.BasePresenterContract;
import com.example.roni.profiler.ui.base.BaseView;

/**
 * Created by roni on 25/01/18.
 */

public interface LoginContract {

    interface AppView extends BaseView{
        String getEmail();
        String getPassword();
        void goToCreateAccountActivity();
        void goToForgotPasswordActivity();
        void goToProfilePageActivity();
    }

    interface Presenter<V extends BaseView> extends BasePresenterContract<V>{
        void onLoginClick();
        void onLoginSuccess();
        void onForgotPasswordClick();
        void onRegisterClick();
        void checkIfExistsLoggedUser();
    }

}
