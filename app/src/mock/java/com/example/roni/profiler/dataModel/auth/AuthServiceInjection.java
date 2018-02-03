package com.example.roni.profiler.dataModel.auth;

/**
 * Created by roni on 03/02/18.
 */

public class AuthServiceInjection {

    public static AuthService getAuthService(){
        return new FakeAuthService();
    }
}
