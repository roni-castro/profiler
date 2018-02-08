package com.example.roni.profiler.profilePage;

import com.example.roni.profiler.dataModel.auth.AuthService;
import com.example.roni.profiler.utils.BaseSchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;

/**
 * Created by roni on 25/01/18.
 */

public class ProfilePagePresenter implements ProfilePageContract.Presenter {
    private CompositeDisposable compositeDisposable;
    private AuthService authService;
    private ProfilePageContract.AppView appView;
    private BaseSchedulerProvider schedulerProvider;

    public ProfilePagePresenter(AuthService authService,
                                ProfilePageContract.AppView appView,
                                BaseSchedulerProvider baseSchedulerProvider){
        this.authService = authService;
        this.schedulerProvider = baseSchedulerProvider;
        this.compositeDisposable = new CompositeDisposable();
        this.appView = appView;
        appView.setPresenter(this);

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
        appView.showLogoutDialog();
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
                                appView.goToLoginActivity();
                            }

                            @Override
                            public void onError(Throwable e) {
                                appView.showMessage(e.getMessage());
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
