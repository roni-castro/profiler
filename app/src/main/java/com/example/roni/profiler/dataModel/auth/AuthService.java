package com.example.roni.profiler.dataModel.auth;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by roni on 01/02/18.
 */

public interface AuthService {
    Single<User> createAccount(Credentials credentials);
    Single<User> loginAccount(Credentials credentials);
    Completable reAuthenticateUserAccount(String credentials);
    Completable logUserOut();
    Completable deleteUserAccount();
    Maybe<User> getUser();
    void removeAuthListener();
}
