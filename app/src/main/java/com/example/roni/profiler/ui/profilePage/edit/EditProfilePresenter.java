package com.example.roni.profiler.ui.profilePage.edit;

import com.example.roni.profiler.dataModel.auth.AuthService;
import com.example.roni.profiler.dataModel.auth.User;
import com.example.roni.profiler.dataModel.database.DatabaseSource;
import com.example.roni.profiler.dataModel.database.Profile;
import com.example.roni.profiler.ui.base.BasePresenter;
import com.example.roni.profiler.utils.BaseSchedulerContract;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;

/**
 * Created by roni on 10/02/18.
 */

public class EditProfilePresenter<V extends EditProfileContract.AppView> extends BasePresenter<V>
    implements EditProfileContract.Presenter<V>{
    private Profile currentProfile;

    public EditProfilePresenter(AuthService authService,
                                BaseSchedulerContract schedulerProvider,
                                CompositeDisposable compositeDisposable,
                                DatabaseSource databaseSource) {
        super(authService, schedulerProvider, compositeDisposable, databaseSource);
    }

    @Override
    public void onAttach(V appView) {
        super.onAttach(appView);
        getActiveUser();
    }


    @Override
    public void onConfirmUpdateMenuClick() {
        String bio = getView().getBio();
        String interests = getView().getInterests();

        if(currentProfile != null){
            currentProfile.setBio(bio);
            currentProfile.setInterests(interests);
            updateProfileInDB();
        }
    }

    private void updateProfileInDB(){
        getView().showLoading();
        getCompositeDisposable().add(
                getDatabaseSource().updateProfile(currentProfile)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        getView().hideLoading();
                        getView().updateProfileData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoading();
                        getView().showMessage(e.getLocalizedMessage());
                    }
                })
        );
    }

    private void getActiveUser(){
        getView().showLoading();
        getCompositeDisposable().add(getAuthService().getUser()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribeWith(new DisposableMaybeObserver<User>() {
                    @Override
                    public void onSuccess(User user) {
                        getView().hideLoading();
                        getUserProfile(user.getUserUid());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoading();
                        getView().showMessage(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoading();
                        getView().goBackToProfilePageWithMessage();
                    }
                })
        );
    }

    public void getUserProfile(String userUID) {
        getView().showLoading();
        getCompositeDisposable().add(
                getDatabaseSource().getProfile(userUID)
                .observeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribeWith(new DisposableMaybeObserver<Profile>(){

                    @Override
                    public void onSuccess(Profile profile) {
                        getView().hideLoading();
                        currentProfile = profile;
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoading();
                        getView().showMessage(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoading();
                        getView().goBackToProfilePageWithMessage();
                    }
                })
        );
    }
}
