package com.example.roni.profiler.ui.createAccount;

import com.example.roni.profiler.dataModel.auth.AuthService;
import com.example.roni.profiler.dataModel.auth.Credentials;
import com.example.roni.profiler.ui.base.BasePresenter;
import com.example.roni.profiler.utils.BaseSchedulerContract;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;

/**
 * Created by roni on 25/01/18.
 */

public class CreateAccountPresenter<V extends CreateAccountContract.AppView> extends BasePresenter<V>
        implements CreateAccountContract.Presenter<V> {

    @Inject
    public CreateAccountPresenter(AuthService authService,
                                  BaseSchedulerContract schedulerProvider,
                                  CompositeDisposable compositeDisposable) {
        super(authService, schedulerProvider, compositeDisposable);
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

    private void attemptToCreateAccount(Credentials cred) {
        getCompositeDisposable().add(
                getAuthService().createAccount(cred)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                onAccountCreatedSuccessfully();
                            }

                            @Override
                            public void onError(Throwable e) {
                                getView().showMessage(e.toString());
                            }
                        })
        );
    }

    @Override
    public void onAccountCreatedSuccessfully() {
        getView().goToProfilePageActivity();
    }
}
