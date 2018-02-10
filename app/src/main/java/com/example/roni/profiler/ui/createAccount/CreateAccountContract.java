package com.example.roni.profiler.ui.createAccount;

import com.example.roni.profiler.ui.base.BasePresenter;
import com.example.roni.profiler.ui.base.BasePresenterContract;
import com.example.roni.profiler.ui.base.BaseView;

/**
 * Created by roni on 25/01/18.
 */

public interface CreateAccountContract {

    interface AppView extends BaseView{
        String getName();
        String getEmail();
        String getPassword();
        String getConfirmationPassword();
        void goToProfilePageActivity();
    }

    interface Presenter<V extends AppView> extends BasePresenterContract<V> {
        void onCreateAccount();
        void onAccountCreatedSuccessfully();
    }
}
