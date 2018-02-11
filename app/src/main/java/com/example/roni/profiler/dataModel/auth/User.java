package com.example.roni.profiler.dataModel.auth;

/**
 * Created by roni on 03/02/18.
 */

public class User {
    private String userUid;
    private String email;
    private String username;

    public User(String userUid, String email, String username) {
        this.userUid = userUid;
        this.email = email;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }
}
