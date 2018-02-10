package com.example.roni.profiler.dataModel.database;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * Created by roni on 10/02/18.
 */

public interface DatabaseSource {
    Completable createNewProfile(Profile profile);

    Completable updateProfile(Profile profile);

    Completable deleteProfile(String uid);

    Maybe<Profile> getProfile(String uid);

}
