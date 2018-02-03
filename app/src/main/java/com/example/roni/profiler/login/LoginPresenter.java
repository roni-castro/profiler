package com.example.roni.profiler.login;

import com.example.roni.profiler.dataModel.auth.AuthService;
import com.example.roni.profiler.utils.BaseSchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

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
        this.view = view;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void onLoginClick() {
        onLoginSuccess();
    }

    @Override
    public void onLoginSuccess() {
        view.goToProfilePageActivity();
    }

    @Override
    public void onForgotPasswordClick() {

    }

    @Override
    public void onRegisterClick() {

    }
}
