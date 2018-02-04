package com.example.roni.profiler.login;

import com.example.roni.profiler.dataModel.auth.AuthService;
import com.example.roni.profiler.dataModel.auth.Credentials;
import com.example.roni.profiler.dataModel.auth.User;
import com.example.roni.profiler.utils.BaseSchedulerProvider;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;

/**
 * Created by roni on 25/01/18.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private CompositeDisposable compositeDisposable;
    private AuthService authService;
    private LoginContract.View view;
    private BaseSchedulerProvider schedulerProvider;

    public LoginPresenter(AuthService authService, LoginContract.View view, BaseSchedulerProvider baseSchedulerProvider){
        this.authService = authService;
        this.schedulerProvider = baseSchedulerProvider;
        this.compositeDisposable = new CompositeDisposable();
        this.view = view;
        view.setPresenter(this);
    }

    /**
     * When this Activity first starts, check if there is a currently logged in user.
     * If so, send the user to their Profile Page.
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
                        view.goToProfilePageActivity();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showToast(e.getMessage());
                    }

                    /**
                     * We didn't find a user in the Auth Database. That's not an error
                     */
                    @Override
                    public void onComplete() {
                       view.showToast("User not found");
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
                                view.goToProfilePageActivity();
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.showToast(e.toString());
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
    }

    @Override
    public void onLoginClick() {
        String email = view.getEmail();
        String password = view.getPassword();

        if(email.isEmpty()){
           view.showToast("Email field cannot be empty");
        } else if (!email.contains("@")) {
            view.showToast("Email is invalid");
        } else if(password.isEmpty()){
            view.showToast("Password field cannot be empty");
        } else{
            //attemptLogin(email, password);
            attemptLogIn(new Credentials(email, password, ""));
        }
    }

    @Override
    public void onLoginSuccess() {
        view.goToProfilePageActivity();
    }

    @Override
    public void onForgotPasswordClick() {
        view.goToForgotPasswordActivity();
    }

    @Override
    public void onRegisterClick() {
        view.goToCreateAccountActivity();
    }
}
