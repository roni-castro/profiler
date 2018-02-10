package com.example.roni.profiler.ui.profilePage;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.example.roni.profiler.ui.base.BaseActivity;
import com.example.roni.profiler.R;

/**
 * Created by roni on 25/01/18.
 */

public class ProfilePageActivity extends BaseActivity {
    private static final String PROFILE_PAGE_FRAGMENT = "PROFILE_PAGE_FRAGMENT";

    @Override
    public int getContentView() {
        return R.layout.activity_profile_page;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getSupportFragmentManager();
        ProfilePageFragment fragment = (ProfilePageFragment) fragmentManager.findFragmentByTag(PROFILE_PAGE_FRAGMENT);
        if(fragment == null) {
            fragment = ProfilePageFragment.newInstance();
        }
        setFragment(R.id.fragment_holder, fragment, PROFILE_PAGE_FRAGMENT);
    }
}
