package com.example.roni.profiler.login;

import android.os.Bundle;

import com.example.roni.profiler.BaseActivity;
import com.example.roni.profiler.R;

public class LoginActivity extends BaseActivity {
    private static final String LOGIN_FRAGMENT = "LOGIN_FRAGMENT";

    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag(LOGIN_FRAGMENT);
        if(loginFragment == null){
            loginFragment = LoginFragment.newInstance();
        }

        setUpToolbar();
        setFragment(R.id.fragment_holder, loginFragment, LOGIN_FRAGMENT);
    }

    private void setUpToolbar(){
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.welcome_msg);
        }
    }
}
