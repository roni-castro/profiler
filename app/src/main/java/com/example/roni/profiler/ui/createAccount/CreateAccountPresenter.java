package com.example.roni.profiler.ui.createAccount;

import android.util.Log;

import com.example.roni.profiler.dataModel.auth.AuthService;
import com.example.roni.profiler.dataModel.auth.Credentials;
import com.example.roni.profiler.dataModel.auth.User;
import com.example.roni.profiler.dataModel.database.DatabaseSource;
import com.example.roni.profiler.dataModel.database.Profile;
import com.example.roni.profiler.ui.base.BasePresenter;
import com.example.roni.profiler.utils.BaseSchedulerContract;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import timber.log.Timber;

/**
 * Created by roni on 25/01/18.
 */

public class CreateAccountPresenter<V extends CreateAccountContract.AppView> extends BasePresenter<V>
        implements CreateAccountContract.Presenter<V> {

    @Inject
    public CreateAccountPresenter(AuthService authService,
                                  BaseSchedulerContract schedulerProvider,
                                  CompositeDisposable compositeDisposable,
                                  DatabaseSource databaseSource
    ) {
        super(authService, schedulerProvider, compositeDisposable, databaseSource);
    }

    @Override
    public void onCreateAccount() {
        String name = getView().getName();
        String email = getView().getEmail();
        String password = getView().getPassword();
        String confirmationPassword = getView().getConfirmationPassword();

        if(name.isEmpty()){
            getView().showMessage("Name field cannot be empty");
        } else if(email.isEmpty()){
            getView().showMessage("Email field cannot be empty");
        } else if (!email.contains("@")) {
            getView().showMessage("Email is invalid");
        } else if(password.isEmpty()){
            getView().showMessage("Password field cannot be empty");
        } else if(password.length() < 6) {
            getView().showMessage("Password should have at least 6  characters");
        } else if(!confirmationPassword.equals(password)){
            getView().showMessage("Confirmation password does not match");
        } else{
            attemptToCreateAccount(new Credentials(email, password, name));
        }
    }

    private void attemptToCreateAccount(final Credentials cred) {
        getCompositeDisposable().add(
                getAuthService().createAccount(cred)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribeWith(new DisposableSingleObserver<User>() {
                            @Override
                            public void onSuccess(User user) {
                                Timber.e(user.toString());
                                user.setUsername(cred.getUsername());
                                onAccountCreatedSuccessfully(user, cred.getPassword());
                            }

                            @Override
                            public void onError(Throwable e) {
                                getView().showMessage(e.toString());
                            }
                        })
        );
    }

    @Override
    public void onAccountCreatedSuccessfully(User user, String password) {
        addUserProfileToDatabase(user.getEmail(), user.getUserUid(), user.getUsername(), password);
    }

    private void addUserProfileToDatabase(final String email, final String userUid, final String name, final String password) {
        final Profile profile = new Profile("", "", "", email, "", name, userUid);
        getCompositeDisposable().add(
                getDatabaseSource().createNewProfileToUser(profile)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribeWith(new DisposableCompletableObserver(){
                    @Override
                    public void onComplete() {
                        getView().saveUserData(userUid, email, name);
                        getView().goToProfilePageActivity();
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO RETRY OPERATION OR DELETE THE CURRENT CREATED USER
                        getView().showMessage(e.getLocalizedMessage());
                        deleteUserCreatedWithoutProfile(email, password);
                    }
                })
        );
    }

    private void deleteUserCreatedWithoutProfile(String email, String password){
        getCompositeDisposable().add(
                getDatabaseSource().deleteUser(email, password)
                .observeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribeWith(new DisposableCompletableObserver(){

                    @Override
                    public void onComplete() {
                        Log.e("FIREBASE", "user deleted because profile could not be created successfully");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("FIREBASE", "User was created without a profile");
                    }
                })
        );
    }

    @Override
    public void onSubscribe() {

    }
}
