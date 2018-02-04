package com.example.roni.profiler.dataModel.auth;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * Created by roni on 01/02/18.
 */

public class FakeAuthService implements AuthService {
    private static final String VALID_EMAIL = "email@example.com";
    private static final String VALID_PASSWORD = "1234567";
    private boolean returnFail = false;

    @Override
    public Completable createAccount(Credentials credentials) {
        if(returnFail){
            Completable.error(new Exception());
        }
        return Completable.complete();
    }

    @Override
    public Completable loginAccount(Credentials credentials) {
        if(returnFail){
            Completable.error(new Exception());
        }
        return Completable.complete();
    }

    @Override
    public Completable reAuthenticateUserAccount(String credentials) {
        if(returnFail){
            Completable.error(new Exception());
        }
        return Completable.complete();
    }

    @Override
    public Completable logUserOut() {
        if(returnFail){
            Completable.error(new Exception());
        }
        return Completable.complete();
    }

    @Override
    public Completable deleteUserAccount() {
        if(returnFail){
            Completable.error(new Exception());
        }
        return Completable.complete();
    }

    @Override
    public Maybe<User> getUser() {
        if(returnFail){
            Maybe.error(new Exception());
        }
        return Maybe.just(new User(VALID_EMAIL, VALID_PASSWORD));
    }

    @Override
    public void setReturnFail(boolean returnFail) {
        this.returnFail = returnFail;
    }
}
