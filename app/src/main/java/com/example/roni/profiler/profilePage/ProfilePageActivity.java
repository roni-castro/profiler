package com.example.roni.profiler.profilePage;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.example.roni.profiler.BaseActivity;
import com.example.roni.profiler.R;

/**
 * Created by roni on 25/01/18.
 */

public class ProfilePageActivity extends BaseActivity {
    private static final String PROFILE_PAGE_FRAGMENT = "PROFILE_PAGE_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        FragmentManager fragmentManager = getSupportFragmentManager();
        ProfilePageFragment fragment = (ProfilePageFragment) fragmentManager.findFragmentByTag(PROFILE_PAGE_FRAGMENT);
        if(fragment == null) {
            fragment = ProfilePageFragment.newInstance();
        }
        setFragment(R.id.fragment_holder, fragment, PROFILE_PAGE_FRAGMENT);
    }
}
