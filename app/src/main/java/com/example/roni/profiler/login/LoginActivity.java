package com.example.roni.profiler.login;

import android.os.Bundle;

import com.example.roni.profiler.BaseActivity;
import com.example.roni.profiler.R;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.welcome_msg);
        }
    }
}
