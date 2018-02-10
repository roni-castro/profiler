package com.example.roni.profiler.dataModel.auth;

import com.example.roni.profiler.dataModel.database.Profile;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * Created by roni on 01/02/18.
 */

public interface AuthService {
    Completable createAccount(Credentials credentials);
    Completable loginAccount(Credentials credentials);
    Completable reAuthenticateUserAccount(String credentials);
    Completable logUserOut();
    Completable deleteUserAccount();
    Maybe<User> getUser();
    void removeAuthListener();

    Completable updateProfileInDB(Profile any);
}
