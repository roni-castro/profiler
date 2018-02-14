package com.example.roni.profiler.ui.profilePage;

import com.example.roni.profiler.dataModel.auth.AuthService;
import com.example.roni.profiler.dataModel.auth.User;
import com.example.roni.profiler.dataModel.database.DatabaseSource;
import com.example.roni.profiler.dataModel.database.Profile;
import com.example.roni.profiler.ui.base.BasePresenter;
import com.example.roni.profiler.utils.BaseSchedulerContract;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;


/**
 * Created by roni on 25/01/18.
 */

public class ProfilePagePresenter<V extends ProfilePageContract.AppView> extends BasePresenter<V>
        implements ProfilePageContract.Presenter<V> {

    @Inject
    public ProfilePagePresenter(AuthService authService,
                                BaseSchedulerContract schedulerProvider,
                                CompositeDisposable compositeDisposable,
                                DatabaseSource databaseSource) {
        super(authService, schedulerProvider, compositeDisposable, databaseSource);
    }

    @Override
    public void loadUserProfileData() {
        User user = getView().loadUserDataFromCache();
        getView().showLoading();
        getCompositeDisposable().add(
            getDatabaseSource().getProfile(user.getUserUid())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribeWith(new DisposableObserver<Profile>() {
                    @Override
                    public void onComplete() {
                        getView().hideLoading();
                        getView().goToLoginActivity();
                    }

                    @Override
                    public void onNext(Profile profile) {
                        getView().hideLoading();
                        getView().setUpProfileFields(profile);
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

    @Override
    public void onSubscribe() {

    }
}
