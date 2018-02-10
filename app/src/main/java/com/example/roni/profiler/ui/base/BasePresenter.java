package com.example.roni.profiler.ui.base;

import com.example.roni.profiler.dataModel.auth.AuthService;
import com.example.roni.profiler.utils.BaseSchedulerContract;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

/**
 * Created by roni on 09/02/18.
 */

public abstract class BasePresenter<V extends BaseView> implements BasePresenterContract<V> {
    private final CompositeDisposable compositeDisposable;
    private final AuthService authService;
    private final BaseSchedulerContract schedulerProvider;
    private V appView;

    public BasePresenter(AuthService authService,
                         BaseSchedulerContract schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        this.authService = authService;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V appView) {
        this.appView = appView;
    }

    @Override
    public void onDetach() {
        compositeDisposable.clear();
        appView = null;
    }

    @Override
    public void setUserAsLoggedOut() {

    }

    public boolean isViewAttached() {
        return appView != null;
    }

    public V getView(){
        if(appView == null) {
          Timber.d("It's necessary to attach the view to the Presenter");
        }
        return appView;
    }

    public BaseSchedulerContract getSchedulerProvider() {
        return schedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public AuthService getAuthService() {
        return authService;
    }
}
