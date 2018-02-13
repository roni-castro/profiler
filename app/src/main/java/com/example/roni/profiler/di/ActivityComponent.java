package com.example.roni.profiler.di;

import com.example.roni.profiler.ui.createAccount.CreateAccountFragment;
import com.example.roni.profiler.ui.login.LoginFragment;
import com.example.roni.profiler.ui.profilePage.ProfilePageFragment;
import com.example.roni.profiler.ui.profilePage.edit.EditProfileFragment;

import dagger.Component;

/**
 * Created by roni on 09/02/18.
 */

@Component(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(LoginFragment fragment);
    void inject(CreateAccountFragment fragment);
    void inject(ProfilePageFragment fragment);
    void inject(EditProfileFragment fragment);
}
