package com.example.roni.profiler.createAccount;

import com.example.roni.profiler.dataModel.auth.AuthService;
import com.example.roni.profiler.dataModel.auth.Credentials;
import com.example.roni.profiler.login.LoginContract;
import com.example.roni.profiler.utils.BaseSchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;

/**
 * Created by roni on 25/01/18.
 */

public class CreateAccountPresenter implements CreateAccountContract.Presenter {
    private CompositeDisposable compositeDisposable;
    private AuthService authService;
    private CreateAccountContract.AppView appView;
    private BaseSchedulerProvider schedulerProvider;

    public CreateAccountPresenter(AuthService authService, CreateAccountContract.AppView appView, BaseSchedulerProvider baseSchedulerProvider){
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
        authService.removeAuthListener();
    }

    @Override
    public void onCreateAccount() {
        String name = appView.getName();
        String email = appView.getEmail();
        String password = appView.getPassword();
        String confirmationPassword = appView.getConfirmationPassword();

        if(name.isEmpty()){
            appView.showMessage("Name field cannot be empty");
        } else if(email.isEmpty()){
            appView.showMessage("Email field cannot be empty");
        } else if (!email.contains("@")) {
            appView.showMessage("Email is invalid");
        } else if(password.isEmpty()){
            appView.showMessage("Password field cannot be empty");
        } else if(password.length() < 6) {
            appView.showMessage("Password should have at least 6  characters");
        } else if(!confirmationPassword.equals(password)){
            appView.showMessage("Confirmation password does not match");
        } else{
            attemptToCreateAccount(new Credentials(email, password, name));
        }
    }

    private void attemptToCreateAccount(Credentials cred) {
        compositeDisposable.add(
                authService.createAccount(cred)
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
    public void onAccountCreatedSuccessfully() {

    }
}
