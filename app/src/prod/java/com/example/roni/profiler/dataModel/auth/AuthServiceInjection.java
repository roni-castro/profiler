package com.example.roni.profiler.auth;

/**
 * Created by roni on 01/02/18.
 */

public class AuthServiceInjection {

    public static AuthService getAuthService(){
        return new FirebaseAuthService();
    }
}
