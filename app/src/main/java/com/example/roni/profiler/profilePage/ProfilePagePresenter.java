package com.example.roni.profiler.profilePage;

import android.content.Intent;

import com.example.roni.profiler.dataModel.auth.AuthService;
import com.example.roni.profiler.login.LoginActivity;
import com.example.roni.profiler.login.LoginContract;
import com.example.roni.profiler.utils.BaseSchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;

/**
 * Created by roni on 25/01/18.
 */

public class ProfilePagePresenter implements ProfilePageContract.Presenter {
    private CompositeDisposable compositeDisposable;
    private AuthService authService;
    private ProfilePageContract.View view;
    private BaseSchedulerProvider schedulerProvider;

    public ProfilePagePresenter(AuthService authService,
                                ProfilePageContract.View view,
                                BaseSchedulerProvider baseSchedulerProvider){
        this.authService = authService;
        this.schedulerProvider = baseSchedulerProvider;
        this.compositeDisposable = new CompositeDisposable();
        this.view = view;
        view.setPresenter(this);

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        compositeDisposable.clear();
    }

    @Override
    public void onThumbnailClick() {

    }

    @Override
    public void onEditProfileClick() {

    }

    @Override
    public void onLogoutClick() {
        view.showLogoutDialog();
    }

    @Override
    public void onLogoutConfirmed() {
        compositeDisposable.add(
                authService.logUserOut()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                view.goToLoginActivity();
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.showToast(e.getMessage());
                            }
                        })
        );
    }

    @Override
    public void onAccountSettingsClick() {

    }

    @Override
    public void onThumbnailLoaded() {

    }
}
