package com.example.roni.profiler.dataModel.database;

/**
 * Created by roni on 10/02/18.
 */

public class DatabaseSourceInjection {
    public static DatabaseSource getDatabaseSource(){
        return FirebaseDatabaseSource.getInstance();
    }
}
