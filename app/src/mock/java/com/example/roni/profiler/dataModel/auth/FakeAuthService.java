package com.example.roni.profiler.dataModel.auth;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * Created by roni on 01/02/18.
 */

public class FakeAuthService implements AuthService {
    @Override
    public Completable createAccount(Credentials credentials) {
        return null;
    }

    @Override
    public Completable loginAccount(Credentials credentials) {
        return null;
    }

    @Override
    public Completable reAuthenticateUserAccount(String credentials) {
        return null;
    }

    @Override
    public Completable logUserOut() {
        return null;
    }

    @Override
    public Completable deleteUserAccount() {
        return null;
    }

    @Override
    public Maybe<User> getUser() {
        return null;
    }

    @Override
    public void setReturnFail(boolean requestFail) {

    }
}
