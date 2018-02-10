package com.example.roni.profiler.ui.profilePage.edit;

import com.example.roni.profiler.dataModel.auth.AuthService;
import com.example.roni.profiler.dataModel.database.Profile;
import com.example.roni.profiler.ui.base.BasePresenter;
import com.example.roni.profiler.utils.BaseSchedulerContract;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by roni on 10/02/18.
 */

public class EditProfilePresenter<V extends EditProfileContract.AppView> extends BasePresenter<V>
    implements EditProfileContract.Presenter<V>{

    public EditProfilePresenter(AuthService authService,
                                BaseSchedulerContract schedulerProvider,
                                CompositeDisposable compositeDisposable) {
        super(authService, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onConfirmUpdateMenuClick() {

    }
}
