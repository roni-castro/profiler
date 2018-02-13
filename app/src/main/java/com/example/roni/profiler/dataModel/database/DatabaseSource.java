package com.example.roni.profiler.dataModel.database;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by roni on 10/02/18.
 */

public interface DatabaseSource {
    Completable createNewProfileToUser(Profile profile);

    Completable updateProfile(Profile profile);

    Completable deleteUser(String email, String password);

    Observable<Profile> getProfile(String uid);

}
