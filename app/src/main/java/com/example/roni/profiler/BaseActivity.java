package com.example.roni.profiler;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by roni on 02/02/18.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected void setFragment(int containerResId, Fragment fragment, String fragmentTag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerResId, fragment, fragmentTag);
        fragmentTransaction.commit();
    }

}
