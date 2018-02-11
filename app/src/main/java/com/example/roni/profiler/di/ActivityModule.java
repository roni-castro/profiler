package com.example.roni.profiler.di;

import android.content.Context;

import com.example.roni.profiler.dataModel.auth.AuthService;
import com.example.roni.profiler.dataModel.auth.AuthServiceInjection;
import com.example.roni.profiler.dataModel.database.DatabaseSource;
import com.example.roni.profiler.dataModel.database.DatabaseSourceInjection;
import com.example.roni.profiler.ui.base.BaseActivity;
import com.example.roni.profiler.ui.createAccount.CreateAccountContract;
import com.example.roni.profiler.ui.createAccount.CreateAccountPresenter;
import com.example.roni.profiler.ui.login.LoginContract;
import com.example.roni.profiler.ui.login.LoginPresenter;
import com.example.roni.profiler.ui.profilePage.ProfilePageContract;
import com.example.roni.profiler.ui.profilePage.ProfilePagePresenter;
import com.example.roni.profiler.ui.profilePage.edit.EditProfileContract;
import com.example.roni.profiler.ui.profilePage.edit.EditProfilePresenter;
import com.example.roni.profiler.utils.BaseSchedulerContract;
import com.example.roni.profiler.utils.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by roni on 09/02/18.
 */

@Module
public class ActivityModule {
    private BaseActivity mActivity;

    public ActivityModule(BaseActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    Context provideContext() {
        return mActivity;
    }

    @Provides
    BaseActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    AuthService provideAuthService() {
        return AuthServiceInjection.getAuthService();
    }

    @Provides
    DatabaseSource provideDatabaseSourceService() {
        return DatabaseSourceInjection.getDatabaseSource();
    }

    @Provides
    BaseSchedulerContract provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }

    @Provides
    CreateAccountContract.Presenter<CreateAccountContract.AppView> provideCreateAccountPresenter(
            CreateAccountPresenter<CreateAccountContract.AppView> presenter) {
        return presenter;
    }

    @Provides
    LoginContract.Presenter<LoginContract.AppView> provideLoginPresenter(
            LoginPresenter<LoginContract.AppView> presenter) {
        return presenter;
    }

    @Provides
    ProfilePageContract.Presenter<ProfilePageContract.AppView> provideProfilePagePresenter(
            ProfilePagePresenter<ProfilePageContract.AppView> presenter) {
        return presenter;
    }

    @Provides
    EditProfileContract.Presenter<EditProfileContract.AppView> provideEditProfilePresenter(
            EditProfilePresenter<EditProfileContract.AppView> presenter) {
        return presenter;
    }
}
