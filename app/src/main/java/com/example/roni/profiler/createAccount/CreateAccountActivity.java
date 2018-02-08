package com.example.roni.profiler.createAccount;

import android.os.Bundle;

import com.example.roni.profiler.BaseActivity;
import com.example.roni.profiler.R;

public class CreateAccountActivity extends BaseActivity {
    private static final String CREATE_ACCOUNT_FRAGMENT = "CREATE_ACCOUNT_FRAGMENT";

    @Override
    public int getContentView() {
        return R.layout.activity_create_account;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CreateAccountFragment createAccountFragment = (CreateAccountFragment)
                getSupportFragmentManager().findFragmentByTag(CREATE_ACCOUNT_FRAGMENT);
        if(createAccountFragment == null){
            createAccountFragment = CreateAccountFragment.newInstance();
        }
        setFragment(R.id.fragment_holder, createAccountFragment,CREATE_ACCOUNT_FRAGMENT );
        setUpToolbar();
    }

    private void setUpToolbar(){
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.create_account);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
