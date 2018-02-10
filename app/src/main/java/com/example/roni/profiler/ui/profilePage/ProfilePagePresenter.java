package com.example.roni.profiler.ui.profilePage;

import com.example.roni.profiler.dataModel.auth.AuthService;
import com.example.roni.profiler.ui.base.BasePresenter;
import com.example.roni.profiler.utils.BaseSchedulerContract;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;


/**
 * Created by roni on 25/01/18.
 */

public class ProfilePagePresenter<V extends ProfilePageContract.AppView> extends BasePresenter<V>
        implements ProfilePageContract.Presenter<V> {

    @Inject
    public ProfilePagePresenter(AuthService authService,
                                BaseSchedulerContract schedulerProvider,
                                CompositeDisposable compositeDisposable) {
        super(authService, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onResume() {

    }

    @Override
    public void onThumbnailClick() {
        getView().openPhotoGallery();
    }

    @Override
    public void onEditProfileClick() {
        getView().goToEditProfileActivity();
    }

    @Override
    public void onLogoutClick() {
        getView().showLogoutDialog();
    }

    @Override
    public void onLogoutConfirmed() {
        getView().showLoading();
        getCompositeDisposable().add(
                getAuthService().logUserOut()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                getView().hideLoading();
                                getView().goToLoginActivity();
                            }

                            @Override
                            public void onError(Throwable e) {
                                getView().hideLoading();
                                getView().showMessage(e.getLocalizedMessage());
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
