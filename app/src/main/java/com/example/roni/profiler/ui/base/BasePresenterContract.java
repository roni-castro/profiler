package com.example.roni.profiler.ui.base;

/**
 * Created by roni on 01/02/18.
 */

public interface BasePresenterContract<V extends BaseView> {

    void onAttach(V appView);

    void onSubscribe();

    void onDetach();

    //TODO HANDLE API ERROR
    //void handleApiError(ANError error);

    void setUserAsLoggedOut();
}
