package com.example.roni.profiler.ui.profilePage.edit;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.roni.profiler.R;
import com.example.roni.profiler.ui.base.BaseActivity;
import com.example.roni.profiler.ui.profilePage.ProfilePageActivity;

import butterknife.BindView;

public class EditProfileActivity extends BaseActivity {
    private static final String EDIT_PROFILE_PAGE_FRAGMENT = "EDIT_PROFILE_PAGE_FRAGMENT";

    @Override
    public int getContentView() {
        return R.layout.activity_edit_profile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getSupportFragmentManager();
        EditProfileFragment fragment = (EditProfileFragment) fragmentManager.findFragmentByTag(EDIT_PROFILE_PAGE_FRAGMENT);
        if(fragment == null) {
            fragment = EditProfileFragment.newInstance();
        }
        setFragment(R.id.fragment_holder, fragment, EDIT_PROFILE_PAGE_FRAGMENT);
    }
}
