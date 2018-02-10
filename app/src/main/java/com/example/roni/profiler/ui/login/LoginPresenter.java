package com.example.roni.profiler.ui.login;

import com.example.roni.profiler.dataModel.auth.AuthService;
import com.example.roni.profiler.dataModel.auth.Credentials;
import com.example.roni.profiler.dataModel.auth.User;
import com.example.roni.profiler.ui.base.BasePresenter;
import com.example.roni.profiler.utils.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;

/**
 * Created by roni on 25/01/18.
 */

public class LoginPresenter<V extends LoginContract.AppView> extends BasePresenter<V>
        implements LoginContract.Presenter<V> {

    @Inject
    public LoginPresenter(AuthService authService,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(authService, schedulerProvider, compositeDisposable);
    }


    /**
     * When this Activity first starts, check if there is a currently logged in user.
     * If so, send the user to their Profile Page. When the user signs out
     */
    private void getUser(){
        getView().showLoading();
        getCompositeDisposable().add(
                getAuthService().getUser()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribeWith(new DisposableMaybeObserver<User>() {
                    /**
                     * User was found
                     * @param user the logged in user
                     */
                    @Override
                    public void onSuccess(User user) {
                        getView().hideLoading();
                        getView().goToProfilePageActivity();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoading();
                        getView().showMessage(e.getMessage());
                    }

                    /**
                     * We didn't find a user in the Auth Database or it was logged out. That's not an error
                     */
                    @Override
                    public void onComplete() {
                        getView().hideLoading();
                       //appView.showMessage("User not found");
                    }
                })
        );
    }

    public void attemptLogIn(Credentials cred) {
        getView().showLoading();
        getCompositeDisposable().add(
                getAuthService().loginAccount(cred)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                getView().hideLoading();
                                getView().goToProfilePageActivity();
                            }

                            @Override
                            public void onError(Throwable e) {
                                getView().hideLoading();
                                getView().showMessage(e.toString());
                            }
                        })
        );
    }

    @Override
    public void subscribe() {
        getUser();
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void setUserAsLoggedOut() {

    }

    @Override
    public void onLoginClick() {
        String email = getView().getEmail();
        String password = getView().getPassword();

        if(email.isEmpty()){
           getView().showMessage("Email field cannot be empty");
        } else if (!email.contains("@")) {
            getView().showMessage("Email is invalid");
        } else if(password.isEmpty()){
            getView().showMessage("Password field cannot be empty");
        } else{
            attemptLogIn(new Credentials(email, password, ""));
        }
    }

    @Override
    public void onLoginSuccess() {
        getView().goToProfilePageActivity();
    }

    @Override
    public void onForgotPasswordClick() {
        getView().goToForgotPasswordActivity();
    }

    @Override
    public void onRegisterClick() {
        getView().goToCreateAccountActivity();
    }
}
