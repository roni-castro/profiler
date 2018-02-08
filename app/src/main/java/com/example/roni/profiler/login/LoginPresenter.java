package com.example.roni.profiler.login;

import com.example.roni.profiler.dataModel.auth.AuthService;
import com.example.roni.profiler.dataModel.auth.Credentials;
import com.example.roni.profiler.dataModel.auth.User;
import com.example.roni.profiler.utils.BaseSchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;

/**
 * Created by roni on 25/01/18.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private CompositeDisposable compositeDisposable;
    private AuthService authService;
    private LoginContract.AppView appView;
    private BaseSchedulerProvider schedulerProvider;

    public LoginPresenter(AuthService authService, LoginContract.AppView appView, BaseSchedulerProvider baseSchedulerProvider){
        this.authService = authService;
        this.schedulerProvider = baseSchedulerProvider;
        this.compositeDisposable = new CompositeDisposable();
        this.appView = appView;
        appView.setPresenter(this);
    }

    /**
     * When this Activity first starts, check if there is a currently logged in user.
     * If so, send the user to their Profile Page. When the user signs out
     */
    private void getUser(){
        compositeDisposable.add(
                authService.getUser()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribeWith(new DisposableMaybeObserver<User>() {
                    /**
                     * User was found
                     * @param user the logged in user
                     */
                    @Override
                    public void onSuccess(User user) {
                        appView.goToProfilePageActivity();
                    }

                    @Override
                    public void onError(Throwable e) {
                        appView.showMessage(e.getMessage());
                    }

                    /**
                     * We didn't find a user in the Auth Database or it was logged out. That's not an error
                     */
                    @Override
                    public void onComplete() {
                       //appView.showMessage("User not found");
                    }
                })
        );
    }

    public void attemptLogIn(Credentials cred) {
        compositeDisposable.add(
                authService.loginAccount(cred)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                appView.goToProfilePageActivity();
                            }

                            @Override
                            public void onError(Throwable e) {
                                appView.showMessage(e.toString());
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
        compositeDisposable.clear();
        authService.removeAuthListener();
    }

    @Override
    public void onLoginClick() {
        String email = appView.getEmail();
        String password = appView.getPassword();

        if(email.isEmpty()){
           appView.showMessage("Email field cannot be empty");
        } else if (!email.contains("@")) {
            appView.showMessage("Email is invalid");
        } else if(password.isEmpty()){
            appView.showMessage("Password field cannot be empty");
        } else{
            attemptLogIn(new Credentials(email, password, ""));
        }
    }

    @Override
    public void onLoginSuccess() {
        appView.goToProfilePageActivity();
    }

    @Override
    public void onForgotPasswordClick() {
        appView.goToForgotPasswordActivity();
    }

    @Override
    public void onRegisterClick() {
        appView.goToCreateAccountActivity();
    }
}
